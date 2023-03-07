package com.reallct.seabank.controller;


import com.reallct.seabank.controller.req.AssetListReq;
import com.reallct.seabank.controller.req.CommonRequest;
import com.reallct.seabank.controller.req.CommonResponse;
import com.reallct.seabank.entity.AssetEntity;
import com.reallct.seabank.entity.UserEntity;
import com.reallct.seabank.exception.BaseException;
import com.reallct.seabank.servise.AssetService;
import com.reallct.seabank.servise.FamilyService;
import com.reallct.seabank.servise.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class AssetRestController extends BaseController {
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

    private AssetService assetService;
    private UserService userService;
    private FamilyService familyService;

    @Autowired
    public AssetRestController(AssetService assetService, UserService userService, FamilyService familyService) {
        this.assetService = assetService;
        this.userService = userService;
        this.familyService = familyService;
    }


//传参三种方式：
//1.在url中传参数@RequestParam，无需新建实体类，但使用时需要做类型转换
//2.在body中传参数@RequestBody，需要新建req的实体类
//3.在URL中显示带入@PathVariable，如/assets/{assetId}


//查、删可以放url中，也就是1或2，因为只传序号或条件
    //更新、新增还是json吧

    @PostMapping(value = "/assets")

    public Integer insertAssetStatus(@RequestParam("userId") String userId, @RequestParam("type1") String type1, @RequestParam("type2") String type2,
                                     @RequestParam("detail") String detail, @RequestParam("assetAmt") String assetAmt, @RequestParam("note") String note) throws BaseException, IOException {

        AssetEntity entity = new AssetEntity();

        entity.setUserId(Integer.valueOf(userId));
        entity.setType1(type1);
        entity.setType2(type2);
        entity.setDetail(detail);
        entity.setAssetAmt(new BigDecimal(assetAmt));
        entity.setNote(note);
        assetService.insertAssetStatus(entity);
        // 返回自增主键
        return entity.getAssetId();
    }

    @PutMapping(value = "/assets")
    public Integer updateAssetStatus(@RequestParam("assetId") String assetId, @RequestParam("userId") String userId, @RequestParam("type1") String type1, @RequestParam("type2") String type2,
                                     @RequestParam("detail") String detail, @RequestParam("assetAmt") String assetAmt, @RequestParam("note") String note) throws BaseException, IOException {


        AssetEntity entity = new AssetEntity();
        entity.setAssetId(Integer.valueOf(assetId));
        entity.setUserId(Integer.valueOf(userId));
        entity.setType1(type1);
        entity.setType2(type2);
        entity.setDetail(detail);
        entity.setAssetAmt(new BigDecimal(assetAmt));
        entity.setNote(note);

        return assetService.updateAssetStatus(entity);
    }


    @DeleteMapping(value = "/assets/{assetId}")
    public Integer deleteAssetStatus(@PathVariable Integer assetId) throws BaseException {
        return assetService.deleteAssetStatus(assetId);
    }


    @GetMapping(value = "/assets/{assetId}")
    public CommonResponse<?> queryAssetStatus(@PathVariable Integer assetId, HttpServletRequest request) throws BaseException {
        Object res = assetService.queryAssetStatus(assetId);
        return getRes(res);

    }

    @GetMapping(value = "/assets/queryAssetList")
    public CommonResponse<?> queryAssetList(@RequestBody @Valid CommonRequest<AssetListReq> req, HttpSession session) throws BaseException {

        Integer num = req.getParams().getNum();
        Integer pageSize = req.getParams().getPageSize();


        Integer userId = req.getParams().getUserId();
        String mode = req.getParams().getMode();
        String type1 = req.getParams().getType1();
        String type2 = req.getParams().getType2();


        Map<String, Object> param = new HashMap<>();
        param.put("num", num);
        param.put("pageSize", pageSize);





        // 如果是管理员，默认查询全家的交易，除非选择只查自己
        UserEntity user = userService.queryUserInfo(userId);
        if ("Y".equals(user.getIsAdmin())) {
            if ("P".equals(mode)) {
                param.put("userId", userId);
            } else {
                List list = familyService.queryFamilyMember(user.getFamilyId());
                StringBuilder liststr = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    if (i == 0) {
                        liststr.append(list.get(i).toString());
                    } else {
                        liststr.append(", ").append(list.get(i).toString());
                    }
                }
                param.put("liststr", liststr);
            }
        } else {
            param.put("userId", userId);
        }




        param.put("type1", type1);
        param.put("type2", type2);

        System.out.println(param);
        Object res = assetService.queryAssetList(param);


        return getRes(res);
    }


    @GetMapping(value = "/assets/queryAssetListGroup")
    public CommonResponse<?> queryAssetListGroup(@RequestBody @Valid CommonRequest<AssetListReq> req, HttpSession session) throws BaseException {

        Integer num = req.getParams().getNum();
        Integer pageSize = req.getParams().getPageSize();


        Integer userId = req.getParams().getUserId();
        String mode = req.getParams().getMode();
        String type1 = req.getParams().getType1();
        String type2 = req.getParams().getType2();


        Map<String, Object> param = new HashMap<>();
        param.put("num", num);
        param.put("pageSize", pageSize);





        // 如果是管理员，默认查询全家的交易，除非选择只查自己
        UserEntity user = userService.queryUserInfo(userId);
        if ("Y".equals(user.getIsAdmin())) {
            if ("P".equals(mode)) {
                param.put("userId", userId);
            } else {
                List list = familyService.queryFamilyMember(user.getFamilyId());
                StringBuilder liststr = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    if (i == 0) {
                        liststr.append(list.get(i).toString());
                    } else {
                        liststr.append(", ").append(list.get(i).toString());
                    }
                }
                param.put("liststr", liststr);
            }
        } else {
            param.put("userId", userId);
        }




        param.put("type1", type1);
        param.put("type2", type2);

        System.out.println(param);
        Object res = assetService.queryAssetListGroup(param);


        return getRes(res);
    }



}
