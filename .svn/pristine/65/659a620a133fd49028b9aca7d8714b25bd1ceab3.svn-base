package com.chejet.cloud.api;

import com.chejet.cloud.response.ApiResp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 提供给其他系统主动推送应用信息
 * @Date 2018/12/21 11:41
 * @Version 1.0
 */
@RestController
public class SyncAppModelInfo {
    /**
     * 同步应用的功能与权限点信息
     * @return
     */
    @RequestMapping(value = "/sync_func_permission",method = RequestMethod.POST)
    public ApiResp syncAppFuncAndPermissionPoint() {
        // TODO 同步功能与权限点
        /**
         * 逻辑：使用code判断
         * 1、同样权限点做更新
         * 2、没有的做新增
         * 3、删除权限点，角色下绑定的权限点做删除操作
         */
        return ApiResp.success();
    }
}
