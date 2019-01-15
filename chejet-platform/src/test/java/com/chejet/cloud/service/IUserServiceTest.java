package com.chejet.cloud.service;

import com.chejet.cloud.model.SysUser;

import java.util.HashMap;
import java.util.List;

/**
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/05
 */
public interface IUserServiceTest {
	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	SysUser getUserById(Long id);

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	SysUser addUser(SysUser user);

	/**
	 * 获取用户列表
	 * 
	 * @param map
	 * @return
	 */
	List<SysUser> listUsers(HashMap<String, Object> map);

	/**
	 * 分页获取用户列表
	 * 
	 * @param map
	 * @return
	 */

	List<SysUser> listUsersPage(HashMap<String, Object> map);
}
