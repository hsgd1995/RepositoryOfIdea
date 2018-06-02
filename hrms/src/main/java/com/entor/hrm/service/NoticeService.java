package com.entor.hrm.service;

import com.entor.hrm.po.Notice;
import com.entor.hrm.service.impl.PageModel;

/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/1 0001 16:29
 */
public interface NoticeService {
    /**
     * 分页查询通知
     * @param notice
     * @param pageIndex
     * @param pageSize
     * @return PageModel<Notice>
     */
    PageModel<Notice> getByPage(Notice notice, Integer pageIndex, Integer pageSize);
}
