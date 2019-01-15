package com.chejet.cloud.dao;

import com.chejet.cloud.po.App;
import com.chejet.cloud.vo.AppVO;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/13 19:21
 */
public interface ApplicationManagementMapper extends BaseMapper<App> {
    List<AppVO> findAppFoTenant(@Param("tenantId") Long tenantId, @Param("nameCondition") String nameCondition, @Param("pageN0") Integer pageN0, @Param("pageSize") Integer pageSize);

    Integer findAppCountFoTenant(@Param("tenantId") Long tenantId, @Param("nameCondition") String nameCondition);

    List<App> findNotAddedApp(@Param("tenantId") Long tenantId);

    List<App> findAppByCompany(@Param("tenantId") Long tenantId,@Param("companyId") Long companyId);
}
