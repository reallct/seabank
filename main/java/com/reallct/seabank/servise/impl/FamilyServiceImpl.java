package com.reallct.seabank.servise.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reallct.seabank.entity.FamilyEntity;
import com.reallct.seabank.mapper.FamilyMapper;
import com.reallct.seabank.servise.FamilyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FamilyServiceImpl implements FamilyService {
    @Value("${url.code2session}")
    private String code2sessionUrl;
    @Value("${tx.appid}")
    private String appid;
    @Value("${tx.secret}")
    private String secret;
    private final ObjectMapper objectMapper;
    private final FamilyMapper familyMapper;


    @Autowired
    public FamilyServiceImpl(ObjectMapper objectMapper, FamilyMapper familyMapper) {
        this.objectMapper = objectMapper;
        this.familyMapper = familyMapper;

    }


    @Override
    public Integer insertFamilyInfo(FamilyEntity entity) {
        return familyMapper.insertFamilyInfo(entity);

    }

    @Override
    public Integer deleteFamilyInfo(Integer familyId) {
        return familyMapper.deleteFamilyInfo(familyId);

    }

    @Override
    public Integer updateFamilyInfo(FamilyEntity entity) {
        return familyMapper.updateFamilyInfo(entity);

    }

    @Override
    public FamilyEntity queryFamilyInfo(Integer familyId) {
        return familyMapper.queryFamilyInfo(familyId);

    }


    // 通过家庭id查询，返回该家庭全部成员id的list
    @Override
    public List<Integer> queryFamilyMember(Integer familyId) {
        FamilyEntity familyEntity = familyMapper.queryFamilyInfo(familyId);

        List<Integer> list = new ArrayList<>();
        if (null != familyEntity.getMember1()) {
            list.add(familyEntity.getMember1());
        }
        if (null != familyEntity.getMember2()) {
            list.add(familyEntity.getMember2());
        }
        if (null != familyEntity.getMember3()) {
            list.add(familyEntity.getMember3());
        }
        if (null != familyEntity.getMember4()) {
            list.add(familyEntity.getMember4());
        }
        if (null != familyEntity.getMember5()) {
            list.add(familyEntity.getMember5());
        }
        return list;
    }
}
