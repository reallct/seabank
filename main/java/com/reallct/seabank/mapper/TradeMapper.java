package com.reallct.seabank.mapper;

import com.reallct.seabank.entity.TradeEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TradeMapper {


    public Integer insertTradeRecord(TradeEntity trade);
    public Integer updateByTradeId(TradeEntity trade);
    public Integer deleteByTradeId(Integer tradeId);
    public TradeEntity queryByTradeId(Integer tradeId);


    public List<TradeEntity> queryTradeList(Map<String, Object> param);
    Integer queryTradeListNum(Map<String, Object> param);
    String queryTradeSumAmt(Map<String, Object> param);

}