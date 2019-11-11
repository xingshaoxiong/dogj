package com.dogj.dao;

import com.dogj.pojo.DogjItemParam;
import com.dogj.pojo.DogjItemParamQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DogjItemParamDao {
    int countByExample(DogjItemParamQuery example);

    int deleteByExample(DogjItemParamQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(DogjItemParam record);

    int insertSelective(DogjItemParam record);

    List<DogjItemParam> selectByExampleWithBLOBs(DogjItemParamQuery example);

    List<DogjItemParam> selectByExample(DogjItemParamQuery example);

    DogjItemParam selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DogjItemParam record, @Param("example") DogjItemParamQuery example);

    int updateByExampleWithBLOBs(@Param("record") DogjItemParam record, @Param("example") DogjItemParamQuery example);

    int updateByExample(@Param("record") DogjItemParam record, @Param("example") DogjItemParamQuery example);

    int updateByPrimaryKeySelective(DogjItemParam record);

    int updateByPrimaryKeyWithBLOBs(DogjItemParam record);

    int updateByPrimaryKey(DogjItemParam record);
}