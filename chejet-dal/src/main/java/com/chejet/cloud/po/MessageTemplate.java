package com.chejet.cloud.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import org.beetl.sql.core.annotatoin.AssignID;

import java.io.Serializable;
import java.util.Date;

/**
 * sys_message_template
 * @author 
 */
public class MessageTemplate implements Serializable {
    /**
     * 编号
     */
    @AssignID()
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 模板代码
     */
    private Integer code;

    /**
     * 短信类型 字典值：0-注册，1-登录，2-重置手机，3-修改密码，4-注销账号，5-更换租户管理员，邀请注册
     */
    private Integer messageType;

    /**
     * 短信模板
     */
    private String messageTemplate;

    /**
     * 适用范围 字典值：0-平台内，1-租户内，2-企业内
     */
    private Integer suitedScope;

    /**
     * 适用对象 字典值：0-租户，1-企业
     */
    private Integer suitedObject;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 更新时间
     */
    private Date mtime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

    public void setMessageTemplate(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public Integer getSuitedScope() {
        return suitedScope;
    }

    public void setSuitedScope(Integer suitedScope) {
        this.suitedScope = suitedScope;
    }

    public Integer getSuitedObject() {
        return suitedObject;
    }

    public void setSuitedObject(Integer suitedObject) {
        this.suitedObject = suitedObject;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MessageTemplate other = (MessageTemplate) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getMessageType() == null ? other.getMessageType() == null : this.getMessageType().equals(other.getMessageType()))
            && (this.getMessageTemplate() == null ? other.getMessageTemplate() == null : this.getMessageTemplate().equals(other.getMessageTemplate()))
            && (this.getSuitedScope() == null ? other.getSuitedScope() == null : this.getSuitedScope().equals(other.getSuitedScope()))
            && (this.getSuitedObject() == null ? other.getSuitedObject() == null : this.getSuitedObject().equals(other.getSuitedObject()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getMtime() == null ? other.getMtime() == null : this.getMtime().equals(other.getMtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getMessageType() == null) ? 0 : getMessageType().hashCode());
        result = prime * result + ((getMessageTemplate() == null) ? 0 : getMessageTemplate().hashCode());
        result = prime * result + ((getSuitedScope() == null) ? 0 : getSuitedScope().hashCode());
        result = prime * result + ((getSuitedObject() == null) ? 0 : getSuitedObject().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getMtime() == null) ? 0 : getMtime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", messageType=").append(messageType);
        sb.append(", messageTemplate=").append(messageTemplate);
        sb.append(", suitedScope=").append(suitedScope);
        sb.append(", suitedObject=").append(suitedObject);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}