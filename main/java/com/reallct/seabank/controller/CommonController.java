package com.reallct.seabank.controller;

import com.reallct.seabank.bo.WXUser;
import com.reallct.seabank.controller.req.CommonResponse;
import com.reallct.seabank.controller.req.Request;
import com.reallct.seabank.controller.req.Response;
import com.reallct.seabank.entity.UserEntity;
import com.reallct.seabank.exception.BaseException;
import com.reallct.seabank.servise.CommonService;
import com.reallct.seabank.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ahuang
 * @version V1.0
 * @ClassName: CommonController
 * @Description: 公共接口，登陆等
 * @date 2018年6月8日 下午9:02:00
 */
@Slf4j
@RestController
@RequestMapping("/seabank")
public class CommonController extends BaseController {
    private final CommonService commonService;
    /**
     * @fieldName: test
     * @fieldType: boolean
     * @Description: 是否为测试模式（测试模式根据任意code都可以获取测试用户openid）
     */
    @Value("${tx.test}")
    private boolean test;

    @Autowired
    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }


    // 检查是否注册绑定
    @PostMapping(value = "/CustQueryIsBinded")
    public CommonResponse<?> CustQueryIsBinded(@RequestBody @Valid Request req, HttpSession session) throws BaseException {
        WXUser user;
        String openid;
        // 若未登录，通过code查询openid，将user（openid）放入session
        if (!checkLogin(session)) {
            log.info("未登陆，获取openid");
            String code = (String) req.getParam("code");
            if (!test) {
                openid = commonService.getOpenidByCode(code);
            } else {
                openid = "oe0Ej0besxqth6muj72ZzfYGmMp0";
            }
            if (!StringUtil.isNullOrEmpty(openid)) {
                // 如果返回报文中有openid说明登陆成功
                user = new WXUser();
                user.setOpenid(openid);
                // 保存查询到的openid，加快后续查询速度
                session.setAttribute("user", user);
            } else {
                //否则说明登陆失败
                throw new BaseException("login.failed", "小程序登陆校验失败");
            }
        } else {
            user = (WXUser) session.getAttribute("user");
            openid = user.getOpenid();
            log.info("已登陆，openid=" + openid);
        }

        // 根据openid查询用户是否注册过，bindUser放入session
        UserEntity bindUser = (UserEntity) session.getAttribute("bindUser");

        if (StringUtil.isNullOrEmpty(bindUser)) {
            log.debug("session中没有openid:" + openid + "，绑定信息，从库中查询……");
            bindUser = commonService.getUserByOpenid(openid);
            if (StringUtil.isNullOrEmpty(bindUser)) {
                log.info("OPENID:" + openid + "未绑定！");
            } else {
                log.debug("库中查询到openid:" + openid + "，绑定信息，写入session……");
                session.setAttribute("bindUser", bindUser);
            }
        }
        Map<String, Object> res = new HashMap<>();
        res.put("isBinded", "0");//默认未绑定
        if (!StringUtil.isNullOrEmpty(bindUser)) {
            res.put("isBinded", "1");//已绑定
            res.put("userId", bindUser.getUserId());
            res.put("userName", bindUser.getName());
        }

        return getRes(res);
    }

    // 注册绑定，完成后将bindUser放入session
    @PostMapping(value = "/CustBind")
    public Response custBind(@RequestBody @Valid Request req, HttpSession session) throws BaseException {
        this.checkLoginExp(session);//如果用户没有登陆则报错
        String userName = (String) req.getParam("userName");
        String nickName = (String) req.getParam("nickName");
        String headImgUrl = (String) req.getParam("headImgUrl");
        WXUser user = (WXUser) session.getAttribute("user");
        String openid = user.getOpenid();
        UserEntity bindUser = commonService.getUserByOpenid(openid);
        if (StringUtil.isNullOrEmpty(bindUser)) {
            // 如果库中没有绑定记录，说明用户需要绑定
            bindUser = new UserEntity();
            bindUser.setOpenid(openid);
            bindUser.setName(userName);
            bindUser.setImgUrl(headImgUrl);
            bindUser.setNickName(nickName);
            commonService.custUserBind(bindUser);
            log.debug("注册成功，openid:" + openid + "，写入session……");
            session.setAttribute("bindUser", bindUser);
        } else {
            log.info("该用户已经注册，openid:" + openid);
        }

        return getRes(null);
    }

}
