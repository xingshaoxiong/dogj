package com.dogj.dao;

import com.dogj.pojo.DogjItem;
import com.dogj.pojo.DogjItemQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DogjItemDao {
    int countByExample(DogjItemQuery example);

    int deleteByExample(DogjItemQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(DogjItem record);

    int insertSelective(DogjItem record);

    List<DogjItem> selectByExample(DogjItemQuery example);

    DogjItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DogjItem record, @Param("example") DogjItemQuery example);

    int updateByExample(@Param("record") DogjItem record, @Param("example") DogjItemQuery example);

    int updateByPrimaryKeySelective(DogjItem record);

    int updateByPrimaryKey(DogjItem record);
}