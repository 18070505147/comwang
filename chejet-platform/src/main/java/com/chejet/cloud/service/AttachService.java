package com.chejet.cloud.service;

import com.chejet.cloud.po.Attach;

/**
 * @author Neo.fang
 * @creatTime 2018/12/14 0014
 */
public interface AttachService {

    /**
     * 存入文件附件管理，返回路径ID
     */
    Attach saveAttach(String link, Integer type);

}
