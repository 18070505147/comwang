package com.chejet.cloud.controller;

import java.sql.SQLException;
import java.util.List;

import com.chejet.cloud.annotation.OperationType;
import com.chejet.cloud.annotation.Slog;
import com.chejet.cloud.util.BeanUtil;
import com.chejet.jmatrix.core.annotation.ValidInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.po.Company;
import com.chejet.cloud.po.Department;
import com.chejet.cloud.response.ApiResp;
import com.chejet.cloud.service.DepartmentService;
import com.chejet.cloud.vo.DepartmentVO;
import com.chejet.cloud.vo.ItemVO;

/**
 * 部门管理api
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/13
 */
@RestController
@RequestMapping(value = "/department", produces = "application/json;charset=UTF-8")
public class DepartmentController extends BaseController {

	@Autowired
	DepartmentService departmentService;

	@Slog(operation_type = OperationType.CREATE)
	@RequestMapping(KEY_SAVE)
	public ApiResp save(@RequestBody String payload) throws Exception {
		Department department  = JSONObject.parseObject(payload, Department.class);
		BeanUtil.beanAttributeValueTrim(department);
		// save返回值
		boolean temp = departmentService.saveDepartment(department, currentUser());
		if (temp) {
			return ApiResp.success(temp);
		} else {
			return ApiResp.failed(temp);
		}
	}

	@Slog(operation_type = OperationType.UPDATE)
	@RequestMapping(KEY_EDIT)
	public ApiResp update(@RequestBody String payload) throws Exception {
		Department department  = JSONObject.parseObject(payload, Department.class);
		BeanUtil.beanAttributeValueTrim(department);
		boolean temp = departmentService.updateDepartment(department, currentUser());
		if (temp) {
			return ApiResp.success(temp);
		} else {
			return ApiResp.failed(temp);
		}
	}

	@Slog(operation_type = OperationType.DELETE)
	@RequestMapping("/remove/{departmentId}")
	public ApiResp remove(@PathVariable Long departmentId) throws Exception {
		boolean temp = departmentService.deleteDepartment(departmentId, currentUser());
		if (temp) {
			return success(DEL_SUCCESS_MSG);
		} else {
			return error(DEL_FAIL_MSG);
		}
	}

	@RequestMapping("/dict/{companyId}")
	public ApiResp dict(@PathVariable Long companyId) throws Exception {
		List<ItemVO> lv = departmentService.listDepartment(companyId, currentUser());
		return success(lv, PAGE_SUCCESS_MSG);

	}

	@RequestMapping("/tree/{companyId}")
	public ApiResp tree(@PathVariable Long companyId) throws Exception {
		List<Department> voTree = departmentService.treeDepartment(companyId, currentUser());
		return success(voTree, PAGE_SUCCESS_MSG);
	}
}
