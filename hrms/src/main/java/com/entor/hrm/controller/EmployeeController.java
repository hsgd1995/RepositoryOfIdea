package com.entor.hrm.controller;

import com.entor.hrm.po.Employee;
import com.entor.hrm.service.EmployeeService;
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
 * @Date: 2018/6/5 0005 17:25
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/emp/list")
    @ResponseBody
    public Object list(@ModelAttribute Employee employee,Integer pageIndex,Integer pageSize){
        if (pageIndex == null){
            pageIndex = 1;
        }
        if (pageSize == null){
            pageSize = PAGE_DEFAULT_SIZE;
        }
        return employeeService.getByPage(employee,pageIndex,pageSize);
    }

    @GetMapping("/emp/del")
    public String del(@RequestParam("id") Integer id,Model model){
        employeeService.removeEmp(id);
        model.addAttribute("删除成功！");
        return "/emp/hrms_emp";
    }

    @RequestMapping("/emp/add")
    public String add(@ModelAttribute Employee employee, Model model, HttpServletRequest request){
        if ("GET".equalsIgnoreCase(request.getMethod())){
            return "/emp/hrms_emp_add";
        }
        return "/emp/hrms_emp_add";
    }
}
