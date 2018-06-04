package com.entor.hrm.controller;

import com.entor.hrm.po.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HrmContoller {

    /**
     * 登录页面
     * @return
     */
    @GetMapping("/index")
    public String index() {
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

    @GetMapping("/document")
    public String document() {
        return "/document/hrms_document";
    }
}
