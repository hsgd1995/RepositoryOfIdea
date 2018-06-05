package com.entor.hrm.controller;

import com.entor.hrm.po.Notice;
import com.entor.hrm.po.User;
import com.entor.hrm.service.NoticeService;
import com.entor.hrm.to.CommonMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.entor.hrm.util.common.HrmConstants.PAGE_DEFAULT_SIZE;
import static com.entor.hrm.util.common.HrmConstants.USER_SESSION;

/**
 * @Titel:通知
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/1 0001 16:28
 */
@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 最新通知
     *
     * @param model
     * @return
     */
    @GetMapping("/notice/welcome")
    public String welcome(Model model) {
        Notice notice = noticeService.getNewest();
        notice.getUser().setCreateDate(new java.sql.Date(notice.getUser().getCreateDate().getTime()));
        model.addAttribute(notice);
        return "notice/hrms_welcome";
    }

    /**
     * 获取近期通知
     *
     * @return
     */
    @GetMapping("/notice/recent")
    @ResponseBody
    public Object recent() {
        return noticeService.getRecent();
    }

    /**
     * 获取列表
     * @param notice
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/notice/list")
    @ResponseBody
    public Object list(@ModelAttribute Notice notice, Integer pageIndex, Integer pageSize) {
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = PAGE_DEFAULT_SIZE;
        }
        return noticeService.getByPage(notice, pageIndex, pageSize);
    }

    /**
     * 查看通知
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/notice/look/{id}")
    public String look(@PathVariable("id") Integer id, Model model) {
        Notice notice = noticeService.getById(id);
        notice.setCreateDate(new java.sql.Date(notice.getCreateDate().getTime()));
        //将实体存到model中，用于页面获取user,页面默认取值方式为${user.loginName};
        model.addAttribute(notice);
        return "notice/hrms_notice_look";
    }

    /**
     * 新增通知
     * @param notice
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/notice/add")
    public String add(@ModelAttribute Notice notice, Model model, HttpServletRequest request, HttpSession session){
            if ("GET".equalsIgnoreCase(request.getMethod())){
                return "notice/hrms_notice_add";
            }
            notice.setUser((User)session.getAttribute(USER_SESSION));
            noticeService.addNotice(notice);
            model.addAttribute(new CommonMessage("添加成功！"));
            return "notice/hrms_notice";
    }

    /**
     * 更新通知
     * @param notice
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/notice/update")
    public String update(@ModelAttribute Notice notice, Model model, HttpServletRequest request){
        if ("GET".equalsIgnoreCase(request.getMethod())){
            model.addAttribute(noticeService.getById(notice.getId()));
            return "/notice/hrms_notice_update";
        }

        noticeService.update(notice);
        model.addAttribute(new CommonMessage("修改成功"));
        return "/notice/hrms_notice";
    }

    /**
     * 删除通知
     * @param id
     * @return
     */
    @GetMapping("/notice/del/{id}")
    @ResponseBody
    public Object del(@PathVariable("id") Integer id) {

        noticeService.removeById(id);
        return new CommonMessage("删除成功！");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @GetMapping("/notice/batchDel")
    @ResponseBody
    public Object batchDel(@RequestParam("ids[]") Integer ids[]){
        for(Integer id:ids){
            System.out.println(id);
        }
        noticeService.batchDelete(ids);
        return new CommonMessage("删除成功!");
    }
}
