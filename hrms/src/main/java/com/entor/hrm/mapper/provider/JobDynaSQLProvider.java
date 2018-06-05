package com.entor.hrm.mapper.provider;

import com.entor.hrm.po.Job;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.JOB_MAP;
import static com.entor.hrm.util.common.HrmConstants.JOB_TABLE;


/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/5 0005 15:11
 */
public class JobDynaSQLProvider {
    /**
     * 分页动态查询通知
     *
     * @param params
     * @return
     */
    public String selectWithParams(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("*");
            FROM(JOB_TABLE);
            if (params.get(JOB_MAP) != null) {
                Job job = (Job) params.get(JOB_MAP);
                if (!StringUtils.isEmpty(job.getName())) {
                    WHERE(" name like concat ('%',#{job.name},'%')");
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
            FROM(JOB_TABLE);
            if (params.get(JOB_MAP) != null) {
                Job job = (Job) params.get(JOB_MAP);
                if (!StringUtils.isEmpty(job.getName())) {
                    WHERE(" name like concat ('%',#{job.name},'%')");
                }
            }
        }}.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    /**
     * 动态更新职位
     * @param job
     * @return
     */
    public String update(Job job){
        return new SQL(){{
            UPDATE(JOB_TABLE);
            if (job.getName()!=null){
                SET(" name = #{name} ");
            }
            if (job.getRemark()!=null){
                SET(" remark = #{remark} ");
            }
            WHERE("id = #{id} ");
        }}.toString();
    }

    /**
     * 动态保存职位
     * @param job
     * @return
     */
    public String save(Job job){
        return new SQL(){{
            INSERT_INTO(JOB_TABLE);
            if (!StringUtils.isEmpty(job.getName())){
                VALUES("name","#{name}");
            }
            if (!StringUtils.isEmpty(job.getRemark())){
                VALUES("remark","#{remark}");
            }
        }}.toString();
    }
}
