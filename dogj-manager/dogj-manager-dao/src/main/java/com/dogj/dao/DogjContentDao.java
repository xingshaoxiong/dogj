package com.dogj.dao;

import com.dogj.pojo.DogjContent;
import com.dogj.pojo.DogjContentQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DogjContentDao {
    int countByExample(DogjContentQuery example);

    int deleteByExample(DogjContentQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(DogjContent record);

    int insertSelective(DogjContent record);

    List<DogjContent> selectByExampleWithBLOBs(DogjContentQuery example);

    List<DogjContent> selectByExample(DogjContentQuery example);

    DogjContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DogjContent record, @Param("example") DogjContentQuery example);

    int updateByExampleWithBLOBs(@Param("record") DogjContent record, @Param("example") DogjContentQuery example);

    int updateByExample(@Param("record") DogjContent record, @Param("example") DogjContentQuery example);

    int updateByPrimaryKeySelective(DogjContent record);

    int updateByPrimaryKeyWithBLOBs(DogjContent record);

    int updateByPrimaryKey(DogjContent record);
}