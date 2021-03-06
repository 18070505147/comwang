package com.chejet.cloud.service.impl;

import com.chejet.cloud.builder.CompanyTreeBuilder;
import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.common.RoleTypeEnum;
import com.chejet.cloud.dao.FunctionPermissionPointMapper;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.po.*;
import com.chejet.cloud.service.AuthorityManagementService;
import com.chejet.cloud.util.StringUtils;
import com.chejet.cloud.vo.*;
import com.chejet.cloud.util.LongIdGen;
import org.beetl.sql.core.DSTransactionManager;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/12 19:48
 */
@Service
public class AuthorityManagementServiceImpl implements AuthorityManagementService {
    @Autowired
    private SQLManager sqlManager;
    @Autowired
    private FunctionPermissionPointMapper functionPermissionPointMapper;

    /**
     * 保存角色
     */
    @Override
    @Transactional
    public boolean saveRole(GroupAppRole groupAppRole) throws Exception {
        DSTransactionManager.start();
        int i = 0;
        try {
            if (groupAppRole.getId() == null) {
                long id = LongIdGen.get().nextId();
                groupAppRole.setId(id);
                groupAppRole.setEnableFlag(true);
                /*    todo 待确认        */
                groupAppRole.setRoleType(0);
                long count = sqlManager.lambdaQuery(GroupAppRole.class).andEq(GroupAppRole::getName, groupAppRole.getName()).count();
                if (count > 0) {
                    throw new BaseException(ErrorCodeEnum.ROLE_HAS_SAME_NAME);
                }
                i = sqlManager.insertTemplate(groupAppRole);
            } else {
                deleteAssociation(groupAppRole.getId());
                i = sqlManager.updateTemplateById(groupAppRole);

            }

            Long id = groupAppRole.getId();
            List<SensitiveDataVO> sensitiveDataList = groupAppRole.getDataPermissionList();
            sensitiveDataList.forEach(sensitiveData -> {
                if (!sensitiveData.getViewFlag()) {
                    if (sensitiveData.getModifiedFlag()) {
                        sensitiveData.setViewFlag(true);
                    }
                }
                RelRoleDataPermission relRoleDataPermission = new RelRoleDataPermission();
                relRoleDataPermission.setRoleId(id);
                relRoleDataPermission.setId(LongIdGen.get().nextId());
                relRoleDataPermission.setSentitiveId(sensitiveData.getSentitiveId());
                relRoleDataPermission.setViewFlag(sensitiveData.getViewFlag());
                relRoleDataPermission.setModifiedFlag(sensitiveData.getModifiedFlag());
                sqlManager.insertTemplate(relRoleDataPermission);
            });
            List<FunctionPermissionPointVO> functionPermissionPointList = groupAppRole.getPointPermissionList();
            functionPermissionPointList.forEach(point -> {
                RelRolePointPermission relRolePointPermission = new RelRolePointPermission();
                relRolePointPermission.setId(LongIdGen.get().nextId());
                relRolePointPermission.setRoleId(id);
                relRolePointPermission.setPermissionPointId(point.getPermissionPointId());
                relRolePointPermission.setEnableFlag(point.getEnableFlag());
                relRolePointPermission.setCloseFlag(point.getCloseFlag());
                sqlManager.insertTemplate(relRolePointPermission);

            });
        } catch (Exception e) {
            DSTransactionManager.rollback();
            e.printStackTrace();
        }

        DSTransactionManager.commit();
        return i == 1;
    }

    /**
     * 修改功能状态
     */
    @Override
    public boolean updateFunctionStatus(AppFunction appFunction) {
        int i = sqlManager.updateTemplateById(appFunction);
        return i == 1;
    }

    /**
     * 删除角色
     */
    @Override
    @Transactional
    public boolean deleteRole(Long roleId) {
        deleteAssociation(roleId);
        int result = sqlManager.deleteById(GroupAppRole.class, roleId);
        return result == 1;
    }

    /**
     * 用户添加角色
     */
    @Override
    @Transactional
    public boolean saveUserToRole(List<RelUserGroupRole> relUserGroupRoleList) throws Exception {
        DSTransactionManager.start();
        try {
            relUserGroupRoleList.forEach(relUserGroupRole -> {
                relUserGroupRole.setId(LongIdGen.get().nextId());
                sqlManager.insertTemplate(relUserGroupRole);
            });
        } catch (Exception e) {
            DSTransactionManager.rollback();
            return false;
        }
        DSTransactionManager.commit();
        return true;
    }

    /**
     * 删除用户角色
     */
    @Override
    public boolean deleteUserFromRole(IdVO idVO) {
        if (idVO == null
                || idVO.getUserId() == null
                || idVO.getCompanyId() == null
                || idVO.getTenantId() == null
                || idVO.getAppId() == null
                || idVO.getRoleId() == null) {
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        RelUserGroupRole role = sqlManager.lambdaQuery(RelUserGroupRole.class)
                .andEq(RelUserGroupRole::getTenantId, idVO.getTenantId())
                .andEq(RelUserGroupRole::getCompanyId, idVO.getCompanyId())
                .andEq(RelUserGroupRole::getAppId, idVO.getAppId())
                .andEq(RelUserGroupRole::getUserId, idVO.getUserId())
                .andEq(RelUserGroupRole::getRoleId, idVO.getRoleId())
                .single();
        if (role == null) {
            return false;
        }
        int result = sqlManager.deleteById(RelUserGroupRole.class, role.getId());

        return result == 1;
    }

    /**
     * 获取app角色列表
     */
    @Override
    public List<GroupAppRole> getAppRoles(Long tenantId, Long companyId, Long appId) {
        List<GroupAppRole> roleList = sqlManager.lambdaQuery(GroupAppRole.class)
                .andEq(GroupAppRole::getTenantId, tenantId)
                .andEq(GroupAppRole::getCompanyId, companyId)
                .andEq(GroupAppRole::getAppId, appId)
                .select();
        if (roleList == null) {
            return null;
        }
        roleList.forEach(r -> {
            List<FunctionPermissionPointVO> pointVOList = sqlManager.lambdaQuery(FunctionPermissionPointVO.class)
                    .andEq(FunctionPermissionPointVO::getRoleId, r.getId()).select();
            List<SensitiveDataVO> dataVOList = sqlManager.lambdaQuery(SensitiveDataVO.class)
                    .andEq(SensitiveDataVO::getRoleId, r.getId()).select();
            r.setDataPermissionList(dataVOList);
            r.setPointPermissionList(pointVOList);
        });
        return roleList;

    }

    /**
     * 获取权限树
     */
    @Override
    public  List<AppDeploymodule> getPermissionTree(Long tenantId, Long appId, Long companyId) {
//        获取租户下的指定的app
        RelTenantApp single = sqlManager.lambdaQuery(RelTenantApp.class)
                .andEq(RelTenantApp::getAppId, appId)
                .andEq(RelTenantApp::getTenantId, tenantId).single();
        if (single == null) {
            throw new BaseException(ErrorCodeEnum.APP_ROLE_LITS_NONEXISTS);
        }
//        获取当前app的销售版本及其所包含的配置包
        List<RelCompanyApp> relCompanyApps = sqlManager.lambdaQuery(RelCompanyApp.class)
                .andEq(RelCompanyApp::getAppId, appId)
                .andEq(RelCompanyApp::getTenantId, tenantId)
                .andEq(RelCompanyApp::getCompanyId, companyId)
                .select();
        List<Long> deploymoduleIds = new ArrayList<>();
        relCompanyApps.forEach(d -> {
            deploymoduleIds.add(d.getDeploymoduleId());
        });
        if (deploymoduleIds.size() == 0) {
            throw new BaseException(ErrorCodeEnum.APP_DEPLOY_LIST_EXISTS);
        }
        List<AppDeploymodule> tree = new ArrayList<>();
        deploymoduleIds.forEach(id -> {
            AppDeploymodule appDeployModule = sqlManager.lambdaQuery(AppDeploymodule.class).andEq(AppDeploymodule::getId, id).single();
            if (appDeployModule == null) {
                throw new BaseException(ErrorCodeEnum.APP_DEPLOY_NOT_FIND);
            }
//        获取配置包下的功能
            List<AppFunction> appFunctionList = sqlManager.lambdaQuery(AppFunction.class)
                    .andEq(AppFunction::getAppId, appId)
                    .andEq(AppFunction::getStatus, 1)
                    .andEq(AppFunction::getDeploymoduleId, id)
                    .select();
            //        权限功能树封装
            List<AppFunction> rootTree = new ArrayList<>();
            appFunctionList.forEach(af -> {
//            获取指定功能下的权限点集合
                List<FunctionPermissionPoint> pointList = sqlManager.lambdaQuery(FunctionPermissionPoint.class)
                        .andEq(FunctionPermissionPoint::getFunctionId, af.getId())
                        .andEq(FunctionPermissionPoint::getEnableFlag, true)
                        .select();
                af.setFunctionPermissionPointsList(pointList);
//            获取指定功能点下的敏感数据集合
                List<SensitiveData> sensitiveDataList = sqlManager.lambdaQuery(SensitiveData.class)
                        .andEq(SensitiveData::getFunctionId, af.getId())
                        .select();
                af.setSensitiveDataList(sensitiveDataList);
                if (af.getParentId() == null) {
                    rootTree.add(af);
                }
                appFunctionList.forEach(afChild -> {
                    if (Objects.equals(afChild.getParentId(), af.getId())) {
                        if (CollectionUtils.isEmpty(af.getChild())) {
                            List<AppFunction> child = new ArrayList<>();
                            child.add(afChild);
                            af.setChild(child);
                        } else {
                            af.getChild().add(afChild);
                        }
                    }
                });
            });
            appDeployModule.setAppFunctionList(rootTree);
            tree.add(appDeployModule);
        });
        return tree;
    }

    /**
     * 获取当前角色下的用户
     *
     * @param tenantId
     * @param roleId
     */
    @Override
    public List<RoleUserVO> getRoleUser(Long tenantId, Long roleId, Long companyId) {
        List<RoleUserVO> roleUser = functionPermissionPointMapper.findRoleUser(tenantId, roleId, companyId);
        return roleUser;
    }

    /**
     * 获取所选角色的权限列表
     *
     * @param roleList
     */
    @Override
    public RoleParaVO getPointFoRoles(List<Long> roleList) {
        String roleIds = StringUtils.strip(roleList.toString(), "[]");

        List<FunctionPermissionPointVO> pointPermissionList = functionPermissionPointMapper.getPointPermissionList(roleIds);
        List<SensitiveDataVO> sensitiveDataList = functionPermissionPointMapper.getSensitiveDataList(roleIds);
        RoleParaVO roleParaVO = new RoleParaVO(pointPermissionList, sensitiveDataList);
        return roleParaVO;
    }

    /**
     * 获取企业列表
     *
     * @param tenantId
     * @param userId
     * @param roleType
     */
    @Override
    public List<CompanyVO> getCompanyList(Long tenantId, Long userId, Integer roleType) {
        if (Objects.equals(roleType, RoleTypeEnum.COMPANY_ADMIN.getValue())) {
            return functionPermissionPointMapper.getCompany(tenantId, userId);
        }
        List<CompanyVO> companies = sqlManager.lambdaQuery(CompanyVO.class)
                .andEq(CompanyVO::getTenantId, tenantId)
                .andEq(CompanyVO::getCompanyStatus, 1)
                .select();
        return CompanyTreeBuilder.toTreeList(companies);
    }

    /**
     * 获取用户列表
     */
    @Override
    public PageQuery<RoleUserVO> getUserFoRole(IdVO idVO) {
        String condition = idVO.getCondition();
        if (!StringUtils.isEmpty(condition) && !condition.contains("%")) {
            condition = "%" + condition + "%";
        } else if (!StringUtils.isEmpty(condition) && condition.contains("%")) {
            return null;
        }
        Integer voPageNo = idVO.getPageNo();
        Integer pageSize = idVO.getPageSize();

        if (voPageNo == null) {
            voPageNo = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        Integer pageNo = (voPageNo - 1) * pageSize;
        PageQuery<RoleUserVO> pageQuery = new PageQuery<>();
        pageQuery.setPageNumber(voPageNo);
        pageQuery.setPageSize(pageSize);
        List<RoleUserVO> userFoRole = functionPermissionPointMapper.findUserFoRole(idVO.getTenantId(), idVO.getCompanyId(),
                idVO.getAppId(), idVO.getRoleId(), condition, pageNo, pageSize);
        Integer userFoRoleCount = functionPermissionPointMapper.findUserFoRoleCount(idVO.getTenantId(), idVO.getCompanyId(),
                idVO.getAppId(), idVO.getRoleId(), condition);

        pageQuery.setList(userFoRole);
        pageQuery.setTotalRow(userFoRoleCount);
        return pageQuery;
    }

    /**
     * 删除角色对应的关联关系
     */
    private void deleteAssociation(Long roleId) {
        List<RelRoleDataPermission> dataPermissionList = sqlManager.lambdaQuery(RelRoleDataPermission.class)
                .andEq(RelRoleDataPermission::getRoleId, roleId)
                .select();
        List<RelRolePointPermission> pointPermissionList = sqlManager.lambdaQuery(RelRolePointPermission.class)
                .andEq(RelRolePointPermission::getRoleId, roleId)
                .select();
        if (dataPermissionList != null) {
            dataPermissionList.forEach(date -> {
                sqlManager.deleteById(RelRoleDataPermission.class, date.getId());
            });

        }
        if (pointPermissionList != null) {
            pointPermissionList.forEach(date -> {
                sqlManager.deleteById(RelRolePointPermission.class, date.getId());
            });
        }
    }

}
