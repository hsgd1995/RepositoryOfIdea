package com.entor.hrm.controller;

import com.entor.hrm.po.Department;
import com.entor.hrm.service.DepartmentService;
import com.entor.hrm.to.CommonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.entor.hrm.util.common.HrmConstants.PAGE_DEFAULT_SIZE;

/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/5 0005 09:46
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    /**
     * 获取列表
     * @param department
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/dept/list")
    @ResponseBody
    public Object list(@ModelAttribute Department department, Integer pageIndex, Integer pageSize) {
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = PAGE_DEFAULT_SIZE;
        }
        return departmentService.getByPage(department, pageIndex, pageSize);
    }

    @RequestMapping("/dept/add")
    public String add(@ModelAttribute Department department,HttpServletRequest request,Model model){
        if ("GET".equalsIgnoreCase(request.getMethod())){
            return "/dept/hrms_dept_add";
        }
        departmentService.addDept(department);
        model.addAttribute("添加成功！");
        return "/dept/hrms_dept";
    }

    /**
     * 查看部门信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/dept/look/{id}")
    public String look(@PathVariable("id") Integer id, Model model){
        model.addAttribute(departmentService.getById(id));
        return "/dept/hrms_dept_look";
    }

    /**
     * 更新
     * @param department
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/dept/update")
    public String update(@ModelAttribute Department department, HttpServletRequest request,Model model){
        if ("GET".equalsIgnoreCase(request.getMethod())){
            model.addAttribute(departmentService.getById(department.getId()));
            return "/dept/hrms_dept_update";
        }
        departmentService.modifyDept(department);
        model.addAttribute(new CommonMessage("修改成功！"));
        return "/dept/hrms_dept";
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @GetMapping("/dept/del")
    public String del(@RequestParam("id") Integer id,Model model){
        departmentService.removeDept(id);
        model.addAttribute(new CommonMessage("删除成功！"));
        return "/dept/hrms_dept";
    }
}
