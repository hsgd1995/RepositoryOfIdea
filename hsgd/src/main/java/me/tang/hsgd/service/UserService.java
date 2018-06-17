package me.tang.hsgd.service;

import me.tang.hsgd.po.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    /**
     * 获取所有用户
     * @return
     */
    List<User> getAllUser();

    /**
     * 登录
     * @param loginName
     * @param password
     * @return
     */
    boolean login(String loginName, String password, HttpSession session);
}
