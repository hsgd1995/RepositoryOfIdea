package com.entor.hrm.service;

import com.entor.hrm.po.Employee;
import com.entor.hrm.service.impl.PageModel;

/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/5 0005 17:14
 */
public interface EmployeeService {
    /**
     * 分页查询
     * @param employee
     * @param pageIndex
     * @param pageSize
     * @return PageModel<Notice>
     */
    PageModel<Employee> getByPage(Employee employee, Integer pageIndex, Integer pageSize);

    /**
     * 删除员工
     * @param id
     */
    void removeEmp(Integer id);
}
