package me.tang.hsgd.service.impl;

import me.tang.hsgd.mapper.UserMapper;
import me.tang.hsgd.po.User;
import me.tang.hsgd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

import static me.tang.hsgd.util.common.HrmConstants.USER_SESSION;
import static org.springframework.transaction.annotation.Isolation.DEFAULT;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service("userService")
@Transactional(propagation = REQUIRED,isolation = DEFAULT,readOnly = false)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUser(){
        return userMapper.selectAll();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean login(String loginName,String password, HttpSession session) {
        boolean flag = false;
        User user = userMapper.selectByLoginNameAndPassword(loginName,password);
        if(user!=null){
            flag = true;
            session.setAttribute(USER_SESSION,user);
        }
        return flag;
    }
}
