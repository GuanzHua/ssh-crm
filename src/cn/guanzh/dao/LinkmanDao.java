package cn.guanzh.dao;

import cn.guanzh.domain.Linkman;

import java.util.List;

public interface LinkmanDao {
    /**
     * 添加联系人
     * @param linkman
     */
    void addLinkman(Linkman linkman);

    /**
     * 查询总记录数
     * @return
     */
    int findCount();

    /**
     * 查询联系人的pageBeanList
     * @param start
     * @param pageSize
     * @return
     */
    List<Linkman> finPageBeanList(int start, int pageSize);

    /**
     * 根据id查询联系人
     * @param linkid
     * @return
     */
    Linkman findByLinkid(int linkid);

    /**
     * 修改联系人
     * @param linkman
     */
    void updateLinkman(Linkman linkman);

    /**
     * 删除联系人
     * @param linkman
     */
    void deleteLinkman(Linkman linkman);

    /**
     * 多条件组合查询
     * @param linkman
     * @param start
     * @param pageSize
     * @return
     */
    List<Linkman> findMoreCondition(Linkman linkman, int start, int pageSize);
}
