package com.hcy.core.service.impl;

import com.hcy.core.service.MathUtil;
import org.springframework.stereotype.Service;

import java.util.Formatter;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-1 12:38
 * @Description: 一些数学运算
 */

@Service
public class MathUtilImpl implements MathUtil {
    @Override
    public String getFormPassWord(int code) {

        int a = code / 1000;
        int b = (code - (a * 1000)) / 100;
        int d = code % 10;
        int c = ((code % 100) - d) / 10;
        System.out.println(a+""+b+""+c+""+d);
        Formatter formatter = new Formatter();
        formatter.format("%c", a + 'A');
        formatter.format("%c", d + 'C');
        formatter.format("%c", b + 'H');
        String str =  formatter.format("%c", c + 'I').toString();
        return str;
    }
}
