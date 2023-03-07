package com.reallct.seabank.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class AssetGroupEntity implements Serializable {


    private static final long serialVersionUID = -8996805001007498914L;
    private String type1;
    private String type2;
    private BigDecimal sumAssetAmt;
    private String count;
}