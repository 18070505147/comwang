selectUserById
===
*根据id获取sys_user
	select * from sys_user where id = #id#
	
listUsers
===
*获取用户列表
	select * from sys_user where 1 = 1 
	@if(!isEmpty(username)){
		and username = #username#
	@}
	
listUsersPage
===
	select 
	@pageTag(){
		a.*
	@}
	from sys_user a
	
findTelephoneByTenantId
===
SELECT U.telephone FROM sys_tenant T 
LEFT JOIN sys_user U ON T.user_id=U.id
WHERE T.ID=#tenantId#