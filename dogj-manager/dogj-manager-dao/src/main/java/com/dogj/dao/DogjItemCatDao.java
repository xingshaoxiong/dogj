package com.dogj.dao;

import com.dogj.pojo.DogjItemCat;
import com.dogj.pojo.DogjItemCatQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DogjItemCatDao {
    int countByExample(DogjItemCatQuery example);

    int deleteByExample(DogjItemCatQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(DogjItemCat record);

    int insertSelective(DogjItemCat record);

    List<DogjItemCat> selectByExample(DogjItemCatQuery example);

    DogjItemCat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DogjItemCat record, @Param("example") DogjItemCatQuery example);

    int updateByExample(@Param("record") DogjItemCat record, @Param("example") DogjItemCatQuery example);

    int updateByPrimaryKeySelective(DogjItemCat record);

    int updateByPrimaryKey(DogjItemCat record);
}