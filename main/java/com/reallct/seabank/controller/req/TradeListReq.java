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
public class TradeListReq extends ListReq {
	private String tradeDate;
	private Integer userId;
	private String type1;
	private String type2;
	private String tradeChannel;
	// 查询模式，F家庭/P个人
	private String mode;
	// 日期开始
	private Integer beginDate;
	// 日期结束
	private Integer endDate;


}
