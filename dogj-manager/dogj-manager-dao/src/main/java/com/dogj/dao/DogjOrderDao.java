package com.dogj.dao;

import com.dogj.pojo.DogjOrder;
import com.dogj.pojo.DogjOrderQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DogjOrderDao {
    int countByExample(DogjOrderQuery example);

    int deleteByExample(DogjOrderQuery example);

    int deleteByPrimaryKey(String orderId);

    int insert(DogjOrder record);

    int insertSelective(DogjOrder record);

    List<DogjOrder> selectByExample(DogjOrderQuery example);

    DogjOrder selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") DogjOrder record, @Param("example") DogjOrderQuery example);

    int updateByExample(@Param("record") DogjOrder record, @Param("example") DogjOrderQuery example);

    int updateByPrimaryKeySelective(DogjOrder record);

    int updateByPrimaryKey(DogjOrder record);
}