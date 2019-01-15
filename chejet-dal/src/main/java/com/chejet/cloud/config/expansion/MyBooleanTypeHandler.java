package com.chejet.cloud.config.expansion;

import org.beetl.sql.core.mapping.type.JavaSqlTypeHandler;
import org.beetl.sql.core.mapping.type.PrimitiveValue;
import org.beetl.sql.core.mapping.type.TypeParameter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author Neo.fang
 * @creatTime 2018/11/5 0005
 */
public class MyBooleanTypeHandler extends JavaSqlTypeHandler implements PrimitiveValue {

    Boolean b = false;
    @Override
    public Object getValue(TypeParameter typePara) throws SQLException{
        ResultSet rs = typePara.getRs();
        int type = typePara.getColumnType();
        if(type==Types.BIT||type==Types.TINYINT||type==Types.INTEGER){
            //数字类型映射到boolean
            int value = rs.getInt(typePara.getIndex());
            if(rs.wasNull()){
                if( typePara.isPrimitive()){
                    return b;
                }else{
                    return null;
                }
            }else{
                // 1为true，0为false
                return value==1;
            }
        }else{
            boolean a = rs.getBoolean(typePara.getIndex());
            if(rs.wasNull()){
                if( typePara.isPrimitive()){
                    return b;
                }else{
                    return null;
                }
            }else{
                return a;
            }
        }

    }

    @Override
    public Object getDefaultValue() {
        return b;
    }
}
