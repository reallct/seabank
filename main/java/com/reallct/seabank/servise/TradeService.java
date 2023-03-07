package com.reallct.seabank.servise;

import com.reallct.seabank.bo.TradeList;
import com.reallct.seabank.entity.TradeEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public interface TradeService {


    Integer insertTradeRecord(TradeEntity trade);

    Integer deleteTradeRecord(Integer userId);

    Integer updateTradeRecord(TradeEntity trade);

    TradeEntity queryTradeRecord(Integer userId);


    // 交易查询（列表）
    TradeList<TradeEntity> queryTradelist(Map<String, Object> param);

    // 交易总额查询
    TradeEntity queryTradeAmtSum(Integer userId);


}
