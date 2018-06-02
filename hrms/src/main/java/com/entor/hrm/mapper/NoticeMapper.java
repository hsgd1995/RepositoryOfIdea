package com.entor.hrm.mapper;

import com.entor.hrm.mapper.provider.NoticeDynaSQLProvider;
import com.entor.hrm.po.Notice;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

/**
 * @Titel:通知Mapper
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/1 0001 16:30
 */
public interface NoticeMapper {

    /**
     * 动态查询通知
     * @param params
     * @return
     */
    @SelectProvider(type = NoticeDynaSQLProvider.class,method = "selectWithParams")
    @Results({
            @Result(column = "create_date",property = "createDate",javaType = java.util.Date.class),
            @Result(column = "user_id",property = "user",javaType = com.entor.hrm.po.User.class,
            one = @One(select = "com.entor.hrm.mapper.UserMapper.selectById",fetchType = FetchType.EAGER))
    })
    List<Notice> selectByPage(Map<String ,Object> params);
    /**
     * 动态查询通知总记录数
     *
     * @param params
     * @returnu
     */
    @SelectProvider(type = NoticeDynaSQLProvider.class, method = "count")
    Integer count(Map<String, Object> params);
}
