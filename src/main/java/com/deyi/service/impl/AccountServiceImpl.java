package com.deyi.service.impl;

import com.deyi.common.Constants;
import com.deyi.common.ServerResponse;
import com.deyi.common.TokenCache;
import com.deyi.dao.AccountMapper;
import com.deyi.entity.Account;
import com.deyi.service.IAccountService;
import com.deyi.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("iAccountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void select() {

    }

    @Override
    public ServerResponse<Account> login(String userName, String passWord) {
        System.out.println("正在登录....");
        int resultCount=accountMapper.checkUserName(userName);
        if (resultCount==0){
            return ServerResponse.createByErrorMessage("找不到用户名，请检查");
        }
        String md5password= MD5Util.MD5EncodeUtf8(passWord);
        Account account=accountMapper.loginCheck(userName,md5password);
        if (account==null){
            //走到这里说明用户名存在，只能是密码错误。
            return  ServerResponse.createByErrorMessage("密码错误");
        }
        account.setPassWord(StringUtils.EMPTY);

        return  ServerResponse.createBySuccess("登录成功",account);

    }

    @Override
    public ServerResponse<String> register(Account account){
        ServerResponse<String> validatedResponse=this.checkValidate(account.getUserName(),Constants.USERNAME);
        if (!validatedResponse.isSuccess()){
            return validatedResponse;
        }
        validatedResponse=this.checkValidate(account.getPhoneNumber(),Constants.PHONE);
        if (!validatedResponse.isSuccess()){
            return validatedResponse;
        }

        //TODO 设置管理员权限.
        account.setPassWord(MD5Util.MD5EncodeUtf8(account.getPassWord()));

        int resultCount =accountMapper.insert(account);

        if (resultCount==0){
            return  ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMsg("注册成功.");
    }
    //返回消息如果是Error说明用户或者手机存在。
    public ServerResponse<String> checkValidate(String str , String type){
        //这里使用isNotBlank不用isNotEmpty因为两者对"  "两个空格的字符串的判断不一致我们希望都是空格的话就当作空处理.
        if (StringUtils.isNotBlank(type)){
            if (Constants.USERNAME.equals(type)){
                int resultCount=accountMapper.checkUserName(str);
                if (resultCount>0){
                    return ServerResponse.createByErrorMessage("用户名存在，请检查");
                }
            } else if (Constants.PHONE.equals(type)){
                int resultCount=accountMapper.checkPhone(str);
                if (resultCount>0){
                    return ServerResponse.createByErrorMessage("电话存在，请检查");
                }
            }else {
                return ServerResponse.createByErrorMessage("参数错误, 错误参数为:"+type);
            }
        }else {
            return ServerResponse.createByErrorMessage("参数为空，请输入参数。");
        }
        return  ServerResponse.createBySuccessMsg("验证成功!");
    }

    @Override
    public ServerResponse<String> selectQuestion(String username) {
        ServerResponse response =this.checkValidate(username,Constants.USERNAME);

        if ( response.isSuccess() ){
            return  ServerResponse.createByErrorMessage("用户不存在");
        }
        String question=accountMapper.selectQuestionByUserName(username);
        if (StringUtils.isNotBlank(question)){
            return ServerResponse.createBySuccess(question);
        }

        return ServerResponse.createByErrorMessage("未设置问题。");
    }

    @Override
    public ServerResponse<String> checkAnser(String username, String question, String answer) {
        int resultCount= accountMapper.checkAnswer(username,question,answer);
        if (resultCount>0){
            //生成一个几乎不会重复的字符串,并且放入本地cache，设置有效期.
            String forgetToken=UUID.randomUUID().toString();
            TokenCache.setKey("token_"+username,forgetToken);
            return  ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题答案错误.");
    }
}
