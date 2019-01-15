query_info
===
SELECT
	a.*,
	b.url_name AS `portrait_url` 
FROM
	sys_user AS a
	LEFT JOIN sys_attach AS b ON a.portrait_url_id = b.id
	WHERE a.id=#userId#
	
	
update_personal_info
===
```sql
UPDATE sys_user 
SET nickname = #nickname#,
birth = #birth#,
gender = #gender#,
email = #email#,
address = #address#,
detail_address = #detailAddress#,
portrait_url_id = #portraitUrlId#
WHERE
	id = #id#;
```
