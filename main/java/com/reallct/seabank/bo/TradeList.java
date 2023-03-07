package com.reallct.seabank.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * @param <T>
 * @author ahuang
 * @version V1.0
 * @ClassName: UserList
 * @Description:
 * @date 2018年6月11日 下午9:30:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TradeList<T> extends PageList<T> {
    private BigDecimal sumAmt;
    @JsonProperty(value = "tradeList")
    private List<T> objectList;

    public TradeList(Integer startNum, Integer totalNum, Integer pageSize, BigDecimal sumAmt, List<T> list) {
        super(startNum, totalNum, pageSize, list);
        this.sumAmt = sumAmt;
        this.objectList = list;
    }

}
