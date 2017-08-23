package com.spzh.excel.util;

import com.spzh.excel.annotation.ExcelField;
import com.spzh.excel.form.AreaForm;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyq on 2017/8/23.
 */
public class ExcelUtils {

    public  static void  export(String fileName,Class<?> clazz){
        List<String> headers = getExcelHeaders(clazz);
    }


    public static List<String> getExcelHeaders(Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();
        List<String> headers = new ArrayList<String>();
        for(Field field : fields){
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            headers.add(excelField.title());
        }
        return headers;
    }

    public void init(String fileName,List<String> headers){
       // WorkBook wb = SXSSFWorkbook
    }

    //导出
     //1.设置表格header数据，及样式
    //2.设置内容数据，及样式
        //2.1添加一个单元格,设置单元格的值(函数1),设置单元格cell的 style(函数2)
    //3.输出到浏览器

    public static void main(String[] args){
        ExcelUtils.export("测试导出", AreaForm.class);
    }
}
