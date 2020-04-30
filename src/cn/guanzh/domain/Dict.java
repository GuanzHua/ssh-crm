package cn.guanzh.domain;

import java.util.HashSet;
import java.util.Set;

public class Dict {
    private Integer did;
    private String dname;

    //表示一个客户级别中的多个客户对象
    Set<Customer> setCustomer = new HashSet<Customer>();
    public Set<Customer> getSetCustomer() {
        return setCustomer;
    }
    public void setSetCustomer(Set<Customer> setCustomer) {
        this.setCustomer = setCustomer;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "Dict{" +
                "did='" + did + '\'' +
                ", dname='" + dname + '\'' +
                '}';
    }
}
