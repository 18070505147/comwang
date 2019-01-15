package com.chejet.cloud.controller;

import com.chejet.cloud.annotation.Slog;
import com.chejet.cloud.po.App;
import com.chejet.cloud.response.ApiResp;
import com.chejet.cloud.response.ResultBuilder;
import com.chejet.cloud.service.ApplicationManagementService;
import com.chejet.cloud.vo.AppVO;
import com.chejet.cloud.vo.IdVO;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/11 14:17
 */
@RestController
@RequestMapping(value = "/app/{tenantId}", produces = "application/json;charset=UTF-8")
public class ApplicationManagementController extends BaseController {
    @Autowired
    private ApplicationManagementService applicationManagementService;

    @GetMapping(value = "/page")
    public ApiResp pageFoApp(@PathVariable Long tenantId,
                             @RequestParam(required = false) String nameCondition,
                             @RequestParam Integer pageNo,
                             @RequestParam(required = false) Integer pageSize) {
        PageQuery<AppVO> appPageQuery = applicationManagementService.appFoPage(tenantId, nameCondition, pageNo, pageSize);
        return ResultBuilder.buildDateSuccess(appPageQuery);
    }

    @GetMapping(value = "/list")
    public ApiResp findNotAddedApp(@PathVariable Long tenantId) {
        List<AppVO> notAddedApp = applicationManagementService.findNotAddedApp(tenantId);
        return ResultBuilder.buildDateSuccess(notAddedApp);
    }
    @Slog
    @PostMapping(value = "/save")
    public ApiResp saveApp(@RequestBody List<IdVO> idVOList) {
        return ResultBuilder.buildDateSuccess(applicationManagementService.saveApp(idVOList));
    }

    @GetMapping(value = "/byCompany/{companyId}")
    public ApiResp getAppByCompanyId(@PathVariable Long tenantId,
                                     @PathVariable Long companyId) {
        List<App> appList = applicationManagementService.getAppByCompanyId(tenantId, companyId);
        return ResultBuilder.buildDateSuccess(appList);
    }
}
