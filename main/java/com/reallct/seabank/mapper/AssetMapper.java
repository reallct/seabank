package com.reallct.seabank.mapper;

import com.reallct.seabank.entity.AssetEntity;
import com.reallct.seabank.entity.AssetGroupEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AssetMapper {






    public Integer insertAssetStatus(AssetEntity entity);
    public Integer updateByAssetId(AssetEntity entity);
    public Integer deleteByAssetId(Integer assetId);
    public AssetEntity queryByAssetId(Integer assetId);


    public List<AssetEntity> queryAssetList(Map<String, Object> param);
    Integer queryAssetListNum(Map<String, Object> param);

    public List<AssetGroupEntity> queryAssetListGroup(Map<String, Object> param);
    Integer queryAssetListGroupNum(Map<String, Object> param);


}