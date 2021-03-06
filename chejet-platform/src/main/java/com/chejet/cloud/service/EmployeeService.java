package com.chejet.cloud.service;

import com.chejet.cloud.common.CurrentUserInfo;
import com.chejet.cloud.po.Company;
import com.chejet.cloud.po.Employee;
import com.chejet.cloud.po.GroupAppRole;
import com.chejet.cloud.vo.EmployeeParaVO;
import com.chejet.cloud.vo.EmployeeVO;
import com.chejet.cloud.vo.IdVO;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;


/**
 * 员工管理biz接口
 *
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/13
 */
public interface EmployeeService {

    PageQuery<EmployeeVO> pageEmployee(Employee employee, CurrentUserInfo currentUser);

    public List<EmployeeVO> listEmployee(Employee employee, CurrentUserInfo currentUser);

    boolean saveEmployee(EmployeeVO employeeVO, CurrentUserInfo currentUser) throws Exception;

    boolean deleteEmployee(Long companyId, Long employeeId, CurrentUserInfo currentUser) throws Exception;

    public boolean editEmployee(EmployeeVO employeeVO, CurrentUserInfo currentUser) throws Exception;

    Employee listEmployeePermisson(Long employeeId, Long appId, CurrentUserInfo currentUser) throws Exception;

    public boolean editEmployeePermisson(Employee employee, CurrentUserInfo currentUser) throws Exception;

    /**
     * 返回员工的应用角色列表
     *
     * @param employeeId
     * @param companyId
     * @return
     */
    List queryEmployeeAppRoleList(Long employeeId, Long companyId);

    /**
     * 根据员工ID查询员工信息
     *
     * @param employeeId
     * @return
     */
    Company queryCompanyInfo(Long employeeId);

    /**
     * 获取员工未赋予角色
     */
    List<GroupAppRole> getRoles(IdVO idVO);

    /**
     * 高级申请保存
     */
    Boolean saveAdvancedAuthentication(EmployeeParaVO employeeParaVO);

    /**
     * 设置企业管理员，可选员工列表
     *
     * @param pageNo
     * @param pageSize
     * @param companyId
     * @param tenantId
     * @param name
     * @return
     */
    PageQuery<EmployeeVO> queryEmployeeForCompanyManager(Integer pageNo, Integer pageSize, Long companyId, Long tenantId, String name);
}
