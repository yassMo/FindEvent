package wsSem.ws;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;

import tools.JsonResultGenerator;
import wsSem.logic.StyleLogic;
import wsSem.logic.UserLogic;
import wsSem.model.Style;
import wsSem.model.User;

@Path("/AccueilService")
public class AccueilService {
	//Should produce error code by JAX-RS if would like to integrate with Angular
	StyleLogic styleLogic;
	UserLogic userLogic;
	String stringResult;

	@SuppressWarnings("finally")
	@Path("/styles")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllStyles(){
		try{
			styleLogic = new StyleLogic();
			List<Style> styles = styleLogic.getAllStyles();	
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("200", "success", styles);
		}catch(Exception e){
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("500", "fail", null);
		}finally{
			return stringResult;
		}
	}


	@SuppressWarnings("finally")
	@Path("/stylesAvailableByUserName")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getStylesAvailableByUserName(@Context UriInfo uriInfo){
		String username=uriInfo.getQueryParameters().getFirst("username");
		try{
			styleLogic = new StyleLogic();
			List<Style> styles = styleLogic.getStylesAvailableByUserName(username);
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("200", "success", styles);
		}catch(Exception e){
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("500", "fail", null);
		}finally{
			return stringResult;
		}
	}

	@SuppressWarnings("finally")
	@Path("/stylesByUserName")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getStylesByUsername(@Context UriInfo uriInfo){
		String username=uriInfo.getQueryParameters().getFirst("username");
		try{
			styleLogic = new StyleLogic();
			List<Style> styles = styleLogic.getStylesByUserName(username);
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("200", "success", styles);
		}catch(Exception e){
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("500", "fail", null);
		}finally{
			return stringResult;
		}
	}

	@SuppressWarnings("finally")
	@Path("/userStyles/add")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String addStyleToUser(@Context UriInfo uriInfo){

		String username=uriInfo.getQueryParameters().getFirst("username");
		String style_id = uriInfo.getQueryParameters().getFirst("style_id");
		try{	
			userLogic = new UserLogic();
			List<Style> styles = userLogic.addStyleToUser(username, style_id);
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("200", "success", styles);
		}catch(Exception e){
			e.printStackTrace();
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("500", "fail", null);
		}finally{
			return stringResult;
		}
	}

	@SuppressWarnings("finally")
	@Path("/userStyles/delete")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteStyleOfUser(@Context UriInfo uriInfo){
		String username=uriInfo.getQueryParameters().getFirst("username");
		String style_id = uriInfo.getQueryParameters().getFirst("style_id");
		try{
			userLogic = new UserLogic();
			List<Style> styles = userLogic.deleteUserStyle(username, style_id);
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("200", "success", styles);
		}catch(Exception e){
			e.printStackTrace();
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("500", "fail", null);
		}finally{
			return stringResult;
		}
	}
}
