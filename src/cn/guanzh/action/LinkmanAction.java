package cn.guanzh.action;

import cn.guanzh.domain.Customer;
import cn.guanzh.domain.Linkman;
import cn.guanzh.domain.PageBean;
import cn.guanzh.service.CustomerService;
import cn.guanzh.service.LinkmanService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LinkmanAction extends ActionSupport implements ModelDriven<Linkman>{

    //注入service
    private LinkmanService linkmanService;
    public void setLinkmanService(LinkmanService linkmanService) {
        this.linkmanService = linkmanService;
    }

    //注入客户service
    private CustomerService customerService;
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    //模型驱动获取表单对象
    private Linkman linkman = new Linkman();
    @Override
    public Linkman getModel() {
        return linkman;
    }

    //文件上传相关
    //上传文件，变量名称需要是表单文件上传项的name值
    private File upload;
    //上传的文件名称，变量名称需要是表单文件上传项的name值+FileName
    private String uploadFileName;
    //生成get和set方法
    public File getUpload() {
        return upload;
    }
    public void setUpload(File upload) {
        this.upload = upload;
    }
    public String getUploadFileName() {
        return uploadFileName;
    }
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    //添加联系人-到添加页面
    public String toAddPage() {
        //查询所有客户，把所有客户list传递到页面显示，调用客户service里面的方法得到所有客户
        List<Customer> customerList = customerService.findAll();
        //放到域对象中
        ServletActionContext.getRequest().setAttribute("customerList",customerList);
        return "toAddPage";
    }

    //添加联系人的方法
    public String addLinkman(){
        //判断是否需要上传文件
        if(upload != null){
            //上传文件代码
            //服务器文件夹中创建文件
            File serverFile = new File("F:\\sshimg" +"/"+ uploadFileName);   //要上传到的目标文件夹路径
            //把上传的文件复制到服务器文件里面
            try {
                FileUtils.copyFile(upload,serverFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        linkmanService.addLinkman(linkman);
        return "addLinkman";
    }

    //使用属性封装获取currentPage
    private Integer currentPage = 1;
    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    //联系人列表的方法
    public String list() {
        PageBean pageBean = linkmanService.listLinkman(currentPage);
        ServletActionContext.getRequest().setAttribute("pageBean",pageBean);
        return "list";
    }

    //到修改联系人页面
    public String showLinkman(){
        //使用模型驱动得到id值
        int linkid = linkman.getLinkid();
        //根据id查询联系人对象
        Linkman linkman = linkmanService.findByLinkid(linkid);

        //需要所有客户的list集合
        List<Customer> customerList = customerService.findAll();

        //放到域对象
        ServletActionContext.getRequest().setAttribute("linkman",linkman);
        ServletActionContext.getRequest().setAttribute("customerList",customerList);
        return "showLinkman";
    }

    //修改联系人
    public String updateLinkman(){
        linkmanService.updataLinkman(linkman);
        return "updateLinkman";
    }

    //删除联系人
    public String deleteLinkman(){
        //模型驱动获取linkid的值
        int linkid = linkman.getLinkid();
        //根据linkid查询
        Linkman linkman = linkmanService.findByLinkid(linkid);
        if(linkman != null){
            linkmanService.deleteLinkman(linkman);
        }
        return "deleteLinkman";
    }

    //到联系人综合查询
    public String toSelectPage(){
        //查询所有客户，传递到页面下拉列表中
        List<Customer> customerList = customerService.findAll();
        ServletActionContext.getRequest().setAttribute("customerList",customerList);
        return "toSelectPage";
    }

    //多条件查询
    public String moreCondition(){
        PageBean<Linkman> pageBean = linkmanService.findMoreCondition(currentPage,linkman);
        ServletActionContext.getRequest().setAttribute("pageBean",pageBean);
        return "moreCondition";
    }


}
