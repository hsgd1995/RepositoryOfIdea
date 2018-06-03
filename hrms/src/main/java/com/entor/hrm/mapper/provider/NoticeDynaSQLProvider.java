package com.entor.hrm.mapper.provider;

import com.entor.hrm.po.Notice;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.NOTICE_TABLE;

/**
 * @Titel:
 * @Description:
 * @Auther: Administrator
 * @Date: 2018/6/1 0001 16:42
 */
public class NoticeDynaSQLProvider {

    /**
     * 分页动态查询通知
     *
     * @param params
     * @return
     */
    public String selectWithParams(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("*");
            FROM(NOTICE_TABLE);
            if (params.get("notice") != null) {
                Notice notice = (Notice) params.get("notice");
                if (!StringUtils.isEmpty(notice.getTitle())) {
                    WHERE(" title like concat ('%',#{notice.title},'%')");
                }
                if (!StringUtils.isEmpty(notice.getContent())) {
                    WHERE(" content like concat ('%',#{notice.content},'%')");
                }
                if (notice.getCreateDate() != null) {
                    WHERE(" date_format(create_date,'%Y-%m-%d') = date_format(#{notice.createDate},'%Y-%m-%d')");
                }
            }
        }}.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
        }
        return sql;
    }

    /**
     * 动态查询通知总记录数
     *
     * @param params
     * @return
     */
    public String count(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("count(*)");
            FROM(NOTICE_TABLE);
            if (params.get("notice") != null) {
                Notice notice = (Notice) params.get("notice");
                if (!StringUtils.isEmpty(notice.getTitle())) {
                    WHERE(" title like concat ('%',#{notice.title},'%') ");
                }
                if (!StringUtils.isEmpty(notice.getContent())) {
                    WHERE(" content like concat ('%',#{notice.content},'%') ");
                }
                if (notice.getCreateDate() != null) {
                    WHERE("date_format(create_date, '%Y-%m-%d') = date_format(#{notice.createDate}, '%Y-%m-%d') ");
                }
            }
        }}.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    /**
     * 动态更新通知
     *
     * @param notice
     * @return
     */
    public String updateNotice(Notice notice) {
        return new SQL() {{
            UPDATE(NOTICE_TABLE);
            if (!StringUtils.isEmpty(notice.getTitle())) {
                SET("title = #{title}");
            }
            if (!StringUtils.isEmpty(notice.getContent())) {
                SET("content = #{content}");
            }
            if (notice.getUser() != null && notice.getUser().getId() != null) {
                SET("user_id = #{user.id}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }

    /**
     * 动态插入数据
     *
     * @param notice
     * @return
     */
    public String insertNotice(Notice notice) {
        return new SQL() {{
            INSERT_INTO(NOTICE_TABLE);
            if (!StringUtils.isEmpty(notice.getTitle())) {
                VALUES("title", "#{title}");
            }
            if (!StringUtils.isEmpty(notice.getContent())) {
                VALUES("content", "#{content}");
            }
            if (notice.getUser() != null && notice.getUser().getId() != null) {
                VALUES("user_id", "#{user.id}");
            }
        }}.toString();
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public String batchDel(Map<String,Object> ids) {
        StringBuffer sb = new StringBuffer("delete from ");
        sb.append(NOTICE_TABLE).append(" where id in (");
        Integer[] noticeIds = (Integer[]) ids.get("ids");
        if (noticeIds.length > 0) {
            for (Integer id : noticeIds) {
                sb.append(id).append(",");
            }
        } else {
            sb.append("null");
        }
        sb.append(")");
        System.out.println(sb.toString().replace(",)", ")"));
        return sb.toString().replace(",)", ")");
    }
}
