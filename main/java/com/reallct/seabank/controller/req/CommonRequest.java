package com.reallct.seabank.controller.req;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class CommonRequest<T> {
	private String method;
	private Map<String, Object> header;
	@Valid
	@NotNull(message = "params不能为空")
	private T params;
}
