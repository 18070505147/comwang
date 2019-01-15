package com.chejet.cloud.service.impl;

import com.chejet.cloud.dao.UserMapper;
import com.chejet.cloud.model.SysUser;
import com.chejet.cloud.service.IUserServiceTest;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

/**
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/05
 */
@Service
public class UserServiceImplTest implements IUserServiceTest {

	@Autowired
	SQLManager sqlManager;

	@Autowired
	UserMapper userMapper;

	@Override
	public SysUser getUserById(Long id) {
		SysUser user = userMapper.selectUserById(id);
		return user;
	}

	@Override
	public SysUser addUser(SysUser user) {
		sqlManager.insert(user);
		return user;
	}

	@Override
	public List<SysUser> listUsers(HashMap<String, Object> map) {
		List<SysUser> list = sqlManager.select("sysUser.listUsers", SysUser.class, map);
		SysUser user = new SysUser();
		user.setUsername("hpp");
		return userMapper.template(user);
	}

	@Override
	public List<SysUser> listUsersPage(HashMap<String, Object> map) {
		PageQuery<SysUser> query = new PageQuery<SysUser>();
		query.setPageNumber(1);
		query.setPageSize(2);
		sqlManager.pageQuery("sysUser.listUsersPage", SysUser.class, query);
		return query.getList();
	}

}
