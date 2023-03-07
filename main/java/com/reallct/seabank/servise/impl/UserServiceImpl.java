package com.reallct.seabank.servise.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reallct.seabank.bo.UserList;
import com.reallct.seabank.entity.UserEntity;
import com.reallct.seabank.mapper.UserMapper;
import com.reallct.seabank.servise.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Value("${url.code2session}")
    private String code2sessionUrl;
    @Value("${tx.appid}")
    private String appid;
    @Value("${tx.secret}")
    private String secret;
    private final ObjectMapper objectMapper;
    private final UserMapper userMapper;


    @Autowired
    public UserServiceImpl(ObjectMapper objectMapper, UserMapper userMapper) {
        this.objectMapper = objectMapper;
        this.userMapper = userMapper;
    }


    @Override
    public UserEntity getUserByUserId(Integer userId) {
        UserEntity user1 = userMapper.queryByUserId(userId);
        return user1;
    }

    @Override
    public UserList<UserEntity> getUserListByphone(Map<String, Object> param) {
        Integer num = (Integer) param.get("num"); // 当前页起始id
        Integer pageSize = (Integer) param.get("pageSize"); // 页面大小
        param.put("isCount", false);
        List<UserEntity> userList = userMapper.queryListByPhone(param);// 用户列表
        param.put("isCount", true);
        Integer totalNum = userMapper.queryUserNumByPhone(param); // 用户总数

        return new UserList<>(num, totalNum, pageSize, userList);

    }

    @Override
    public UserEntity queryUserBindInfo(Integer userId) {
        return null;
    }




    @Override
    public UserEntity addUserInfo(Integer userId) {
        return null;
    }

    @Override
    public UserEntity queryUserInfo(Integer userId) {
        UserEntity user = userMapper.queryByUserId(userId);
        return user;
    }

    @Override
    public UserEntity updateUserInfo(Integer userId) {
        return null;
    }

    @Override
    public UserEntity modifyDisplayType(Integer userId) {
        return null;
    }


}
