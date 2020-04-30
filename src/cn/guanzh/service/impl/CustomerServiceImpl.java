package cn.guanzh.service.impl;

import cn.guanzh.dao.CustomerDao;
import cn.guanzh.domain.Customer;
import cn.guanzh.domain.Dict;
import cn.guanzh.domain.PageBean;
import cn.guanzh.service.CustomerService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CustomerServiceImpl implements CustomerService {


    private CustomerDao customerDao;
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * 添加客户的方法
     * @param customer
     */
    @Override
    public void add(Customer customer) {
        customerDao.add(customer);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    /**
     * 根据cid查询
     * @param cid
     * @return
     */
    @Override
    public Customer findByCid(int cid) {
        return customerDao.findByCid(cid);
    }

    /**
     * 删除的方法
     * @param customer
     */
    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    /**
     * 修改的方法
     * @param customer
     */
    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    /**
     * 封装分页数据到pageBean对象里
     * @param currentPage
     * @return
     */
    @Override
    public PageBean listPage(Integer currentPage) {
        PageBean<Customer> pageBean = new PageBean<Customer>();
        //当前页
        pageBean.setCurrentPage(currentPage);
        //总记录数
        int totalCount = customerDao.findCount();
        pageBean.setTotalCount(totalCount);
        //每页显示记录数
        int pageSize = 10;
        //总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pageBean.setTotalPage(totalPage);

        int start = (currentPage - 1) * pageSize;     //数据库查询开始的记录数
        List<Customer> list = customerDao.finPageBeanList(start,pageSize);
        pageBean.setList(list);

        return pageBean;
    }

    /**
     * 条件查询
     *
     * @param currentPage
     * @param customer
     * @return
     */
    @Override
    public PageBean findCondition(Integer currentPage, Customer customer) {
        PageBean<Customer> pageBean = new PageBean<Customer>();
        //当前页
        pageBean.setCurrentPage(currentPage);

        //每页显示记录数
        int pageSize = 10;
        int start = (currentPage - 1) * pageSize;

        List<Customer> list = customerDao.findCondition(customer,start,pageSize);
        //总记录数
        int totalCount = list.size();
        pageBean.setTotalCount(totalCount);
        //总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pageBean.setTotalPage(totalPage);

        pageBean.setList(list);

        return pageBean;
    }

    /**
     *
     *
     * @param currentPage
     * @param customer
     * @return
     */
    @Override
    public PageBean<Customer> findMoreCondition(Integer currentPage, Customer customer) {
        PageBean<Customer> pageBean = new PageBean<Customer>();
        //当前页
        pageBean.setCurrentPage(currentPage);
        //每页显示记录数
        int pageSize = 10;
        int start = (currentPage - 1) * pageSize;     //数据库查询开始的记录数
        List<Customer> list = customerDao.findMoreCondition(customer,start,pageSize);
        //总记录数
        int totalCount =list.size();
        pageBean.setTotalCount(totalCount);
        //总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pageBean.setTotalPage(totalPage);

        pageBean.setList(list);
        return pageBean;
    }

    /**
     * 查询客户级别
     * @return
     */
    @Override
    public List<Dict> findDictLevel() {
        return customerDao.findDictLevel();
    }

    /**
     * 根据客户来源进行统计
     * @return
     */
    @Override
    public List findCountSource() {
        return customerDao.findCountSource();
    }

    /**
     * 根据客户级别查询
     * @return
     */
    @Override
    public List findCountLevel() {
        return customerDao.findCountLevel();
    }

}
