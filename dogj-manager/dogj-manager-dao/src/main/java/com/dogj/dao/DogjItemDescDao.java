package com.dogj.dao;

import com.dogj.pojo.DogjItemDesc;
import com.dogj.pojo.DogjItemDescQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DogjItemDescDao {
    int countByExample(DogjItemDescQuery example);

    int deleteByExample(DogjItemDescQuery example);

    int deleteByPrimaryKey(Long itemId);

    int insert(DogjItemDesc record);

    int insertSelective(DogjItemDesc record);

    List<DogjItemDesc> selectByExampleWithBLOBs(DogjItemDescQuery example);

    List<DogjItemDesc> selectByExample(DogjItemDescQuery example);

    DogjItemDesc selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") DogjItemDesc record, @Param("example") DogjItemDescQuery example);

    int updateByExampleWithBLOBs(@Param("record") DogjItemDesc record, @Param("example") DogjItemDescQuery example);

    int updateByExample(@Param("record") DogjItemDesc record, @Param("example") DogjItemDescQuery example);

    int updateByPrimaryKeySelective(DogjItemDesc record);

    int updateByPrimaryKeyWithBLOBs(DogjItemDesc record);

    int updateByPrimaryKey(DogjItemDesc record);
}