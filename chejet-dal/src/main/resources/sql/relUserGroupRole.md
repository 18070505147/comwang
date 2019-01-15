findUserTenantRole
===
SELECT t.name tenantName, t.status, r.* FROM sys_tenant t
RIGHT JOIN (
	SELECT a.tenant_id, b.role_type FROM rel_user_group_role a
	LEFT JOIN sys_group_app_role b ON a.role_id = b.id
	WHERE a.user_id = #userId# AND b.role_type IN(3,4)
	GROUP BY a.tenant_id, b.role_type
) r ON t.id = r.tenant_id

findUserCompanyAppRole
===
SELECT a.tenant_id tenantId, a.company_id companyId, b.name tenantName, c.name companyName 
FROM rel_user_group_role a
LEFT JOIN sys_tenant b ON a.tenant_id = b.id
LEFT JOIN sys_company c ON a.company_id = c.id
WHERE a.app_id = #appId# AND a.user_id = #userId#
GROUP BY a.tenant_id, a.company_id