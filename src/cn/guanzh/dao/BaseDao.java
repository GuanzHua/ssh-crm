package cn.guanzh.dao;

import java.util.List;

public interface BaseDao<T> {

    //添加
    void add(T t);

    //修改
    void update(T t);

    //删除
    //修改
    void delete(T t);

    //根据id查询
    T findById(int id);

    //查询所有
    List<T> findAll();
}
