package com.entor.hrm.controller;

import com.entor.hrm.po.Notice;
import com.entor.hrm.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.entor.hrm.util.common.HrmConstants.PAGE_DEFAULT_SIZE;

/**
 * @Titel:通知
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/1 0001 16:28
 */
@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/notice/list")
    @ResponseBody
    public Object list(@ModelAttribute Notice notice,Integer pageIndex,Integer pageSize){
        if (pageIndex == null){
            pageIndex = 1;
        }
        if (pageSize == null){
            pageSize = PAGE_DEFAULT_SIZE;
        }
        return noticeService.getByPage(notice,pageIndex,pageSize);
    }
}
