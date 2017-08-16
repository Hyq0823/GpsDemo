package com.spzh.utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.text.SimpleDateFormat;

/**
 * json工具类
 * @author hyq
 *
 */
public class JsonUtils {
	
	/**
	 *json串转换为javabean 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static  <T> T json2Obj(String json,Class<T> clazz){
		ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM:dd HH:mm:ss"));
			try {
                return objectMapper.readValue(json, clazz);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
	}
	
	/**
     * 对象转换为json串
     * 优雅的输出json,自动换行，缩进//.withDefaultPrettyPrinter();
     * @param obj
     * @return
     */
    public static String obj2Json(Object obj){
        ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter ow = objectMapper.writer();
            try {
                return ow.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
    }
	
	public static void main(String[] args) {
//		String json = "{\"result\":0,\"alarms\":[{\"info\":0,\"desc\":\"\",\"atp\":11,\"did\":\"500000\",\"vid\":null,\"etm\":1451374197000,\"stm\":1451374197000,\"guid\":\"500000EB9B109898F74ADCB1B4446B9FFD2\",\"p1\":12000,\"p2\":6000,\"p3\":10000,\"p4\":0,\"img\":\"\",\"hd\":1,\"hdu\":\"admin\",\"hdc\":\"vcxvcvcxv\",\"hdt\":\"2015-12-29 16:50:50\",\"ss1\":805327235,\"ss2\":0,\"es1\":805327235,\"es2\":0,\"slng\":113850504,\"slat\":22628389,\"elng\":113850504,\"elat\":22628389,\"ssp\":990,\"esp\":990,\"slc\":164338463,\"elc\":164338463,\"smlng\":\"113.861938\",\"smlat\":\"22.631491\",\"emlng\":\"113.861938\",\"emlat\":\"22.631491\"},{\"info\":0,\"desc\":\"\",\"atp\":11,\"did\":\"500000\",\"vid\":null,\"etm\":1451374197000,\"stm\":1451374197000,\"guid\":\"500000EB9B109898F74ADCB1B4446B9FFD2\",\"p1\":12000,\"p2\":6000,\"p3\":10000,\"p4\":0,\"img\":\"\",\"hd\":1,\"hdu\":\"admin\",\"hdc\":\"vcxvcvcxv\",\"hdt\":\"2015-12-29 16:50:50\",\"ss1\":805327235,\"ss2\":0,\"es1\":805327235,\"es2\":0,\"slng\":113850504,\"slat\":22628389,\"elng\":113850504,\"elat\":22628389,\"ssp\":990,\"esp\":990,\"slc\":164338463,\"elc\":164338463,\"smlng\":\"113.861938\",\"smlat\":\"22.631491\",\"emlng\":\"113.861938\",\"emlat\":\"22.631491\"}],\"pagination\":{\"totalPages\":42,\"currentPage\":1,\"pageRecords\":50,\"totalRecords\":2078,\"sortParams\":null,\"hasNextPage\":true,\"hasPreviousPage\":false,\"nextPage\":2,\"previousPage\":1,\"startRecord\":0}}";
//		WarningForm warningForm = json2Obj(json, WarningForm.class);
//		System.out.println(warningForm);
//		String time = DateUtils.formatDateTime(Long.parseLong("1451374197000"));
	}
}
