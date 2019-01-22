package com.wong.engidentifier.annotation;

import com.wong.engidentifier.bean.Info;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author WongKyunban
 * description
 * created at 2019-01-21 上午10:56
 * @version 1.0
 */
public class DBTools {

    public static String query(Info info){
        StringBuilder sb = new StringBuilder();
        Class ifo = info.getClass();
        boolean exist = ifo.isAnnotationPresent(Table.class);
        if(!exist){
            return null;
        }

        Table table = (Table)ifo.getAnnotation(Table.class);
        String tableName = table.value();

        sb.append("select * from ").append(tableName).append("where 1=1");

        Field[] fArray = ifo.getDeclaredFields();

        for(Field field:fArray){
            boolean fExist = field.isAnnotationPresent(Column.class);
            if(!fExist){
                return null;
            }
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();

            String fieldName = field.getName();

            Object fieldValue = null;

            String getMethodName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
            try{
                Method method = ifo.getMethod(getMethodName);
                fieldValue = method.invoke(ifo);
            }catch (Exception e){
                e.printStackTrace();
            }
            sb.append(" and ").append(columnName).append("=").append(fieldValue);

        }
        return sb.toString();
    }

}
