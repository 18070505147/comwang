package com.chejet.cloud.service;

import com.chejet.cloud.vo.RsultMsgVO;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/12 9:34
 */
public interface ReplaceTenantAdminService {
    Boolean replaceTenantAdmin(Long tenantId, String oldMobileNo,String validMobileNo, String mobileNo,String oldUserId);
    RsultMsgVO checkUserRole(Long tenantId, String mobileNo);
}
