package com.deyi.controller.frontend;

import com.deyi.common.Constants;
import com.deyi.common.ServerResponse;
import com.deyi.entity.Account;
import com.deyi.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by deyi
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IAccountService service;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Account> login(String username, String password , HttpSession session){
        ServerResponse<Account> response =service.login(username,password);
        if (response.isSuccess()){
            session.setAttribute(Constants.CURRENT_USER,response.getData());
        }
        return  response;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(){
        return  "user/login";
    }

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        Account account = (Account) session.getAttribute(Constants.CURRENT_USER);
        if (account==null){
            return ServerResponse.createByErrorMessage("登出失败，请确认用户是否登录.");
        }
        session.removeAttribute(Constants.CURRENT_USER);
        return ServerResponse.createBySuccess("登出成功.");
    }

    @RequestMapping(value="register",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> register(Account account){
        return  service.register(account);
    }

    @RequestMapping(value="checkValidate",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> checkValidate(String str , String type){
        return  service.checkValidate(str,type);
    }

    @RequestMapping(value="getAccountInfo",method = RequestMethod.GET)
    @ResponseBody
    //返回用户信息或者错误信息
    public ServerResponse<Account> getAccountInfo(HttpSession session){
        Account account =(Account) session.getAttribute(Constants.CURRENT_USER);
        if (account!=null){
            return  ServerResponse.createBySuccess(account);
        }
        return  ServerResponse.createBySuccessMsg("用户未登录，无法获取用户信息。");
    }

    @RequestMapping(value="forgetPassword",method = RequestMethod.GET)
    @ResponseBody
    //根据用户名返回找回密码的问题
    public ServerResponse<String> forgetPassword(String username){
        return  service.selectQuestion(username);
    }

    @RequestMapping(value="forgetAnserCheck",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetAnswerCheck(String username , String question , String answer ){
        return  service.checkAnser(username,question,answer);
    }



}
