package me.tang.hsgd;

import me.tang.hsgd.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"/WEB-INF/configs/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testGetAllUser(){
        System.out.println(userMapper.selectAll());
    }
}
