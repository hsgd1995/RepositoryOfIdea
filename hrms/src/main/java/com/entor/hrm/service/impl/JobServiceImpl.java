package com.entor.hrm.service.impl;

import com.entor.hrm.mapper.JobMapper;
import com.entor.hrm.po.Job;
import com.entor.hrm.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.JOB_MAP;

/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/5 0005 15:27
 */
@Service("jobService")
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class JobServiceImpl implements JobService{

    @Autowired
    private JobMapper jobMapper;
    
    @Transactional(readOnly = true)
    @Override
    public PageModel<Job> getByPage(Job job, Integer pageIndex, Integer pageSize){
        //1.整理参数
        Map<String,Object> params = new HashMap<>();
        params.put(JOB_MAP,job);
        //根据检索条件查询记录总数
        int recordCount = jobMapper.count(params);
        System.out.println("总记录数:"+recordCount);
        //整理分页参数
        PageModel<Job> pageModel = new PageModel<>();
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(pageSize);
        pageModel.setRecordCount(recordCount);
        if (recordCount>0){
            //根据检索和分页条件查询用户记录，保存到分页对象中
            params.put("pageModel",pageModel);
            pageModel.setPageList(jobMapper.selectByPage(params));
        }
        return pageModel;
    }

    @Transactional(readOnly = true)
    @Override
    public Job getById(Integer id) {
        return jobMapper.selectById(id);
    }

    @Override
    public void modifyJob(Job job) {
        jobMapper.update(job);
    }

    @Override
    public void removeJob(Integer id) {
        jobMapper.delete(id);
    }

    @Override
    public void addJob(Job job) {
        jobMapper.save(job);
    }
}

