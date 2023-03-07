package com.reallct.seabank.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
* @ClassName: UserList
* @Description:
* @author ahuang
* @date 2018年6月11日 下午9:30:19
* @version V1.0
* @param <T>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AssetList<T>  extends PageList<T>{
	@JsonProperty(value = "assetList")
	private List<T> objectList;
	public AssetList(Integer startNum, Integer totalNum, Integer pageSize, List<T> list) {
		super(startNum, totalNum, pageSize, list);
		this.objectList = list;
	}

}
