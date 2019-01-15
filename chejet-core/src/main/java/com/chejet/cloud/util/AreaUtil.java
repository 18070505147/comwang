package com.chejet.cloud.util;

import com.alibaba.fastjson.JSONArray;
import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.exception.BaseException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Neo.fang
 * @creatTime 2018/12/14 0014
 */
public class AreaUtil {

    public JSONArray getArea(){
        JSONArray jsonArray;
        try {

            InputStream input = getClass().getClassLoader().getResourceAsStream("area.json");

            BufferedReader in = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null){
                buffer.append(line);
            }
            String area = buffer.toString();

            jsonArray = JSONArray.parseArray(area);
        }catch (Exception e){
            throw new BaseException(ErrorCodeEnum.RESOURCE_NOT_EXIST);
        }
        return jsonArray;
    }

    public static void main(String[] args) {
//        System.out.println(getArea().size());
    }






}
