package com.chejet.cloud.service.impl;

import com.chejet.cloud.common.*;
import com.chejet.cloud.constant.Constants;
import com.chejet.cloud.dao.EmployeeMapper;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.exception.BizException;
import com.chejet.cloud.po.*;
import com.chejet.cloud.service.EmployeeService;
import com.chejet.cloud.service.VerificationCodeService;
import com.chejet.cloud.util.LongIdGen;
import com.chejet.cloud.util.PasswordProvider;
import com.chejet.cloud.util.StringUtils;
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

import java.util.*;

/**
 * 员工管理biz 实现
 *
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/13
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    private SQLManager sqlManager;
    @Autowired
    private VerificationCodeService verificationCodeService;

    @Override
    public PageQuery<EmployeeVO> pageEmployee(Employee employee, CurrentUserInfo currentUser) {

        PageQuery<EmployeeVO> query = null;
        PageQuery<EmployeeVO> page = null;
        try {
            if (employee != null && employee.getCompanyId() != null) {
                query = new PageQuery<EmployeeVO>();
                query.setPageNumber(employee.getPageNumber());
                query.setPageSize(employee.getPageSize());
                if (!StringUtils.isEmpty(employee.getTelephone())) {
                    query.setPara("telephone", employee.getTelephone());
                }
                if (!StringUtils.isEmpty(employee.getName())) {
                    query.setPara("name", employee.getName());
                }

                if (null != employee.getDepartmentId()) {
                    query.setPara("departmentId", employee.getDepartmentId());
                }
                query.setPara("companyId", employee.getCompanyId());

                if (!StringUtils.isBlank(employee.getOrderBy())) {
                    query.setOrderBy(employee.getOrderBy());
                }
                page = sqlManager.pageQuery("employee.pageEmployee", EmployeeVO.class, query);
            } else {
                throw new BizException(ErrorCodeEnum.PARAM_ERROR);
            }
        } catch (Exception e) {
            logger.error("查询员工列表出错：{}", e.getMessage());
            throw new BizException(ErrorCodeEnum.EMPLOYEE_PAGE_GET_ERROR);
        }

        return page;
    }

    @Override
    public List<EmployeeVO> listEmployee(Employee employee, CurrentUserInfo currentUser) {
        List<EmployeeVO> list = null;
        try {
            if (employee != null && employee.getCompanyId() != null) {

                Map<String, Object> map = new HashMap<String, Object>();

                if (!StringUtils.isEmpty(employee.getTelephone())) {
                    map.put("telephone", employee.getTelephone());
                }

                if (!StringUtils.isEmpty(employee.getName())) {
                    map.put("name", employee.getName());
                }

                map.put("companyId", employee.getCompanyId());

                list = sqlManager.select("employee.listEmployee", EmployeeVO.class, map);
                for(EmployeeVO employeeVO : list){
                    employeeVO.setSort(list.indexOf(employeeVO)+1);
                    if (EmployeeStatusEnum.ONJOB.getCode().equals(employeeVO.getStatus())){
                        employeeVO.setStatusChs(EmployeeStatusEnum.ONJOB.getValue());
                    }else {
                        employeeVO.setStatusChs(EmployeeStatusEnum.LEAVEJOB.getValue());
                    }
                }
            } else {
                throw new BizException(ErrorCodeEnum.PARAM_ERROR);
            }
        } catch (Exception e) {
            logger.error("员工列表导出出错：{}", e.getMessage());
            throw new BizException(ErrorCodeEnum.EMPLOYEE_EXPORT_ERROR);
        }

        return list;
    }

    @Override
    public boolean saveEmployee(EmployeeVO employeeVO, CurrentUserInfo currentUser) throws Exception {

        int i = 0;
        try {
            if (currentUser.getTenantId() != 0L) {
                // 判断输入的手机号是不是租户管理员
                List<Tenant> tenants = sqlManager.lambdaQuery(Tenant.class)
                        .andEq(Tenant::getId, currentUser.getTenantId()).select();
                Employee employee = new Employee();
                BeanUtils.copyProperties(employeeVO,employee);
                if (tenants.size() == 1) {
                    User tenantManager = sqlManager.lambdaQuery(User.class)
                            .andEq(User::getId, tenants.get(0).getUserId()).single();
                    if (tenantManager.getTelephone().equals(employee.getTelephone())) {
                        throw new BizException(ErrorCodeEnum.TENANT_CANNOT_BE_EMPLOYEE);
                    }
                }
                if (employee != null && employee.getDepartmentId() != null && employee.getCompanyId() != null
                        && !StringUtils.isEmpty(employee.getTelephone()) && !StringUtils.isEmpty(employee.getName())) {
                    Query<User> query = sqlManager.query(User.class);
                    User user = query.andEq("telephone", employee.getTelephone()).single("id", "telephone");
                    if (user == null) {
                        if (employee.getRelRoleList() != null) {
                            User u = new User();
                            long userId = LongIdGen.get().nextId();
                            u.setId(userId);
                            if (null != employeeVO.getPortraitUrlId()){
                                u.setPortraitUrlId(employeeVO.getPortraitUrlId());
                            }
                            u.setNickname(employee.getTelephone());
//                            u.setUsername(employee.getTelephone());
                            String defaultPass = PasswordProvider.generateRandomPassword(8, true);
                            String Password = PasswordProvider.encrypt(defaultPass);
                            u.setPassword(Password);
                            u.setAccountStatus(AccountStatus.NORMAL.getCode());
                            u.setTelephone(employee.getTelephone());
                            u.setLoginCount(0);
                            u.setModifiedFlag(true);
                            Date d = new Date();
                            u.setCtime(d);
                            u.setMtime(d);
                            u.setRegisterTime(d);

                            long id = LongIdGen.get().nextId();
                            employee.setId(id);
                            employee.setCtime(d);
                            employee.setMtime(d);
                            employee.setUserId(userId);
                            employee.setUserName(employeeVO.getName());
                            employee.setStatus(1);
                            employee.setCompanyId(employeeVO.getCompanyId());
                            employee.setTenantId(currentUser.getTenantId());

                            List<EmployeeAppScope> scopePermissonList = new ArrayList<EmployeeAppScope>(
                                    employee.getRelRoleList().size());
                            for (RelUserGroupRole ur : employee.getRelRoleList()) {
                                long id2 = LongIdGen.get().nextId();
                                ur.setId(id2);
                                ur.setCtime(d);
                                ur.setMtime(d);
                                ur.setEmployeeId(employee.getId());
                                ur.setCompanyId(employee.getCompanyId());
                                ur.setUserId(userId);
                                ur.setTenantId(currentUser.getTenantId());

                                EmployeeAppScope as = new EmployeeAppScope();

                                long id3 = LongIdGen.get().nextId();
                                as.setId(id3);
                                as.setScopeCode("department");
                                as.setCtime(d);
                                as.setMtime(d);
                                as.setAppId(ur.getAppId());
                                as.setEmployeeId(employee.getId());
                                as.setCompanyId(employee.getCompanyId());
                                as.setUserId(userId);
                                as.setTenantId(currentUser.getTenantId());
                                scopePermissonList.add(as);
                            }

                            DSTransactionManager.start();
                            sqlManager.insertTemplate(u);
                            sqlManager.insertTemplate(employee);
                            sqlManager.insertBatch(RelUserGroupRole.class, employee.getRelRoleList());
                            sqlManager.insertBatch(EmployeeAppScope.class, scopePermissonList);
                            //创建完成后需要短信通知初始密码
                            String tenantPhone = employeeMapper.findTenantPhone(currentUser.getTenantId());
                            String telephone = employee.getTelephone();
                            String[] para = {tenantPhone, telephone, defaultPass, Constants.PRODUCT_URL};
                            verificationCodeService.pushSmsMessage(telephone, para);
                            DSTransactionManager.commit();
                        } else {
                            User u = new User();
                            long userId = LongIdGen.get().nextId();
                            u.setId(userId);
                            if (null != employeeVO.getPortraitUrlId()){
                                u.setPortraitUrlId(employeeVO.getPortraitUrlId());
                            }
                            u.setNickname(employee.getTelephone());
                            u.setPassword(PasswordProvider.encrypt(PasswordProvider.generateRandomPassword(6, true)));
                            u.setAccountStatus(AccountStatus.NORMAL.getCode());
                            u.setTelephone(employee.getTelephone());
                            u.setModifiedFlag(true);
                            Date d = new Date();
                            u.setCtime(d);
                            u.setMtime(d);
                            u.setRegisterTime(d);

                            long id = LongIdGen.get().nextId();
                            employee.setId(id);
                            employee.setCtime(d);
                            employee.setMtime(d);
                            employee.setUserId(userId);
                            employee.setStatus(1);
                            employee.setUserName(employeeVO.getName());
                            employee.setCompanyId(employeeVO.getCompanyId());
                            employee.setTenantId(currentUser.getTenantId());

                            DSTransactionManager.start();
                            sqlManager.insertTemplate(u);
                            sqlManager.insertTemplate(employee);
                            DSTransactionManager.commit();
                        }

                    } else {
                        List<Employee> eList = sqlManager.execute(new SQLReady(
                                        "SELECT se.*, su.telephone FROM sys_employee se "
                                                + "LEFT JOIN sys_user su ON se.user_id = su.id WHERE su.telephone=? "
                                                + "AND se.company_id=? and se.tenant_id=?",
                                        employee.getTelephone(), employee.getCompanyId(), currentUser.getTenantId()),
                                Employee.class);
                        // TODO 如果手机号注册用户已经存在，新增员工时是否需要更新用户资料，如头像。

                        if (eList != null && !eList.isEmpty()) {
                            throw new BizException(ErrorCodeEnum.EMPLOYEE_EXISTS);
                        } else {
                            if (employee.getRelRoleList() != null) {
                                // 创建员工
                                long id = LongIdGen.get().nextId();
                                employee.setId(id);
                                Date d = new Date();
                                employee.setCtime(d);
                                employee.setMtime(d);
                                employee.setUserId(user.getId());
                                employee.setStatus(1);
                                employee.setUserName(employeeVO.getName());
                                employee.setTenantId(currentUser.getTenantId());

                                // 创建角色
                                List<EmployeeAppScope> scopePermissonList = new ArrayList<EmployeeAppScope>(
                                        employee.getRelRoleList().size());
                                for (RelUserGroupRole ur : employee.getRelRoleList()) {
                                    long id2 = LongIdGen.get().nextId();
                                    ur.setId(id2);
                                    ur.setCtime(d);
                                    ur.setMtime(d);
                                    ur.setEmployeeId(employee.getId());
                                    ur.setCompanyId(employee.getCompanyId());
                                    ur.setUserId(user.getId());
                                    ur.setTenantId(currentUser.getTenantId());
                                    EmployeeAppScope as = new EmployeeAppScope();

                                    long id3 = LongIdGen.get().nextId();
                                    as.setId(id3);
                                    as.setScopeCode("department");
                                    as.setCtime(d);
                                    as.setMtime(d);
                                    as.setAppId(ur.getAppId());
                                    as.setEmployeeId(employee.getId());
                                    as.setCompanyId(employee.getCompanyId());
                                    as.setUserId(user.getId());
                                    as.setTenantId(currentUser.getTenantId());
                                    scopePermissonList.add(as);

                                }

                                DSTransactionManager.start();
                                sqlManager.insertTemplate(employee);
                                sqlManager.insertBatch(RelUserGroupRole.class, employee.getRelRoleList());
                                sqlManager.insertBatch(EmployeeAppScope.class, scopePermissonList);
                                DSTransactionManager.commit();

                            } else {
                                // 创建员工
                                long id = LongIdGen.get().nextId();
                                employee.setId(id);
                                Date d = new Date();
                                employee.setCtime(d);
                                employee.setMtime(d);
                                employee.setUserId(user.getId());
                                employee.setStatus(1);
                                employee.setUserName(employeeVO.getName());
                                employee.setTenantId(currentUser.getTenantId());
                                i = sqlManager.insertTemplate(employee);
                            }
                        }
                    }
                } else {
                    throw new BizException(ErrorCodeEnum.PARAM_ERROR);
                }
            } else {
                throw new BizException(ErrorCodeEnum.TENANT_ID_ERROR);
            }

        } catch (BizException e) {
            DSTransactionManager.rollback();
            e.printStackTrace();
            throw e;
        }

        return true;
    }

    @Override
    public boolean deleteEmployee(Long companyId, Long employeeId, CurrentUserInfo currentUser) throws Exception {

        try {
            if (currentUser.getTenantId() != null) {
                if (companyId != null && employeeId != null) {
                    Query<Employee> query = sqlManager.query(Employee.class);
                    Employee employee = query.andEq("id", employeeId).single();
                    if (employee != null) {
                        DSTransactionManager.start();
                        sqlManager.executeUpdate(new SQLReady("delete from sys_employee where id=?", employeeId));
                        sqlManager.executeUpdate(
                                new SQLReady("delete from rel_user_group_role where employee_id=? and company_id=?",
                                        employeeId, companyId));
                        sqlManager.executeUpdate(
                                new SQLReady("delete from sys_employee_app_scope where employee_id=? and company_id=?",
                                        employeeId, companyId));
                        DSTransactionManager.commit();
                    } else {
                        throw new BizException(ErrorCodeEnum.EMPLOYEE_NONEXISTS);
                    }
                } else {
                    throw new BizException(ErrorCodeEnum.EMPLOYEE_NONEXISTS);
                }

            }
        } catch (BizException e) {
            DSTransactionManager.rollback();
            e.printStackTrace();
            throw e;
        }

        return true;
    }

    @Override
    public boolean editEmployee(EmployeeVO employeeVO, CurrentUserInfo currentUser) throws Exception {
        int i = 0;

        try {
            if (currentUser.getTenantId() != 0L) {

                Employee employee = new Employee();
                BeanUtils.copyProperties(employeeVO,employee);
                if (employee != null && employee.getDepartmentId() != null && employee.getCompanyId() != null
                        && !StringUtils.isEmpty(employee.getTelephone()) && !StringUtils.isEmpty(employee.getName())) {
                    Date d = new Date();
                    employee.setMtime(d);
                    employee.setUserName(employeeVO.getName());

                    if (0 == employeeVO.getStatus()){
                        Employee originEmployee = sqlManager.lambdaQuery(Employee.class)
                                .andEq(Employee::getId,employeeVO.getId()).single();
                        if (1 == originEmployee.getStatus()){
                            employee.setLeaveDate(d);
                        }
                    }
                    // 同步更新用户表
                    User user = new User();
                    user.setId(employee.getUserId());
                    user.setEmail(employee.getEmail());
                    user.setTelephone(employee.getTelephone());
                    if (null != employeeVO.getPortraitUrlId()){
                        user.setPortraitUrlId(employeeVO.getPortraitUrlId());
                    }
                    DSTransactionManager.start();
                    i = sqlManager.updateTemplateById(employee);
                    i &= sqlManager.updateTemplateById(user);
                    sqlManager.executeUpdate(
                            new SQLReady("delete from rel_user_group_role where employee_id=? and company_id=?",
                                    employee.getId(), employee.getCompanyId()));
                    if (employee.getRelRoleList() != null && !employee.getRelRoleList().isEmpty()) {
                        for (RelUserGroupRole ur : employee.getRelRoleList()) {
                            long id = LongIdGen.get().nextId();
                            ur.setId(id);
                            ur.setCtime(d);
                            ur.setMtime(d);
                            ur.setEmployeeId(employee.getId());
                            ur.setCompanyId(employee.getCompanyId());
                            ur.setUserId(employee.getUserId());
                            ur.setTenantId(currentUser.getTenantId());
                        }
                        sqlManager.insertBatch(RelUserGroupRole.class, employee.getRelRoleList());
                    }
                    DSTransactionManager.commit();
                } else {
                    throw new BizException(ErrorCodeEnum.PARAM_ERROR);
                }
            }

        } catch (Exception e) {
            DSTransactionManager.rollback();
            e.printStackTrace();
            throw e;
        }

        return i == 1;
    }

    @Override
    public Employee listEmployeePermisson(Long employeeId, Long appId, CurrentUserInfo currentUser) {
        // id,appId
        Employee employee = new Employee();
        int i = 0;

        if (currentUser.getTenantId() != 0L) {
            if (employeeId != null && appId != null) {
                employee.setId(employeeId);
                employee.setAppId(appId);
                List<GroupAppRole> eList = sqlManager.execute(new SQLReady(
                        "SELECT sr.* FROM sys_group_app_role sr  LEFT JOIN rel_user_group_role rr ON sr.id = rr.role_id WHERE rr.employee_id=? and rr.app_id=?",
                        employeeId, appId), GroupAppRole.class);
                if (eList != null && !eList.isEmpty()) {
                    for (GroupAppRole ar : eList) {
                        ar.setRelDataPermission(sqlManager.lambdaQuery(RelRoleDataPermission.class)
                                .andEq(RelRoleDataPermission::getRoleId, ar.getId()).select());
                        ar.setRelPointPermission(sqlManager.lambdaQuery(RelRolePointPermission.class)
                                .andEq(RelRolePointPermission::getRoleId, ar.getId()).select());
                    }

                    employee.setGroupAppRole(eList);

                    employee.setScopePermisson(sqlManager.lambdaQuery(EmployeeAppScope.class)
                            .andEq(EmployeeAppScope::getEmployeeId, employeeId).andEq(EmployeeAppScope::getAppId, appId)
                            .single());

                } else {
                    throw new BizException(ErrorCodeEnum.USER_ROLE_INVALID);
                }
            } else {
                throw new BizException(ErrorCodeEnum.PARAM_ERROR);
            }

        } else {
            throw new BizException(ErrorCodeEnum.TENANT_ID_ERROR);
        }

        return employee;
    }

    @Override
    public boolean editEmployeePermisson(Employee employee, CurrentUserInfo currentUser) throws Exception {
        DSTransactionManager.start();
        try {
            if (currentUser.getTenantId() != 0L) {
                Date d = new Date();
                if (employee.getRelRoleList() != null && !employee.getRelRoleList().isEmpty()) {
                    for (RelUserGroupRole ur : employee.getRelRoleList()) {
                        sqlManager.executeUpdate(
                                new SQLReady("delete from rel_user_group_role where employee_id=? and app_id=?",
                                        employee.getId(), ur.getAppId()));
                        long id = LongIdGen.get().nextId();
                        ur.setId(id);
                        ur.setCtime(d);
                        ur.setMtime(d);
                        ur.setCompanyId(employee.getCompanyId());
                        ur.setEmployeeId(employee.getId());
                        ur.setUserId(employee.getUserId());
                        ur.setTenantId(currentUser.getTenantId());
                    }

                    sqlManager.insertBatch(RelUserGroupRole.class, employee.getRelRoleList());
                } else {
                    throw new BizException(ErrorCodeEnum.EMPLOYEE_APP_ROLE_NULL);
                }
                if (employee.getScopePermisson() != null) {
                    EmployeeAppScope as = employee.getScopePermisson();
                    as.setMtime(d);
                    as.setUserId(employee.getUserId());
                    as.setCompanyId(employee.getCompanyId());
                    as.setTenantId(currentUser.getTenantId());
                    sqlManager.updateTemplateById(as);
                } else {
                    throw new BizException(ErrorCodeEnum.EMPLOYEE_APP_SCOPE_NULL);
                }
            } else {
                throw new BizException(ErrorCodeEnum.TENANT_ID_ERROR);
            }
        } catch (Exception e) {
            DSTransactionManager.rollback();
            e.printStackTrace();
            throw e;
        }

        DSTransactionManager.commit();

        return true;
    }

    @Override
    public List queryEmployeeAppRoleList(Long employeeId, Long companyId) {
        List<EmployeeAppRole> result = new ArrayList<>();
        // 首先查出所在公司拥有的应用
        Company company = queryCompanyInfo(companyId);
        if (null == company) {
            return null;
        }
        Long tenantId = company.getTenantId();
        Map<String, Object> params = new HashMap<>();
        params.put("tenantId", tenantId);
        params.put("companyId", companyId);
        params.put("employeeId", employeeId);
        List<EmployeeAppRole> appList = sqlManager.select("employee.query_company_app", EmployeeAppRole.class, params);
        if (!CollectionUtils.isEmpty(appList)) {
            for (EmployeeAppRole ear : appList) {
                ear.setEmployeeId(employeeId);
                // 查出应用下的所有角色
                params.put("appId", ear.getAppId());
                List<RoleItem> appRoles = sqlManager.select("employee.query_company_app_roles", RoleItem.class, params);
                ear.setRoleList(appRoles);

                // 查出员工拥有的角色列表,员工ID为空表示新增员工时候，查询应用与角色列表
                List<RoleItem> checkedRoles = new ArrayList<>();
                if (null != employeeId) {
                    checkedRoles = sqlManager.select("employee.query_employee_checked_roles", RoleItem.class, params);
                }
                ear.setCheckedRole(checkedRoles);

                result.add(ear);
            }
        }

        return result;
    }

    @Override
    public Company queryCompanyInfo(Long companyId) {
        try {
            Company company = sqlManager.single(Company.class, companyId);
            return company;
        } catch (Exception e) {
            logger.error("该公司不存在：公司ID->{}", companyId);
            return null;
        }
    }

    /**
     * 获取员工未赋予角色
     *
     * @param idVO
     */
    @Override
    public List<GroupAppRole> getRoles(IdVO idVO) {
        if (idVO == null
                || idVO.getTenantId() == null
                || idVO.getCompanyId() == null
                || idVO.getAppId() == null
                || idVO.getEmployeeId() == null) {
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        Long tenantId = idVO.getTenantId();
        Long companyId = idVO.getCompanyId();
        Long appId = idVO.getAppId();
        Long employeeId = idVO.getEmployeeId();
        Employee employee = sqlManager.lambdaQuery(Employee.class).andEq(Employee::getId, employeeId).single();
        List<GroupAppRole> roles = employeeMapper.getRoles(tenantId, companyId, appId, employee.getUserId());
        return roles;
    }

    /**
     * 高级授权保存
     *
     * @param employeeParaVO
     */
    @Override
    @Transactional
    public Boolean saveAdvancedAuthentication(EmployeeParaVO employeeParaVO) {
        if (employeeParaVO == null
                || employeeParaVO.getCompanyId() == null
                || employeeParaVO.getEmployeeId() == null
                || employeeParaVO.getTenantId() == null
                || employeeParaVO.getAppId() == null
                || employeeParaVO.getScopeType() == null) {
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        Long tenantId = employeeParaVO.getTenantId();
        Long companyId = employeeParaVO.getCompanyId();
        Long employeeId = employeeParaVO.getEmployeeId();
        Long appId = employeeParaVO.getAppId();
        Integer scopeType = employeeParaVO.getScopeType();
        List<Long> roleIdList = employeeParaVO.getRoleIdList();
        List<Long> departmentIdList = employeeParaVO.getDepartmentIdList();
        Employee employee = sqlManager.lambdaQuery(Employee.class).andEq(Employee::getId, employeeId).single();
        if (employee == null) {
            throw new BaseException(ErrorCodeEnum.EMPLOYEE_NONEXISTS);
        }
        if (roleIdList != null) {
            List<RelUserGroupRole> roles = sqlManager.lambdaQuery(RelUserGroupRole.class)
                    .andEq(RelUserGroupRole::getUserId, employee.getUserId())
                    .andEq(RelUserGroupRole::getTenantId, tenantId)
                    .andEq(RelUserGroupRole::getCompanyId, companyId)
                    .andEq(RelUserGroupRole::getAppId, appId)
                    .select();
            roles.forEach(relUserGroupRole -> {
                sqlManager.deleteById(RelUserGroupRole.class, relUserGroupRole.getId());
            });
            roleIdList.forEach(roleId -> {
                RelUserGroupRole relUserGroupRole = new RelUserGroupRole();
                relUserGroupRole.setId(LongIdGen.get().nextId());
                relUserGroupRole.setUserId(employee.getUserId());
                relUserGroupRole.setRoleId(roleId);
                relUserGroupRole.setTenantId(tenantId);
                relUserGroupRole.setCompanyId(companyId);
                relUserGroupRole.setEmployeeId(employeeId);
                relUserGroupRole.setAppId(appId);
                sqlManager.insertTemplate(relUserGroupRole);
            });
        }
        SysEmployeeAppScope scope = sqlManager.lambdaQuery(SysEmployeeAppScope.class)
                .andEq(SysEmployeeAppScope::getTenantId, tenantId)
                .andEq(SysEmployeeAppScope::getCompanyId, companyId)
                .andEq(SysEmployeeAppScope::getAppId, appId)
                .andEq(SysEmployeeAppScope::getUserId, employee.getUserId())
                .andEq(SysEmployeeAppScope::getEmployeeId, employeeId).single();
        if (scope == null) {
            scope = new SysEmployeeAppScope();
            scope.setId(LongIdGen.get().nextId());
            scope.setTenantId(tenantId);
            scope.setCompanyId(companyId);
            scope.setAppId(appId);
            scope.setUserId(employee.getUserId());
            scope.setEmployeeId(employeeId);
            scope.setScopeType(scopeType);
            setDepartmentId(scopeType, departmentIdList, scope);
            return sqlManager.insertTemplate(scope) == 1;
        }
        scope.setAppId(appId);
        scope.setScopeType(scopeType);
        setDepartmentId(scopeType, departmentIdList, scope);
        return sqlManager.updateTemplateById(scope) == 1;
    }

    @Override
    public PageQuery<EmployeeVO> queryEmployeeForCompanyManager(Integer pageNo, Integer pageSize, Long companyId, Long tenantId, String name) {

        PageQuery<EmployeeVO> query = new PageQuery<>();

        query.setPara("tenantId", tenantId);
        if (StringUtils.isEmpty(name)) {
            query.setPara("nameCondition", null);
        } else {
            query.setPara("nameCondition", name);
        }
        query.setPageNumber(pageNo);
        query.setPageSize(pageSize);
        query.setPara("companyId", companyId);
        try {
            // 查询所有企业员工
            Company company = sqlManager.lambdaQuery(Company.class)
                    .andEq(Company::getId, companyId).single();
            // 组合待查公司ID
            List<Long> companyIdList = new ArrayList<>();
            while (null != company) {
                if (1 == company.getCompanyStatus()){
                    companyIdList.add(company.getId());
                }
                if (null != company.getParentId()) {
                    company = sqlManager.lambdaQuery(Company.class)
                            .andEq(Company::getId, company.getParentId()).single();
                } else {
                    break;
                }
            }
            query.setPara("companies", companyIdList);
            query = sqlManager.pageQuery("employee.query_employee_for_company_manager", EmployeeVO.class, query);
            query.getTotalPage();
            return query;
        } catch (Exception e) {
            logger.error("查询待选管理员列表出错：{}", e.getMessage());
            throw new BizException(ErrorCodeEnum.DB_SELECT_EXCEPTION);
        }
    }

    private void setDepartmentId(Integer scopeType, List<Long> departmentIdList, SysEmployeeAppScope scope) {
        if (Objects.equals(scopeType, ScopeType.SECTION_SCOPE.getValue())) {
            if (departmentIdList == null) {
                throw new BaseException(ErrorCodeEnum.DEPARTMENT_IS_NOT_NULL);
            }
            String departmentIds = StringUtils.strip(departmentIdList.toString(), "[]");
            scope.setDepartmentIdList(departmentIds);
        }
    }
}
