package com.entor.hrm.mapper;

import com.entor.hrm.mapper.provider.NoticeDynaSQLProvider;
import com.entor.hrm.po.Notice;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.NOTICE_TABLE;
import static org.apache.ibatis.mapping.FetchType.EAGER;

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
            one = @One(select = "com.entor.hrm.mapper.UserMapper.selectById",fetchType = EAGER))
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

    @Select("select * from "+NOTICE_TABLE +" where id = #{id}")
    @Results({
            @Result(column = "create_date",property = "createDate",javaType = java.util.Date.class),
            @Result(column = "user_id",property = "user",javaType = com.entor.hrm.po.User.class,
            one = @One(select = "com.entor.hrm.mapper.UserMapper.selectById",fetchType = EAGER))
    })
    Notice selectById(Integer id);

    /**
     * 获取最新通知
     * @return
     */
    @Select("select * from " + NOTICE_TABLE + " order by create_date desc limit 0,1")
    @Results({
            @Result(column = "create_date",property = "createDate",javaType = java.util.Date.class),
            @Result(column = "user_id",property = "user",javaType = com.entor.hrm.po.User.class,
                    one = @One(select = "com.entor.hrm.mapper.UserMapper.selectById",fetchType = EAGER))
    })
    Notice selectNewest();

    /**
     * 获取近期通知(近期最多五条记录)
     * @return
     */
    @Results({
            @Result(column = "create_date",property = "createDate",javaType = java.util.Date.class),
            @Result(column = "user_id",property = "user",javaType = com.entor.hrm.po.User.class,
                    one = @One(select = "com.entor.hrm.mapper.UserMapper.selectById",fetchType = EAGER))
    })
    @Select("select * from "+NOTICE_TABLE+" order by create_date desc limit 0,5")
    List<Notice> selectRecent();

    /**
     * 删除通知
     * @param id
     */
    @Delete("delete from "+ NOTICE_TABLE +" where id = #{id}")
    void delNotice(Integer id);

    /**
     * 动态更新通知
     * @param notice
     */
    @UpdateProvider(type = NoticeDynaSQLProvider.class,method = "updateNotice")
    void update(Notice notice);

    @InsertProvider(type = NoticeDynaSQLProvider.class,method = "insertNotice")
    void insert(Notice notice);

    @DeleteProvider(type = NoticeDynaSQLProvider.class,method = "batchDel")
    void batchDel(Map<String,Object> map);
}
