package cn.guanzh.dao.impl;

import cn.guanzh.dao.CustomerDao;
import cn.guanzh.domain.Customer;
import cn.guanzh.domain.Dict;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.HashMap;
import java.util.List;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

    /**
     * 添加客户
     * @param customer
     */
    @Override
    public void add(Customer customer) {
        this.getHibernateTemplate().save(customer);
    }

    /**
     * 查询所有的方法
     * @return
     */
    @Override
    public List<Customer> findAll() {
        return (List<Customer>) this.getHibernateTemplate().find("from Customer ");
    }

    /**
     * 根据cid查询
     * @param cid
     * @return
     */
    @Override
    public Customer findByCid(int cid) {
        return this.getHibernateTemplate().get(Customer.class,cid);
    }

    /**
     * 删除的方法
     * @param customer
     */
    @Override
    public void delete(Customer customer) {
        this.getHibernateTemplate().delete(customer);
    }

    /**
     * 修改的方法
     * @param customer
     */
    @Override
    public void update(Customer customer) {
        this.getHibernateTemplate().update(customer);
    }

    /**
     * 查询总记录数
     * @return
     */
    @Override
    public int findCount() {
        List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");
        //从list中把值得到
        if(list != null && list.size() != 0){
            Object o = list.get(0);
            //变成int
            Long lo = (Long) o;
            int count = lo.intValue();
            return count;
        }
        return 0;
    }

    /**
     * 查询每页显示记录的List
     * @return
     */
    @Override
    public List<Customer> finPageBeanList(int start, int pageSize) {
        //使用离线对象和hibernateTemplate中的方法实现
        //创建离线对象，设置对哪个实体类进行操作
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);

        List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, start, pageSize);
        return list;
    }

    /**
     * 条件查询-根据客户名称
     * @param customer
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Customer> findCondition(Customer customer, int start, int pageSize) {
/*        List<Customer> list = (List<Customer>) this.getHibernateTemplate().find("from Customer where custName like ?", "%" + customer.getCustName() + "%");
        return list;*/

        //使用离线查询
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
        criteria.add(Restrictions.like("custName","%" + customer.getCustName() + "%"));
        List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria,start,pageSize);
        return list;
    }

    /**
     * 多条件组合查询
     *
     * @param customer
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Customer> findMoreCondition(Customer customer, int start, int pageSize) {
        //创建离线查询对象
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);

        if(customer.getCustName() != null && !"".equals(customer.getCustName())){
            criteria.add(Restrictions.like("custName","%" + customer.getCustName() + "%"));
        }

        if(customer.getDictCustLevel().getDid() != null && customer.getDictCustLevel().getDid()>0){
            criteria.add(Restrictions.eq("dictCustLevel.did",customer.getDictCustLevel().getDid()));
        }

        if(customer.getCustSource() != null && !"".equals(customer.getCustSource())){
            criteria.add(Restrictions.like("custSource","%" + customer.getCustSource() + "%"));
        }
        List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, start, pageSize);
        return list;
    }

    /**
     * 查询客户级别
     * @return
     */
    @Override
    public List<Dict> findDictLevel() {
        return (List<Dict>) this.getHibernateTemplate().find("from Dict ");
    }

    /**
     * 根据客户来源进行查询
     * @return
     */
    @Override
    public List findCountSource() {
        //因为需要复杂条件查询，故使用底层sql实现，SQLQuery
        //得到session
        Session session = this.getSessionFactory().getCurrentSession();
        //得到sqlQuery
        SQLQuery sqlQuery = session.createSQLQuery("select count(*) as num,custSource from t_customer group by custSource");

        //把返回值转换为map
        sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));

        //调用方法得到结果
        List list = sqlQuery.list();

        return list;
    }

    @Override
    public List findCountLevel() {
        Session session = this.getSessionFactory().getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select c.num, d.dname from (select count(*) as num, custLevel from t_customer group by custLevel) c,t_dict d where c.custLevel = d.did");
        sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
        List list = sqlQuery.list();
        return list;
    }


}
