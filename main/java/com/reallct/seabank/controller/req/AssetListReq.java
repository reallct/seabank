package com.reallct.seabank.controller.req;

import lombok.Data;

/**
* @ClassName: CustQueryBookListReq
* @Description: CustQueryBookListReq请求报文
* @author ahuang
* @date 2018年6月11日 下午8:54:43
* @version V1.0
 */
@Data
public class AssetListReq extends ListReq {
	private Integer userId;
	private String type1;
	private String type2;
	private String mode;

}
