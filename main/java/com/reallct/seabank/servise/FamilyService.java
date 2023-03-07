package com.reallct.seabank.servise;

import com.reallct.seabank.entity.FamilyEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface FamilyService {


    Integer insertFamilyInfo(FamilyEntity entity);

    Integer deleteFamilyInfo(Integer familyId);

    Integer updateFamilyInfo(FamilyEntity entity);

    FamilyEntity queryFamilyInfo(Integer familyId);


    // 通过家庭id查询，返回该家庭全部成员的id list
    List<Integer> queryFamilyMember(Integer familyId);


}
