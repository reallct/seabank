package com.reallct.seabank.controller;


import com.reallct.seabank.controller.req.CommonResponse;
import com.reallct.seabank.entity.FamilyEntity;
import com.reallct.seabank.exception.BaseException;
import com.reallct.seabank.servise.FamilyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Rest风格的接口
 *
 * @author ahuang
 * @version V1.0
 * @Title: BookRestController
 * @Program: bookCornerServer
 * @Package com.ahuang.bookCornerServer.controller
 * @create 2018-07-05 22:34
 */
@Slf4j
@RestController
@RequestMapping(path = "/seabank/v1")
public class FamilyRestController extends BaseController {
    /**
     * JWT加密密钥
     */
    @Value("${jwt.secret}")
    protected String SECRET;

    /**
     * JWT超时时间
     */
    @Value("${jwt.expiration.time}")
    protected long EXPIRATIONTIME;

    private FamilyService familyService;

    @Autowired
    public FamilyRestController(FamilyService familyService) {
        this.familyService = familyService;

    }


//传参三种方式：
//1.在url中传参数@RequestParam，无需新建实体类，但使用时需要做类型转换
//2.在body中传参数@RequestBody，需要新建req的实体类
//3.在URL中显示带入@PathVariable，如/families/{familyId}


//查、删可以放url中，也就是1或2，因为只传序号或条件
    //更新、新增还是json吧

    @PostMapping(value = "/families")

    public Integer insertFamilyInfo(@RequestParam("familyName") String familyName, @RequestParam("member1") String member1, @RequestParam("member2") String member2, @RequestParam("member3") String member3,
                                    @RequestParam("member4") String member4, @RequestParam("member5") String member5) throws BaseException, IOException {

        FamilyEntity entity = new FamilyEntity();
        entity.setFamilyName(familyName);
        entity.setMember1(Integer.valueOf(member1));
        entity.setMember2(Integer.valueOf(member2));
        entity.setMember3(Integer.valueOf(member3));
        entity.setMember4(Integer.valueOf(member4));
        entity.setMember5(Integer.valueOf(member5));

        familyService.insertFamilyInfo(entity);
        // 返回自增主键
        return entity.getFamilyId();
    }

    @PutMapping(value = "/families")
    public Integer updateFamilyInfo(@RequestParam("familyId") String familyId, @RequestParam("familyName") String familyName, @RequestParam("member1") String member1, @RequestParam("member2") String member2, @RequestParam("member3") String member3,
                                    @RequestParam("member4") String member4, @RequestParam("member5") String member5) throws BaseException, IOException {


        FamilyEntity entity = new FamilyEntity();
        entity.setFamilyId(Integer.valueOf(familyId));
        entity.setFamilyName(familyName);
        entity.setMember1(Integer.valueOf(member1));
        entity.setMember2(Integer.valueOf(member2));
        entity.setMember3(Integer.valueOf(member3));
        entity.setMember4(Integer.valueOf(member4));
        entity.setMember5(Integer.valueOf(member5));

        return familyService.updateFamilyInfo(entity);
    }


    @DeleteMapping(value = "/families/{familyId}")
    public Integer deleteFamilyInfo(@PathVariable Integer familyId) throws BaseException {
        return familyService.deleteFamilyInfo(familyId);
    }


    @GetMapping(value = "/families/{familyId}")
    public CommonResponse<?> queryFamilyInfo(@PathVariable Integer familyId, HttpServletRequest request) throws BaseException {
        Object res = familyService.queryFamilyInfo(familyId);
        return getRes(res);

    }

}
