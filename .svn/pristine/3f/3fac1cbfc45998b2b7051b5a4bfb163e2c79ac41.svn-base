package com.chejet.cloud.dao;

import com.chejet.cloud.model.SysUser;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.HashMap;
import java.util.List;

/**
 * slqrecource注解对应sql目录下的.md文件 （可选）
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/05
 */
@SqlResource("com.chejet.cloud")
public interface UserMapper extends BaseMapper<SysUser> {
	/**
	 * 必须用@Param指定参数否则会报beetl.sql.core.BeetlSQLException: 未命名的参数 0
	 * 
	 * @param id
	 * @return
	 */
	SysUser selectUserById(@Param("id") Long id);

	/**
	 * 获取用户列表
	 * 
	 * @param map
	 * @return
	 */
	List<SysUser> listUsers(HashMap<String, Object> map);
}
