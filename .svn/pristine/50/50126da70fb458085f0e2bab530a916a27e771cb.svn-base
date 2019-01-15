query_info
===
SELECT
	a.*,
	b.url_name AS `portrait_url` 
FROM
	sys_user AS a
	LEFT JOIN sys_attach AS b ON a.portrait_url_id = b.id
	WHERE a.id=#userId#