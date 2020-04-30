package cn.guanzh.dao;

import cn.guanzh.domain.Customer;
import cn.guanzh.domain.Dict;

import java.util.List;

public interface CustomerDao {

    /**
     * 添加客户
     * @param customer
     */
    void add(Customer customer);

    /**
     * 查询所有
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
     * 查询总记录数
     * @return
     */
    int findCount();

    /**
     * 查询pageBeanList
     * @return
     */
    List<Customer> finPageBeanList(int start, int pageSize);

    /**
     * 条件查询
     * @param customer
     * @param start
     * @param pageSize
     * @return
     */
    List<Customer> findCondition(Customer customer, int start, int pageSize);

    /**
     * 多条件组合查询
     *
     * @param customer
     * @param start
     * @param pageSize
     * @return
     */
    List<Customer> findMoreCondition(Customer customer, int start, int pageSize);

    /**
     * 查询客户级别
     * @return
     */
    List<Dict> findDictLevel();

    /**
     * 根据客户来源进行查询
     * @return
     */
    List findCountSource();

    /**
     * 根据客户级别进行查询
     * @return
     */
    List findCountLevel();
}
