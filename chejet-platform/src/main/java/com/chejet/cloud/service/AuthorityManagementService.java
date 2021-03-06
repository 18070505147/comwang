package com.chejet.cloud.service;

import com.chejet.cloud.po.*;
import com.chejet.cloud.vo.*;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/12 19:48
 */
public interface AuthorityManagementService {
    /**
     * 保存角色
     */
    boolean saveRole(GroupAppRole groupAppRole) throws Exception;

    /**
     * 修改功能状态
     */
    boolean updateFunctionStatus(AppFunction appFunction);

    /**
     * 删除角色
     */
    boolean deleteRole(Long roleId);

    /**
     * 用户添加角色
     */
    boolean saveUserToRole(List<RelUserGroupRole> relUserGroupRoleList) throws Exception;

    /**
     * 删除用户角色
     */
    boolean deleteUserFromRole(IdVO idVO);

    /**
     * 获取权限列表
     */
    PageQuery<RoleUserVO> getUserFoRole(IdVO idVO);

    /**
     * 获取app列表
     */
    List<GroupAppRole> getAppRoles(Long tenantId, Long companyId, Long appId);

    /**
     * 获取权限树
     */
    List<AppDeploymodule> getPermissionTree(Long tenantId, Long appId, Long companyId);

    /**
     * 获取当前角色下的用户
     */
    List<RoleUserVO> getRoleUser(Long tenantId, Long roleId,Long companyId);

    /**
     * 获取所选角色的权限列表
     */
    RoleParaVO getPointFoRoles(List<Long> roleList);

    /**
     * 获取企业列表
     * */
    List<CompanyVO> getCompanyList(Long tenantId,Long userId,Integer roleType);
}
