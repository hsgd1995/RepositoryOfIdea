package com.entor.hrm.mapper;

import com.entor.hrm.mapper.provider.UserDynaSQLProvider;
import com.entor.hrm.po.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.USER_TABLE;

public interface UserMapper {

    /**
     * 根据id查询用户
     *
     * @param id
     * @return {@link User}
     */
    @Select("select * from " + USER_TABLE + " where id = #{id}")
    @Results({
            @Result(column = "login_name", property = "loginName", javaType = java.lang.String.class),
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class)
    })
    User selectById(Integer id);

    /**
     * 查询所有记录
     *
     * @return
     */
    @Select("select * from " + USER_TABLE)
    @Results({
            @Result(column = "login_name", property = "loginName", javaType = java.lang.String.class)
    })
    List<User> selectAll();

    /**
     * 动态查询用户分页记录
     *
     * @param params ①检索的条件；②分页参数
     * @return {@link List<User>}
     */
    @SelectProvider(type = UserDynaSQLProvider.class, method = "selectWithParams")
    @Results({
            @Result(column = "login_name", property = "loginName", javaType = java.lang.String.class),
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class)
    })
    List<User> selectByPage(Map<String, Object> params);

    /**
     * 动态查询用户记录总数
     *
     * @param params ①检索的条件
     * @return
     */
    @SelectProvider(type = UserDynaSQLProvider.class, method = "count")
    int count(Map<String, Object> params);

    /**
     * 动态更新用户
     *
     * @param user
     */
    @UpdateProvider(type = UserDynaSQLProvider.class, method = "updateUser")
    void update(User user);

    /**
     * 动态插入用户
     *
     * @param user
     */
    @InsertProvider(type = UserDynaSQLProvider.class, method = "insertUser")
    void insert(User user);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    @Delete("delete from " + USER_TABLE + " where id = #{id}")
    void deleteById(Integer id);

    /**
     * 根据id批量删除用户
     *
     * @param params
     */
    @DeleteProvider(type = UserDynaSQLProvider.class, method = "batchDeleteUser")
    void batchDelete(Map<String, Object> params);
}
