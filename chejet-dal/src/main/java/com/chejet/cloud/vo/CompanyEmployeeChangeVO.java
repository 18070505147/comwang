package com.chejet.cloud.vo;

/**
 * @Description
 * @Date 2018/12/15 13:43
 * @Version 1.0
 */
public class CompanyEmployeeChangeVO {
    /**
     * 企业名称
     */
    private String name;
    /**
     * 入职人数
     */
    private Long entryNum=0L;
    /**
     * 离职人数
     */
    private Long leaveNum=0L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEntryNum() {
        return entryNum;
    }

    public void setEntryNum(Long entryNum) {
        this.entryNum = entryNum;
    }

    public Long getLeaveNum() {
        return leaveNum;
    }

    public void setLeaveNum(Long leaveNum) {
        this.leaveNum = leaveNum;
    }
}
