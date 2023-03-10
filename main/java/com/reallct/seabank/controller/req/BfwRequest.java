package com.reallct.seabank.controller.req;

import lombok.Data;

@Data
public class BfwRequest<T> {
	private CommonRequest<T> json;
	
	public T getParams() {
		return json.getParams();
	}
}
