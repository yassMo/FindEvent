(function(){
	'use strict';
	var mod = angular.module('mod.service');

	mod.factory('EvenementService', ['$rootScope', 'cmWSFacade', 'webStorage',
	                                 function($rootScope, cmWSFacade,webStorage){

		return{
			getAllEvenements:function(lat, lgt, radius, city, genre){
				return cmWSFacade.cmWSGet('EvenementService/getAllEvenements?lat='+lat+'&lgt='+lgt+'&radius='+radius+"&city="+city+"&genre="+genre, false);
			}, 
			getGroupesByGenre:function(stylesByUser){
				var genre1 = _.isUndefined(stylesByUser[0])?null:stylesByUser[0].label;
				var genre2 = _.isUndefined(stylesByUser[1])?null:stylesByUser[1].label;
				var genre3 = _.isUndefined(stylesByUser[2])?null:stylesByUser[2].label;
				var genre4 = _.isUndefined(stylesByUser[3])?null:stylesByUser[3].label;
				return cmWSFacade.cmWSGet('EvenementService/getGroupesByGenre?genre1='+genre1+'&genre2='+genre2+'&genre3='+genre3+'&genre4='+genre4, true);
			}, 
			getArtistesByGenre:function(stylesByUser){
				var genre1 = _.isUndefined(stylesByUser[0])?null:stylesByUser[0].label;
				var genre2 = _.isUndefined(stylesByUser[1])?null:stylesByUser[1].label;
				var genre3 = _.isUndefined(stylesByUser[2])?null:stylesByUser[2].label;
				var genre4 = _.isUndefined(stylesByUser[3])?null:stylesByUser[3].label;
				return cmWSFacade.cmWSGet('EvenementService/getArtistsByGenre?genre1='+genre1+'&genre2='+genre2+'&genre3='+genre3+'&genre4='+genre4, true);
			}, 
			getAlbumsByArtiste:function(idJamendoArtiste, artistName){
				//change artistName format: ex. vincent j==>Vincent_J
				var finalName = "";
				artistName = _.clean(artistName);
				var words = _.words(artistName, " ");
				_.each(words, function(item){
					finalName=_.join("_", finalName, _.capitalize(item));
				});
				finalName = _.ltrim(finalName, '_');
				return cmWSFacade.cmWSGet('EvenementService/getAlbumsByArtiste?idJamendo='+idJamendoArtiste+"&artistName="+finalName, true);
			}
		}
	}]);
})();