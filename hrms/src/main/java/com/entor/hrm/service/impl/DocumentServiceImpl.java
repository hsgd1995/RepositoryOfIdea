package com.entor.hrm.service.impl;

import com.entor.hrm.mapper.DocumentMapper;
import com.entor.hrm.po.Document;
import com.entor.hrm.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.DOC_UPLOAD_PATH;

/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/4 0004 11:08
 */
@Service("documentService")
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentMapper documentMapper;
    @Override
    public PageModel<Document> getByPage(Document document, Integer pageIndex, Integer pageSize) {
        // 1.整理参数
        Map<String, Object> params = new HashMap<>();
        params.put("document", document);
        // 根据检索条件查询记录总数
        int recordCount = documentMapper.count(params);
        System.out.println("按条件查询的结果记录数："+recordCount);
        // 整理分页参数
        PageModel<Document> pageModel = new PageModel<Document>();
        System.out.println("pageModel ->" + pageModel);
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(pageSize);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            // 根据检索和分页条件查询用户记录，保存到分页对象中
            params.put("pageModel", pageModel);
            pageModel.setPageList(documentMapper.selectByPage(params));
        }
        return pageModel;
    }

    @Override
    public Document getById(Integer id) {
        return documentMapper.selectById(id);
    }

    @Override
    public void save(Document document) {
        documentMapper.save(document);
    }

    @Override
    public void batchRemoveDocument(Integer[] ids) {
        Map<String,Object> params = new HashMap<>();
        params.put("ids",ids);
        //删除服务器上的文件
        for (Document document:documentMapper.selectByIds(params)){
            File file = new File(DOC_UPLOAD_PATH+File.separator+document.getFilename());
            if (file !=null && file.exists()){
                file.delete();
            }
        }
        documentMapper.batchDel(params);
    }
}
