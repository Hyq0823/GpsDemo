package com.spzh.excel;

import com.spzh.excel.annotation.ExcelField;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyq on 2017/8/24.
 */
public class ImportExcel {
    private Workbook wb;
    private List<Sheet> sheets = new ArrayList<Sheet>();
    private List<Object[]> annotationList = new ArrayList<Object[]>();

    private static final String SUFFIX_XLS = "xls";
    private static final String SUFFIX_XLSX = "xlsx";
    private static final String SETTER_PREFIX = "set";

    public  ImportExcel(MultipartFile file) throws IOException {
        this(file.getOriginalFilename(),file.getInputStream());
    }

    public  ImportExcel(String fileName, InputStream inputStream) throws IOException {
        if(fileName==null || "".equals(fileName)){
            throw new IllegalArgumentException("excel import file is empty...");
        }
        if(fileName.toLowerCase().endsWith(SUFFIX_XLS)){
            this.wb = new HSSFWorkbook(inputStream);
        }else if(fileName.toLowerCase().endsWith(SUFFIX_XLSX)){
            this.wb = new XSSFWorkbook(inputStream);
        }else{
            throw new IllegalArgumentException("only support file with suffix for xls/sxls !");
        }
        for(int i=0;i<wb.getNumberOfSheets();i++){
            sheets.add(wb.getSheetAt(i));
        }
        System.out.println("import init success......");
    }



    public <T> List<T> getData(Class<T> clazz) throws Exception {
        initAnnotations(clazz);
        List<T> totalDatas = new ArrayList<T>();
       for(Sheet sheet : sheets){
           List<T> list = parseSheet(sheet,clazz);
           totalDatas.addAll(list);
       }
       return totalDatas;
    }


    //=============================================================================
    private <T> List<T> parseSheet(Sheet sheet,Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<T>();
        for(int i=2;i<=sheet.getLastRowNum(); i++){
            StringBuffer sb = new StringBuffer();
            sb.append("行：").append(i).append("  ");
            int currentColumn = 0;
            Row row = sheet.getRow(i);
            T t = clazz.newInstance();
            for(Object[] os : annotationList){
                Field field = (Field)os[1];
                Method invokeMethod = getInvokeMethod(clazz,field.getName(), field.getType());
                Object cellValue = getCellValue(row,currentColumn++);
                invokeMethod.invoke(t, cellValue);
                sb.append(cellValue).append(" | ");
            }
            System.out.println(sb.append("\r\n").toString());
            list.add(t);
        }
        return list;
    }

    private <T> Method getInvokeMethod(Class<T> clazz,String fieldName,Class<?> fileType) throws Exception {
        String setterMethod = SETTER_PREFIX + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
       return clazz.getMethod(setterMethod, fileType);
    }
    private Object getCellValue(Row row, int column){
        Object val = "";
        try{
            Cell cell = row.getCell(column);
            if (cell != null){
                if (cell.getCellTypeEnum() == CellType.NUMERIC){
                    val = cell.getNumericCellValue();
                    String value = val+"";
                    val = value.substring(0,value.indexOf("."));
                }else if (cell.getCellTypeEnum() == CellType.STRING){
                    val = cell.getStringCellValue();
                }else if (cell.getCellTypeEnum() == CellType.FORMULA){
                    val = cell.getCellFormula();
                }else if (cell.getCellTypeEnum() == CellType.BOOLEAN){
                    val = cell.getBooleanCellValue();
                }else if (cell.getCellTypeEnum() == CellType.ERROR){
                    val = cell.getErrorCellValue();
                }
            }
        }catch (Exception e) {
            System.out.println("read cell error...."+e.getMessage());
            return val;
        }
        return val;
    }

    private <T> void  initAnnotations(Class<T> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            if (excelField == null) {
                continue;
            }
            this.annotationList.add(new Object[]{excelField, field});
        }
    }
}
