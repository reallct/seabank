package com.reallct.seabank;

import com.reallct.seabank.entity.AssetEntity;
import com.reallct.seabank.entity.FamilyEntity;
import com.reallct.seabank.entity.TradeEntity;
import com.reallct.seabank.entity.UserEntity;
import com.reallct.seabank.mapper.UserMapper;
import com.reallct.seabank.servise.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/*
 *
 * mapper及service测试
 *
 * */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommonService commonService;
    @Autowired
    private UserService userService;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private AssetService assetService;
    @Autowired
    private FamilyService familyService;


    @org.junit.Test
    public void test() throws Exception {
        System.out.println('1');
    }

    // mybatis-注解方式
    @org.junit.Test
    public void queryByUserId1() throws Exception {

        UserEntity user = new UserEntity();
        user.setUserId(1000);
        System.out.println(userMapper.queryByUserId1(user.getUserId()));

    }


    // mybatis-XML方式
    @org.junit.Test
    public void queryUserById() throws Exception {

        UserEntity user = new UserEntity();
        user.setUserId(1000);
        System.out.println(userMapper.queryByUserId(user.getUserId()));

    }


    @org.junit.Test
    public void queryUserNumByPhone() throws Exception {

        Map<String, Object> param = new HashMap<>();
        param.put("num", 10);
        param.put("pageSize", 20);
        param.put("phone", "12312341234");
        System.out.println(userMapper.queryUserNumByPhone(param));

    }

    @org.junit.Test
    public void queryListByPhone() throws Exception {

        Map<String, Object> param = new HashMap<>();
        //从第N+1条开始，展示20条
        param.put("num", 0);
        param.put("pageSize", 20);
        param.put("phone", "12312341234");
        System.out.println(userMapper.queryListByPhone(param));

    }

    @org.junit.Test
    public void getUserByUserId() throws Exception {

        System.out.println(userService.getUserByUserId(123));

    }

    @org.junit.Test
    public void getUserListByphone() throws Exception {

        Map<String, Object> param = new HashMap<>();
        //从第N+1条开始，展示20条
        param.put("num", 0);
        param.put("pageSize", 20);
        param.put("phone", "12312341234");
        System.out.println(userService.getUserListByphone(param));

    }

    // 以下为trade测试

    @org.junit.Test
    public void a1() throws Exception {


        TradeEntity entity = new TradeEntity();
        entity.setUserId(1231);
        entity.setType1("33");
        entity.setType2("22");
        entity.setDetail("11");
        entity.setTradeAmt(new BigDecimal("1110.12"));
        entity.setTradeChannel("123123");
        entity.setNote("note123123");
        tradeService.insertTradeRecord(entity);
        System.out.println(entity.getTradeId());

    }


    @org.junit.Test
    public void a2() throws Exception {


        TradeEntity entity = new TradeEntity();
        entity.setTradeId(1007);
        entity.setUserId(123123);
        entity.setType1("33");
        entity.setType2("22");
        entity.setDetail("11");
        entity.setTradeAmt(new BigDecimal("1110.12"));
        entity.setTradeChannel("123123");
        entity.setNote("note123123");
        tradeService.updateTradeRecord(entity);
        System.out.println(entity.getTradeId());

    }


    @org.junit.Test
    public void a3() throws Exception {


        tradeService.deleteTradeRecord(1007);

    }

    @org.junit.Test
    public void a4() throws Exception {


        System.out.println(tradeService.queryTradeRecord(1006));

    }

    // 以下为asset测试

    @org.junit.Test
    public void b1() throws Exception {


        AssetEntity entity = new AssetEntity();
        entity.setUserId(8888);
        entity.setType1("33");
        entity.setType2("22");
        entity.setDetail("11");
        entity.setAssetAmt(new BigDecimal("1110.12"));
        entity.setNote("note123123");
        assetService.insertAssetStatus(entity);
        System.out.println(entity.getAssetId());

    }


    @org.junit.Test
    public void b2() throws Exception {

        AssetEntity entity = new AssetEntity();

        entity.setAssetId(2001);
        entity.setUserId(8888);
        entity.setType1("3322111");
        entity.setType2("22");
        entity.setDetail("11");
        entity.setAssetAmt(new BigDecimal("1110.12"));
        entity.setNote("note123123");
        assetService.updateAssetStatus(entity);
        System.out.println(entity.getAssetId());


    }


    @org.junit.Test
    public void b3() throws Exception {


        assetService.deleteAssetStatus(2001);

    }

    @org.junit.Test
    public void b4() throws Exception {


        System.out.println(assetService.queryAssetStatus(2002));

    }
    // 以下为family测试

    @org.junit.Test
    public void c1() throws Exception {


        FamilyEntity entity = new FamilyEntity();
        entity.setFamilyName("我的家");
        entity.setMember1(11);
        entity.setMember2(22);
        entity.setMember3(33);
        entity.setMember4(44);
        entity.setMember5(55);
        familyService.insertFamilyInfo(entity);
        System.out.println(entity.getFamilyId());

    }


    @org.junit.Test
    public void c2() throws Exception {

        FamilyEntity entity = new FamilyEntity();

        entity.setFamilyId(201);
        entity.setFamilyName("我的家222");
        entity.setMember1(11);
        entity.setMember2(22);
        entity.setMember3(33);
        entity.setMember4(44);
        entity.setMember5(55);
        familyService.updateFamilyInfo(entity);
        System.out.println(entity.getFamilyId());


    }


    @org.junit.Test
    public void c3() throws Exception {


        familyService.deleteFamilyInfo(131);

    }

    @org.junit.Test
    public void c4() throws Exception {


        System.out.println(familyService.queryFamilyInfo(201));

    }

    @org.junit.Test
    public void familytest() throws Exception {

        System.out.println(familyService.queryFamilyMember(211));

    }

}

