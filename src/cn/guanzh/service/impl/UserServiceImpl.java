package cn.guanzh.service.impl;

import cn.guanzh.dao.UserDao;
import cn.guanzh.domain.User;
import cn.guanzh.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user);
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
