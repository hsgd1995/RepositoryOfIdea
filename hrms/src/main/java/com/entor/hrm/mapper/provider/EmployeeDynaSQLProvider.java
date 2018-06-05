package com.entor.hrm.mapper.provider;

import com.entor.hrm.po.Employee;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.*;

/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/5 0005 16:49
 */
public class EmployeeDynaSQLProvider {
    /**
     * 分页动态查询通知
     *
     * @param params
     * @return
     */
    public String selectWithParams(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("*");
            FROM(EMPLOYEE_TABLE);

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
            FROM(EMPLOYEE_TABLE);

        }}.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }
}
