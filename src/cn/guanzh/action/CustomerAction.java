package cn.guanzh.action;

import cn.guanzh.domain.Customer;
import cn.guanzh.domain.Dict;
import cn.guanzh.domain.PageBean;
import cn.guanzh.service.CustomerService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.util.List;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

    private CustomerService customerService;
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
    //模型驱动获取表单对象
    private Customer customer = new Customer();
    @Override
    public Customer getModel() {
        return customer;
    }

    //条件查询的方法
    public String listCondition() {
        //如果输入客户名称，根据客户名称查询，如果为空，查询所有
        if(customer.getCustName() != null && !"".equals(customer.getCustName())) {
            //不为空
            PageBean pageBean = customerService.findCondition(currentPage,customer);
            ServletActionContext.getRequest().setAttribute("pageBean",pageBean);
        }else {
            //不输入任何内容，查询所有
            listPage();
        }
        return "listCondition";
    }



    //使用属性封装获取currentPage
    private Integer currentPage = 1;
    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    //分页查询的方法
    public String listPage() {

        //调用service里的方法实现封装
        PageBean pageBean = customerService.listPage(currentPage);
        //放到域对象里面
        ServletActionContext.getRequest().setAttribute("pageBean",pageBean);
        return "listPage";
    }

    //到添加页面
    public String toAddPage() {
        //查询所有级别
        List<Dict> dictList = customerService.findDictLevel();
        ServletActionContext.getRequest().setAttribute("dictList",dictList);
        return "toAddPage";
    }

    //添加的方法
    public String add() {
        //添加逻辑
        customerService.add(customer);
        return "add";
    }


    //定义list变量,用于值栈操作
/*    private List<Customer> list;
    public List<Customer> getList() {
        return list;
    }*/


    //展示客户列表的方法
/*    public String list() {
        List<Customer> list = customerService.findAll();
        //放到域对象
        ServletActionContext.getRequest().setAttribute("list",list);

        //返回的list放到值栈里面
//        list = customerService.findAll();
        return "list";
    }*/

    //删除的方法
    public String delete() {
        //使用模型驱动获取表单提交的cid的值
        int cid = customer.getCid();
        //根据cid查询
        Customer customer = customerService.findByCid(cid);
        //调用方法删除
        if(customer != null) {
            customerService.delete(customer);
        }
        return "delete";
    }

    //修改-根据cid查询
    public String showCustomer() {
        //使用模型驱动获取表单提交的cid的值
        int cid = customer.getCid();
        //根据cid查询
        Customer customer = customerService.findByCid(cid);
        //放到域对象中用于页面渲染显示
        ServletActionContext.getRequest().setAttribute("customer",customer);
        return "showCustomer";
    }

    //修改的方法
    public String update(){
        customerService.update(customer);
        return "update";
    }

    //到查询客户信息的页面
    public  String toSelectCustomerPage(){
        //需要回显数据
        List<Dict> dictList = customerService.findDictLevel();
        ServletActionContext.getRequest().setAttribute("dictList",dictList);
        return "toSelectCustomerPage";
    }

    //多条件查询
    public String moreCondition(){
        PageBean<Customer> pageBean = customerService.findMoreCondition(currentPage,customer);
        ServletActionContext.getRequest().setAttribute("pageBean",pageBean);
        return "moreCondition";
    }

    //根据客户来源进行统计
    public String countSource(){
        List list = customerService.findCountSource();
        ServletActionContext.getRequest().setAttribute("list",list);
        return "countSource";
    }

    //根据客户级别进行统计
    public String countLevel(){
        List list = customerService.findCountLevel();
        ServletActionContext.getRequest().setAttribute("list",list);
        return "countLevel";
    }

}
