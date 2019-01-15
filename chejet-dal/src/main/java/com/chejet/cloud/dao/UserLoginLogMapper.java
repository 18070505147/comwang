 package com.chejet.cloud.dao;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import com.chejet.cloud.po.UserLoginLog;

/**
  *登录日志DAO
  * 
  * @author ansen.zhu@carlt.com.cn
  * @date 2018/12/06
  */
 @SqlResource("com.chejet.cloud")
 public interface UserLoginLogMapper extends BaseMapper<UserLoginLog>{

}
