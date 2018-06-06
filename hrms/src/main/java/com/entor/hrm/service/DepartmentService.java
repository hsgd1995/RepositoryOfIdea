package com.entor.hrm.service;

import com.entor.hrm.po.Department;
import com.entor.hrm.service.impl.PageModel;

import java.util.List;

/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/5 0005 09:47
 */
public interface DepartmentService {
    /**
     * 分页查询通知
     * @param department
     * @param pageIndex
     * @param pageSize
     * @return PageModel<Notice>
     */
    PageModel<Department> getByPage(Department department, Integer pageIndex, Integer pageSize);

    /**
     * 获取全部部门
     * @return
     */
    List<Department> getAll();

    /**
     * 根据id获取部门信息
     * @param id
     * @return
     */
    Department getById(Integer id);

    /**
     * 修改部门信息
     * @param department
     */
    void modifyDept(Department department);

    /**
     * 删除
     * @param id
     */
    void removeDept(Integer id);

    /**
     * 添加部门
     * @param department
     */
    void addDept(Department department);
}
