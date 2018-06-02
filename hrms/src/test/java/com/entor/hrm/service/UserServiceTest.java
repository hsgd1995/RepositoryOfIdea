package com.entor.hrm.service;

import com.entor.hrm.po.User;
import com.entor.hrm.service.impl.PageModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * 用户服务测试类
 */
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testGetAll() {
        System.out.println(userService.getAll());
    }

    @Test
    public void testGetByPage() {
        User user = new User();
        user.setUsername("周");
        user.setStatus(1);
        PageModel<User> pageModel = userService.getByPage(user, 1, 2);
        System.out.println(pageModel.getFirstLimitParam());
        System.out.println(pageModel.getPageSize());
        System.out.println(pageModel.getRecordCount());
        System.out.println(pageModel.getPageList());
    }

    @Test
    public void testModify() {
        User user = new User();
        user.setId(2);
        user.setUsername("zhangsan");
        user.setPassword("222222");
        user.setCreateDate(new Date());

        userService.modifyUser(user);
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setLoginName("wangwu");
        user.setUsername("王五");
        user.setPassword("555555");
        user.setStatus(1);

        userService.save(user);
    }


    @Test
    public void testRemoveById() {
        userService.removeById(4);
    }


    @Test
    public void testBatchRemoveUser() {
//        userService.batchRemoveUser(null);
        userService.batchRemoveUser(new Integer[]{5, 6, 7});
    }

    public static void main(String[] args){
        System.out.println("hello");
    }
}
