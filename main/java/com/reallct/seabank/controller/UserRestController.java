package com.reallct.seabank.controller;


import com.reallct.seabank.controller.req.CommonRequest;
import com.reallct.seabank.controller.req.CommonResponse;
import com.reallct.seabank.controller.req.UserListReq;
import com.reallct.seabank.entity.UserEntity;
import com.reallct.seabank.exception.BaseException;
import com.reallct.seabank.servise.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
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
public class UserRestController extends BaseController {
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

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;

    }

    //    @RequestMapping(path = "/user1/{userId}", method = {RequestMethod.GET})
    @GetMapping(value = "/user1/{userId}")
    public UserEntity queryUser1(@PathVariable Integer userId, HttpServletRequest request) throws BaseException {
        //UserEntity user = checkLoginForJWT(request);
        return userService.getUserByUserId(userId);
    }


    @PostMapping(value = "/user2")
    public CommonResponse<?> queryUser2(@RequestBody @Valid CommonRequest<UserListReq> req, HttpSession session) throws BaseException {
        // 检查是否登录
        this.checkLoginExp(session);
        Integer num = req.getParams().getNum();
        String phone = req.getParams().getPhone();

        //搜索的关键词都改成小写
        Map<String, Object> param = new HashMap<>();
        param.put("num", num);
        param.put("pageSize", 20);
        param.put("phone", phone);

        Object res = userService.getUserListByphone(param);


        return getRes(res);
    }
}
