package cn.guanzh.domain;

import java.util.HashSet;
import java.util.Set;

public class Customer {

    private Integer cid;
    private String custName;
//    private String custLevel;
    private String custSource;
    private String custPhone;
    private String custMobile;
    private String custAddress;

    //在客户实体类中表示客户所属级别
    private Dict dictCustLevel;


    //表示一个客户中所有的拜访记录
    private Set<Visit> setCusVisit = new HashSet<Visit>();
    public Set<Visit> getSetCusVisit() {
        return setCusVisit;
    }
    public void setSetCusVisit(Set<Visit> setCusVisit) {
        this.setCusVisit = setCusVisit;
    }

    //表示所有的联系人
    private Set<Linkman> setLinkman = new HashSet<Linkman>();
    public Set<Linkman> getSetLinkman() {
        return setLinkman;
    }
    public void setSetLinkman(Set<Linkman> setLinkman) {
        this.setLinkman = setLinkman;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public Dict getDictCustLevel() {
        return dictCustLevel;
    }

    public void setDictCustLevel(Dict dictCustLevel) {
        this.dictCustLevel = dictCustLevel;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cid=" + cid +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custMobile='" + custMobile + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", dictCustLevel=" + dictCustLevel +
                ", setCusVisit=" + setCusVisit +
                ", setLinkman=" + setLinkman +
                '}';
    }
}
