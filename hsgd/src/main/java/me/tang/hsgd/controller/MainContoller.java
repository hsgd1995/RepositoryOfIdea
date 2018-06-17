package me.tang.hsgd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainContoller {

    /**
     * 登录页面
     *
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "login";
    }
}
