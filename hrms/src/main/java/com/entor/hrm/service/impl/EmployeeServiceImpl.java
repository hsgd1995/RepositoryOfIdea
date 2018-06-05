package com.entor.hrm.service.impl;

import com.entor.hrm.mapper.EmployeeMapper;
import com.entor.hrm.po.Employee;
import com.entor.hrm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.EMPLOYEE_MAP;

/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/5 0005 17:14
 */
@Service("employeeService")
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Transactional(readOnly = true)
    @Override
    public PageModel<Employee> getByPage(Employee employee, Integer pageIndex, Integer pageSize) {
        PageModel<Employee> pageModel = new PageModel<>();
        try {

            //1.整理参数
            Map<String, Object> params = new HashMap<>();
            params.put("employee", employee);
            //根据检索条件查询记录总数
            int recordCount = employeeMapper.count(params);
            System.out.println("总记录数:" + recordCount);
            //整理分页参数
            pageModel.setPageIndex(pageIndex);
            pageModel.setPageSize(pageSize);
            pageModel.setRecordCount(recordCount);
            if (recordCount > 0) {
                //根据检索和分页条件查询用户记录，保存到分页对象中
                params.put("pageModel", pageModel);
                System.out.println("员工列表：" + employeeMapper.selectByPage(params));
                pageModel.setPageList(employeeMapper.selectByPage(params));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageModel;
    }

    @Override
    public void removeEmp(Integer id) {
        employeeMapper.deleteById(id);
    }
}
