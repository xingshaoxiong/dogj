package com.dogj.dao;

import com.dogj.pojo.DogjItemParamItem;
import com.dogj.pojo.DogjItemParamItemQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DogjItemParamItemDao {
    int countByExample(DogjItemParamItemQuery example);

    int deleteByExample(DogjItemParamItemQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(DogjItemParamItem record);

    int insertSelective(DogjItemParamItem record);

    List<DogjItemParamItem> selectByExampleWithBLOBs(DogjItemParamItemQuery example);

    List<DogjItemParamItem> selectByExample(DogjItemParamItemQuery example);

    DogjItemParamItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DogjItemParamItem record, @Param("example") DogjItemParamItemQuery example);

    int updateByExampleWithBLOBs(@Param("record") DogjItemParamItem record, @Param("example") DogjItemParamItemQuery example);

    int updateByExample(@Param("record") DogjItemParamItem record, @Param("example") DogjItemParamItemQuery example);

    int updateByPrimaryKeySelective(DogjItemParamItem record);

    int updateByPrimaryKeyWithBLOBs(DogjItemParamItem record);

    int updateByPrimaryKey(DogjItemParamItem record);
}