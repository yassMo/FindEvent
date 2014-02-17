package wsSem.ws;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import tools.JsonResultGenerator;
import wsSem.logic.AccountLogic;
import wsSem.model.Style;
import wsSem.model.User;

@Path("/LoginService")
public class LoginService {
	AccountLogic accountLogic;
	String stringResult;
	
	@SuppressWarnings("finally")
	@Path("/loginCheck")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String checkUser(@Context UriInfo info) {
		
		String username=info.getQueryParameters().getFirst("username");
		String password = info.getQueryParameters().getFirst("password");
		try{
			accountLogic = new AccountLogic();
			List<User> users = accountLogic.checkLogin(username, password);
			if(users.size()!=0){
				stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("200", "success", users);
			}else{
				stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("200", "fail", null);
			}
		}catch(Exception e){
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("200", "fail", null);
		}finally{
			return stringResult;
		}
	}
}
