package com.deyi.service;

/**
 * 定义接口为以后的AOP做准备。
 */

import com.deyi.common.ServerResponse;
import com.deyi.entity.Account;

public interface IAccountService {

    void select();

    ServerResponse<Account> login(String userName, String passWord);

    ServerResponse<String> register(Account account);

    ServerResponse<String> checkValidate(String str , String type);

    ServerResponse<String> selectQuestion(String username);

    ServerResponse<String> checkAnser(String username, String question, String answer);
}
