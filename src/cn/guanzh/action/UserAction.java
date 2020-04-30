package cn.guanzh.action;

import cn.guanzh.domain.User;
import cn.guanzh.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class UserAction extends ActionSupport {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //属性封装获取
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //登录的方法
    public String login() {
        //封装到实体类对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //调用service方法实现
        User userExist = userService.login(user);
        //判断
        if (userExist != null) {
            //登录成功
            //使用session保持登录状态
            HttpServletRequest request = ServletActionContext.getRequest();
            request.getSession().setAttribute("user",userExist);
            return "loginSuccess";
        } else {
            //登录失败
            return "login";
        }

    }

}
