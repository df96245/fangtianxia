package jedis;

import com.deyi.dao.redis.RedisDao;
import com.deyi.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017/8/3.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-mybatis.xml"})
public class JedisTest {

    @Autowired
    RedisDao redisDao;

    @Test
    public void test(){
        Jedis jedis =new Jedis("127.0.0.1",6379);
        jedis.set("name","deyi");
        String value=jedis.get("name");
        System.out.printf("value="+value);
        jedis.close();
    }

    @Test
    public void demo(){
        Account account = new Account();
        account.setUserId(1000);
        account.setUserName("username");
        account.setPassWord("password");
        redisDao.pudObject(account);

        Account object=redisDao.getObject(1000L,Account.class);
        System.out.printf("object="+object);

    }
}
