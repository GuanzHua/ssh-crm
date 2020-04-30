package cn.guanzh.dao.impl;

import cn.guanzh.dao.VisitDao;
import cn.guanzh.domain.Visit;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class VisitDaoImpl extends HibernateDaoSupport implements VisitDao {

    /**
     * 添加客户拜访
     * @param visit
     */
    @Override
    public void addVisit(Visit visit) {
        this.getHibernateTemplate().save(visit);
    }

    /**
     * 查询总记录数
     * @return
     */
    @Override
    public int findCount() {
        List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Visit ");
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
     * 查询客户拜访的pageBeanList
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Visit> finPageBeanList(int start, int pageSize) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Visit.class);
        List<Visit> list = (List<Visit>) this.getHibernateTemplate().findByCriteria(criteria, start, pageSize);
        return list;
    }

    /**
     * 根据vid查询客户拜访记录
     * @param vid
     * @return
     */
    @Override
    public Visit findByVid(int vid) {
        return this.getHibernateTemplate().get(Visit.class,vid);
    }

    /**
     * 修改客户拜访记录
     * @param visit
     */
    @Override
    public void updateVisit(Visit visit) {
        this.getHibernateTemplate().update(visit);
    }

    /**
     * 删除客户拜访记录
     * @param visit
     */
    @Override
    public void deleteVisit(Visit visit) {
        this.getHibernateTemplate().delete(visit);
    }

    /**
     * 多条件组合查询
     * @param visit
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Visit> findMoreCondition(Visit visit, int start, int pageSize) {
        //创建离线对象
        DetachedCriteria criteria = DetachedCriteria.forClass(Visit.class);
        if(visit.getUser().getUid() != null && visit.getUser().getUid() > 0){
            criteria.add(Restrictions.eq("user.uid",visit.getUser().getUid()));
        }
        if(visit.getCustomer().getCid() != null && visit.getCustomer().getCid()>0){
            criteria.add(Restrictions.eq("customer.cid",visit.getCustomer().getCid()));
        }
        List<Visit> list = (List<Visit>) this.getHibernateTemplate().findByCriteria(criteria, start, pageSize);
        return list;
    }
}
