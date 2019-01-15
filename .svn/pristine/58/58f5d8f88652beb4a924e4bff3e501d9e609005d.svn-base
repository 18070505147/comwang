package com.chejet.cloud.rpc;

import java.util.List;

/**
 * @author Neo.fang
 * @creatTime 2018/11/8 0008
 */
public interface AuthenticationRpcService {

    /**
     * 验证是否已经登录
     * @param token 授权码
     * @return
     */
    boolean validate(String token);


    /**
     * 获取当前应用所有权限(含菜单)
     *
     * @param token 授权码 (如果token不为空，获取当前用户的所有权限， token为空 获取app所有的的权限)
     * @param appId 应用ID
     * @param tenantId 租户ID
     * @return point功能点的列表（是否有companyId的边界，返回均为列表）
     */
    List<RpcPermission> findPermissionList(String token, String appId, String tenantId, String companyId);
}
