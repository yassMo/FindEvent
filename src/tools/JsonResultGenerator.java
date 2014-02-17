package tools;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import wsSem.model.JsonResult;
import wsSem.model.Style;

public class JsonResultGenerator {
	
	private JsonResultGenerator() {}
	private static JsonResultGenerator instance = null;
	public static JsonResultGenerator getJsonResultFactory(){
		if(instance==null){
			instance = new JsonResultGenerator();
		}
		return instance;
	}

	public String createJsonResultString(String status, String result, List<?> binding){
		String jsonString = "";
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		ArrayList<?> resultBind;
		JsonResult resultObj = new JsonResult();
		if(binding==null){
			resultBind = new ArrayList();
		}else{
			resultBind = new ArrayList(binding);
		}
		resultObj.setResult(result);
		resultObj.setStatus(status);
		resultObj.setBinding(resultBind);
		try {
			mapper.writeValue(strWriter, resultObj);
			jsonString= strWriter.toString();
			System.out.println(jsonString);

		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}

}
