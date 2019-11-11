package com.dogj.dao;

import com.dogj.pojo.DogjOrderShipping;
import com.dogj.pojo.DogjOrderShippingQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DogjOrderShippingDao {
    int countByExample(DogjOrderShippingQuery example);

    int deleteByExample(DogjOrderShippingQuery example);

    int deleteByPrimaryKey(String orderId);

    int insert(DogjOrderShipping record);

    int insertSelective(DogjOrderShipping record);

    List<DogjOrderShipping> selectByExample(DogjOrderShippingQuery example);

    DogjOrderShipping selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") DogjOrderShipping record, @Param("example") DogjOrderShippingQuery example);

    int updateByExample(@Param("record") DogjOrderShipping record, @Param("example") DogjOrderShippingQuery example);

    int updateByPrimaryKeySelective(DogjOrderShipping record);

    int updateByPrimaryKey(DogjOrderShipping record);
}