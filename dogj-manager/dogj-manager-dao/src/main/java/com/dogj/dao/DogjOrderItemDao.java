package com.dogj.dao;

import com.dogj.pojo.DogjOrderItem;
import com.dogj.pojo.DogjOrderItemQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DogjOrderItemDao {
    int countByExample(DogjOrderItemQuery example);

    int deleteByExample(DogjOrderItemQuery example);

    int deleteByPrimaryKey(String id);

    int insert(DogjOrderItem record);

    int insertSelective(DogjOrderItem record);

    List<DogjOrderItem> selectByExample(DogjOrderItemQuery example);

    DogjOrderItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DogjOrderItem record, @Param("example") DogjOrderItemQuery example);

    int updateByExample(@Param("record") DogjOrderItem record, @Param("example") DogjOrderItemQuery example);

    int updateByPrimaryKeySelective(DogjOrderItem record);

    int updateByPrimaryKey(DogjOrderItem record);
}