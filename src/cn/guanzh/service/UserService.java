package cn.guanzh.service;

import cn.guanzh.domain.User;

import java.util.List;

public interface UserService {


    //用户登录方法
    User login(User user);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();
}
