package com.chejet.cloud.config.expansion;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.mapping.BeanProcessor;

/**
 * @author Neo.fang
 * @creatTime 2018/11/5 0005
 */
public class MyBeanProcessor extends BeanProcessor {

    public MyBeanProcessor(SQLManager sqlManager) {
        super(sqlManager);
        MyBooleanTypeHandler myBoolean = new MyBooleanTypeHandler();
        this.handlers.put(Boolean.class, myBoolean);
        this.handlers.put(boolean.class, myBoolean);

    }
}
