package com.chejet.cloud.vo;

/**
 * @Description
 * @Date 2018/12/15 15:26
 * @Version 1.0
 */
public class StatisticVO {
    /**
     * 公司名称
     */
    private String name;
    /**
     * 在线人数
     */
    private Long onlineNum = 0L;
    /**
     * 总人数
     */
    private Long offlineNum=0L;

    /**
     * 总人数
     */
    private Long total=0L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(Long onlineNum) {
        this.onlineNum = onlineNum;
    }

    public Long getOfflineNum() {
        return offlineNum;
    }

    public void setOfflineNum(Long offlineNum) {
        this.offlineNum = offlineNum;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
