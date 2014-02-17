package wsSem.ws;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;

import module.business.BusinessModelManip;

import org.codehaus.jettison.json.JSONObject;
import org.openjena.atlas.json.JsonObject;

import tools.JsonResultGenerator;
import wsSem.facade.SparqlQueryEndpointFacade;
import wsSem.logic.AnnotationLogic;
import wsSem.model.JsonBusinessObject;
import wsSem.model.Style;
import wsSem.model.User;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Path("/BusinessService")
public class BusinessService {
	AnnotationLogic annotationLogic;
	String stringResult;

	@SuppressWarnings("finally")
	@Path("/getSampleBusinessObjects")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getSampleBusinessObjects(){
		try{
			annotationLogic = new AnnotationLogic();
			List<JsonBusinessObject> listeBusiness = annotationLogic.getSampleBusinessObjects(); 
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("200", "success", listeBusiness);
		}catch(Exception e){
			e.printStackTrace();
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("500", "fail", null);
		}finally{
			return stringResult;
		}
	}

	@SuppressWarnings("finally")
	@Path("/getBusinessByRequest")
	@POST
	@Consumes(value={MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public String getBusinessByRequest(String query){
		try {
			annotationLogic = new AnnotationLogic();
			JSONObject jObject = new JSONObject(query.trim());
			String str_sparql = (String) jObject.get("sparql");
			List<JsonBusinessObject> listeBusiness = annotationLogic.getBusinessByRequest(str_sparql);
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("200", "success", listeBusiness);
		} catch (Exception e) {
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("500", "fail", null);
		}finally{
			return stringResult;	
		}
	}

	@SuppressWarnings("finally")
	@Path("/addAnnotation")
	@POST
	@Consumes(value={MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public String addAnnotation(String str_jsonobj){
		try {
			annotationLogic = new AnnotationLogic();
			annotationLogic.addAnnotation(str_jsonobj);
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("200", "success", null);
		} catch (JsonParseException e1) {
			e1.printStackTrace();
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("500", "fail", null);
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("500", "fail", null);
		} catch (IOException e1) {
			e1.printStackTrace();
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("500", "fail", null);
		}catch(Exception e){
			e.printStackTrace();
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("500", "fail", null);
		}finally{
			return stringResult;	
		}
	}

}
