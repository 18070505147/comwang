package com.chejet.cloud.dao;

import com.chejet.cloud.dto.UserGroup;
import com.chejet.cloud.po.RelUserGroupRole;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Neo.fang
 * @creatTime 2018/12/15 0015
 */
@Repository
public interface UserGroupRoleMapper extends BaseMapper<RelUserGroupRole> {

    List<UserGroup> findUserTenantRole(@Param("userId") Long userId);

    List<UserGroup> findUserCompanyAppRole(@Param("appId") Long appId, @Param("userId") Long userId);
}
