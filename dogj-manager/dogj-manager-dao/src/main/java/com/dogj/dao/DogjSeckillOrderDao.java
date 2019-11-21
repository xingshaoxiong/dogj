package com.dogj.dao;

import com.dogj.pojo.DogjSeckillOrder;
import com.dogj.pojo.DogjSeckillOrderQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DogjSeckillOrderDao {
    int countByExample(DogjSeckillOrderQuery example);

    int deleteByExample(DogjSeckillOrderQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(DogjSeckillOrder record);

    int insertSelective(DogjSeckillOrder record);

    List<DogjSeckillOrder> selectByExample(DogjSeckillOrderQuery example);

    DogjSeckillOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DogjSeckillOrder record, @Param("example") DogjSeckillOrderQuery example);

    int updateByExample(@Param("record") DogjSeckillOrder record, @Param("example") DogjSeckillOrderQuery example);

    int updateByPrimaryKeySelective(DogjSeckillOrder record);

    int updateByPrimaryKey(DogjSeckillOrder record);
}