package me.tang.hsgd.controller;

import me.tang.hsgd.to.CommonMessage;
import me.tang.hsgd.po.User;
import me.tang.hsgd.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static me.tang.hsgd.util.common.HrmConstants.USER_SESSION;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LogManager.getLogger();

    @GetMapping("/user/test1")
    public String test1(Model model){
        System.out.println("进入测试方法1");
        model.addAttribute(userService.getAllUser().get(0));
        return "/test1";
    }

    @GetMapping("/user/test2")
    public String test2(Model model){
        System.out.println("进入测试方法2");
        model.addAttribute(userService.getAllUser().get(0));
        return "/test";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, HttpSession session){
        //判断当前session是否已经保存用户
        if (session.getAttribute(USER_SESSION) != null) {
            return "redirect:main";
        }
        LOGGER.info("用户：->{}",user);
        boolean flag = userService.login(user.getLoginName(),user.getPassword(),session);
        if(flag){
            model.addAttribute(session.getAttribute(USER_SESSION));
            return "redirect:main";
        }else {
            model.addAttribute(new CommonMessage("用户名或密码错误！"));
            return "/login";
        }
    }

    /**
     * 主页
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/main")
    public String main(HttpSession session, Model model) {
        model.addAttribute(session.getAttribute(USER_SESSION));
        return "/main";
    }
}
