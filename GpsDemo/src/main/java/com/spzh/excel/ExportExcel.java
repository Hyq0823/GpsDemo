package com.spzh.excel;

import com.spzh.excel.annotation.ExcelField;
import com.spzh.excel.form.AreaForm;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by hyq on 2017/8/23.
 */
public class ExportExcel {
    private SXSSFWorkbook wb;
    private SXSSFSheet sheet;
    private Map<String, CellStyle> styles;
    private int currentRow;
    private List<Object[]> annotationList = new ArrayList<Object[]>();
    private List<String> headers = new ArrayList<String>();
    private String excelTitle;


    private static final String GETTER_PREFIX = "get";
    private static final String STYLE_TITLE = "title";
    private static final String STYLE_HEAD = "head";
    private static final String STYLE_DATA_CENTER = "data_center";
    private static final String STYLE_DATA_LEFT = "data_left";
    private static final String STYLE_DATA_RIGHT = "data_right";

    public ExportExcel(String excelTitle, Class<?> clazz) {
        this.excelTitle = excelTitle;
        this.headers = createHeaders(clazz);
        init();
    }

    public ExportExcel(String excelTitle,List<String> headers){
        this.excelTitle = excelTitle;
        this.headers = headers;
        init();
    }

    public ExportExcel(String excelTitle,String[] headers){
        this.excelTitle = excelTitle;
        this.headers = Arrays.asList(headers);
        init();
    }

    public ExportExcel() {
    }



    /**
     * 初始化(标题、表头)
     */
    public void init() {
        if (this.headers == null || this.headers.size() == 0) {
            throw new IllegalArgumentException("excel headers shoud not be null!");
        }
        this.wb = new SXSSFWorkbook(500);
        this.sheet = wb.createSheet();
        this.styles = createStyles();
        if (excelTitle != null || !"".equals(excelTitle)) {
            buildExcelTitle();
        }
        bulidExcelHeader();
        System.out.println("init excel title and head success....");
    }


    public <T> ExportExcel setData(List<T> data)throws  Exception{
        if(annotationList.size() == 0){
            System.out.println("Warn.....excelFiled annotation field not found!");
        }
        for(T t :data){
            int column = 0;
            Row row = this.addRow();
            StringBuilder sb = new StringBuilder();
            for(Object[] os : annotationList){
                Object value = null;
                ExcelField excelField = (ExcelField) os[0];
                String defaultValue = excelField.value();
                if(defaultValue!=null && !"".equals(defaultValue)){//固定值
                    value = defaultValue ;
                }else{
                    if(os[1] instanceof Field){
                        Field field = (Field) os[1];
                        value = reflectGetValue(t,field.getName());
                    }
                //如果excelFiled声明在方法上面的
                }
                this.addCell(row, column++, value);
                sb.append(value + ", ");
            }
            System.out.println("Write success: ["+row.getRowNum()+"] "+sb.toString());
        }
        return this;
    }



    //==================basic============================================================================
    /**
     * 添加一个单元格
     * @param row 添加的行
     * @param column 添加列号
     * @param val 添加值
     * @return 单元格对象
     */
    public Cell addCell(Row row, int column, Object val){
        Cell cell = row.createCell(column);
        CellStyle style = styles.get(STYLE_DATA_CENTER);
        try {
            if (val == null){
                cell.setCellValue("");
            } else if (val instanceof String) {
                cell.setCellValue((String) val);
            } else if (val instanceof Integer) {
                cell.setCellValue((Integer) val);
            } else if (val instanceof Long) {
                cell.setCellValue((Long) val);
            } else if (val instanceof Double) {
                cell.setCellValue((Double) val);
            } else if (val instanceof Float) {
                cell.setCellValue((Float) val);
            } else if (val instanceof Date) {
                DataFormat format = wb.createDataFormat();
                style.setDataFormat(format.getFormat("yyyy-MM-dd HH:mm:ss"));
                cell.setCellValue((Date) val);
            }else{
                cell.setCellValue("");
            }
        } catch (Exception ex) {
            System.out.println("Set cell value ["+row.getRowNum()+","+column+"] error: " + ex.toString());
            cell.setCellValue(val.toString());
        }
        cell.setCellStyle(style);
        return cell;
    }


    private Object reflectGetValue(Object obj, String propertyName) throws Exception {
        String getMethodName  = GETTER_PREFIX + propertyName.substring(0,1).toUpperCase()+ propertyName.substring(1);
        Method method = obj.getClass().getMethod(getMethodName, new Class[]{});
       return method.invoke(obj,new Object[]{});
    }

    /**
     * 输出前准备
     *
     * @param fileName 输出文件名
     */
    public static HttpServletResponse handleRespWriteInfo(HttpServletResponse response, String fileName) throws IOException, UnsupportedEncodingException {
        fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        return response;
    }

    /**
     * 输出到客户端
     *
     * @param fileName 输出文件名
     */
    public ExportExcel write(HttpServletResponse response, String fileName) throws IOException {
        handleRespWriteInfo(response, fileName);
        write(response.getOutputStream());
        return this;
    }

    /**
     * 输出数据流
     *
     * @param os 输出数据流
     */
    public ExportExcel write(OutputStream os) throws IOException {
        wb.write(os);
        return this;
    }





    /**
     * 添加一行
     * @return 行对象
     */
    public Row addRow(){
        return sheet.createRow(currentRow++);
    }
    /**
     * cell样式库,可以继续扩展
     */
    public Map<String, CellStyle> createStyles() {
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font titleFont = wb.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBold(true);
        style.setFont(titleFont);
        styles.put(STYLE_TITLE, style);

        style = wb.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put(STYLE_DATA_CENTER, style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get(STYLE_DATA_CENTER));
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = wb.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        styles.put(STYLE_HEAD, style);
        return styles;
    }

    /**
     * 构造excel标题
     * @return
     */
    private int buildExcelTitle() {
        Row headRow = sheet.createRow(currentRow++);
        headRow.setHeightInPoints(20);
        Cell headCell = headRow.createCell(0);
        headCell.setCellStyle(styles.get(STYLE_TITLE));
        headCell.setCellValue(excelTitle);
        return sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.size() - 1));
    }

    /**
     * 构造表头
     */
    private void bulidExcelHeader() {
        Row headerRow = sheet.createRow(currentRow++);
        headerRow.setHeightInPoints(14);
        // sheet.trackAllColumnsForAutoSizing();
        for (int i = 0; i < headers.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle(styles.get(STYLE_HEAD));
            String value = headers.get(i);
            cell.setCellValue(value);
            //   sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i,value.getBytes().length*2*256);
        }
    }
    public List<String> createHeaders(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> headers = new ArrayList<String>();
        for (Field field : fields) {
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            headers.add(excelField.title());
            annotationList.add(new Object[]{excelField, field});
        }
        return headers;
    }



    public static void mian(String[] args) {
        Field[] fields = AreaForm.class.getDeclaredFields();
        for(Field field :fields){
            System.out.println(field.getName());
        }
    }


}
