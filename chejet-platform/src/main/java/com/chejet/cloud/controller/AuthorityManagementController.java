package com.chejet.cloud.controller;

import com.chejet.cloud.annotation.Slog;
import com.chejet.cloud.common.CurrentUserInfo;
import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.po.*;
import com.chejet.cloud.response.ApiResp;
import com.chejet.cloud.response.ResultBuilder;
import com.chejet.cloud.service.AuthorityManagementService;
import com.chejet.cloud.vo.*;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/12 23:30
 */
@RestController
@RequestMapping(value = "/authority/{tenantId}", produces = "application/json;charset=UTF-8")
public class AuthorityManagementController extends BaseController {
    @Autowired
    private AuthorityManagementService authorityManagementService;

    @Slog
    @PostMapping(value = "/saveRole")
    public ApiResp saveRole(@RequestBody GroupAppRole groupAppRole) {

        if (groupAppRole == null) {
            return ResultBuilder.buildMessageFail(ErrorCodeEnum.ROLES_NOT_FOUND.getMessage());
        }
        try {
            authorityManagementService.saveRole(groupAppRole);
        } catch (Exception e) {
            throw new BaseException(ErrorCodeEnum.USER_INFO_ADD_ERROR);
        }
        return ResultBuilder.buildDateSuccess(true);
    }

    @Slog
    @PostMapping(value = "/update")
    public ApiResp updateFunctionStatus(@RequestBody AppFunction appFunction) {
        boolean result = authorityManagementService.updateFunctionStatus(appFunction);
        if (!result) {
            throw new BaseException(ErrorCodeEnum.DB_UPDATE_FAIL);
        }
        return ResultBuilder.buildDateSuccess(true);
    }

    @Slog
    @PostMapping(value = "/saveUserToRole")
    public ApiResp saveUserToRole(@RequestBody List<RelUserGroupRole> relUserGroupRoleList) {

        if (relUserGroupRoleList.size() == 0) {
            return ResultBuilder.buildMessageFail(ErrorCodeEnum.EMPLOYEE_IS_NOT_NULL.getMessage());
        }
        try {
            authorityManagementService.saveUserToRole(relUserGroupRoleList);
        } catch (Exception e) {
            throw new BaseException(ErrorCodeEnum.USER_INFO_ADD_ERROR);
        }
        return ResultBuilder.buildDateSuccess(true);
    }

    @GetMapping(value = "/getPermissionTree/{companyId}/{appId}")
    public ApiResp getPermissionTree(@PathVariable Long tenantId,
                                     @PathVariable Long appId,
                                     @PathVariable Long companyId) {
        List<AppDeploymodule> permissionTree = authorityManagementService.getPermissionTree(tenantId, appId, companyId);
        return ResultBuilder.buildDateSuccess(permissionTree);
    }

    @GetMapping(value = "/getRoles/{companyId}/{appId}")
    public ApiResp getAppRoles(@PathVariable Long tenantId,
                               @PathVariable Long companyId,
                               @PathVariable Long appId) {
        List<GroupAppRole> appRoles = authorityManagementService.getAppRoles(tenantId, companyId, appId);
        return ResultBuilder.buildDateSuccess(appRoles);
    }

    @PostMapping(value = "/getUser")
    public ApiResp getUserFoRole(@PathVariable Long tenantId, @RequestBody IdVO idVO) {
        idVO.setTenantId(tenantId);
        PageQuery<RoleUserVO> userList = authorityManagementService.getUserFoRole(idVO);
        return ResultBuilder.buildDateSuccess(userList);
    }

    @DeleteMapping(value = "/deleteRole/{roleId}")
    public ApiResp deleteRole(@PathVariable Long roleId) {
        boolean result = authorityManagementService.deleteRole(roleId);
        if (!result) {
            throw new BaseException(ErrorCodeEnum.USER_ROLE_INVALID);
        }
        return ResultBuilder.buildDateSuccess(true);
    }

    @PostMapping(value = "/deleteUser")
    public ApiResp deleteUserFromRole(@PathVariable Long tenantId, @RequestBody IdVO idVO) {
        idVO.setTenantId(tenantId);
        boolean result = authorityManagementService.deleteUserFromRole(idVO);

        if (!result) {
            throw new BaseException(ErrorCodeEnum.USER_INVALID);
        }
        return ResultBuilder.buildDateSuccess(true);
    }

    @GetMapping(value = "/roleUser/{roleId}/{companyId}")
    public ApiResp getRoleUser(@PathVariable Long tenantId,
                               @PathVariable Long roleId,
                               @PathVariable Long companyId) {
        List<RoleUserVO> roleUser = authorityManagementService.getRoleUser(tenantId, roleId,companyId);
        return ResultBuilder.buildDateSuccess(roleUser);
    }

    @PostMapping(value = "/rolesPermission")
    public ApiResp getRolesPermission(@RequestBody List<Long> roleList) {
        RoleParaVO pointFoRoles = authorityManagementService.getPointFoRoles(roleList);
        return ApiResp.success(pointFoRoles);
    }

    @GetMapping(value = "/companyList/{userId}/{roleType}")
    public ApiResp getCompanyList(@PathVariable Long tenantId,
                                  @PathVariable Long userId,
                                  @PathVariable Integer roleType) {
        List<CompanyVO> companyList = authorityManagementService.getCompanyList(tenantId, userId, roleType);
        return ApiResp.success(companyList);
    }
}
