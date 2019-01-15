package com.chejet.cloud.model;

import org.beetl.sql.core.annotatoin.AssignID;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Neo.fang@carlt.com.cn
 * @creatTime 2018/9/5 0005
 */
public class User{

    @AssignID("simple")
    private Long id;

    private Integer age;

    private Long roleId;

    private String name;

    private Date createDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User [id=" + this.id
                + ", name="+ this.name
                + ", age=" + this.age
                + ", roleId=" + this.roleId
                + ", createDate=" + this.createDate
                + ", createDate=" + dateUtil(this.createDate) +"]";
    }

    public String dateUtil(Date date){
        if (date == null){
            return "";
        }
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }
}
