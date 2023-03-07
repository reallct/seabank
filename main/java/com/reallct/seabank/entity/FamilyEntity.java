package com.reallct.seabank.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author reallct
 * @version V1.0
 * @ClassName: FamilyEntity
 * @Description: 家庭信息实体类
 * @date 2023年2月8日 上午午9:33:26
 */
@Data
public class FamilyEntity implements Serializable {

    private static final long serialVersionUID = 3536655626324467849L;
    private Integer familyId;
    private String familyName;
    private Integer member1;
    private Integer member2;
    private Integer member3;
    private Integer member4;
    private Integer member5;
    private Integer createUserId;
    private Date createTimestamp;
    private Integer modifyUserId;
    private Date modifyTimestamp;
}