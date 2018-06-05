package com.entor.hrm.mapper;

import com.entor.hrm.mapper.provider.DepartmentDynaSQLProvider;
import com.entor.hrm.po.Department;
import com.entor.hrm.service.DepartmentService;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.DEPT_TABLE;


/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/5 0005 09:48
 */
public interface DepartmentMapper {

    /**
     * 动态查询通知
     * @param params
     * @return
     */
    @SelectProvider(type = DepartmentDynaSQLProvider.class,method = "selectWithParams")
    List<Department> selectByPage(Map<String ,Object> params);
    /**
     * 动态查询通知总记录数
     *
     * @param params
     * @returnu
     */
    @SelectProvider(type = DepartmentDynaSQLProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    /**
     * 根据id查询部门信息
     * @param id
     * @return
     */
    @Select("select * from "+DEPT_TABLE+" where id = #{id}")
    Department selectById(Integer id);

    /**
     * 更新部门信息
     * @param department
     */
    @UpdateProvider(type = DepartmentDynaSQLProvider.class,method = "update")
    void update(Department department);

    @Delete("delete from "+DEPT_TABLE+" where id = #{id}")
    void delete(Integer id);

    @InsertProvider(type = DepartmentDynaSQLProvider.class,method = "save")
    void save(Department department);
}
