package cn.guanzh.dao;

import cn.guanzh.domain.Visit;

import java.util.List;

public interface VisitDao {
    /**
     * 添加客户拜访
     * @param visit
     */
    void addVisit(Visit visit);

    /**
     * 查询总记录数
     * @return
     */
    int findCount();

    /**
     * 查询客户拜访的pageBeanList
     * @param start
     * @param pageSize
     * @return
     */
    List<Visit> finPageBeanList(int start, int pageSize);

    /**
     * 根据vid查询客户拜访记录
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
     * @param visit
     * @param start
     * @param pageSize
     * @return
     */
    List<Visit> findMoreCondition(Visit visit, int start, int pageSize);
}
