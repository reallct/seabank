package com.reallct.seabank.servise;

import com.reallct.seabank.bo.UserList;
import com.reallct.seabank.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public interface UserService {


    UserEntity getUserByUserId(Integer userId);

    UserList<UserEntity> getUserListByphone(Map<String, Object> param);

	// 查询用户是否注册
	UserEntity queryUserBindInfo(Integer userId);

    // 增加客户
    UserEntity addUserInfo(Integer userId);

    // 查询客户信息
    UserEntity queryUserInfo(Integer userId);

	// 更新客户信息
	UserEntity updateUserInfo(Integer userId);

    // 修改默认展示方式
    UserEntity modifyDisplayType(Integer userId);


}
