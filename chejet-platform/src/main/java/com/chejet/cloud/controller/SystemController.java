package com.chejet.cloud.controller;

import com.chejet.cloud.response.ApiResp;
import com.chejet.cloud.response.ResultBuilder;
import com.chejet.cloud.util.AreaUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Neo.fang
 * @creatTime 2018/12/14 0014
 */
@RestController
@RequestMapping("/system")
public class SystemController {

    @ResponseBody
    @RequestMapping(value = "/area", method = RequestMethod.GET)
    public ApiResp getArea(){
        AreaUtil areaUtil = new AreaUtil();
        return ResultBuilder.buildDateSuccess(areaUtil.getArea());
    }
}
