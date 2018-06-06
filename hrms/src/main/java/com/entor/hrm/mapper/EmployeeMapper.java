package com.entor.hrm.mapper;

import com.entor.hrm.mapper.provider.EmployeeDynaSQLProvider;
import com.entor.hrm.po.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.EMPLOYEE_TABLE;
import static org.apache.ibatis.mapping.FetchType.EAGER;
import static org.apache.ibatis.mapping.FetchType.LAZY;

/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/5 0005 16:26
 */
public interface EmployeeMapper {
    /**
     * 动态查询通知
     * @param params
     * @return
     */
    @SelectProvider(type = EmployeeDynaSQLProvider.class,method = "selectWithParams")
    @Results({
            @Result(column = "dept_id",property = "department",javaType = com.entor.hrm.po.Department.class,
                    one = @One(select = "com.entor.hrm.mapper.DepartmentMapper.selectById",fetchType = EAGER)),
            @Result(column = "job_id",property = "job",javaType = com.entor.hrm.po.Job.class,
                    one = @One(select = "com.entor.hrm.mapper.JobMapper.selectById",fetchType = EAGER)),
            @Result(column = "card_id",property = "cardId",javaType = java.lang.String.class),
            @Result(column = "post_code",property = "postCode",javaType = java.lang.String.class),
            @Result(column = "qq_num",property = "qqNum",javaType = java.lang.String.class),
            @Result(column = "create_date",property = "createDate",javaType = java.util.Date.class)
    })
    List<Employee> selectByPage(Map<String ,Object> params);
    /**
     * 动态查询通知总记录数
     *
     * @param params
     * @returnu
     */
    @SelectProvider(type = EmployeeDynaSQLProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    /**
     * 删除
     * @param id
     */
    @Delete("delete from "+EMPLOYEE_TABLE+" where id = #{id}")
    void deleteById(Integer id);

    /**
     * 保存
     * @param employee
     */
    @InsertProvider(type = EmployeeDynaSQLProvider.class,method = "insert")
    void save(Employee employee);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    @Select("select * from "+EMPLOYEE_TABLE+" where id = #{id}")
    @Results({
            @Result(column = "dept_id",property = "department",javaType = com.entor.hrm.po.Department.class,
                    one = @One(select = "com.entor.hrm.mapper.DepartmentMapper.selectById",fetchType = EAGER)),
            @Result(column = "job_id",property = "job",javaType = com.entor.hrm.po.Job.class,
                    one = @One(select = "com.entor.hrm.mapper.JobMapper.selectById",fetchType = EAGER)),
            @Result(column = "card_id",property = "cardId",javaType = java.lang.String.class),
            @Result(column = "post_code",property = "postCode",javaType = java.lang.String.class),
            @Result(column = "qq_num",property = "qqNum",javaType = java.lang.String.class),
            @Result(column = "create_date",property = "createDate",javaType = java.util.Date.class)
    })
    Employee selectById(Integer id);

    /**
     * 更新员工
     * @param employee
     */
    @UpdateProvider(type = EmployeeDynaSQLProvider.class,method = "update")
    void update(Employee employee);

    @DeleteProvider(type = EmployeeDynaSQLProvider.class,method = "batchDel")
    void batchDel(Map<String, Object> params);

    /**
     * 根据id获取多条记录
     * @param params
     * @return
     */
    @SelectProvider(type = EmployeeDynaSQLProvider.class,method = "selectByids")
    @Results({
            @Result(column = "dept_id",property = "department",javaType = com.entor.hrm.po.Department.class,
                    one = @One(select = "com.entor.hrm.mapper.DepartmentMapper.selectById",fetchType = EAGER)),
            @Result(column = "job_id",property = "job",javaType = com.entor.hrm.po.Job.class,
                    one = @One(select = "com.entor.hrm.mapper.JobMapper.selectById",fetchType = EAGER)),
            @Result(column = "card_id",property = "cardId",javaType = java.lang.String.class),
            @Result(column = "post_code",property = "postCode",javaType = java.lang.String.class),
            @Result(column = "qq_num",property = "qqNum",javaType = java.lang.String.class),
            @Result(column = "create_date",property = "createDate",javaType = java.util.Date.class)
    })
    List<Employee> selectByIds(Map<String, Object> params);
}
