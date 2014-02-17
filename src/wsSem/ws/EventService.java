package wsSem.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import tools.Tools;
import tools.JsonResultGenerator;
import wsSem.facade.SparqlQueryEndpointFacade;
import wsSem.logic.ArtistLogic;
import wsSem.logic.EventLogic;
import wsSem.model.JsonAlbum;
import wsSem.model.JsonArtist;
import wsSem.model.JsonEvent;
import wsSem.model.JsonGroupe;
import exception.WebServiceException;

@Path("/EvenementService")
public class EventService {
	ArtistLogic artistLogic;
	EventLogic eventLogic;
	String stringResult;

	@SuppressWarnings("finally")
	@Path("getAlbumsByArtiste")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAlbumsByArtiste(@Context UriInfo uriInfo){
		String idJamendo = uriInfo.getQueryParameters().getFirst("idJamendo");
		String artistName = uriInfo.getQueryParameters().getFirst("artistName");
		try{
			artistLogic = new ArtistLogic();
			List<JsonAlbum> listeAlbum = artistLogic.getAlbumsByArtiste(idJamendo, artistName);
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("200", "success", listeAlbum);
		}catch(Exception e){
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("500", "fail", null);
		}finally{
			return stringResult;
		}
	}

	@SuppressWarnings("finally")
	@Path("getArtistsByGenre")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getArtistsByGenre(@Context UriInfo uriInfo){
		String genre1 = uriInfo.getQueryParameters().getFirst("genre1");
		String genre2 = uriInfo.getQueryParameters().getFirst("genre2");
		String genre3 = uriInfo.getQueryParameters().getFirst("genre3");
		String genre4 = uriInfo.getQueryParameters().getFirst("genre4");
		try{
			artistLogic = new ArtistLogic();
			List<JsonArtist> listeArtistes = artistLogic.getArtistsByGenre(genre1, genre2, genre3, genre4);
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("200", "success", listeArtistes);
		}catch(Exception e){
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("500", "fail", null);
		}finally{
			return stringResult;		
		}
	}

	@SuppressWarnings("finally")
	@Path("/getAllEvenements")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllEvenements(@Context UriInfo uriInfo){
		String lat = uriInfo.getQueryParameters().getFirst("lat");//"48.857";
		String lgt = uriInfo.getQueryParameters().getFirst("lgt");//"2.379";
		int radius = Integer.parseInt(uriInfo.getQueryParameters().getFirst("radius"));//10;
		String city = uriInfo.getQueryParameters().getFirst("city");//"Montpellier";
		String genre = "";
		try{
			eventLogic = new EventLogic();
			List<JsonEvent> eventList = eventLogic.getAllEvenements(lat, lgt, radius, city, genre);
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("200", "success", eventList);
		}catch (WebServiceException e){
			stringResult = JsonResultGenerator.getJsonResultFactory().createJsonResultString("500", "fail", null);
		}finally{
			return stringResult;
		}
	}
}
