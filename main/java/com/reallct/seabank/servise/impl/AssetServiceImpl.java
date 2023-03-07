package com.reallct.seabank.servise.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reallct.seabank.bo.AssetGroupList;
import com.reallct.seabank.bo.AssetList;
import com.reallct.seabank.entity.AssetEntity;
import com.reallct.seabank.entity.AssetGroupEntity;
import com.reallct.seabank.mapper.AssetMapper;
import com.reallct.seabank.servise.AssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AssetServiceImpl implements AssetService {
    @Value("${url.code2session}")
    private String code2sessionUrl;
    @Value("${tx.appid}")
    private String appid;
    @Value("${tx.secret}")
    private String secret;
    private final ObjectMapper objectMapper;
    private final AssetMapper assetMapper;


    @Autowired
    public AssetServiceImpl(ObjectMapper objectMapper, AssetMapper assetMapper) {
        this.objectMapper = objectMapper;
        this.assetMapper = assetMapper;


    }

    @Override
    public Integer insertAssetStatus(AssetEntity entity) {
        return assetMapper.insertAssetStatus(entity);
    }

    @Override
    public Integer deleteAssetStatus(Integer assetId) {
        return assetMapper.deleteByAssetId(assetId);
    }

    @Override
    public Integer updateAssetStatus(AssetEntity entity) {
        return assetMapper.updateByAssetId(entity);
    }


    @Override
    public AssetEntity queryAssetStatus(Integer assetId) {

        AssetEntity trade = assetMapper.queryByAssetId(assetId);
        return trade;
    }


    @Override
    public AssetList<AssetEntity> queryAssetList(Map<String, Object> param) {
        Integer num = (Integer) param.get("num"); // 当前页起始id
        Integer pageSize = (Integer) param.get("pageSize"); // 页面大小
        param.put("isCount", false);
        List<AssetEntity> list = assetMapper.queryAssetList(param);
        param.put("isCount", true);
        Integer totalNum = assetMapper.queryAssetListNum(param);
        return new AssetList<>(num, totalNum, pageSize, list);
    }



    @Override
    public AssetGroupList<AssetGroupEntity> queryAssetListGroup(Map<String, Object> param) {

        Integer num = (Integer) param.get("num"); // 当前页起始id
        Integer pageSize = (Integer) param.get("pageSize"); // 页面大小
        param.put("isCount", false);
        List<AssetGroupEntity> list = assetMapper.queryAssetListGroup(param);
        param.put("isCount", true);
        Integer totalNum = assetMapper.queryAssetListGroupNum(param);
        return new AssetGroupList<>(num, totalNum, pageSize, list);

    }


}
