package com.entor.hrm.controller;

import com.entor.hrm.po.Document;
import com.entor.hrm.po.User;
import com.entor.hrm.service.DocumentService;
import com.entor.hrm.to.CommonMessage;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;

import static com.entor.hrm.util.common.HrmConstants.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;

/**
 * @Titel:下载中心
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/4 0004 11:07
 */
@Controller
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    /**
     * 获取列表
     *
     * @param document
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/document/list")
    @ResponseBody
    public Object list(@ModelAttribute Document document, Integer pageIndex, Integer pageSize) {
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = PAGE_DEFAULT_SIZE;
        }
        return documentService.getByPage(document, pageIndex, pageSize);

    }

    /**
     * 下载
     *
     * @param id
     * @return
     */
    @GetMapping("/document/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable("id") Integer id) throws Exception {

        //根据id查询到下载的文档信息
        Document document = documentService.getById(id);
        //下载的文件名称，转码
        String filename = new String(document.getFilename().getBytes("UTF-8"), "iso-8859-1");
        //设置响应头信息
        HttpHeaders headers = new HttpHeaders();
        //通知浏览器以attachment方式解析
        headers.setContentDispositionFormData("attachment", filename);
        //设置响应的数据格式：二进制流数据
        headers.setContentType(APPLICATION_OCTET_STREAM);

        //创建下载文件对象
        File file = new File(DOC_UPLOAD_PATH + File.separator + document.getFilename());

        //响应文档
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, CREATED);

    }

    /**
     * 上传
     * @param document
     * @param model
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("/document/upload")
    public String upload(@ModelAttribute Document document, Model model, HttpSession session, HttpServletRequest request){
        if("GET".equalsIgnoreCase(request.getMethod())){
            return "/document/hrms_document_upload";
        }
        try{
            //上传文档名
            String filename = document.getFile().getOriginalFilename();
            //将上传文档保存到一个目标文档中
            document.getFile().transferTo(new File(DOC_UPLOAD_PATH+File.separator+filename));
            //保存文档信息到数据库
            //设置filename
            document.setFilename(filename);
            //设置关联的user对象
            document.setUser((User) session.getAttribute(USER_SESSION));
            //保存
            documentService.save(document);
            model.addAttribute(new CommonMessage("上传成功！"));
            return "/document/hrms_document";
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute(new CommonMessage("上传失败!"));
            return "/document/hrms_document";
        }
    }

    @GetMapping("/document/batchDel")
    @ResponseBody
    public Object batchDel(@RequestParam("ids[]") Integer[] ids){
        for (Integer integer : ids) {
            System.out.println("document - batchDel - id:"+integer);
        }
        documentService.batchRemoveDocument(ids);
        return new CommonMessage("批量删除成功！");
    }
}
