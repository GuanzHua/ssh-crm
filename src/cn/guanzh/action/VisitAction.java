package cn.guanzh.action;

import cn.guanzh.domain.Customer;
import cn.guanzh.domain.PageBean;
import cn.guanzh.domain.User;
import cn.guanzh.domain.Visit;
import cn.guanzh.service.CustomerService;
import cn.guanzh.service.UserService;
import cn.guanzh.service.VisitService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.util.List;

public class VisitAction extends ActionSupport implements ModelDriven<Visit>{

    //模型驱动获取表单对象
    private Visit visit = new Visit();
    @Override
    public Visit getModel() {
        return visit;
    }

    //注入visitService
    private VisitService visitService;
    public void setVisitService(VisitService visitService) {
        this.visitService = visitService;
    }

    //注入customerService
    private CustomerService customerService;
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    //注入userService
    private UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //到新增页面
    public String toAddPage(){
        //查询所有客户
        List<Customer> customerList = customerService.findAll();
        //查询所有用户
        List<User> userList = userService.findAll();
        //放到域对象中
        ServletActionContext.getRequest().setAttribute("customerList",customerList);
        ServletActionContext.getRequest().setAttribute("userList",userList);
        return "toAddPage";
    }

    //添加客户拜访
    public String addVisit(){
        visitService.addVisit(visit);
        return "addVisit";
    }

    //使用属性封装获取currentPage
    private Integer currentPage = 1;
    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    //查询所有客户拜访记录并返回pageBean
    public String list() {
        PageBean pageBean = visitService.listVisit(currentPage);
        ServletActionContext.getRequest().setAttribute("pageBean",pageBean);
        return "list";
    }

    //到修改联系人页面
    public String showVisit(){
        //使用模型驱动得到id值
        int vid = visit.getVid();
        //根据id查询联系人对象
        Visit visit = visitService.findByVid(vid);

        //需要所有客户拜访的list集合
        List<Customer> customerList = customerService.findAll();
        //所有用户的list集合
        List<User> userList = userService.findAll();

        //放到域对象
        ServletActionContext.getRequest().setAttribute("visit",visit);
        ServletActionContext.getRequest().setAttribute("userList",userList);
        ServletActionContext.getRequest().setAttribute("customerList",customerList);
        return "showVisit";
    }

    //修改客户拜访记录
    public String updateVisit(){
        visitService.updateVisit(visit);
        return "updateVisit";
    }

    //删除客户拜访记录
    public  String deleteVisit() {
        //模型驱动获取vid的值
        int vid = visit.getVid();
        //根据linkid查询
        Visit visit = visitService.findByVid(vid);
        if(visit != null){
            visitService.deleteVisit(visit);
        }
        return "deleteVisit";
    }

    //到多条件组合查询页面
    public String toSelectPage(){
        //查询所有用户和客户信息，传递到页面下拉列表中
        List<User> userList = userService.findAll();
        List<Customer> customerList = customerService.findAll();
        ServletActionContext.getRequest().setAttribute("userList",userList);
        ServletActionContext.getRequest().setAttribute("customerList",customerList);
        return "toSelectPage";
    }

    //多条件组合查询
    public String moreCondition(){
        PageBean<Visit> pageBean = visitService.findMoreCondition(currentPage,visit);
        ServletActionContext.getRequest().setAttribute("pageBean",pageBean);
        return "moreCondition";
    }

}
