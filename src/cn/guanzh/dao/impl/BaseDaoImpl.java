package cn.guanzh.dao.impl;

import cn.guanzh.dao.BaseDao;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    private Class pClass;

    //构造方法
    public BaseDaoImpl() {
        //得到当前运行类的class
        Class<? extends BaseDaoImpl> aClass = this.getClass();
        //得到当前运行类的父类参数化类型
        Type type = aClass.getGenericSuperclass();
        ParameterizedType ptype = (ParameterizedType) type;

        //得到实际类型的参数
        Type[] types = ptype.getActualTypeArguments();
        //Type接口的实现类是class
        Class tclass = (Class) types[0];
        this.pClass = tclass;
    }

    //添加
    @Override
    public void add(T t) {
        this.getHibernateTemplate().save(t);
    }

    //修改
    @Override
    public void update(T t) {
        this.getHibernateTemplate().update(t);
    }

    //删除
    @Override
    public void delete(T t) {
        this.getHibernateTemplate().delete(t);
    }

    //根据id查询
    @Override
    public T findById(int id) {
        return (T) this.getHibernateTemplate().get(pClass, id);
    }

    //查询所有
    @Override
    public List<T> findAll() {
        return (List<T>) this.getHibernateTemplate().find("from " + pClass.getSimpleName());
    }
}
