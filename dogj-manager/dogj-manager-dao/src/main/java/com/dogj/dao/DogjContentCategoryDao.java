package com.dogj.dao;

import com.dogj.pojo.DogjContentCategory;
import com.dogj.pojo.DogjContentCategoryQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DogjContentCategoryDao {
    int countByExample(DogjContentCategoryQuery example);

    int deleteByExample(DogjContentCategoryQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(DogjContentCategory record);

    int insertSelective(DogjContentCategory record);

    List<DogjContentCategory> selectByExample(DogjContentCategoryQuery example);

    DogjContentCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DogjContentCategory record, @Param("example") DogjContentCategoryQuery example);

    int updateByExample(@Param("record") DogjContentCategory record, @Param("example") DogjContentCategoryQuery example);

    int updateByPrimaryKeySelective(DogjContentCategory record);

    int updateByPrimaryKey(DogjContentCategory record);
}