package com.chejet.cloud.dao;

import com.chejet.cloud.po.GroupAppRole;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;
import com.chejet.cloud.po.Employee;

import java.util.List;

/**
 * 员工管理dao
 *
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/13
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 获取当前用户不包含的角色
     */
    List<GroupAppRole> getRoles(@Param("tenantId") Long tenantId,
                                @Param("companyId") Long companyId,
                                @Param("appId") Long appId,
                                @Param("userId") Long userId);

    /**
     * 获取租户管理员手机号码
     */
    String findTenantPhone(@Param("tenantId") Long tenantId);
}
