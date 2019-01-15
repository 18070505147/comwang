findTelephoneByTenantId
===
SELECT U.telephone FROM sys_tenant T 
LEFT JOIN sys_user U ON T.user_id=U.id
WHERE T.ID=#tenantId#

findUserRoles
===
SELECT *FROM sys_group_app_role
WHERE ID IN
(
SELECT R.role_id FROM rel_user_group_role R
WHERE R.tenant_id=#tenantId#
AND R.user_id=#userId#
AND role_type not in(0,1,2,5)
)