package cn.guanzh.service.impl;

import cn.guanzh.dao.VisitDao;
import cn.guanzh.domain.PageBean;
import cn.guanzh.domain.Visit;
import cn.guanzh.service.VisitService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class VisitServiceImpl implements VisitService {

    //注入visitDao
    private VisitDao visitDao;
    public void setVisitDao(VisitDao visitDao) {
        this.visitDao = visitDao;
    }

    /**
     * 添加拜访
     * @param visit
     */
    @Override
    public void addVisit(Visit visit) {
        visitDao.addVisit(visit);
    }

    /**
     * 查询所有客户拜访
     * @param currentPage
     * @return
     */
    @Override
    public PageBean listVisit(Integer currentPage) {
        PageBean<Visit> pageBean = new PageBean<Visit>();
        //当前页
        pageBean.setCurrentPage(currentPage);
        //总记录数
        int totalCount = visitDao.findCount();
        pageBean.setTotalCount(totalCount);
        //每页显示记录数
        int pageSize = 10;
        //总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pageBean.setTotalPage(totalPage);

        int start = (currentPage - 1) * pageSize;     //数据库查询开始的记录数
        List<Visit> list = visitDao.finPageBeanList(start,pageSize);
        pageBean.setList(list);

        return pageBean;
    }

    /**
     * 根据vid查询客户拜访记录
     * @param vid
     * @return
     */
    @Override
    public Visit findByVid(int vid) {
        return visitDao.findByVid(vid);
    }

    /**
     * 修改客户拜访记录
     * @param visit
     */
    @Override
    public void updateVisit(Visit visit) {
        visitDao.updateVisit(visit);
    }

    /**
     * 删除客户拜访记录
     * @param visit
     */
    @Override
    public void deleteVisit(Visit visit) {
        visitDao.deleteVisit(visit);
    }

    /**
     * 多条件组合查询
     * @param currentPage
     * @param visit
     * @return
     */
    @Override
    public PageBean<Visit> findMoreCondition(Integer currentPage, Visit visit) {
        PageBean<Visit> pageBean = new PageBean<Visit>();
        //当前页
        pageBean.setCurrentPage(currentPage);
        //每页显示记录数
        int pageSize = 10;
        int start = (currentPage - 1) * pageSize;     //数据库查询开始的记录数

        List<Visit> list = visitDao.findMoreCondition(visit,start,pageSize);

        //总记录数
        int totalCount = list.size();
        pageBean.setTotalCount(totalCount);
        //总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pageBean.setTotalPage(totalPage);

        pageBean.setList(list);

        return pageBean;
    }
}
