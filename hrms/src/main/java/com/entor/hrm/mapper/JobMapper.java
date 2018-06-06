package com.entor.hrm.mapper;

import com.entor.hrm.mapper.provider.JobDynaSQLProvider;
import com.entor.hrm.po.Job;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.JOB_TABLE;


/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/5 0005 15:10
 */
public interface JobMapper {
    /**
     * 动态查询通知
     * @param params
     * @return
     */
    @SelectProvider(type = JobDynaSQLProvider.class,method = "selectWithParams")
    List<Job> selectByPage(Map<String ,Object> params);
    /**
     * 动态查询通知总记录数
     *
     * @param params
     * @returnu
     */
    @SelectProvider(type = JobDynaSQLProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    /**
     * 根据id查询部门信息
     * @param id
     * @return
     */
    @Select("select * from "+JOB_TABLE+" where id = #{id}")
    Job selectById(Integer id);

    /**
     * 更新部门信息
     * @param job
     */
    @UpdateProvider(type = JobDynaSQLProvider.class,method = "update")
    void update(Job job);

    @Delete("delete from "+JOB_TABLE+" where id = #{id}")
    void delete(Integer id);

    @InsertProvider(type = JobDynaSQLProvider.class,method = "save")
    void save(Job job);

    @Select("select * from "+JOB_TABLE+" where 1=1")
    List<Job> selectAll();
}
