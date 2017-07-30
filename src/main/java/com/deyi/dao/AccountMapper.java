package com.deyi.dao;

import com.deyi.entity.Account;
import org.apache.ibatis.annotations.Param;

public interface  AccountMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    int checkPhone(String phone);

    int checkUserName(String userName);

    //使用多个参数的时候需要加上@Param
    Account loginCheck(@Param("username") String useraname, @Param("password") String password);

    String selectQuestionByUserName(String username);

    int checkAnswer(@Param("username") String username,@Param("question") String question,@Param("answer") String answer);
}