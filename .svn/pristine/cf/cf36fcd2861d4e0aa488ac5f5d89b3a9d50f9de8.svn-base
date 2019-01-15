package com.chejet.cloud.dao;

import com.chejet.cloud.dto.RpcPermission;
import com.chejet.cloud.po.Company;
import com.chejet.cloud.po.FunctionPermissionPoint;
import com.chejet.cloud.vo.*;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/13 11:09
 */
public interface FunctionPermissionPointMapper extends BaseMapper<FunctionPermissionPoint> {

    List<RoleUserVO> findUserFoRole(@Param("tenantId") Long tenantId,
                                    @Param("companyId") Long companyId,
                                    @Param("appId") Long appId,
                                    @Param("roleId") Long roleId,
                                    @Param("condition") Object condition,
                                    @Param("pageN0") Integer pageN0,
                                    @Param("pageSize") Integer pageSize);

    Integer findUserFoRoleCount(@Param("tenantId") Long tenantId,
                                @Param("companyId") Long companyId,
                                @Param("appId") Long appId,
                                @Param("roleId") Long roleId,
                                @Param("condition") Object condition);

    List<RoleUserVO> findRoleUser(@Param("tenantId") Long tenantId,@Param("roleId") Long roleId,@Param("companyId") Long companyId );

    List<FunctionPermissionPointVO> getPointPermissionList(@Param("roleIds") String roleIds);

    List<SensitiveDataVO> getSensitiveDataList(@Param("roleIds") String roleIds);


    List<RpcPermission> findAppPersonalPermissionList(@Param("userId") Long userId,
                                                      @Param("appId") Long appId,
                                                      @Param("tenantId") Long tenantId,
                                                      @Param("companyId") Long companyId);

    List<CompanyVO> getCompany(@Param("tenantId") Long tenantId, @Param("userId") Long userId);

}
