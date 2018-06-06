package com.entor.hrm.service;

import com.entor.hrm.po.Employee;
import com.entor.hrm.service.impl.PageModel;

import java.util.List;

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

    /**
     * 添加员工
     * @param employee
     */
    void addEmp(Employee employee);

    /**
     * 根据id获取实体
     * @param id
     * @return
     */
    Employee getById(Integer id);

    /**
     * 修改员工
     * @param employee
     */
    void modifyEmp(Employee employee);

    /**
     * 批量删除
     * @param ids
     */
    void batchRemoveUser(Integer[] ids);

    /**
     * 根据id获取多条记录
     * @param ids
     * @return
     */
    List<Employee> getByIds(Integer[] ids);
}
