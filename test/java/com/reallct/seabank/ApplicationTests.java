package com.reallct.seabank;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests extends BaseTest {

    @Value("${tx.test}")
    private boolean test;

    //get方法测试
    @Test
    public void queryUser1() throws Exception {
        this.mockMvc.perform(get("/seabank/v1/user1/16")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .header("Authorization", "Bearer " + tokenBinded)
        )
                .andExpect(status().isOk())//判断返回200
                .andDo(print()); // 打印测试过程
    }

    //POST方法测试
    @Test
    public void queryUser2() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("num", 0);
        params.put("pageSize", 20);
        params.put("phone", "12312341234");
        String jsonStr = getRequest("queryUser2", params);// 获取上送报文
        this.mockMvc.perform(post("/seabank/v1/user2")
                .session(session) // 设置模拟session
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .content(jsonStr.getBytes()))        // 设置报文参数
                .andExpect(status().isOk())//判断返回200
                .andExpect(jsonPath("$._isException_").value("false"))//判断接口返回无异常
                .andDo(print()); // 打印测试过程
    }

    //get方法测试
    @Test
    public void queryTradeList() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("num", 0);
        params.put("pageSize", 20);
        params.put("userId", "8888");
//        params.put("beginDate", "20230401");
//        params.put("endDate", "20230801");

        String jsonStr = getRequest("queryTradeList", params);// 获取上送报文
        this.mockMvc.perform(get("/seabank/v1/trades/queryTradeList")
                .session(session) // 设置模拟session
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 设置报文头
                .content(jsonStr.getBytes()))        // 设置报文参数
                .andExpect(status().isOk())//判断返回200
                .andExpect(jsonPath("$._isException_").value("false"))//判断接口返回无异常
                .andDo(print()); // 打印测试过程
    }
}
