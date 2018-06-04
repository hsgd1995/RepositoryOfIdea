package com.entor.hrm.service;

import com.entor.hrm.po.Document;
import com.entor.hrm.po.User;
import com.entor.hrm.service.impl.PageModel;

/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/4 0004 11:08
 */
public interface DocumentService {
    /**
     * 根据参数获得用户分页信息
     * @param document
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageModel<Document> getByPage(Document document, Integer pageIndex, Integer pageSize);

    /**
     * 根据id获取文档信息
     * @param id
     * @return
     */
    Document getById(Integer id);

    /**
     * 保存
     * @param document
     */
    void save(Document document);

    /**
     * 批量删除
     * @param ids
     */
    void batchRemoveDocument(Integer[] ids);
}
