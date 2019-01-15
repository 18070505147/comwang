package com.chejet.cloud.service;

import java.sql.SQLException;
import java.util.List;
import org.beetl.sql.core.engine.PageQuery;

import com.chejet.cloud.common.CurrentUserInfo;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.po.Department;
import com.chejet.cloud.po.Employee;
import com.chejet.cloud.vo.DepartmentVO;
import com.chejet.cloud.vo.ItemVO;

/**
 * 部门管理biz接口
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/13
 */
public interface DepartmentService {
	List<Department> treeDepartment(Long companyId, CurrentUserInfo currentUser) ;

	List<ItemVO> listDepartment(Long companyId, CurrentUserInfo currentUser) ;

	boolean saveDepartment(Department department, CurrentUserInfo currentUser) throws Exception;

	boolean deleteDepartment(Long departmentId , CurrentUserInfo currentUser)  throws Exception;

	boolean updateDepartment(Department department, CurrentUserInfo currentUser) throws Exception;

}
