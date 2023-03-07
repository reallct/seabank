package com.reallct.seabank.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author reallct
 * @version V1.0
 * @ClassName: TradeEntity
 * @Description: 交易流水实体类
 * @date 2023年2月8日 上午午9:33:26
 */
@Data
public class TradeEntity implements Serializable {
    private static final long serialVersionUID = -3418091628224271269L;
    private Integer tradeId;
    private Integer tradeDate;
    private Integer tradeTime;
    private Integer userId;
    private String type1;
    private String type2;
    private String detail;
    private BigDecimal tradeAmt;
    private String tradeChannel;
    private String note;
    private Integer createUserId;
    private Date createTimestamp;
    private Integer modifyUserId;
    private Date modifyTimestamp;
}
