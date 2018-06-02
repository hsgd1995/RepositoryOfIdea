package com.entor.hrm.controller;

import com.entor.hrm.po.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HrmContoller {

    @GetMapping("/index")
    public String index(Model model) {
        User user = new User();
        user.setLoginName("zhangsan");
        user.setUsername("张三");
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/user")
    public String user() {
        return "/user/hrms_user";
    }

    @GetMapping("/dept")
    public String dept() {
        return "/dept/hrms_dept";
    }

    @GetMapping("/job")
    public String job() {
        return "/job/hrms_job";
    }

    @GetMapping("/emp")
    public String emp() {
        return "/emp/hrms_emp";
    }

    @GetMapping("/notice")
    public String notice() {
        return "/notice/hrms_notice";
    }
}
