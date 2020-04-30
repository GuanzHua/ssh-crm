package cn.guanzh.service;

import cn.guanzh.domain.Customer;
import cn.guanzh.domain.Dict;
import cn.guanzh.domain.PageBean;

import java.util.List;

public interface CustomerService {

    /**
     * 添加客户的方法
     * @param customer
     */
    void add(Customer customer);

    /**
     *查询所有
     * @return
     */
    List<Customer> findAll();

    /**
     * 根据cid查询
     * @param cid
     * @return
     */
    Customer findByCid(int cid);

    /**
     * 删除的方法
     * @param customer
     */
    void delete(Customer customer);

    /**
     * 修改的方法
     * @param customer
     */
    void update(Customer customer);

    /**
     * 封装分页数据到pageBean对象里
     * @param currentPage
     * @return
     */
    PageBean listPage(Integer currentPage);

    /**
     * 条件查询
     *
     * @param currentPage
     * @param customer
     * @return
     */
    PageBean findCondition(Integer currentPage, Customer customer);

    /**
     * 多条件组合查询
     *
     * @param currentPage
     * @param customer
     * @return
     */
    PageBean<Customer> findMoreCondition(Integer currentPage, Customer customer);

    /**
     * 查询客户级别
     * @return
     */
    List<Dict> findDictLevel();

    /**
     * 根据客户来源进行统计
     * @return
     */
    List findCountSource();

    /**
     * 根据客户级别进行查询
     * @return
     */
    List findCountLevel();

}
