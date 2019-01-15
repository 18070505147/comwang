pageEmployee
===
	SELECT se.*,
	su.telephone,
	sd.`name` AS `department_name`,
	su.`portrait_url_id` AS `portrait_url_id`,
	sa.`url_name` AS `portrait_url` 
	FROM sys_employee se 
	LEFT JOIN sys_user su ON se.user_id = su.id 
	LEFT JOIN sys_department sd ON se.department_id=sd.id
	LEFT JOIN sys_attach sa ON sa.id=su.portrait_url_id
		WHERE se.company_id=#companyId#
	@if(!isEmpty(telephone)){
		AND	 su.telephone=#telephone#
	@}
	@if(!isEmpty(name)){
		AND su.name=#name#
	@}
	@if(!isEmpty(departmentId)){
    	AND se.department_id=#departmentId#
    @}

pageEmployee$count
===
	SELECT count(1) FROM sys_employee se 
	LEFT JOIN sys_user su ON se.user_id = su.id 
	LEFT JOIN sys_department sd ON se.department_id=sd.id
		WHERE se.company_id=#companyId#
	@if(!isEmpty(telephone)){
		AND	 su.telephone=#telephone#
	@}
	@if(!isEmpty(name)){
		AND se.user_name=#name#
	@}
    @if(!isEmpty(departmentId)){
        AND se.department_id=#departmentId#
    @}
	
listEmployee
===
	SELECT se.*, su.telephone,sd.`name` AS `department_name` FROM sys_employee se 
	LEFT JOIN sys_user su ON se.user_id = su.id 
	LEFT JOIN sys_department sd ON se.department_id=sd.id
		WHERE se.company_id=#companyId#
	@if(!isEmpty(telephone)){
		AND	 su.telephone=#telephone#
	@}
	@if(!isEmpty(name)){
		AND se.user_name=#name#
	@}
	
	
query_company_app
===
* 查询公司的应用列表
```mysql
SELECT DISTINCT
	a.`name` AS app_name,
	b.app_id
FROM
	sys_app AS a
	JOIN rel_company_app AS b ON b.app_id = a.id 
	AND b.tenant_id=#tenantId#
	AND b.company_id=#companyId#
```
query_company_app_roles
===
* 查询某公司下应用的角色列表
```sql
SELECT
	a.id AS role_id,
	a.`name` AS role_name 
FROM
	sys_group_app_role AS a
	WHERE a.app_id=#appId#
	AND a.tenant_id=#tenantId#
	@if(!isEmpty(companyId)){
    	AND (a.company_id=#companyId# OR a.company_id IS NULL)
    @}
```

query_employee_checked_roles
===
* 查询员工已有的应用角色
```sql
SELECT
	a.role_id,
	b.`name` AS role_name 
FROM
	rel_user_group_role AS a
	JOIN sys_group_app_role AS b on a.role_id=b.id
	WHERE a.employee_id=#employeeId#
	AND a.tenant_id=#tenantId#
	AND a.company_id=#companyId#
	AND a.app_id=#appId#
```

getRoles
===
SELECT * FROM sys_group_app_role R
WHERE R.tenant_id=#tenantId#
AND R.company_id=#companyId#
AND R.app_id=#appId#
AND enable_flag=1
AND R.id NOT IN (
SELECT role_id FROM rel_user_group_role UR 
WHERE UR.tenant_id=#tenantId#
AND UR.company_id=#companyId#
AND app_id=#appId#
AND user_id=#userId#)

query_employee_for_company_manager
===
* 设置企业管理员，查询可选员工列表
```sql
SELECT
  @pageTag(){
	a.id AS id,
	a.company_id AS company_id,
	a.department_id AS department_id,
	a.tenant_id AS tenant_id,
	a.user_id AS user_id,
	a.job AS job,
	a.user_name AS user_name,
    a.job_number AS job_number,
	b.telephone AS telephone,
	c.`name` AS department_name,
	d.`name` AS company_name 
  @}
FROM
	sys_employee AS a
	JOIN sys_user AS b ON a.user_id = b.id
	@if(!isEmpty(nameCondition)){
        AND a.user_name like #'%'+nameCondition+'%'#
    @}
	JOIN sys_department AS c ON a.department_id = c.id 
	AND c.tenant_id = #tenantId# AND c.company_id in ( #join(companies)#)
	JOIN sys_company AS d ON d.id = a.company_id 
	AND d.tenant_id = #tenantId# AND d.id in ( #join(companies)#)
	WHERE
    	a.id NOT IN ( SELECT e.employee_id FROM sys_company_manager AS e WHERE company_id = #companyId# )
        AND a.user_id <> (SELECT st.user_id FROM sys_tenant as st WHERE st.id=#tenantId# )
        AND a.`status` = 1
```

findTenantPhone
===
SELECT U.telephone FROM sys_tenant T 
LEFT JOIN sys_user U
ON T.user_id=U.id
WHERE T.id=#tenantId#