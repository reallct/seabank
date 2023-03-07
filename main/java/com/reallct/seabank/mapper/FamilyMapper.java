package com.reallct.seabank.mapper;

import com.reallct.seabank.entity.FamilyEntity;
import org.springframework.stereotype.Service;



@Service
public interface FamilyMapper {


    public Integer insertFamilyInfo(FamilyEntity entity);
    public Integer deleteFamilyInfo(Integer familyId);
    public Integer updateFamilyInfo(FamilyEntity entity);
    public FamilyEntity queryFamilyInfo(Integer familyId);


}