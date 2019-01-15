package com.chejet.cloud.service.impl;

import com.chejet.cloud.service.BaseService;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseServiceImpl implements BaseService {
    @Autowired
    SQLManager sqlManager;

    @Override
    public List<?> queryByPage() {
        System.out.println("hello world");
        return null;
    }
}
