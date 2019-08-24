package com.yjh.db;

import com.yjh.db.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisDemoApplicationTests {

    @Autowired
    UserService userService;
    @Test
    public void contextLoads() {
        //System.out.println(userService.addUser("2","2",1,1,"pcy","123",0,0));
    }

}
