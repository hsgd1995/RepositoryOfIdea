package com.entor.hrm.controller;

import com.entor.hrm.po.User;
import com.entor.hrm.service.UserService;
import com.entor.hrm.to.CommonMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.entor.hrm.util.common.HrmConstants.PAGE_DEFAULT_SIZE;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 在接收页面的值时对值进行处理
     * 绑定数据，将格式为yyyy-MM-dd HH:mm:ss的字符串转成Date类型，使得User中的createDate可以接收数据，
     * 若不转成Date类型，会出现404错误
     * @param webDataBinder
     */
    /*@InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);//设置解析非严格模式
        //该方法将string类型的值转成property对应的类型
        //若实体中的属性是Date类型，则在接收到对应的页面的值时，将String类型的页面的值，转换成Date类型
        webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(sdf,true));//true:允许输入空值，false:不能为空值

    }*/


    //登录方法

    /**
     * 测试前端和后台的交互是否成功
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/main")
    public String main(@ModelAttribute User user, Model model) {
        user.setLoginName("zhangsan");
        user.setUsername("张三");
        model.addAttribute("user", user);
        return "hrms_main";
    }

    /**
     * 获取用户列表
     * @param user
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/user/list")
    @ResponseBody
    public Object list(@ModelAttribute User user, Integer pageIndex, Integer pageSize) {
        System.out.println("前端请求获取数据");

        if (pageIndex == null){
            pageIndex = 1;
        }
        if (pageSize == null){
            pageSize = PAGE_DEFAULT_SIZE;
        }
        return userService.getByPage(user, pageIndex, pageSize);
    }

    /**
     * 删除记录<br>
     * 注解@PathVariable接收url中指定的值
     * @param id
     * @return
     */
    @GetMapping("/user/del/{id}")
    @ResponseBody
    public Object del(@PathVariable("id") Integer id){
        userService.removeById(id);
        return new CommonMessage("删除成功！");
    }

    @PostMapping("/user/froze")
    @ResponseBody//返回值转成json
    public Object froze(@ModelAttribute User user){
        userService.modifyUser(user);
        if (user.getStatus() == 0){
            return new CommonMessage("冻结成功！");
        }
        return new CommonMessage("解冻成功！");
    }

    /**
     * 查看用户
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/user/look/{id}")
    public String look(@PathVariable("id") Integer id,Model model){
        User user = userService.getById(id);
        user.setCreateDate(new java.sql.Date(user.getCreateDate().getTime()));
        //将实体存到model中，用于页面获取user,页面默认取值方式为${user.loginName};
        model.addAttribute(user);
        return "/user/hrms_user_look";
    }

    /**
     * 更新用户<br>
     * 注解@ModelAttribute的作用，将form中的input的数据根据input中name属性和user中属性对应的方式，将input的value填充到user中
     * @param user
     * @param model 装载要返回到页面的数据：并不是用来接收页面的值，而是将后台的值传到页面<br>
     * @param request
     * @return
     */
    @RequestMapping("/user/update")
    public String update(@ModelAttribute User user, Model model, HttpServletRequest request){
        if ("GET".equalsIgnoreCase(request.getMethod())){
            model.addAttribute(userService.getById(user.getId()));
            return "/user/hrms_user_update";
        }
        System.out.println("更新的用户："+user);
        userService.modifyUser(user);
        model.addAttribute(new CommonMessage("修改成功！"));
        return "/user/hrms_user";
    }

    @RequestMapping("/user/add")
    public String add(@ModelAttribute User user,Model model,HttpServletRequest request){
        if ("GET".equalsIgnoreCase(request.getMethod())){
            return "/user/hrms_user_add";
        }
        userService.save(user);
        //将实体存到model中，用于页面获取user,页面默认取值方式为实体首字母小写${commonMessage},取该实体属性使用${commonMessage.message};
        model.addAttribute(new CommonMessage("添加成功！"));
        return "/user/hrms_user";
    }



    @GetMapping("/userInfo")
    public Object list(@Param("id") int id){
        return userService.getUserById(id);
    }
}
