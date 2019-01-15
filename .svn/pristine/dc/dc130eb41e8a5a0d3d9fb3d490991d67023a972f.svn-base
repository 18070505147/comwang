package com.chejet.cloud.annotation;

/**
 * @Description 1-增加，2-删除，3-修改，4-查看，5-查询
 * @Date 2019/1/9 14:17
 * @Version 1.0
 */
public enum OperationType {
    CREATE(1,"create"),
    DELETE(2,"delete"),
    UPDATE(3,"update"),
    RETRIEVE(4,"retrieve"),
    QUERY(5,"retrieve"),
    UNDEFINED(null,"undefine");

    Integer value;
    String  code;

    OperationType(Integer value, String code) {
        this.value = value;
        this.code = code;
    }

    public Integer getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }
}
