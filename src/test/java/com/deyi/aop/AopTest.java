package com.deyi.aop;

import com.deyi.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/7/31.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class AopTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-aop.xml");
        IAccountService service =(IAccountService)context.getBean("accountService");
        service.login("fengdeyi","123456");
        System.out.printf("bean="+service);
    }
}
