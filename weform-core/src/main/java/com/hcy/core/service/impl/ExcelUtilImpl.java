package com.hcy.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hcy.core.mapper.FormMapper;
import com.hcy.core.mapper.JoinMapper;
import com.hcy.core.model.JoinDO;
import com.hcy.core.service.CommonUtil;
import com.hcy.core.service.ExcelUtil;
import com.hcy.core.service.TimeUtil;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-4 10:55
 * @Description: Excel操作工具
 */

@Service
public class ExcelUtilImpl implements ExcelUtil {




    @Autowired
    FormMapper formMapper;

    @Autowired
    JoinMapper joinMapper;

    @Autowired
    TimeUtil timeUtil;

    @Autowired
    CommonUtil commonUtil;


    XSSFWorkbook workBook;
    XSSFSheet sheet;
    XSSFCellStyle cellStyle;

    @Override
    public void init() {
        // 声明一个工作薄
        workBook = null;
        workBook = new XSSFWorkbook();
        // 生成一个表格
        sheet = workBook.createSheet();
        workBook.setSheetName(0,"项目结果");
        cellStyle = workBook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);// 居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);;//垂直
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框
    }

    @Override
    public String export(String formId) {

        FileOutputStream outStream = null;
        String back = "";
        try
        {
            init();
            String formName = formMapper.getTitleByFormid(formId);
            String fileName = formName+timeUtil.getNowTime().substring(0,10)+".xlsx";

            List<JoinDO> data = joinMapper.getJoinDataByFormid(Integer.valueOf(formId));



            //分析提取数据
            List<JSONObject> content = new ArrayList<>();

            for (int i = 0; i < data.size() ; i++) {
                content.add(JSONObject.parseObject(data.get(i).getContent()));
            }


            if (content.size()==0){
                return "";
            }



            //获取标题
            Set<String> keys =  content.get(0).keySet();
            Object[] array = keys.toArray();

            //设置标题
            XSSFRow row = sheet.createRow(1);
            for (int i = 0; i < array.length; i++) {
                String title = array[i].toString();
                Cell cell = row.createCell(i);
                cell.setCellValue(title);
                cell.setCellStyle(cellStyle);
                sheet.setColumnWidth(i, (title.length()+2) * 512);
            }

            //插入需导出的数据
            for(int i = 0; i < content.size(); i++){
                row = sheet.createRow(i+2);
                JSONObject  rowdata =  content.get(i);

                for (int j = 0; j < array.length; j++) {
                    String title = array[i].toString();
                    String str = rowdata.getString(array[j].toString());
                    System.out.println(array[j]);
                    Cell cell = row.createCell(j);
                    cell.setCellValue(str);
                    cell.setCellStyle(cellStyle);
                    if(title.length() < str.length()){
                        sheet.setColumnWidth(j, (str.length()+2) * 512);
                    }
                }

            }


            //随机吧
            File  file = new File(fileName);

            //文件输出流
            outStream = new FileOutputStream(file);
            workBook.write(outStream);
            outStream.flush();

            back = commonUtil.uploadFile(file.getName(), new FileInputStream(file));



        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            if (outStream != null){
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return back;
    }

}
