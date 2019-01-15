package com.chejet.cloud.service.hessianRpcApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Component;

/**
 * @author Neo.fang
 * @creatTime 2018/12/18 0018
 */
@Component
public class HessianServiceConfig {

    @Autowired
    private AuthenticationRpcService authenticationRpcService;


    @Bean(name = "/rpc/authenticationRpcService")
    public HessianServiceExporter accountService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(authenticationRpcService);
        exporter.setServiceInterface(AuthenticationRpcService.class);
        return exporter;
    }



}
