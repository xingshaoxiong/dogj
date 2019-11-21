package com.dogj.dao;

import com.dogj.pojo.DogjSeckillGoods;
import com.dogj.pojo.DogjSeckillGoodsQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DogjSeckillGoodsDao {
    int countByExample(DogjSeckillGoodsQuery example);

    int deleteByExample(DogjSeckillGoodsQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(DogjSeckillGoods record);

    int insertSelective(DogjSeckillGoods record);

    List<DogjSeckillGoods> selectByExample(DogjSeckillGoodsQuery example);

    DogjSeckillGoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DogjSeckillGoods record, @Param("example") DogjSeckillGoodsQuery example);

    int updateByExample(@Param("record") DogjSeckillGoods record, @Param("example") DogjSeckillGoodsQuery example);

    int updateByPrimaryKeySelective(DogjSeckillGoods record);

    int updateByPrimaryKey(DogjSeckillGoods record);
}