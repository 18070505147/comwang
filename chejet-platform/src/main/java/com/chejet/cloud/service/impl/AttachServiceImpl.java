package com.chejet.cloud.service.impl;

import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.po.Attach;
import com.chejet.cloud.service.AttachService;
import com.chejet.cloud.util.LongIdGen;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Neo.fang
 * @creatTime 2018/12/14 0014
 */
@Service
public class AttachServiceImpl implements AttachService {

    @Autowired
    private SQLManager sqlManager;

    @Override
    public Attach saveAttach(String link, Integer type) {

        Attach attach = new Attach();
        Date date = new Date();
        attach.setCtime(date);
        attach.setMtime(date);
        attach.setId(LongIdGen.get().nextId());
        attach.setUrlName(link);
        attach.setUrlType(type);// 地址类型 字典值：0-用户头像，1-企业证书，2-企业logo

        int insert = sqlManager.insert(attach);
        if (insert < 1){
            throw new BaseException(ErrorCodeEnum.DB_INSERT_EXCEPTION);
        }

        return attach;
    }
}
