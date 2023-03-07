package com.reallct.seabank.controller.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 
* @ClassName: CustQueryIsBinded
* @Description: CustQueryIsBinded接口的请求报文
* @author ahuang
* @date 2018年6月8日 下午10:35:42
* @version V1.0
 */
@Data
public class CustQueryIsBindedReq {
	@NotEmpty(message = "code不能为空")
	private String code;
	private String debug;
}
