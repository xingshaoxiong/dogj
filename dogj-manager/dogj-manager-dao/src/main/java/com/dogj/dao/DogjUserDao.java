package com.dogj.dao;

import com.dogj.pojo.DogjUser;
import com.dogj.pojo.DogjUserQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DogjUserDao {
    int countByExample(DogjUserQuery example);

    int deleteByExample(DogjUserQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(DogjUser record);

    int insertSelective(DogjUser record);

    List<DogjUser> selectByExample(DogjUserQuery example);

    DogjUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DogjUser record, @Param("example") DogjUserQuery example);

    int updateByExample(@Param("record") DogjUser record, @Param("example") DogjUserQuery example);

    int updateByPrimaryKeySelective(DogjUser record);

    int updateByPrimaryKey(DogjUser record);
}