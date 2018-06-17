package me.tang.hsgd.mapper;

import me.tang.hsgd.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import static me.tang.hsgd.util.common.HrmConstants.USER_TABLE;

public interface UserMapper {

    @Select("select * from "+USER_TABLE)
    @Results({
            @Result(column = "login_name",property = "loginName",javaType = java.lang.String.class),
            @Result(column = "create_date",property = "createDate",javaType = java.util.Date.class)
    })
    List<User> selectAll();

    @Select("select * from "+USER_TABLE+" where login_name = #{loginName} and password = #{password}")
    @Results({
            @Result(column = "login_name",property = "loginName",javaType = java.lang.String.class),
            @Result(column = "create_date",property = "createDate",javaType = java.util.Date.class)
    })
    User selectByLoginNameAndPassword(@Param("loginName") String loginName,@Param("password") String password);
}
