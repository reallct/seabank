package com.reallct.seabank.mapper;

import com.reallct.seabank.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserMapper {



    @Select("select * from USER_INFO WHERE openid=#{openid}")
    UserEntity queryByOpenid(String openid);


    @Select("select * from USER_INFO WHERE USER_ID=#{userId}")
    public UserEntity queryByUserId1(@Param("userId") Integer userId);


    public UserEntity queryByUserId(Integer userId);



    public List<UserEntity> queryListByPhone(Map<String, Object> param);


    Integer queryUserNumByPhone(Map<String, Object> param);

    Integer insertUserInfo(UserEntity entity);


}