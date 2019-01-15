login_log
===
select * from sys_user_login_log where tenant_id=#tenantId# order by login_time desc
@orm.single({"userId":"id"},"User");
@orm.single({"appId":"id"},"App");

login_log$count
===
select count(1) from sys_user_login_log where tenant_id=#tenantId#

tenant_app_user
===
SELECT
	COUNT( DISTINCT(uid))  as num
FROM
	(
	SELECT
		a.user_id as uid
	FROM
		rel_user_group_role as a
		JOIN rel_tenant_app as b ON a.app_id = b.app_id 
	AND b.app_status IN ( 0, 2 )
	WHERE a.tenant_id = #tenantId#
	) as c
	
tenant_user_statistic_by_app
===
* 主页：租户内按照应用统计用户数目以及在线情况
SELECT
	d.id AS id,
	d.`name` AS `name`,
	a.login_status AS `status`,
	count( 1 ) AS num 
FROM
	sys_user AS a
	JOIN sys_employee AS b ON a.id = b.user_id 
	AND b.tenant_id = #tenantId#
	JOIN rel_tenant_app AS c ON c.tenant_id = b.tenant_id 
	AND c.app_status IN ( 0, 2 )
	JOIN sys_app AS d ON d.id = c.app_id
	JOIN rel_user_group_role AS e ON a.id = e.user_id 
	AND e.tenant_id = b.tenant_id 
GROUP BY
	id,
	`name`,
	login_status;


tenant_employ_entry
===
SELECT
	c.`name` AS departmentName,
	d.`name` AS companyName,
	count( 1 ) AS num 
FROM
	sys_employee AS a
	JOIN sys_tenant AS b ON a.tenant_id = b.id
	JOIN sys_department AS c ON a.department_id = c.id
	JOIN sys_company AS d ON c.company_id = d.id 
WHERE
	DATE_FORMAT( a.entry_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ), '%Y%m' ) 
	AND b.id = #tenantId# 
	AND a.`status`=1
GROUP BY
	departmentName,
	companyName
ORDER BY num DESC
	LIMIT 0,#num#;
	
tenant_employ_leave
===
SELECT
	c.`name` AS departmentName,
	d.`name` AS companyName,
	count( 1 ) AS num 
FROM
	sys_employee AS a
	JOIN sys_tenant AS b ON a.tenant_id = b.id
	JOIN sys_department AS c ON a.department_id = c.id
	JOIN sys_company AS d ON c.company_id = d.id 
WHERE
	DATE_FORMAT( a.leave_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ), '%Y%m' ) 
	AND b.id = #tenantId# 
	AND a.`status`=0
GROUP BY
	departmentName,
	companyName 
ORDER BY num DESC
	LIMIT 0,#num#;
	
company_employ_entry
===
SELECT
	c.`name` AS departmentName,
	d.`name` AS companyName,
	count( 1 ) AS num 
FROM
	sys_employee AS a
	JOIN sys_tenant AS b ON a.tenant_id = b.id
	JOIN sys_department AS c ON a.department_id = c.id
	JOIN sys_company AS d ON c.company_id = d.id
	JOIN sys_company_manager AS e ON e.company_id=d.id AND e.user_id=#userId#
WHERE
	DATE_FORMAT( a.entry_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ), '%Y%m' ) 
	AND b.id = #tenantId# 
	AND a.`status`=1
GROUP BY
	departmentName,
	companyName
ORDER BY num DESC
	LIMIT 0,#num#;
	
company_employ_leave
===
SELECT
	c.`name` AS departmentName,
	d.`name` AS companyName,
	count( 1 ) AS num 
FROM
	sys_employee AS a
	JOIN sys_tenant AS b ON a.tenant_id = b.id
	JOIN sys_department AS c ON a.department_id = c.id
	JOIN sys_company AS d ON c.company_id = d.id
	JOIN sys_company_manager AS e ON e.company_id=d.id AND e.user_id=#userId#
WHERE
	DATE_FORMAT( a.leave_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ), '%Y%m' ) 
	AND b.id = #tenantId# 
	AND a.`status`=1
GROUP BY
	departmentName,
	companyName
ORDER BY num DESC
	LIMIT 0,#num#;
	
company_admin_app_list
===
SELECT DISTINCT
a.`name` AS `name`,
a.description AS description,
b.app_status AS `status`,
b.expire_time AS expireTime 
FROM
	sys_app AS a
	JOIN rel_tenant_app AS b ON b.app_id = a.id AND b.b.tenant_id=#tenantId#
	JOIN rel_company_app AS c ON c.app_id = a.id
	JOIN sys_company_manager AS d ON d.company_id=c.company_id
	WHERE d.user_id=#userId# AND d.tenant_id=#tenantId#;
	
company_admin_user_statistic
===
SELECT
	b.id AS id,
	b.`name` AS `name`,
	d.login_status AS `status`,
	COUNT( 1 ) AS `num` 
FROM
	sys_employee AS a
	JOIN sys_company AS b ON a.company_id = b.id
	JOIN sys_company_manager AS c ON ( c.company_id = b.id 
	AND c.user_id = #userId# 
	AND c.tenant_id = #tenantId# )
	JOIN sys_user AS d ON a.user_id = d.id 
WHERE
	a.`status` = 1 
GROUP BY
	id,`name`,login_status;

company_admin_employee_change
===
SELECT
	b.id AS id,
	b.`name` AS `name`,
	a.`status` AS `status`,
	COUNT( 1 ) AS `num` 
FROM
	sys_employee AS a
	JOIN sys_company AS b ON a.company_id = b.id
	JOIN sys_company_manager AS c ON c.company_id = b.id 
	AND c.user_id = #userId# 
	AND c.tenant_id = #tenantId#
WHERE
	(
		DATE_FORMAT( a.entry_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ), '%Y%m' ) 
		OR DATE_FORMAT( a.leave_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ), '%Y%m' ) 
	) 
GROUP BY
	id,`name`,`status`;
	
