package com.reallct.seabank.servise.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reallct.seabank.bo.TradeList;
import com.reallct.seabank.entity.TradeEntity;
import com.reallct.seabank.mapper.TradeMapper;
import com.reallct.seabank.servise.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TradeServiceImpl implements TradeService {
    @Value("${url.code2session}")
    private String code2sessionUrl;
    @Value("${tx.appid}")
    private String appid;
    @Value("${tx.secret}")
    private String secret;
    private final ObjectMapper objectMapper;
    private final TradeMapper tradeMapper;


    @Autowired
    public TradeServiceImpl(ObjectMapper objectMapper, TradeMapper tradeMapper) {
        this.objectMapper = objectMapper;
        this.tradeMapper = tradeMapper;


    }


    @Override
    public Integer insertTradeRecord(TradeEntity trade) {
        return tradeMapper.insertTradeRecord(trade);
    }

    @Override
    public Integer deleteTradeRecord(Integer tradeId) {
        return tradeMapper.deleteByTradeId(tradeId);
    }

    @Override
    public Integer updateTradeRecord(TradeEntity trade) {
        return tradeMapper.updateByTradeId(trade);
    }


    @Override
    public TradeEntity queryTradeRecord(Integer tradeId) {

        TradeEntity trade = tradeMapper.queryByTradeId(tradeId);
        return trade;
    }


    @Override
    public TradeList<TradeEntity> queryTradelist(Map<String, Object> param) {
        Integer num = (Integer) param.get("num"); // 当前页起始id
        Integer pageSize = (Integer) param.get("pageSize"); // 页面大小
        param.put("isCount", false);
        List<TradeEntity> tradeList = tradeMapper.queryTradeList(param);
        param.put("isCount", true);
        Integer totalNum = tradeMapper.queryTradeListNum(param);
        String sumAmt = tradeMapper.queryTradeSumAmt(param);
        if (sumAmt == null) {
            sumAmt="0";
        }
        return new TradeList<>(num, totalNum, pageSize, new BigDecimal(sumAmt), tradeList);
    }

    @Override
    public TradeEntity queryTradeAmtSum(Integer userId) {
        return null;
    }


}
