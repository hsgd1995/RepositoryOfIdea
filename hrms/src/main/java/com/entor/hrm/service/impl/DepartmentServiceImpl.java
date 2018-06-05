package com.entor.hrm.service.impl;

import com.entor.hrm.mapper.DepartmentMapper;
import com.entor.hrm.po.Department;
import com.entor.hrm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.DEPARTMENT_MAP;

/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/5 0005 09:48
 */
@Service("departmentService")
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Transactional(readOnly = true)
    @Override
    public PageModel<Department> getByPage(Department department, Integer pageIndex, Integer pageSize){
        //1.整理参数
        Map<String,Object> params = new HashMap<>();
        params.put(DEPARTMENT_MAP,department);
        //根据检索条件查询记录总数
        int recordCount = departmentMapper.count(params);
        System.out.println("总记录数:"+recordCount);
        //整理分页参数
        PageModel<Department> pageModel = new PageModel<>();
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(pageSize);
        pageModel.setRecordCount(recordCount);
        if (recordCount>0){
            //根据检索和分页条件查询用户记录，保存到分页对象中
            params.put("pageModel",pageModel);
            pageModel.setPageList(departmentMapper.selectByPage(params));
        }
        return pageModel;
    }

    @Transactional(readOnly = true)
    @Override
    public Department getById(Integer id) {
        return departmentMapper.selectById(id);
    }

    @Override
    public void modifyDept(Department department) {
        departmentMapper.update(department);
    }

    @Override
    public void removeDept(Integer id) {
        departmentMapper.delete(id);
    }

    @Override
    public void addDept(Department department) {
        departmentMapper.save(department);
    }
}
