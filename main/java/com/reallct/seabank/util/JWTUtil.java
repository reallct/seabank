package com.reallct.seabank.util;

import com.reallct.seabank.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * JWT相关工具类
 *
 * @author ahuang
 * @version V1.0
 * @Title: JWTUtil
 * @Program: bookCornerServer
 * @Package com.reallct.seabank.util
 * @create 2018-07-11 20:55
 */
public class JWTUtil {

    public static String getToken(String openid, UserEntity bindUser, String SECRET, long EXPIRATIONTIME) {
        // 如果传入bindUser，说明该用户已经绑定，否则说明没有绑定
        String tokenJWT;
        if(StringUtil.isNullOrEmpty(bindUser)) {
            tokenJWT = Jwts.builder() //生成token
                    // 保存用户信息
                    // 用户openid写入标题
                    .setSubject(openid)
                    // 有效期设置
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                    // 签名设置
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
        } else {
            tokenJWT = Jwts.builder() //生成token
                    // 保存用户信息
//                    .claim("authorities", "ROLE_ADMIN,AUTH_WRITE")
                    .claim("userId", bindUser.getUserId())
                    .claim("name", bindUser.getName())
                    .claim("nickName", bindUser.getNickName())
                    .claim("imgUrl", bindUser.getImgUrl())
                    .claim("isAdmin", bindUser.getIsAdmin())
                    // 用户openid写入标题
                    .setSubject(openid)
                    // 有效期设置
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                    // 签名设置
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
        }
        return tokenJWT;
    }
    /**
    * 根据token获取用户信息，如果返回的user为空，说明没有登陆，如果user只有openid不为空，说明登陆但未绑定，如果user信息完整，说明已登陆且已绑定
    * @params  [token]
    * @return: com.reallct.seabank.entity.UserEntity
    * @Author: ahuang
    * @Date: 2018/7/11 下午9:24
    */
    public static UserEntity getInfo(String token, String SECRET) {
        if(StringUtil.isNullOrEmpty(token)) {// 如果token为空，则返回null
            return null;
        }
        UserEntity user = new UserEntity();
        String TOKEN_PREFIX = "Bearer";        // Token前缀
        Claims claims = Jwts.parser()
                // 验签
                .setSigningKey(SECRET)
                // 去掉 Bearer
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
        // 获取token中信息
        user.setOpenid(claims.getSubject());
        user.setName((String) claims.get("userName"));
        user.setUserId((Integer) claims.get("userId"));
        user.setImgUrl((String) claims.get("imgUrl"));
        user.setNickName((String) claims.get("nickName"));
        user.setIsAdmin((String) claims.get("isAdmin"));
//            String auth = (String) claims.get("authorities");
//            Date date = claims.getExpiration();
//            log.info(openid);
//            log.info(auth);
//            log.info(date.toString());
        return user;
    }

    public static LoginStatus getLoginStatus(UserEntity user) {
        if(StringUtil.isNullOrEmpty(user)) {
            return LoginStatus.NotLogin;
        } else {
            if(StringUtil.isNullOrEmpty(user.getOpenid())) {
                return LoginStatus.NotLogin;
            } else if(StringUtil.isNullOrEmpty(user.getOpenid())) {
                return LoginStatus.LoginWithoutBinded;
            } else {
                return LoginStatus.LoginAndBinded;
            }
        }
    }
}
