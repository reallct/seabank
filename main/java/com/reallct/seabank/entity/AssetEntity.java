package com.reallct.seabank.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author reallct
 * @version V1.0
 * @ClassName: AssetEntity
 * @Description: 资产状况实体类
 * @date 2023年2月8日 上午午9:33:26
 */
@Data
public class AssetEntity implements Serializable {

    private static final long serialVersionUID = 4355090404998740293L;
    private Integer assetId;
    private Integer userId;
    private String type1;
    private String type2;
    private String detail;
    private BigDecimal assetAmt;
    private String note;
    private Integer createUserId;
    private Date createTimestamp;
    private Integer modifyUserId;
    private Date modifyTimestamp;
}