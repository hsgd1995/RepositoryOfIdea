package com.entor.hrm.controller;

import com.entor.hrm.po.Job;
import com.entor.hrm.service.JobService;
import com.entor.hrm.to.CommonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.entor.hrm.util.common.HrmConstants.PAGE_DEFAULT_SIZE;

/**
 * @Titel:职位
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/5 0005 15:34
 */
@Controller
public class JobController {
    @Autowired
    private JobService jobService;
    /**
     * 获取列表
     * @param job
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/job/list")
    @ResponseBody
    public Object list(@ModelAttribute Job job, Integer pageIndex, Integer pageSize) {
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = PAGE_DEFAULT_SIZE;
        }
        return jobService.getByPage(job, pageIndex, pageSize);
    }

    @RequestMapping("/job/add")
    public String add(@ModelAttribute Job job, HttpServletRequest request, Model model){
        if ("GET".equalsIgnoreCase(request.getMethod())){
            return "/job/hrms_job_add";
        }
        jobService.addJob(job);
        model.addAttribute("添加成功！");
        return "/job/hrms_job";
    }

    /**
     * 查看职位信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/job/look/{id}")
    public String look(@PathVariable("id") Integer id, Model model){
        model.addAttribute(jobService.getById(id));
        return "/job/hrms_job_look";
    }

    /**
     * 更新
     * @param job
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/job/update")
    public String update(@ModelAttribute Job job, HttpServletRequest request,Model model){
        if ("GET".equalsIgnoreCase(request.getMethod())){
            model.addAttribute(jobService.getById(job.getId()));
            return "/job/hrms_job_update";
        }
        jobService.modifyJob(job);
        model.addAttribute(new CommonMessage("修改成功！"));
        return "/job/hrms_job";
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("/job/del")
    public String del(@RequestParam("id") Integer id,Model model){
        jobService.removeJob(id);
        model.addAttribute(new CommonMessage("删除成功！"));
        return "/job/hrms_job";
    }
}
