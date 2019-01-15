package com.chejet.cloud.service.impl;

import java.net.CacheRequest;
import java.util.Date;
import java.util.List;

import com.chejet.cloud.po.Employee;
import org.apache.commons.lang3.StringUtils;
import org.beetl.sql.core.DSTransactionManager;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.SQLReady;
import org.beetl.sql.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chejet.cloud.builder.DepartmentTreeBuilder;
import com.chejet.cloud.common.CurrentUserInfo;
import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.dao.DepartmentMapper;
import com.chejet.cloud.exception.BizException;
import com.chejet.cloud.po.Department;
import com.chejet.cloud.service.DepartmentService;
import com.chejet.cloud.vo.ItemVO;

/**
 * 部门管理biz实现
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/13
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	private SQLManager sqlManager;

	@Override
	public List<Department> treeDepartment(Long companyId, CurrentUserInfo currentUser) {
		List<Department> retList=null;
		try{
			if(companyId!=null){
				List<Department> list = sqlManager.query(Department.class).andEq("tenant_id", currentUser.getTenantId())
						.andEq("company_id", companyId).select();
				retList = DepartmentTreeBuilder.toTreeList(list);
			}else{
				throw new BizException(ErrorCodeEnum.PARAM_ERROR);
			}
		}catch(Exception e){
			throw new BizException(ErrorCodeEnum.DEPARTMENT_TREE_EXCEPTION);
		}
		
		return retList;
	}

	@Override
	public List<ItemVO> listDepartment(Long companyId, CurrentUserInfo currentUser) {
		List<ItemVO> list;
		try{
			list = sqlManager.query(Department.class).andEq("company_id", companyId)
					.andEq("tenant_id", currentUser.getTenantId()).select(ItemVO.class, "id, name");
		}catch(Exception e){
			throw new BizException(ErrorCodeEnum.DEPARTMENT_DICT_EXCEPTION);
		}
		return list;

	}

	@Override
	public boolean saveDepartment(Department department, CurrentUserInfo currentUser) throws Exception {
		

		int i = 0;
		try {
			Query<Department> query = sqlManager.query(Department.class);
			List<Department> list = query.andEq("tenant_id", currentUser.getTenantId())
					.andEq("company_id", department.getCompanyId()).select();
			if (list.isEmpty()) {
				Date d = new Date();
				department.setCtime(d);
				department.setMtime(d);
				department.setLeftValue(1);
				department.setRightValue(2);
				department.setCompanyId(department.getCompanyId());
				department.setDescription(department.getDescription());
				department.setName(department.getName());
				department.setTenantId(currentUser.getTenantId());
				department.setCreateBy(currentUser.getUserId());
				department.setModifiedBy(currentUser.getUserId());
				if (department.getDepartmentStatus() == null) {
					department.setDepartmentStatus(1);
				} else {
					department.setDepartmentStatus(department.getDepartmentStatus());
				}
				i = sqlManager.insertTemplate(department);
			} else {
				if (department.getParentId() != null && department.getParentId() != 0L) {
					Query<Department> query2 = sqlManager.query(Department.class);
					List<Department> list2 = query2.andEq("name", department.getName())
							.andEq("company_id", department.getCompanyId()).select();
					if (list2.isEmpty()) {				
						Department parentDepartment = list.get(0);
						Date d = new Date();
						department.setCtime(d);
						department.setMtime(d);
						department.setCreateBy(currentUser.getUserId());
						department.setModifiedBy(currentUser.getUserId());
						department.setLeftValue(parentDepartment.getRightValue());
						department.setRightValue(parentDepartment.getRightValue() + 1);
						department.setParentId(department.getParentId());
						department.setTenantId(currentUser.getTenantId());
						department.setCompanyId(department.getCompanyId());
						department.setDescription(department.getDescription());
						department.setName(department.getName());
						if (department.getDepartmentStatus() == null) {
							department.setDepartmentStatus(1);
						} else {
							department.setDepartmentStatus(department.getDepartmentStatus());
						}
						
						DSTransactionManager.start();
							sqlManager.executeUpdate(new SQLReady(
									"update sys_department set right_value=right_value+2 where right_value>=? and tenant_id=? and company_id=?",
									parentDepartment.getRightValue(), parentDepartment.getTenantId(),
									parentDepartment.getCompanyId()));
							sqlManager.executeUpdate(new SQLReady(
									"update sys_department set left_value=left_value+2 where left_value>=? and tenant_id=? and company_id=?",
									parentDepartment.getRightValue(), parentDepartment.getTenantId(),
									parentDepartment.getCompanyId()));
							i = sqlManager.insertTemplate(department);
						DSTransactionManager.commit();
					} else {
						throw new BizException(ErrorCodeEnum.DEPARTMENT_EXISTS);

					}
				} else {
					throw new BizException(ErrorCodeEnum.DEPARTMENT_PARENT_NOEXISTS);
				}
			}
		} catch (Exception e) {
			DSTransactionManager.rollback();
			throw e;
		}
		
		return i == 1;
	}

	/**
	 * 删除逻辑：1、有子部门时不能删除；2、部门内有在职员工时不能删除
	 * @param departmentId
	 * @param currentUser
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean deleteDepartment(Long departmentId, CurrentUserInfo currentUser) throws Exception {
		try {
			if ( currentUser.getTenantId() != 0L) {
				Query<Department> query = sqlManager.query(Department.class);
				Department com = query.andEq("id", departmentId).single();
				List<Department> departments = sqlManager.lambdaQuery(Department.class)
						.andEq(Department::getParentId,departmentId).select();
				if (null != departments){
					if (departments.size() > 0){
						throw  new  BizException(ErrorCodeEnum.DEPARTMENT_DELETE_HAS_CHILD);
					}
				}
				List<Employee> employees = sqlManager.lambdaQuery(Employee.class)
						.andEq(Employee::getDepartmentId,departmentId)
						.andEq(Employee::getStatus,1)
						.select();
				if (null != employees){
					if (employees.size() > 0){
						throw new  BizException(ErrorCodeEnum.DEPARTMENT_DELETE_HAS_EMPLOYEE);
					}
				}
				if (com != null) {
					Query<Department> query2 = sqlManager.query(Department.class);
					List<Department> poList = query2.andEq("tenant_id", com.getTenantId())
							.andEq("company_id", com.getCompanyId()).andGreat("left_value", com.getLeftValue())
							.andLess("right_value", com.getRightValue()).select();
					if (poList.size() == 0) {
						sqlManager.executeUpdate(new SQLReady("delete from sys_department where id=? and company_id=?",
								com.getId(), com.getCompanyId()));
					} else {
						throw new BizException(ErrorCodeEnum.DEPARTMENT_CHILD_EXCEPTION);
					}
				} else {
					throw new BizException(ErrorCodeEnum.DEPARTMENT_NOEXISTS);
				}

			}
		} catch (Exception e) {		
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateDepartment(Department department, CurrentUserInfo currentUser) throws Exception {	
		try {
			if(department!=null&&!StringUtils.isEmpty(department.getName())){
				Query<Department> query = sqlManager.query(Department.class);
				Department dbd = query.andEq("id", department.getId()).single();
				if ((dbd.getParentId() != null && dbd.getParentId().equals(department.getParentId()))
						|| dbd.getParentId() == null) {
					Date d = new Date();
					department.setMtime(d);
					department.setModifiedBy(currentUser.getUserId());
					sqlManager.updateTemplateById(department);
				} else if (dbd.getParentId() != null && !dbd.getParentId().equals(department.getParentId())) {
					DSTransactionManager.start();
						boolean temp = deleteDepartment(department.getId(), currentUser);
						if (!temp) {
							throw new BizException(ErrorCodeEnum.DEPARTMENT_CHILD_EXCEPTION);
						}
						boolean temp2 = saveDepartment(department, currentUser);
						if (!temp2) {
							throw new BizException(ErrorCodeEnum.DEPARTMENT_SAVE_EXCEPTION);
						}
					DSTransactionManager.commit();
				}
				
			}else{
				throw new BizException(ErrorCodeEnum.PARAM_ERROR);
			}
		} catch (Exception e) {
			DSTransactionManager.rollback();
			throw e;
		}
		
		return true;
	}

}
