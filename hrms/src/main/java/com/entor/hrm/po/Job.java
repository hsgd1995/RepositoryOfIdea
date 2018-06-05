package com.entor.hrm.po;

import java.io.Serializable;

/**
 * @Titel:职位
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/5 0005 15:09
 */
public class Job implements Serializable{
    private Integer id;
    private String name;
    private String remark;

    public Job() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
