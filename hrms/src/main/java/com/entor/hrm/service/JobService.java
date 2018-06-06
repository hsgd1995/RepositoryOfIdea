package com.entor.hrm.service;

import com.entor.hrm.po.Job;
import com.entor.hrm.service.impl.PageModel;

import java.util.List;

/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/5 0005 15:27
 */
public interface JobService {
    /**
     * 分页查询通知
     * @param job
     * @param pageIndex
     * @param pageSize
     * @return PageModel<Notice>
     */
    PageModel<Job> getByPage(Job job, Integer pageIndex, Integer pageSize);


    /**
     * 获取全部职位
     * @return
     */
    List<Job> getAll();


    /**
     * 根据id获取职位信息
     * @param id
     * @return
     */
    Job getById(Integer id);

    /**
     * 修改职位信息
     * @param job
     */
    void modifyJob(Job job);

    /**
     * 删除
     * @param id
     */
    void removeJob(Integer id);

    /**
     * 添加职位
     * @param job
     */
    void addJob(Job job);
}
