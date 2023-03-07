package com.reallct.seabank.servise;

import com.reallct.seabank.bo.AssetGroupList;
import com.reallct.seabank.bo.AssetList;
import com.reallct.seabank.entity.AssetEntity;
import com.reallct.seabank.entity.AssetGroupEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public interface AssetService {


    Integer insertAssetStatus(AssetEntity entity);

    Integer deleteAssetStatus(Integer assetId);

    Integer updateAssetStatus(AssetEntity entity);

    AssetEntity queryAssetStatus(Integer assetId);



    AssetList<AssetEntity> queryAssetList(Map<String, Object> param);
    AssetGroupList<AssetGroupEntity> queryAssetListGroup(Map<String, Object> param);


}
