package com.entor.hrm.service.impl;

import com.entor.hrm.mapper.NoticeMapper;
import com.entor.hrm.po.Notice;
import com.entor.hrm.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/1 0001 16:29
 */
@Service("noticeService")
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public PageModel<Notice> getByPage(Notice notice,Integer pageIndex,Integer pageSize){
        //1.整理参数
        Map<String,Object> params = new HashMap<>();
        params.put("notice",notice);
        //根据检索条件查询记录总数
        int recordCount = noticeMapper.count(params);
        System.out.println("总记录数:"+recordCount);
        //整理分页参数
        PageModel<Notice> pageModel = new PageModel<>();
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(pageSize);
        pageModel.setRecordCount(recordCount);
        if (recordCount>0){
            //根据检索和分页条件查询用户记录，保存到分页对象中
            params.put("pageModel",pageModel);
            pageModel.setPageList(noticeMapper.selectByPage(params));
        }
        return pageModel;
    }
}
