package com.entor.hrm.service;

import com.entor.hrm.po.Notice;
import com.entor.hrm.service.impl.PageModel;

import java.util.List;

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

    /**
     * 查看通知
     * @param id
     * @return
     */
    Notice getById(Integer id);

    /**
     * 查询最新通知
     * @return
     */
    Notice getNewest();

    /**
     * 获取近期通知
     * @return
     */
    List<Notice> getRecent();

    /**
     * 删除通知
     * @param id
     */
    void removeById(Integer id);

    /**
     * 更新通知
     * @param notice
     */
    void update(Notice notice);

    /**
     * 新增通知
     *
     * @param notice
     */
    void addNotice(Notice notice);

    /**
     * 批量删除
     * @param ids
     */
    void batchDelete(Integer[] ids);
}
