package cn.guanzh.service.impl;

import cn.guanzh.dao.LinkmanDao;
import cn.guanzh.domain.Linkman;
import cn.guanzh.domain.PageBean;
import cn.guanzh.service.LinkmanService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class LinkmanServiceImpl implements LinkmanService {

    private LinkmanDao linkmanDao;
    public void setLinkmanDao(LinkmanDao linkmanDao) {
        this.linkmanDao = linkmanDao;
    }

    /**
     * 添加联系人
     * @param linkman
     */
    @Override
    public void addLinkman(Linkman linkman) {
        linkmanDao.addLinkman(linkman);
    }

    /**
     * 查询所有联系人
     * @param currentPage
     * @return
     */
    @Override
    public PageBean listLinkman(Integer currentPage) {
        PageBean<Linkman> pageBean = new PageBean<Linkman>();
        //当前页
        pageBean.setCurrentPage(currentPage);
        //总记录数
        int totalCount = linkmanDao.findCount();
        pageBean.setTotalCount(totalCount);
        //每页显示记录数
        int pageSize = 10;
        //总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pageBean.setTotalPage(totalPage);

        int start = (currentPage - 1) * pageSize;     //数据库查询开始的记录数
        List<Linkman> list = linkmanDao.finPageBeanList(start,pageSize);
        pageBean.setList(list);

        return pageBean;
    }

    /**
     * 根据id查询
     * @param linkid
     * @return
     */
    @Override
    public Linkman findByLinkid(int linkid) {
        return linkmanDao.findByLinkid(linkid);
    }

    /**
     * 修改联系人
     * @param linkman
     */
    @Override
    public void updataLinkman(Linkman linkman) {
        linkmanDao.updateLinkman(linkman);
    }

    /**
     * 删除联系人
     * @param linkman
     */
    @Override
    public void deleteLinkman(Linkman linkman) {
        linkmanDao.deleteLinkman(linkman);
    }

    /**
     * 多条件组合查询
     * @param currentPage
     * @param linkman
     * @return
     */
    @Override
    public PageBean<Linkman> findMoreCondition(Integer currentPage, Linkman linkman) {
        PageBean<Linkman> pageBean = new PageBean<Linkman>();
        //当前页
        pageBean.setCurrentPage(currentPage);

        //每页显示记录数
        int pageSize = 10;
        int start = (currentPage - 1) * pageSize;     //数据库查询开始的记录数

        List<Linkman> list = linkmanDao.findMoreCondition(linkman,start,pageSize);
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
