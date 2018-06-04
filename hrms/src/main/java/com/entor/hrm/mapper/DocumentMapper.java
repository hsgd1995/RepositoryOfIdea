package com.entor.hrm.mapper;

import com.entor.hrm.mapper.provider.DocumentDynaSQLProvider;
import com.entor.hrm.po.Document;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.DOCUMENT_TABLE;
import static org.apache.ibatis.mapping.FetchType.EAGER;

/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/4 0004 11:09
 */
public interface DocumentMapper {
    /**
     * 动态查询文档分页记录
     *
     * @param params ①检索的条件；②分页参数
     * @return {@link List<Document>}
     */
    @SelectProvider(type = DocumentDynaSQLProvider.class, method = "selectWithParams")
    @Results({
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "user_id", property = "user", javaType = com.entor.hrm.po.User.class,
                    one = @One(select = "com.entor.hrm.mapper.UserMapper.selectById", fetchType = EAGER))

    })
    List<Document> selectByPage(Map<String, Object> params);

    /**
     * 动态查询文档记录总数
     *
     * @param params ①检索的条件
     * @return
     */
    @SelectProvider(type = DocumentDynaSQLProvider.class, method = "count")
    int count(Map<String, Object> params);

    @Select("select * from " + DOCUMENT_TABLE +" where id = #{id}")
    @Results({
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "user_id", property = "user", javaType = com.entor.hrm.po.User.class,
                    one = @One(select = "com.entor.hrm.mapper.UserMapper.selectById", fetchType = EAGER))
    })
    Document selectById(Integer id);
    /**
     * 动态插入文档
     *
     * @param document
     */
    @InsertProvider(type = DocumentDynaSQLProvider.class, method = "insertDocument")
    void save(Document document);

    @DeleteProvider(type = DocumentDynaSQLProvider.class, method = "batchDelete")
    void batchDel(Map<String, Object> params);

    /**
     * 根据id查询多条记录
     * @param params
     * @return {@link List<Document>}
     */
    @SelectProvider(type = DocumentDynaSQLProvider.class,method = "selectByIds")
    List<Document> selectByIds(Map<String, Object> params);
}
