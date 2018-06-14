package com.entor.hrm.controller;

import com.entor.hrm.po.User;
import com.entor.hrm.service.UserService;
import com.entor.hrm.to.CommonMessage;
import com.entor.hrm.util.ExcelUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.PAGE_DEFAULT_SIZE;
import static com.entor.hrm.util.common.HrmConstants.USER_SESSION;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private static Logger LOGGER = LogManager.getLogger();
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


    /**
     * 登录
     *
     * @param user
     * @param model
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(@ModelAttribute User user, Model model, HttpServletRequest request, HttpSession session) {
        try {
        //1.拦截/main的GET请求
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            model.addAttribute(new CommonMessage("请先登录..."));
            return "redirect:index";
        }
        //2.判断当前session是否已经保存用户
        if (session.getAttribute(USER_SESSION) != null) {
            return "redirect:main";
        }

        //3.根据登录名和密码查找用户
        System.out.println("loginName:" + user.getLoginName() + ",password:" + user.getPassword());

            user = userService.findByLoginNameAndPassword(user.getLoginName(), user.getPassword());

        if (user != null) {
            model.addAttribute(user);
            session.setAttribute(USER_SESSION, user);
            return "redirect:main";
        }
        model.addAttribute(new CommonMessage("用户名或密码不匹配!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/login";
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(USER_SESSION);
        return "/login";
    }

    /**
     * 个人中心
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/personel/{id}")
    public String personel(@PathVariable("id") Integer id, Model model) {
        model.addAttribute(userService.getById(id));
        return "/user/hrms_user_center";
    }

    /**
     * 更新
     *
     * @param user
     * @param model
     * @return
     */
    @PostMapping("/personel/edit")
    public String personel(@ModelAttribute User user, Model model) {
        userService.modifyUser(user);
        model.addAttribute("user", userService.getById(user.getId()));
        model.addAttribute(new CommonMessage("修改成功！"));
        return "/user/hrms_user_center";
    }

    /**
     * 测试前端和后台的交互是否成功
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/main")
    public String main(HttpSession session, Model model) {
        model.addAttribute(session.getAttribute(USER_SESSION));
        return "/hrms_main";
    }

    /**
     * 获取用户列表
     *
     * @param user
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/user/list")
    @ResponseBody
    public Object list(@ModelAttribute User user, Integer pageIndex, Integer pageSize) {
        System.out.println("前端请求获取数据");

        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = PAGE_DEFAULT_SIZE;
        }
        return userService.getByPage(user, pageIndex, pageSize);
    }

    /**
     * 删除记录<br>
     * 注解@PathVariable接收url中指定的值
     *
     * @param id
     * @return
     */
    @GetMapping("/user/del/{id}")
    @ResponseBody
    public Object del(@PathVariable("id") Integer id) {
        userService.removeById(id);
        return new CommonMessage("删除成功！");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @GetMapping("/user/batchDel")
    @ResponseBody
    public Object batchDel(@RequestParam("ids[]") Integer[] ids) {
        userService.batchRemoveUser(ids);
        return new CommonMessage("批量删除成功！");
    }

    /**
     * 冻结/解冻
     *
     * @param user
     * @return
     */
    @PostMapping("/user/froze")
    @ResponseBody//返回值转成json
    public Object froze(@ModelAttribute User user) {
        userService.modifyUser(user);
        if (user.getStatus() == 0) {
            return new CommonMessage("冻结成功！");
        }
        return new CommonMessage("解冻成功！");
    }

    /**
     * 查看用户
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/user/look/{id}")
    public String look(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);
        //user.setCreateDate(new java.sql.Date(user.getCreateDate().getTime()));
        //将实体存到model中，用于页面获取user,页面默认取值方式为${user.loginName};
        model.addAttribute(user);
        return "/user/hrms_user_look";
    }

    /**
     * 更新用户<br>
     * 注解@ModelAttribute的作用，将form中的input的数据根据input中name属性和user中属性对应的方式，将input的value填充到user中
     *
     * @param user
     * @param model   装载要返回到页面的数据：并不是用来接收页面的值，而是将后台的值传到页面<br>
     * @param request
     * @return
     */
    @RequestMapping("/user/update")
    public String update(@ModelAttribute User user, Model model, HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            LOGGER.info("用户id -> {}", user.getId());
            model.addAttribute(userService.getById(user.getId()));
            return "/user/hrms_user_update";
        }
        userService.modifyUser(user);
        model.addAttribute(new CommonMessage("修改成功！"));
        return "/user/hrms_user";
    }

    /**
     * 新增用户
     *
     * @param user
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/user/add")
    public String add(@ModelAttribute User user, Model model, HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return "/user/hrms_user_add";
        }
        userService.save(user);
        //将实体存到model中，用于页面获取user,页面默认取值方式为实体首字母小写${commonMessage},取该实体属性使用${commonMessage.message};
        model.addAttribute(new CommonMessage("添加成功！"));
        return "/user/hrms_user";
    }

    /**
     * 导入Excel，
     * @param request
     */
    @RequestMapping("/user/importExcel")
    public String importExcel(HttpServletRequest request, Model model){
        try {
            int adminId = 1;
            //获取上传的文件
            MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
            MultipartFile file = multipart.getFile("file");
            InputStream in = file.getInputStream();
            //数据导入
            userService.batchAddUser(in, file.getOriginalFilename());
            in.close();
            model.addAttribute(new CommonMessage("修改成功！"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/user/hrms_user";
    }

    /**
     * 导出Excel
     *
     * @param ids
     * @param response
     */
    @RequestMapping("/user/export")
    public void export(@RequestParam("ids[]") Integer[] ids, HttpServletResponse response) {
        for (Integer id : ids) {
            System.out.println("UserController - export - id:" + id);
        }
        try {
            //1.准备集合
            List<User> list = userService.getByIds(ids);

            System.out.println("导出记录数：" + list.size());
            //2.准备标题
            Map<String, String> titles = new HashMap<>();
            titles.put("id", "用户ID");
            titles.put("loginName", "登录名");
            titles.put("password", "密码");
            titles.put("status", "状态");
            titles.put("username", "用户名");
            titles.put("createDate", "创建日期");

            //3.准备响应流
            String filename = new String("用户列表.xls".getBytes("UTF-8"), "iso-8859-1");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + filename);
            response.setHeader("Pragma", "No-cache");

            //4.准备sheet名称
            String sheetName = "用户列表";

            //5.调用导出Excel
            ExcelUtil<User> excelUtil = new ExcelUtil<>();
            excelUtil.export(titles, response.getOutputStream(), list, sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/userInfo")
    public Object list(@Param("id") int id) {
        return userService.getUserById(id);
    }
}
