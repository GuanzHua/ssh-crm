package cn.guanzh.service;

import cn.guanzh.domain.Linkman;
import cn.guanzh.domain.PageBean;

public interface LinkmanService {
    /**
     * 添加联系人
     * @param linkman
     */
    void addLinkman(Linkman linkman);

    /**
     * 查询所有联系人
     * @return
     * @param currentPage
     */
    PageBean listLinkman(Integer currentPage);

    /**
     * 根据id查询
     * @param linkid
     * @return
     */
    Linkman findByLinkid(int linkid);

    /**
     * 修改联系人
     * @param linkman
     */
    void updataLinkman(Linkman linkman);

    /**
     * 删除联系人
     * @param linkman
     */
    void deleteLinkman(Linkman linkman);

    /**
     * 多条件组合查询
     * @param currentPage
     * @param linkman
     * @return
     */
    PageBean<Linkman> findMoreCondition(Integer currentPage, Linkman linkman);
}
