findAppFoTenant
===
SELECT A.*,T.app_status,T.expire_time FROM sys_app A
LEFT JOIN rel_tenant_app T ON A.id=T.app_id 
WHERE T.tenant_id=#tenantId# 
@trim(){
    @if(!isEmpty(nameCondition)){
    AND  A.name like #nameCondition#
    @}  
@}
limit #pageN0#,#pageSize#

findAppCountFoTenant
===
SELECT COUNT(*) FROM sys_app A
LEFT JOIN rel_tenant_app T ON A.id=T.app_id 
WHERE T.tenant_id=#tenantId#
@trim(){
   @if(!isEmpty(nameCondition)){
       AND  A.name like #nameCondition#
       @}
@} 

findNotAddedApp
===
SELECT * FROM sys_app  
WHERE id not in 
(SELECT app_id FROM rel_tenant_app WHERE tenant_id=#tenantId# )
and app_type=2
and enable_flag=1

findAppByCompany
===
SELECT distinct A.* FROM sys_app A
INNER JOIN rel_company_app R ON A.ID=R.app_id
LEFT JOIN rel_tenant_app TA ON TA.app_id=A.ID
WHERE
R.company_id=#companyId#
AND R.tenant_id=#tenantId#
AND R.tenant_id=TA.tenant_id
AND TA.app_status IN (0,2)