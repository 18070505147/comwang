 package com.chejet.cloud.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chejet.cloud.dao.DictionaryDataMapper;
import com.chejet.cloud.dao.DictionaryTypeMapper;
import com.chejet.cloud.po.DictionaryData;
import com.chejet.cloud.po.DictionaryType;
import com.chejet.cloud.service.DictionaryDataService;

/**
 * 数据字典值对biz process
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/11
 */
@Service
 public class DictionaryDataServiceImpl implements DictionaryDataService{
	Logger logger = LoggerFactory.getLogger(DictionaryDataServiceImpl.class);
	
	@Autowired
	DictionaryDataMapper dictionaryDataMapper;
	
	@Autowired
	DictionaryTypeMapper dictionaryTypeMapper;
	
	@Override
	public void insertBatch() {
		
		List<DictionaryType> dictTypeList = dictionaryTypeMapper.all();
		List<DictionaryData> dictDataList = new ArrayList<DictionaryData>();
		
		for(DictionaryType dictionaryType:dictTypeList){
			String[] dictDataStringList = dictionaryType.getRemark().split(",");
			int i=0;
			for(String dictDataString:dictDataStringList){
				i++;
				DictionaryData dictData = new DictionaryData();
				dictData.setSeq(i);
				String [] s2 = dictDataString.split("-");
				dictData.setValue(Integer.valueOf(s2[0]));
				dictData.setEnText(s2[1].split(":")[0]);
				dictData.setText(s2[1].split(":")[1]);
				dictData.setRemark(dictionaryType.getCode());
				dictData.setTypeId(dictionaryType.getId());
				dictData.setCtime(new Date());
				dictData.setMtime(new Date());
				dictData.setDeleteFlag(false);
				dictDataList.add(dictData);
			}
			logger.info(new StringBuilder().append(dictionaryType.getId()).append(dictionaryType.getRemark()).toString());
		}
		
		dictionaryDataMapper.insertBatch(dictDataList);
	}
}
