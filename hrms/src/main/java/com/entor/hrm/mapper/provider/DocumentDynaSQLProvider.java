package com.entor.hrm.mapper.provider;

import com.entor.hrm.po.Document;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.DOCUMENT_TABLE;

/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/4 0004 11:10
 */
public class DocumentDynaSQLProvider {

    /**
     * 动态分页查询用户记录
     *
     * @param params
     * @return
     */
    public String selectWithParams(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("*");
            FROM(DOCUMENT_TABLE);

            // 1.处理检索条件
            if (params.get("document") != null) {
                Document document = (Document) params.get("document");
                if (!StringUtils.isEmpty(document.getTitle())) {
                    WHERE("title like concat('%',  #{document.title},'%')");
                }

            }
        }}.toString();

        // 2.处理分页参数
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize}";
        }
        return sql;
    }

    /**
     * 动态分页查询用户总记录数
     *
     * @param params
     * @return
     */
    public String count(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("count(*)");
            FROM(DOCUMENT_TABLE);

            // 1.处理检索条件
            if (params.get("document") != null) {
                Document document = (Document) params.get("document");
                if (!StringUtils.isEmpty(document.getTitle())) {
                    WHERE("title like concat('%',  #{document.title},'%')");
                }

            }
        }}.toString();
        return sql;
    }

    /**
     * 动态插入文档
     * @param document
     * @return
     */
    public String  insertDocument(Document document){
        return new SQL(){{
            INSERT_INTO(DOCUMENT_TABLE);
            if (!StringUtils.isEmpty(document.getTitle())){
                VALUES("title","#{title}");
            }
            if (!StringUtils.isEmpty(document.getFilename())){
                VALUES("filename","#{filename}");
            }
            if (!StringUtils.isEmpty(document.getRemark())){
                VALUES("remark","#{remark}");
            }
            if (document.getUser()!=null&&document.getUser().getId()!=null){
                VALUES("user_id","#{user.id}");
            }
        }}.toString();
    }

    /**
     * 批量删除
     * @param params
     * @return
     */
    public String batchDelete(Map<String,Object> params){
       return CommonDynaSQLProvider.batchDelete(DOCUMENT_TABLE,params);
    }

    public String selectByIds(Map<String,Object> params){
        return CommonDynaSQLProvider.selectByIds(DOCUMENT_TABLE,params);
    }
}
