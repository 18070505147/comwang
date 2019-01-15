package com.chejet.cloud.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chejet.cloud.po.Department;

public class DepartmentTreeBuilder {
	public static List<Department> toTreeList(List<Department> departmentList) {
		Map<Object, Department> maps = new HashMap<>();
		List<Department> list = new ArrayList<>();
		for (Department department : departmentList) {
			addTreeNode(department, maps, list);
		}
		return list;
	}

	public static void addTreeNode(Department department, Map<Object, Department> maps, List<Department> list) {
		if (maps.containsKey(department.getId())) {
			maps.get(department.getId()).setEntity(department);
		} else {
			maps.put(department.getId(), department);
		}
		if (department.getParentId() == null) {
			list.add(maps.get(department.getId()));
		} else {
			if (maps.containsKey(department.getParentId())) {
				maps.get(department.getParentId()).addChildren(department);
			} else {
				Department parent = new Department();
				parent.addChildren(department);
				maps.put(department.getParentId(), parent);
			}
		}
	}
}
