package org.zz.myspringboot.mapper;

import org.apache.ibatis.annotations.*;
import org.zz.myspringboot.entity.User;

import java.util.List;

/**
 * @author Zhou Wenzhe
 * @date 2018/6/27
 */
@Mapper
public interface UserMapper {

    int countByUsername(String username);

    @Select("select * from t_user")
    List<User> findAll();

    //@Update("update t_user set username = #{username} where id = #{id}")
    void update(@Param("id") Long id, @Param("username") String username,@Param("age") Integer age,@Param("password") String password);

    @Insert("insert into t_user values(null,#{username},#{age},#{password})")
    void add(@Param("username") String username, @Param("age") Integer age,@Param("password") String password);

    @Delete("delete from t_user where id = #{id}")
    void delete(Long id);

    @Select("select * from t_user where id = #{id}")
    User findUserById(Integer id);
}
