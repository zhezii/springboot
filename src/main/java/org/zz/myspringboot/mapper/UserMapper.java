package org.zz.myspringboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
}
