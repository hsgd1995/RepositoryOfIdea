package com.entor.hrm.controller;

import com.entor.hrm.po.Department;
import com.entor.hrm.po.Employee;
import com.entor.hrm.po.Job;
import com.entor.hrm.service.DepartmentService;
import com.entor.hrm.service.EmployeeService;
import com.entor.hrm.service.JobService;
import com.entor.hrm.to.CommonMessage;
import com.entor.hrm.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private JobService jobService;

    @GetMapping("/emp/list")
    @ResponseBody
    public Object list(@ModelAttribute Employee employee,Integer pageIndex,Integer pageSize,Integer jobId,Integer deptId){
        if (pageIndex == null){
            pageIndex = 1;
        }
        if (pageSize == null){
            pageSize = PAGE_DEFAULT_SIZE;
        }
        if(!StringUtils.isEmpty(jobId) && !"undefined".equalsIgnoreCase(jobId.toString())){
            Job job = new Job();
            job.setId(jobId);
            employee.setJob(job);
        }
        if (!StringUtils.isEmpty(deptId) && !"undefined".equalsIgnoreCase(deptId.toString())){
            Department department = new Department();
            department.setId(deptId);
            employee.setDepartment(department);
        }
        return employeeService.getByPage(employee,pageIndex,pageSize);
    }

    /**
     * 删除
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/emp/del")
    @ResponseBody
    public Object del(@RequestParam("id") Integer id,Model model){
        employeeService.removeEmp(id);
        return new CommonMessage("删除成功！");
    }

    @GetMapping("/emp/batchDel")
    @ResponseBody
    public Object batchDel(@RequestParam("ids[]") Integer[] ids,Model model){
        employeeService.batchRemoveUser(ids);
        return new CommonMessage("批量删除成功！");
    }

    /**
     * 添加
     * @param employee
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/emp/add")
    public String add(@ModelAttribute Employee employee, Model model, HttpServletRequest request){
        if ("GET".equalsIgnoreCase(request.getMethod())){



            return "/emp/hrms_emp_add";
        }

        employeeService.addEmp(employee);
        model.addAttribute("添加成功");
        return "/emp/hrms_emp";
    }

    /**
     * 查看
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/emp/look/{id}")
    public String look(@PathVariable("id") Integer id, Model model){
        model.addAttribute(employeeService.getById(id));
        return "/emp/hrms_emp_look";
    }

    /**
     * 更新
     * @param employee
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/emp/update")
    public String update(@ModelAttribute Employee employee,HttpServletRequest request,Model model){
        if ("GET".equalsIgnoreCase(request.getMethod())){
            model.addAttribute(employeeService.getById(employee.getId()));
            return "/emp/hrms_emp_update";
        }

        employeeService.modifyEmp(employee);
        model.addAttribute("修改成功！");
        return "/emp/hrms_emp";
    }

    /**
     * 导出Excel
     * @param ids
     */
    @RequestMapping("/emp/export")
    public void export(@RequestParam("ids[]") Integer[] ids, HttpServletResponse response){
        try {
//1.准备集合
            List<Employee> list = employeeService.getByIds(ids);
            //2.准备标题
            Map<String,String> titles = new HashMap<>();
            titles.put("id","员工ID");
            titles.put("name","姓名");
            titles.put("department","部门");
            titles.put("job","职位");
            titles.put("age","年龄");
            titles.put("cardId","工号");
            titles.put("address","地址");
            titles.put("postCode","邮编");
            titles.put("tel","电话号码");
            titles.put("phone","手机号码");
            titles.put("qqNum","qq号码");
            titles.put("email","电子邮箱");
            titles.put("sex","性别");
            titles.put("party","政治面貌");
            titles.put("birthday","出生日期");
            titles.put("race","名族");
            titles.put("education","学历");
            titles.put("speciality","特长");
            titles.put("hobby","爱好");
            titles.put("remark","备注");
            titles.put("createDate","创建日期");

            //3.响应流
            String filename = new String("员工列表.xls".getBytes("UTF-8"),"iso-8859-1");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition","attachment;filename="+filename);
            response.setHeader("Pragma","No-cache");

            //4.准备sheet名称
            String sheetName = "员工列表";

            //5.调用导出Excel
            ExcelUtil<Employee> excelUtil = new ExcelUtil<>();
            excelUtil.export(titles,response.getOutputStream(),list,sheetName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
