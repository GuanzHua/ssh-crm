package cn.guanzh.dao.impl;

import cn.guanzh.dao.UserDao;
import cn.guanzh.domain.User;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
//    private HibernateTemplate hibernateTemplate;
//    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//        this.hibernateTemplate = hibernateTemplate;
//    }

//    //调用方法得到hibernateTemplate
//    private HibernateTemplate hibernateTemplate = this.getHibernateTemplate();

    /**
     * 登录查询
     *
     * @param user
     * @return
     */
    @Override
    public User findByUsernameAndPassword(User user) {
        String sql = "from User where username = ? and password = ?";
        List<User> list = (List<User>) this.getHibernateTemplate().find(sql, user.getUsername(), user.getPassword());

        //如果查询之后，list为空，则需要判断
        if(list != null && list.size() != 0) {
            User u = list.get(0);
            return u;
        }

        return null;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) this.getHibernateTemplate().find("from User ");
    }


}
