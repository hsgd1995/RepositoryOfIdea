package com.entor.hrm.service.impl;

import java.util.List;

import static com.entor.hrm.util.common.HrmConstants.PAGE_DEFAULT_SIZE;

public class PageModel<T> {
    /**
     * 分页总记录数
     */
    private int recordCount;

    /**
     * 当前页码
     */
    private int pageIndex;

    /**
     * 分页查询第一个限制参数
     */
    private int firstLimitParam;

    /**
     * 每页记录数
     */
    private int pageSize = PAGE_DEFAULT_SIZE;

    /**
     * 分页记录详情
     */
    private List<T> pageList;

    public int getRecordCount() {
        this.recordCount = this.recordCount >= 0 ? this.recordCount : 0;
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageIndex() {
        this.pageIndex = this.pageIndex >= 1 ? this.pageIndex : 1;
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        this.pageSize = this.pageSize >= 1 ? this.pageSize : PAGE_DEFAULT_SIZE;
        return pageSize;
    }

    public int getFirstLimitParam() {
        this.firstLimitParam = (getPageIndex() - 1) * getPageSize();
        this.firstLimitParam = this.firstLimitParam >= 0 ? this.firstLimitParam : 0;
        return firstLimitParam;
    }

    public void setFirstLimitParam(int firstLimitParam) {
        this.firstLimitParam = firstLimitParam;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }
}
