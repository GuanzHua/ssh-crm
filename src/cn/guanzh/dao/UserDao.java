package cn.guanzh.dao;

import cn.guanzh.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 登录的查询方法
     * @param user
     * @return
     */
    User findByUsernameAndPassword(User user);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

}
