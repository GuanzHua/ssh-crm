package cn.guanzh.dao.impl;

import cn.guanzh.dao.LinkmanDao;
import cn.guanzh.domain.Linkman;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class LinkmanDaoImpl extends HibernateDaoSupport implements LinkmanDao {


    /**
     * 添加联系人
     * @param linkman
     */
    @Override
    public void addLinkman(Linkman linkman) {
        this.getHibernateTemplate().save(linkman);
    }

    /**
     * 查询总记录数
     * @return
     */
    @Override
    public int findCount() {
        List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Linkman ");
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
     * 查询联系人的pageBeanList
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Linkman> finPageBeanList(int start, int pageSize) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
        List<Linkman> list = (List<Linkman>) this.getHibernateTemplate().findByCriteria(criteria, start, pageSize);
        return list;
    }

    /**
     * 根据id查询联系人
     * @param linkid
     * @return
     */
    @Override
    public Linkman findByLinkid(int linkid) {
        return this.getHibernateTemplate().get(Linkman.class,linkid);
    }

    /**
     * 修改联系人
     * @param linkman
     */
    @Override
    public void updateLinkman(Linkman linkman) {
        this.getHibernateTemplate().update(linkman);
    }

    /**
     * 删除联系人
     * @param linkman
     */
    @Override
    public void deleteLinkman(Linkman linkman) {
        this.getHibernateTemplate().delete(linkman);
    }

    @Override
    public List<Linkman> findMoreCondition(Linkman linkman, int start, int pageSize) {
        //创建离线对象
        DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
        if(linkman.getLkmName() != null && !"".equals(linkman.getLkmName())){
            criteria.add(Restrictions.like("lkmName","%" + linkman.getLkmName() + "%"));
        }
        if(linkman.getCustomer().getCid() != null && linkman.getCustomer().getCid()>0){
            criteria.add(Restrictions.eq("customer.cid",linkman.getCustomer().getCid()));
        }
        List<Linkman> list = (List<Linkman>) this.getHibernateTemplate().findByCriteria(criteria, start, pageSize);
        return list;
    }
}
