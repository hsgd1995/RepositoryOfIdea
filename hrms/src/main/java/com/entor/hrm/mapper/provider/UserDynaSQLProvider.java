package com.entor.hrm.mapper.provider;

import com.entor.hrm.po.User;
import jdk.nashorn.internal.objects.annotations.Where;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.USER_TABLE;

/**
 * 用户表SQL构建器
 */
public class UserDynaSQLProvider {

    /**
     * 动态分页查询用户记录
     *
     * @param params
     * @return
     */
    public String selectWithParams(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("*");
            FROM(USER_TABLE);

            // 1.处理检索条件
            if (params.get("user") != null) {
                User user = (User) params.get("user");
                if (!StringUtils.isEmpty(user.getUsername())) {
                    WHERE("username like concat('%',  #{user.username},'%')");
                }
                if (user.getStatus() != null) {
                    WHERE("status = #{user.status}");
                }
                if (user.getCreateDate() != null){
                    WHERE("date_format(create_date,'%Y-%m-%d')=date_format(#{user.createDate},'%Y-%m-%d')");
                }
            }
        }}.toString();

        // 2.处理分页参数
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize}";
        }
        return sql;
    }

    /**
     * 动态分页查询用户总记录数
     *
     * @param params
     * @return
     */
    public String count(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("count(*)");
            FROM(USER_TABLE);

            // 1.处理检索条件
            if (params.get("user") != null) {
                User user = (User) params.get("user");
                if (!StringUtils.isEmpty(user.getUsername())) {
                    WHERE("username like concat('%', #{user.username},'%')");
                }
                if (user.getCreateDate() != null) {
                    WHERE("date_format(create_date, '%Y-%m-%d') = date_format(#{user.createDate}, '%Y-%m-%d') ");
                }
                if (user.getStatus() != null) {
                    WHERE("status = #{user.status}");
                }
            }
        }}.toString();
        return sql;
    }

    /**
     * 动态更新用户
     *
     * @param user
     * @return
     */
    public String updateUser(User user) {
        return new SQL() {{
            UPDATE(USER_TABLE);
            if (!StringUtils.isEmpty(user.getPassword())) {
                SET("password = #{password}");
            }
            if (!StringUtils.isEmpty(user.getUsername())) {
                SET("username = #{username}");
            }
            if (user.getStatus() != null) {
                SET("status = #{status}");
            }
            if (user.getCreateDate() != null) {
                SET("create_date = #{createDate}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }

    /**
     * 动态插入用户
     *
     * @param user
     * @return
     */
    public String insertUser(User user) {
        return new SQL() {{
            INSERT_INTO(USER_TABLE);
            if (!StringUtils.isEmpty(user.getLoginName())) {
                VALUES("login_name", "#{loginName}");
            }
            if (!StringUtils.isEmpty(user.getPassword())) {
                VALUES("password", "#{password}");
            }
            if (!StringUtils.isEmpty(user.getUsername())) {
                VALUES("username", "#{username}");
            }
            if (user.getStatus() != null) {
                VALUES("status", "#{status}");
            }
        }}.toString();
    }

    /**
     * 根据id批量删除用户
     *
     * @param params
     * @return
     */
    public String batchDeleteUser(Map<String, Object> params) {
        StringBuffer sqlBuffer = new StringBuffer("delete from ");
        sqlBuffer.append(USER_TABLE).append(" where id in (");
        if (params.get("ids") != null) {
            Integer[] ids = (Integer[]) params.get("ids");
            if (ids.length > 0) {
                for (Integer id : ids) {
                    sqlBuffer.append(id).append(",");
                }
            } else {
                // 数组中不存在id
                sqlBuffer.append("null");
            }
        } else {
            // params中不存在id
            sqlBuffer.append("null");
        }
        sqlBuffer.append(")");
        return sqlBuffer.toString().replace(",)", ")");
    }

    /**
     * 根据多个id获取记录
     * @param params
     * @return
     */
    public String selectByIds(Map<String ,Object> params){
        StringBuffer sb = new StringBuffer("select * from ");
        sb.append(USER_TABLE).append(" where id in (");
        if (params.get("ids")!=null){
            Integer[] ids = (Integer[]) params.get("ids");
            if (ids.length>0){
                for (Integer id:ids){
                    sb.append(id).append(",");
                }
            }else {
                sb.append("null");
            }
        }else {
            sb.append("null");
        }
        sb.append(")");
        return sb.toString().replace(",)",")");
    }

}
