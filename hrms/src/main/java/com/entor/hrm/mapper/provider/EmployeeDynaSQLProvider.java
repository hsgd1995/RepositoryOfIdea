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

            if (params.get("employee")!=null){
                Employee employee = (Employee) params.get("employee");
                if (!StringUtils.isEmpty(employee.getName())){
                    WHERE("name like concat ('%',#{employee.name},'%')");
                }
                if (employee.getJob()!=null && employee.getJob().getId()!=null){
                    WHERE("job_id = #{employee.job.id}");
                }
                if (employee.getDepartment()!=null && employee.getDepartment().getId()!=null){
                    WHERE("dept_id = #{employee.department.id}");
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
            FROM(EMPLOYEE_TABLE);
            if (params.get("employee")!=null){
                Employee employee = (Employee) params.get("employee");
                if (!StringUtils.isEmpty(employee.getName())){
                    WHERE("name like concat ('%',#{employee.name},'%')");
                }
                if (employee.getJob()!=null && employee.getJob().getId()!=null){
                    WHERE("job_id = #{employee.job.id}");
                }
                if (employee.getDepartment()!=null && employee.getDepartment().getId()!=null){
                    WHERE("dept_id = #{employee.department.id}");
                }
            }
        }}.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }


    /**
     * 插入记录
     * @param employee
     * @return
     */
    public String insert(Employee employee){
        return new SQL(){{
            INSERT_INTO(EMPLOYEE_TABLE);
            if (employee.getDepartment()!=null&&employee.getDepartment().getId()!=null){
                VALUES("dept_id","#{department.id}");
            }
            if (employee.getJob()!=null&&employee.getJob().getId()!=null){
                VALUES("job_id","#{job.id}");
            }
            if (!StringUtils.isEmpty(employee.getName())){
                VALUES("name","#{name}");
            }
            if (employee.getAge()!=null){
                VALUES("age","#{age}");
            }
            if (!StringUtils.isEmpty(employee.getCardId())){
                VALUES("card_id","#{cardId}");
            }
            if (!StringUtils.isEmpty(employee.getAddress())){
                VALUES("address","#{address}");
            }
            if (!StringUtils.isEmpty(employee.getPostCode())){
                VALUES("post_code","#{postCode}");
            }
            if (!StringUtils.isEmpty(employee.getTel())){
                VALUES("tel","#{tel}");
            }
            if (!StringUtils.isEmpty(employee.getPhone())){
                VALUES("phone","#{phone}");
            }
            if (!StringUtils.isEmpty(employee.getQqNum())){
                VALUES("qq_num","#{qqNum}");
            }
            if (!StringUtils.isEmpty(employee.getEmail())){
                VALUES("email","#{email}");
            }
            if (employee.getSex()!=null){
                VALUES("sex","sex");
            }
            if (!StringUtils.isEmpty(employee.getParty())){
                VALUES("party","#{party}");
            }
            if (!StringUtils.isEmpty(employee.getBirthday())){
                VALUES("birthday","#{birthday}");
            }
            if (!StringUtils.isEmpty(employee.getRace())){
                VALUES("race","#{race}");
            }
            if (!StringUtils.isEmpty(employee.getEducation())){
                VALUES("education","#{education}");
            }
            if (!StringUtils.isEmpty(employee.getSpeciality())){
                VALUES("speciality","#{speciality}");
            }
            if (!StringUtils.isEmpty(employee.getHobby())){
                VALUES("hobby","#{hobby}");
            }
            if (!StringUtils.isEmpty(employee.getRemark())){
                VALUES("remark","#{remark}");
            }

        }}.toString();
    }

    public String update(Employee employee){
        return new SQL(){{
            UPDATE(EMPLOYEE_TABLE);
            if (employee.getDepartment()!=null&&employee.getDepartment().getId()!=null){
                SET("dept_id = #{department.id}");
            }
            if (employee.getJob()!=null&&employee.getJob().getId()!=null){
                SET("job_id = #{job.id}");
            }
            if (!StringUtils.isEmpty(employee.getName())){
                SET("name = #{name}");
            }
            if (employee.getAge()!=null){
                SET("age = #{age}");
            }
            if (!StringUtils.isEmpty(employee.getAddress())){
                SET("address = #{address}");
            }
            if (!StringUtils.isEmpty(employee.getPostCode())){
                SET("post_code = #{postCode}");
            }
            if (!StringUtils.isEmpty(employee.getTel())){
                SET("tel = #{tel}");
            }
            if (!StringUtils.isEmpty(employee.getPhone())){
                SET("phone = #{phone}");
            }
            if (!StringUtils.isEmpty(employee.getQqNum())){
                SET("qq_num = #{qqNum}");
            }
            if (!StringUtils.isEmpty(employee.getEmail())){
                SET("email = #{email}");
            }
            if (employee.getSex()!=null){
                SET("sex = sex");
            }
            if (!StringUtils.isEmpty(employee.getParty())){
                SET("party = #{party}");
            }
            if (!StringUtils.isEmpty(employee.getBirthday())){
                SET("birthday = #{birthday}");
            }
            if (!StringUtils.isEmpty(employee.getRace())){
                SET("race = #{race}");
            }
            if (!StringUtils.isEmpty(employee.getEducation())){
                SET("education = #{education}");
            }
            if (!StringUtils.isEmpty(employee.getSpeciality())){
                SET("speciality = #{speciality}");
            }
            if (!StringUtils.isEmpty(employee.getHobby())){
                SET("hobby = #{hobby}");
            }
            if (!StringUtils.isEmpty(employee.getRemark())){
                SET("remark = #{remark}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }

    /**
     * 批量删除
     * @return
     */
    public String batchDel(Map<String ,Object> params){
        StringBuffer stringBuffer = new StringBuffer("delete from ");
        stringBuffer.append(EMPLOYEE_TABLE).append(" where id in (");
        if (params.get("ids")!=null){
            Integer[] ids = (Integer[]) params.get("ids");
            if(ids.length>0){
                for (Integer id:ids){
                    stringBuffer.append(id).append(",");
                }
            }else {
                stringBuffer.append("null");
            }

        }else {
            stringBuffer.append("null");
        }
        stringBuffer.append(")");
        return stringBuffer.toString().replace(",)",")");
    }

    public String selectByids(Map<String ,Object> params){
        StringBuffer stringBuffer = new StringBuffer("select * from ");
        stringBuffer.append(EMPLOYEE_TABLE).append(" where id in (");
        if (params.get("ids")!=null){
            Integer[] ids = (Integer[]) params.get("ids");
            if(ids.length>0){
                for (Integer id:ids){
                    stringBuffer.append(id).append(",");
                }
            }else {
                stringBuffer.append("null");
            }

        }else {
            stringBuffer.append("null");
        }
        stringBuffer.append(")");
        return stringBuffer.toString().replace(",)",")");
    }
}
