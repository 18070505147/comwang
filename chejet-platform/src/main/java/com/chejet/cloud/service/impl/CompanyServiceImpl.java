package com.chejet.cloud.service.impl;

import com.chejet.cloud.builder.CompanyTreeBuilder;
import com.chejet.cloud.common.CurrentUserInfo;
import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.common.RoleTypeEnum;
import com.chejet.cloud.constant.Constants;
import com.chejet.cloud.dao.CompanyMapper;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.exception.BizException;
import com.chejet.cloud.po.*;
import com.chejet.cloud.service.CompanyService;
import com.chejet.cloud.util.LongIdGen;
import com.chejet.cloud.vo.*;
import org.beetl.sql.core.DSTransactionManager;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.SQLReady;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.*;

/**
 * 公司管理biz实现
 *
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/13
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    private static String COMPANY_ADMIN_SUFFIX = "企业管理员";
    Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);
    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    private SQLManager sqlManager;

    @Override
    public List<CompanyVO> treeCompany(Company comp, CurrentUserInfo currentUser) {
        List<CompanyVO> retList = null;
        try {
            List<Company> list = sqlManager.query(Company.class).andEq("tenant_id", currentUser.getTenantId()).select();
            List<CompanyVO> companyVOS = new ArrayList<>();
            for (Company company : list) {
                CompanyVO companyVO = new CompanyVO();
                BeanUtils.copyProperties(company, companyVO);
                if (company.getLogoUrlId() != null) {
                    Attach logo = sqlManager.lambdaQuery(Attach.class).andEq(Attach::getId, company.getLogoUrlId()).single();
                    companyVO.setLogoUrl(logo == null ? "" : logo.getUrlName());
                }
                if (company.getCertificateUrlId() != null) {
                    Attach certificate = sqlManager.lambdaQuery(Attach.class).andEq(Attach::getId, company.getCertificateUrlId()).single();
                    companyVO.setCertificateUrl(certificate == null ? "" : certificate.getUrlName());
                }
                companyVOS.add(companyVO);
            }
            retList = CompanyTreeBuilder.toTreeList(companyVOS);
        } catch (Exception e) {
            throw new BizException(ErrorCodeEnum.COMPANY_TREE_EXCEPTION);
        }

        return retList;
    }

    @Override
    public PageQuery<Company> pageCompany(Company company, CurrentUserInfo currentUser) {
        PageQuery<Company> page = null;
        List<Long> list = sqlManager.query(CompanyManager.class).andEq("tenant_id", currentUser.getTenantId())
                .andEq("user_id", currentUser.getUserId()).select(Long.class, "company_id as id");
        if (!list.isEmpty()) {

            Query<Company> query = sqlManager.query(Company.class);
            if (!StringUtils.isEmpty(company.getName())) {
                query.andEq("name", company.getName());
            }

            if (!StringUtils.isEmpty(company.getContactPerson())) {
                query.andEq("contact_person", company.getContactPerson());
            }

            if (!StringUtils.isEmpty(company.getContactPerson())) {
                query.andEq("contact_person_phone", company.getContactPersonPhone());
            }

            page = query.andIn("id", list).page(company.getPageNumber(), company.getPageSize());
        } else {
            throw new BizException(ErrorCodeEnum.MANAGER_COMPANY_NOEXISTS);
        }

        return page;
    }

    @Override
    public List<Company> queryCompany(Company company) {

        List<Company> poList = null;
        List<Company> list = sqlManager.query(Company.class).andEq("id", company.getId()).select();
        if (!list.isEmpty()) {
            Company com = list.get(0);
            Query<Company> query2 = sqlManager.query(Company.class);
            poList = query2.andEq("tenant_id", com.getTenantId())
                    .andBetween("left_value", com.getLeftValue(), com.getRightValue()).orderBy("left_value").select();
        } else {
            throw new BizException(ErrorCodeEnum.COMPANY_NOEXISTS);
        }

        return poList;
    }

    @Override
    public boolean saveCompany(Company company, CurrentUserInfo currentUser) throws Exception {

        int i = 0;

        try {
            long id = LongIdGen.get().nextId();
            Date d = new Date();
            List<Company> list = sqlManager.query(Company.class).andEq("tenant_id", currentUser.getTenantId()).select();
            if (CollectionUtils.isEmpty(list)) {
                company.setId(id);

                company.setCtime(d);
                company.setMtime(d);
                company.setLeftValue(1);
                company.setRightValue(2);
                company.setCompanyStatus(1);
                company.setCreateBy(currentUser.getUserId());
                company.setModifiedBy(currentUser.getUserId());

                Department department = new Department();
                long id2 = LongIdGen.get().nextId();
                department.setId(id2);
                department.setCtime(d);
                department.setMtime(d);
                department.setLeftValue(1);
                department.setRightValue(2);
                department.setCompanyId(id);
                department.setDescription(department.getDescription());
                department.setName("总部");
                department.setTenantId(currentUser.getTenantId());
                department.setDepartmentStatus(1);
                department.setCreateBy(currentUser.getUserId());
                department.setModifiedBy(currentUser.getUserId());
                GroupAppRole groupAppRole = new GroupAppRole();
                groupAppRole.setId(LongIdGen.get().nextId());
                groupAppRole.setCompanyId(id);
                groupAppRole.setTenantId(currentUser.getTenantId());
                groupAppRole.setRoleType(RoleTypeEnum.COMPANY_ADMIN.getValue());
                groupAppRole.setCtime(d);
                groupAppRole.setEnableFlag(true);
                groupAppRole.setName(company.getName().concat(COMPANY_ADMIN_SUFFIX));

                DSTransactionManager.start();
                i = sqlManager.insertTemplate(company);
                i = sqlManager.insertTemplate(department);
                sqlManager.insertTemplate(groupAppRole);
                DSTransactionManager.commit();

            } else {
                if (company.getParentId() != null && company.getParentId() != 0L) {
                    List<Company> list2 = sqlManager.query(Company.class).andEq("id", company.getParentId()).select();
                    if (!list2.isEmpty()) {
                        Company parentCompany = list2.get(0);
                        // 父级企业状态判断,新企业不允许挂在禁用企业下;上级企业不能是自己;不能是下级企业
                        if (0 == parentCompany.getCompanyStatus()) {
                            logger.error("上级企业：{},已经禁用", parentCompany.getName());
                            throw new BizException(ErrorCodeEnum.COMPANY_HAS_ENABLE_CHILD);
                        }
                        company.setId(id);
                        company.setCtime(d);
                        company.setMtime(d);
                        company.setLeftValue(parentCompany.getRightValue() == null ? 1 : parentCompany.getRightValue());
                        company.setRightValue(parentCompany.getRightValue() == null ? 2 : parentCompany.getRightValue() + 1);
                        company.setCompanyStatus(1);
                        company.setCreateBy(currentUser.getUserId());
                        company.setModifiedBy(currentUser.getUserId());

                        // 创建默认总部
                        Department department = new Department();
                        long id2 = LongIdGen.get().nextId();
                        department.setId(id2);
                        department.setCtime(d);
                        department.setMtime(d);
                        department.setLeftValue(1);
                        department.setRightValue(2);
                        department.setCompanyId(id);
                        department.setDescription(department.getDescription());
                        department.setName("总部");
                        department.setTenantId(currentUser.getTenantId());
                        department.setDepartmentStatus(1);
                        department.setCreateBy(currentUser.getUserId());
                        department.setModifiedBy(currentUser.getUserId());

                        GroupAppRole groupAppRole = new GroupAppRole();
                        groupAppRole.setId(LongIdGen.get().nextId());
                        groupAppRole.setCompanyId(id);
                        groupAppRole.setTenantId(currentUser.getTenantId());
                        groupAppRole.setRoleType(RoleTypeEnum.COMPANY_ADMIN.getValue());
                        groupAppRole.setCtime(d);
                        groupAppRole.setEnableFlag(true);
                        groupAppRole.setName(company.getName().concat(COMPANY_ADMIN_SUFFIX));

                        DSTransactionManager.start();
                        sqlManager.executeUpdate(new SQLReady(
                                "update sys_company set right_value=right_value+2 where right_value>=? and tenant_id=?",
                                parentCompany.getRightValue(), parentCompany.getTenantId()));
                        sqlManager.executeUpdate(new SQLReady(
                                "update sys_company set left_value=left_value+2 where left_value>=? and tenant_id=?",
                                parentCompany.getRightValue(), parentCompany.getTenantId()));
                        i = sqlManager.insertTemplate(company);
                        sqlManager.insertTemplate(department);
                        sqlManager.insertTemplate(groupAppRole);
                        DSTransactionManager.commit();
                    }
                } else {
                    throw new BizException(ErrorCodeEnum.COMPANY_PARENT_NOEXISTS);
                }
            }

        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            DSTransactionManager.rollback();
            e.printStackTrace();
            throw e;
        }

        return i == 1;
    }

    @Override
    public boolean deleteCompany(Company company, CurrentUserInfo currentUser) {
        if (currentUser.getTenantId() != null) {
            if (null != company.getId()) {
                List<Company> list = sqlManager.query(Company.class).andEq("id", company.getId()).select();
                if (!list.isEmpty()) {
                    Company com = list.get(0);
                    List<Company> poList = sqlManager.query(Company.class).andEq("tenant_id", com.getTenantId())
                            .andGreat("left_value", com.getLeftValue()).andLess("right_value", com.getRightValue())
                            .select();
                    if (poList.size() == 0) {
                        sqlManager.executeUpdate(new SQLReady("delete from sys_company where id=?", com.getId()));
                    } else {
                        throw new BizException(ErrorCodeEnum.DEPARTMENT_CHILD_EXCEPTION);
                    }
                } else {
                    throw new BizException(ErrorCodeEnum.DEPARTMENT_NOEXISTS);
                }
            } else {
                throw new BizException(ErrorCodeEnum.PARAM_ERROR);
            }
        } else {
            throw new BizException(ErrorCodeEnum.TENANT_ID_ERROR);
        }

        return true;
    }

    @Override
    public boolean updateCompany(Company company, CurrentUserInfo currentUser) {
        int i = 0;
        try {
            Date d = new Date();
            Company originCompany = sqlManager.lambdaQuery(Company.class)
                    .andEq(Company::getId, company.getId())
                    .single();
            company.setMtime(d);
            company.setModifiedBy(currentUser.getUserId());
            // 如果有子公司不允许禁用
            if (0 == company.getCompanyStatus()) {
                List<Company> companies = sqlManager.lambdaQuery(Company.class)
                        .andEq(Company::getParentId, company.getId())
                        .andEq(Company::getCompanyStatus, 1)
                        .select();
                if (companies.size() > 0) {
                    logger.error("{}下有未禁用的子公司，不能禁用", company.getName());
                    throw new BizException(ErrorCodeEnum.COMPANY_HAS_ENABLE_CHILD);
                }
            }
            List<Company> list2 = sqlManager.query(Company.class).andEq("id", company.getParentId()).select();
            if (!list2.isEmpty()) {
                Company parentCompany = list2.get(0);
                // 父级企业状态判断,新企业不允许挂在禁用企业下;上级企业不能是自己;不能是下级企业
                if (0 == parentCompany.getCompanyStatus()) {
                    logger.error("上级企业：{},已经禁用", parentCompany.getName());
                    throw new BizException(ErrorCodeEnum.COMPANY_CAN_NOT_BE_DISABLED);
                } else if (parentCompany.getId().equals(company.getId())) {
                    logger.error("上级企业不能选择当前企业");
                    throw new BizException(ErrorCodeEnum.COMPANY_CAN_NOT_BE_SELF);
                } else if ((parentCompany.getLeftValue() > originCompany.getLeftValue())
                        && (parentCompany.getRightValue() < originCompany.getRightValue())) {
                    logger.error("上级企业不能选择当前企业的下属企业");
                    throw new BizException(ErrorCodeEnum.COMPANY_CAN_NOT_BE_CHILD);
                }
            }

            // TODO 当上级企业变更时，这个直接更新会有问题，树形结构没有做变更,需要重写（重要）
            if (company.getParentId().equals(originCompany.getParentId())) {
                i = sqlManager.updateTemplateById(company);
            } else {
                // 当上级企业变更时，自定义更新逻辑
                if (updateCompanyLeftRight(originCompany, company, list2.get(0))) {
                    i = 1;
                }
            }
        } catch (BizException e) {
            throw e;
        } catch (Exception e) {
            logger.error("发生未知错误:{}", e);
            throw new BizException(ErrorCodeEnum.COMPANY_UPDATE_EXCEPTION);
        }

        return i == 1;
    }

    /**
     * 更新企业信息（当上级企业变更时适用）
     * 更新逻辑：
     * 1、所有大于当前节点右值的左右值都减去（rightValue-leftValue+1）,此步骤相当于删除子树
     * 2、当前节点及其子节点左右值统一减去（leftValue-1）,此步骤相当于重构子树
     * 3、更新当前节点的上级企业ID，大于父节点左值的节点左右值统一加父节点左值，子树所有节点的左右值统一加父节点左值，此步骤相当于插入子树
     *
     * @param originCompany 原企业信息
     * @param newCompany    更新后企业信息
     * @param parentCompany 新的上级企业
     * @return
     */
    @Transactional
    boolean updateCompanyLeftRight(Company originCompany, Company newCompany, Company parentCompany) throws Exception {
        Integer diffValue = originCompany.getRightValue() - originCompany.getLeftValue() + 1;
        // 先查询出子树所有节点
        Map<String, Object> params = new HashMap<>();
        params.put("tenantId", originCompany.getTenantId());
        params.put("leftValue", originCompany.getLeftValue());
        params.put("rightValue", originCompany.getRightValue());
        List<Company> subtreeNodes = sqlManager.select("company.query_company_subtree", Company.class, params);

        // 更新除子树外的节点的左右值
        params.put("value", diffValue);
        params.put("leftValue", originCompany.getRightValue());
        sqlManager.update("company.update_company_left_del_subtree", params);
        sqlManager.update("company.update_company_right_del_subtree", params);

        // 重新查询父节点的信息
        parentCompany = sqlManager.lambdaQuery(Company.class).andEq(Company::getId, parentCompany.getId()).single();

        // 更新插入子树后的节点的左右值
        params.put("leftValue", parentCompany.getRightValue());
        params.put("value", diffValue);
        sqlManager.update("company.update_company_left_add_subtree", params);
        sqlManager.update("company.update_company_right_add_subtree", params);

        // 记录父节点右值作为子树起始值
        Integer startValue = parentCompany.getRightValue();

        // 更新新父节点的右值
        parentCompany.setRightValue(parentCompany.getRightValue() + diffValue);
        sqlManager.updateTemplateById(parentCompany);

        // 更新子树的左右值
        diffValue = originCompany.getLeftValue();
        for (Company company : subtreeNodes) {
            if (company.getId().equals(originCompany.getId())) {
                newCompany.setParentId(parentCompany.getId());
                newCompany.setLeftValue(company.getLeftValue() - diffValue + startValue);
                newCompany.setRightValue(company.getRightValue() - diffValue + startValue);
                subtreeNodes.set(subtreeNodes.indexOf(company), newCompany);
            } else {
                company.setLeftValue(company.getLeftValue() - diffValue + startValue);
                company.setRightValue(company.getRightValue() - diffValue + startValue);
            }
        }
        sqlManager.updateByIdBatch(subtreeNodes);

        return true;
    }

    @Override
    public List<ItemVO> listCompany(Company company, CurrentUserInfo currentUser) throws Exception {

        List<ItemVO> list = null;
        try {
            list = sqlManager.query(Company.class).andEq("tenant_id", currentUser.getTenantId()).select(ItemVO.class,
                    "id, name");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(ErrorCodeEnum.COMPANY_DICT_EXCEPTION);
        }

        return list;
    }

    @Override
    public List<ItemVO> listCompany(CurrentUserInfo currentUser) {
        List<ItemVO> list = null;
        try {
            if (currentUser != null && currentUser.getTenantId() != null) {
                if (RoleTypeEnum.COMPANY_ADMIN.getValue().equals(currentUser.getRoleType())) {

                    list = sqlManager.execute(new SQLReady(
                            "select sc.id, sc.name from sys_company_manager  scm left join sys_company sc on sc.id = scm.company_id where scm.user_id =?  and scm.tenant_id=?",
                            currentUser.getUserId(), currentUser.getTenantId()), ItemVO.class);
                } else if (RoleTypeEnum.TENANT_ADMIN.getValue().equals(currentUser.getRoleType())) {
                    list = sqlManager.query(Company.class).andEq("tenant_id", currentUser.getTenantId())
                            .select(ItemVO.class, "id, name");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(ErrorCodeEnum.MANAGER_COMPANY_LIST_ERROR);
        }
        return list;
    }

    @Override
    public List<CompanyManagerVO> listCompanyManager(Long companyId, CurrentUserInfo currentUser) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("companyId", companyId);
        params.put("tenantId", currentUser.getTenantId());
        List<CompanyManagerVO> list = sqlManager.select("company.findCompanyManager", CompanyManagerVO.class, params);
        if (list != null && !list.isEmpty()) {
            for (CompanyManagerVO cm : list) {
                params.put("managerId", cm.getId());
                params.put("appId", cm.getAppId());
                List<AppModule> lo = sqlManager.select("company.findAppModuleByManagerIdAndAppId", AppModule.class,
                        params);
                cm.setAppModuleList(lo);
            }
        }
        return list;
    }

    @Override
    @Transactional
    public boolean saveCompanyManager(IdVO idVO) {
        if (idVO == null
                || idVO.getTenantId() == null
                || idVO.getCompanyId() == null
                || idVO.getEmployeeIdList() == null) {
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        Long tenantId = idVO.getTenantId();
        Long companyId = idVO.getCompanyId();
        List<Long> employeeIdList = idVO.getEmployeeIdList();
        List<RelCompanyApp> relCompanyApps = sqlManager.lambdaQuery(RelCompanyApp.class).andEq(RelCompanyApp::getTenantId, tenantId)
                .andEq(RelCompanyApp::getCompanyId, companyId).select();
        // 若企业没有授权应用，则无法设置企业管理员
        if (CollectionUtils.isEmpty(relCompanyApps)) {
            logger.error("该企业没有授权任何应用，需授权应用后再设置企业管理员");
            throw new BizException(ErrorCodeEnum.COMPANY_NO_AUTH_APP);
        }
        employeeIdList.forEach(employeeId -> {
            Employee employee = sqlManager.lambdaQuery(Employee.class)
                    .andEq(Employee::getId, employeeId).single();
            User user = sqlManager.lambdaQuery(User.class).andEq(User::getId, employee.getUserId()).single();
            if (employee == null || user == null) {
                throw new BaseException(ErrorCodeEnum.USER_INVALID);
            }
            Long userId = employee.getUserId();
            Tenant tenant = sqlManager.lambdaQuery(Tenant.class).andEq(Tenant::getId, tenantId).single();
            if (Objects.equals(tenant.getUserId(), userId)) {
                throw new BaseException(ErrorCodeEnum.EMPLOYEE_IS_TENANT);
            }
            CompanyManager companyManager = new CompanyManager();
            long managerId = LongIdGen.get().nextId();
            companyManager.setId(managerId);
            companyManager.setCompanyId(companyId);
            companyManager.setTenantId(tenantId);
            companyManager.setEmployeeId(employeeId);
            companyManager.setUserId(userId);
            companyManager.setName(employee.getUserName());
            companyManager.setCtime(new Date());
            companyManager.setMtime(new Date());
            sqlManager.insertTemplate(companyManager);
            // 查询企业管理员角色信息
            List<GroupAppRole> groupAppRoles = sqlManager.lambdaQuery(GroupAppRole.class)
                    .andEq(GroupAppRole::getCompanyId, companyId)
                    .andEq(GroupAppRole::getTenantId, tenantId)
                    .andEq(GroupAppRole::getRoleType, RoleTypeEnum.COMPANY_ADMIN.getValue())
                    .select();
            GroupAppRole groupAppRole = new GroupAppRole();
            if (CollectionUtils.isEmpty(groupAppRoles)) {
                // 创建一个企业管理员的角色关联，否则无法登陆
                groupAppRole.setId(LongIdGen.get().nextId());
                groupAppRole.setRoleType(RoleTypeEnum.COMPANY_ADMIN.getValue());
                Company single = sqlManager.lambdaQuery(Company.class)
                        .andEq(Company::getId, companyId).single();
                if (single == null) {
                    throw new BaseException(ErrorCodeEnum.COMPANY_NOEXISTS);
                }
                groupAppRole.setName(single.getName() + "企业管理员");
                groupAppRole.setCompanyId(companyId);
                groupAppRole.setTenantId(tenantId);
                groupAppRole.setEnableFlag(true);
                sqlManager.insertTemplate(groupAppRole);
            } else {
                groupAppRole = groupAppRoles.get(0);
            }

            RelUserGroupRole userGroupRole = new RelUserGroupRole();
            userGroupRole.setId(LongIdGen.get().nextId());
            userGroupRole.setCompanyId(companyId);
            userGroupRole.setTenantId(tenantId);
            userGroupRole.setUserId(userId);
            userGroupRole.setRoleId(groupAppRole.getId());
            userGroupRole.setEmployeeId(employeeId);
            sqlManager.insertTemplate(userGroupRole);

            // 记录是否已经存在企业与APP关联
            List<RelCompanyApp> existReltion = new ArrayList<>();

            relCompanyApps.forEach(relCompanyApp -> {
                RelManagerDeploymodule relManagerDeploymodule = new RelManagerDeploymodule();
                relManagerDeploymodule.setId(LongIdGen.get().nextId());
                relManagerDeploymodule.setDeploymoduleId(relCompanyApp.getDeploymoduleId());
                relManagerDeploymodule.setAppId(relCompanyApp.getAppId());
                relManagerDeploymodule.setManagerId(managerId);
                relManagerDeploymodule.setCtime(new Date());
                relManagerDeploymodule.setMtime(new Date());
                sqlManager.insertTemplate(relManagerDeploymodule);

                RelCompanyApp rca = new RelCompanyApp();
                rca.setAppId(relCompanyApp.getAppId());
                rca.setCompanyId(relCompanyApp.getCompanyId());
                // 插入管理员与应用关联
                if (!listContain(existReltion, rca)) {
                    RelCompanyManagerApp relCompanyManagerApp = new RelCompanyManagerApp();
                    relCompanyManagerApp.setId(LongIdGen.get().nextId());
                    relCompanyManagerApp.setAppId(relCompanyApp.getAppId());
                    relCompanyManagerApp.setCompanyId(companyId);
                    relCompanyManagerApp.setTenantId(tenantId);
                    relCompanyManagerApp.setManagerId(companyManager.getId());
                    relCompanyManagerApp.setCtime(new Date());
                    relCompanyManagerApp.setMtime(new Date());
                    sqlManager.insertTemplate(relCompanyManagerApp);
                    existReltion.add(rca);
                }
            });
        });

        return true;
    }

    private boolean listContain(List<RelCompanyApp> relCompanyApps, RelCompanyApp rca) {
        if (null == relCompanyApps || null == rca) {
            return false;
        }
        if (CollectionUtils.isEmpty(relCompanyApps)) {
            return false;
        }
        for (RelCompanyApp relCompanyApp : relCompanyApps) {
            if (relCompanyApp.getAppId().equals(rca.getAppId()) &&
                    relCompanyApp.getCompanyId().equals(rca.getCompanyId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteCompanyManager(Long companyId, Long appId, CurrentUserInfo currentUser) throws Exception {
        try {
            if (companyId != null && companyId != null) {
                if (currentUser.getTenantId() != null) {
                    //查出管理员详细信息
                    CompanyManager companyManager = sqlManager.lambdaQuery(CompanyManager.class)
                            .andEq(CompanyManager::getId, companyId).single();
                    // 查出企业管理员角色ID
                    List<GroupAppRole> groupAppRoles = sqlManager.lambdaQuery(GroupAppRole.class)
                            .andEq(GroupAppRole::getCompanyId, companyManager.getCompanyId())
                            .andEq(GroupAppRole::getTenantId, companyManager.getTenantId())
                            .andEq(GroupAppRole::getRoleType, RoleTypeEnum.COMPANY_ADMIN.getValue())
                            .select();
                    Long roleId = null;
                    if (groupAppRoles.size() > 0) {
                        roleId = groupAppRoles.get(0).getId();
                    }
                    DSTransactionManager.start();

                    sqlManager.executeUpdate(new SQLReady("delete from rel_manager_deploymodule where manager_id=? and app_Id=?",
                            companyManager.getId(), appId));
                    List<RelManagerDeploymodule> managerDeploymodules = sqlManager.lambdaQuery(RelManagerDeploymodule.class)
                            .andEq(RelManagerDeploymodule::getId, companyManager.getId()).select();
                    if (managerDeploymodules .size()==0) {
                        sqlManager
                                .executeUpdate(new SQLReady("delete from sys_company_manager where id=?", companyManager.getId()));

                        if (null != roleId) {
                            sqlManager.executeUpdate(
                                    new SQLReady("delete from rel_user_group_role where role_id=? and user_id=? and employee_id=?", roleId, companyManager.getUserId(), companyManager.getEmployeeId()));
                        }
                        // 删除管理员与APP关联表
                        sqlManager.lambdaQuery(RelCompanyManagerApp.class)
                                .andEq(RelCompanyManagerApp::getManagerId, companyManager.getId()).delete();
                    }
                    DSTransactionManager.commit();
                } else {
                    throw new BizException(ErrorCodeEnum.TENANT_ID_ERROR);
                }
            } else {
                throw new BizException(ErrorCodeEnum.PARAM_ERROR);
            }
        } catch (Exception e) {
            DSTransactionManager.rollback();
            e.printStackTrace();
            throw new BizException(ErrorCodeEnum.MANAGER_DELETE_EXCEPTION);
        }

        return true;
    }

    @Override
    public boolean editManagerDeploymodule(Long managerId, Long appId, List<RelManagerDeploymodule> moduleList,
                                           CurrentUserInfo currentUser) throws Exception {
        try {
            if (moduleList != null && managerId != null && !moduleList.isEmpty()) {
                if (currentUser.getTenantId() != null) {

                    Date d = new Date();
                    Set appSet = new HashSet();
                    for (RelManagerDeploymodule rmd : moduleList) {
                        long id = LongIdGen.get().nextId();
                        rmd.setId(id);
                        rmd.setCtime(d);
                        rmd.setMtime(d);
                        appSet.add(rmd.getAppId());
                    }
                    DSTransactionManager.start();
                    sqlManager.lambdaQuery(RelManagerDeploymodule.class)
                            .andEq(RelManagerDeploymodule::getManagerId, managerId)
                            .delete();
                    sqlManager.insertBatch(RelManagerDeploymodule.class, moduleList);
                    DSTransactionManager.commit();
                } else {
                    throw new BizException(ErrorCodeEnum.TENANT_ID_ERROR);
                }
            } else {
                sqlManager.lambdaQuery(RelManagerDeploymodule.class)
                        .andEq(RelManagerDeploymodule::getManagerId, managerId)
                        .delete();
            }
        } catch (SQLException e) {
            DSTransactionManager.rollback();
            throw e;
        } catch (BizException e) {
            logger.error("保存企业管理员配置包信息失败：{}", e.getMessage());
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public List<App> getCompanyRoleList(Long companyId, CurrentUserInfo currentUser) throws Exception {
        List<App> list = new ArrayList<App>();
        if (currentUser.getTenantId() != null) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("companyId", companyId);
            params.put("tenantId", currentUser.getTenantId());
            List<AppRole> lo = sqlManager.select("company.findAppRoleByCompany", AppRole.class, params);

            if (lo != null && !lo.isEmpty()) {
                Map<String, Object> app = new HashMap<String, Object>();
                for (AppRole o : lo) {
                    if (!app.containsKey(o.getId().toString())) {
                        app.put(o.getId().toString(), new App());
                        App a = (App) app.get(o.getId().toString());
                        a.setId(o.getId());
                        a.setName(o.getName());
                        a.setIconUrl(o.getIconUrl());
                        a.setExhibitImageUrl(o.getExhibitImageUrl());
                        a.setDisplayName(o.getDisplayName());
                        ArrayList<GroupAppRole> li = new ArrayList<GroupAppRole>();
                        GroupAppRole gar = new GroupAppRole();
                        gar.setAppId(o.getId());
                        gar.setId(o.getRoleId());
                        gar.setName(o.getRoleName());
                        li.add(gar);
                        a.setListAppRole(li);
                        list.add(a);
                    } else {
                        App a = (App) app.get(o.getId().toString());
                        List<GroupAppRole> li = a.getListAppRole();
                        GroupAppRole gar = new GroupAppRole();
                        gar.setAppId(o.getId());
                        gar.setId(o.getRoleId());
                        gar.setName(o.getRoleName());
                        li.add(gar);
                    }
                }
            }
        } else {
            throw new BizException(ErrorCodeEnum.TENANT_ID_ERROR);
        }

        return list;
    }

    @Override
    public boolean saveCompanyApp(List<RelCompanyApp> rcaList, CurrentUserInfo currentUser) throws Exception {

        try {
            if (rcaList != null && !rcaList.isEmpty()) {
                Date d = new Date();
                for (RelCompanyApp cm : rcaList) {
                    long id = LongIdGen.get().nextId();
                    cm.setId(id);
                    cm.setCtime(d);
                    cm.setMtime(d);
                    cm.setTenantId(currentUser.getTenantId());
                }
                DSTransactionManager.start();
                sqlManager.insertBatch(RelCompanyApp.class, rcaList);
                DSTransactionManager.commit();
            } else {
                throw new BizException(ErrorCodeEnum.PARAM_ERROR);
            }
        } catch (Exception e) {
            DSTransactionManager.rollback();
            e.printStackTrace();
            throw e;
        }

        return true;
    }

    @Override
    public List<AppModule> getManagerDeploymodule(Long managerId, Long appId, CurrentUserInfo currentUser) {
        List<AppModule> lo = null;
        if (currentUser.getTenantId() != null) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("managerId", managerId);
            params.put("appId", appId);
            lo = sqlManager.select("company.findAppModuleByManagerIdAndAppId", AppModule.class, params);
        } else {
            throw new BizException(ErrorCodeEnum.TENANT_ID_ERROR);
        }

        return lo;
    }

    @Override
    @Transactional
    public List<AppDeployModuleVO> appListAndDeployModule(Long companyId, CurrentUserInfo currentUserInfo) throws Exception {
        List<AppDeployModuleVO> appList = new ArrayList<>();

        try {
            // 优化建议：循环查询需要优化
            try {
                Map<String, Object> params = new HashMap<>();
                params.put("tenantId", currentUserInfo.getTenantId());
                params.put("userId", currentUserInfo.getUserId());
                appList = sqlManager.select("company.query_tenant_app_list", AppDeployModuleVO.class, params);
                if (!CollectionUtils.isEmpty(appList)) {
                    Iterator it = appList.iterator();
                    while (it.hasNext()) {
                        AppDeployModuleVO app = (AppDeployModuleVO) it.next();  //把Object型强转成int型

                        params.put("appId", app.getId());
                        params.put("versionId", app.getMarketVersionId());
                        params.put("companyId", companyId);
                        List<AppDeploymodule> deploymodules = sqlManager.select("company.query_app_deploy_module_list",
                                AppDeploymodule.class, params);

                        // TODO 查询已经分配的配置包
                        /*List<AppDeploymodule> authedDeployModules = sqlManager.select("company.",
                                AppDeploymodule.class, params);*/
                        List<DeployModuleVO> deployModuleVOList = new ArrayList<>();
                        if (!CollectionUtils.isEmpty(deploymodules)) {
                            for (AppDeploymodule deploymodule : deploymodules) {
                                DeployModuleVO dvo = new DeployModuleVO();
                                BeanUtils.copyProperties(deploymodule, dvo);
                                dvo.setChecked(false);
                                deployModuleVOList.add(dvo);
                            }
                            /*for (AppDeploymodule deploymodule : authedDeployModules){
                                DeployModuleVO dvo = new DeployModuleVO();
                                BeanUtils.copyProperties(deploymodule, dvo);
                                dvo.setChecked(true);
                                deployModuleVOList.add(dvo);
                            }*/
                            app.setDeployModules(deployModuleVOList);
                            if (StringUtils.isEmpty(app.getIconUrl())) {
                                app.setIconUrl(Constants.DEFAULT_ICON);
                            }
                        } else {
                            it.remove();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new BizException(ErrorCodeEnum.DB_SELECT_EXCEPTION);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }


        return appList;
    }

    @Override
    public List<AppDeployModuleVO> listCompanyApp(IdVO idVO) throws Exception {
        if (idVO == null || idVO.getTenantId() == null
                || idVO.getCompanyId() == null) {
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        Long tenantId = idVO.getTenantId();
        Long companyId = idVO.getCompanyId();
        Long appId = idVO.getAppId();
        List<AppDeployModuleVO> appList;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("tenantId", tenantId);
            params.put("companyId", companyId);
            if (appId != null) {
                params.put("appId", appId);
            }
            appList = sqlManager.select("company.getCompanyAppList", AppDeployModuleVO.class, params);
            if (!CollectionUtils.isEmpty(appList)) {
                for (AppDeployModuleVO app : appList) {
                    params.put("appId", app.getId());
                    List<AppDeploymodule> deploymodules = sqlManager.select("company.getAppDeployModuleList",
                            AppDeploymodule.class, params);
                    List<DeployModuleVO> deployModuleVOList = new ArrayList<>();
                    if (!CollectionUtils.isEmpty(deploymodules)) {
                        for (AppDeploymodule deploymodule : deploymodules) {
                            DeployModuleVO dvo = new DeployModuleVO();
                            BeanUtils.copyProperties(deploymodule, dvo);
                            deployModuleVOList.add(dvo);
                        }
                    }
                    app.setDeployModules(deployModuleVOList);
                    if (StringUtils.isEmpty(app.getIconUrl())) {
                        app.setIconUrl(Constants.DEFAULT_ICON);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(ErrorCodeEnum.DB_SELECT_EXCEPTION);
        }

        return appList;
    }

    @Override
    public boolean deleteCompanyAppModule(Long companyId, Long appId, Long deployModuleId) {
        List<RelCompanyApp> companyApps = sqlManager.lambdaQuery(RelCompanyApp.class)
                .andEq(RelCompanyApp::getAppId, appId)
                .andEq(RelCompanyApp::getCompanyId, companyId)
                .andEq(RelCompanyApp::getDeploymoduleId, deployModuleId).select();

        if (companyApps != null && companyApps.size() != 0) {
            for (RelCompanyApp companyApp : companyApps) {
                sqlManager.deleteById(RelCompanyApp.class, companyApp.getId());
            }
        }

        return true;
    }

    @Override
    public List<ItemVO> dictCompanyApp(Long companyId, CurrentUserInfo currentUser) throws Exception {
        List<ItemVO> appList = new ArrayList<>();
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("companyId", companyId);
            appList = sqlManager.select("company.getCompanyAppDict", ItemVO.class, params);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(ErrorCodeEnum.DB_SELECT_EXCEPTION);
        }

        return appList;
    }

    @Override
    public PageQuery queryCompanyByPageForManager(CurrentUserInfo currentUserInfo,
                                                  String companyName,
                                                  String contact,
                                                  String telephone,
                                                  Integer pageNo,
                                                  Integer pageSize) {
        Long tenantId = currentUserInfo.getTenantId();
        Map<String, Object> params = new HashMap<>();
        params.put("tenantId", tenantId);
        params.put("userId", currentUserInfo.getUserId());
        params.put("contact", contact);
        params.put("companyName", companyName);
        params.put("telephone", telephone);
        PageQuery<CompanyVO> query = new PageQuery<>();
        query.setParas(params);
        query.setPageNumber(pageNo);
        query.setPageSize(pageSize);
        try {
            query = sqlManager.pageQuery("company.query_company_by_page_for_manager", CompanyVO.class, query);
            if (!CollectionUtils.isEmpty(query.getList())) {
                for (CompanyVO companyVO : query.getList()) {
                    Long counts = sqlManager.lambdaQuery(Employee.class)
                            .andEq(Employee::getCompanyId, companyVO.getId()).count();
                    companyVO.setCounts(counts.intValue());
                }
            }
            return query;
        } catch (Exception e) {
            logger.error("查询企业管理员的企业列表失败：参数->{}", params.toString());
            e.printStackTrace();
            throw new BizException(ErrorCodeEnum.DB_SELECT_EXCEPTION);
        }
    }
}
