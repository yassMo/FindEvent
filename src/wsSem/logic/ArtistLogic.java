package wsSem.logic;

import java.util.ArrayList;
import java.util.List;

import exception.WebServiceException;
import wsSem.facade.SparqlQueryEndpointFacade;
import wsSem.model.JsonAlbum;
import wsSem.model.JsonArtist;
import wsSem.model.JsonEvent;

public class ArtistLogic {
	//Request from Jamendo
	public List<JsonAlbum> getAlbumsByArtiste(String idJamendo, String username){
		List<JsonAlbum> listeAlbum = new ArrayList<JsonAlbum>();
		listeAlbum = SparqlQueryEndpointFacade.getAlbumsByArtiste(idJamendo, username);
		return listeAlbum;
	}
	
	public List<JsonArtist> getArtistsByGenre(String genre1, String genre2, String genre3, String genre4){
		List<JsonArtist> listeArtistes = new ArrayList<JsonArtist>();
		listeArtistes = SparqlQueryEndpointFacade.getArtistesByGenres(genre1, genre2, genre3, genre4);
		return listeArtistes;
	}
}
