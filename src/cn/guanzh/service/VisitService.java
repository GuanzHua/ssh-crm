package cn.guanzh.service;

import cn.guanzh.domain.PageBean;
import cn.guanzh.domain.Visit;

public interface VisitService {
    /**
     * 添加拜访
     * @param visit
     */
    void addVisit(Visit visit);

    /**
     * 客户拜访列表方法
     * @param currentPage
     * @return
     */
    PageBean listVisit(Integer currentPage);

    /**
     * 根据id查询客户拜访记录
     * @param vid
     * @return
     */
    Visit findByVid(int vid);

    /**
     * 修改客户拜访记录
     * @param visit
     */
    void updateVisit(Visit visit);

    /**
     * 删除客户拜访记录
     * @param visit
     */
    void deleteVisit(Visit visit);

    /**
     * 多条件组合查询
     * @param currentPage
     * @param visit
     * @return
     */
    PageBean<Visit> findMoreCondition(Integer currentPage, Visit visit);
}
