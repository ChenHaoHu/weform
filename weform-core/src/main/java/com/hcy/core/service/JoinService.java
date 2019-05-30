package com.hcy.core.service;


import com.hcy.core.model.JoinDO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-3 23:08
 * @Description: 报名操作接口
 */

@Service
public interface JoinService {

    Integer joinForm(String formid,String userid,String content);

    List<JoinDO> getJoinData(String formid, String userid);

    String deleteJoinData(String id);

    String exportJoinData(Integer formid);

    List getUserDataByFormid(Integer formid);

    String getJoinPassword(String id);

    List getJoinlistByUserid(Integer userid);

}
