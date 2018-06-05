package com.entor.hrm.mapper.provider;

import com.entor.hrm.po.Department;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.*;

/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/5 0005 09:56
 */
public class DepartmentDynaSQLProvider {

    /**
     * 分页动态查询通知
     *
     * @param params
     * @return
     */
    public String selectWithParams(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("*");
            FROM(DEPT_TABLE);
            if (params.get(DEPARTMENT_MAP) != null) {
                Department department = (Department) params.get(DEPARTMENT_MAP);
                if (!StringUtils.isEmpty(department.getName())) {
                    WHERE(" name like concat ('%',#{department.name},'%')");
                }
            }
        }}.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
        }
        return sql;
    }

    /**
     * 动态查询通知总记录数
     *
     * @param params
     * @return
     */
    public String count(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("count(*)");
            FROM(DEPT_TABLE);
            if (params.get(DEPARTMENT_MAP) != null) {
                Department department = (Department) params.get(DEPARTMENT_MAP);
                if (!StringUtils.isEmpty(department.getName())) {
                    WHERE(" name like concat ('%',#{department.name},'%')");
                }
            }
        }}.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    /**
     * 动态更新部门
     * @param department
     * @return
     */
    public String update(Department department){
        return new SQL(){{
            UPDATE(DEPT_TABLE);
            if (department.getName()!=null){
                SET(" name = #{name} ");
            }
            if (department.getRemark()!=null){
                SET(" remark = #{remark} ");
            }
            WHERE("id = #{id} ");
        }}.toString();
    }

    /**
     * 动态保存部门
     * @param department
     * @return
     */
    public String save(Department department){
        return new SQL(){{
            INSERT_INTO(DEPT_TABLE);
            if (!StringUtils.isEmpty(department.getName())){
                VALUES("name","#{name}");
            }
            if (!StringUtils.isEmpty(department.getRemark())){
                VALUES("remark","#{remark}");
            }
        }}.toString();
    }
}
