package com.reallct.seabank.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author reallct
 * @version V1.0
 * @ClassName: UserEntity
 * @Description: 用户信息实体类
 * @date 2023年2月8日 上午午9:33:26
 */
@Data
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -6268730946114089444L;
    private Integer userId;
    private String name;
    private String nickName;
    private String phone;
    private String openid;
    private String imgUrl;
    private String isAdmin;
    private Integer familyId;
    private Integer createUserId;
    private Date createTimestamp;
    private Integer modifyUserId;
    private Date modifyTimestamp;
}
