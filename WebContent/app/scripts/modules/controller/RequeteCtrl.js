(function(){
	'use strict';
	var module = angular.module('mod.controller');

	module.controller('RequeteCtrl',  ['$scope', 'cmWSFacade', 'webStorage', 'BusinessService','$routeParams',
	                                   function($scope, cmWSFacade, webStorage,BusinessService, $routeParams){
		$scope.dataAnnotation = [];
		$scope.gridOptions = {
				data: 'dataAnnotation',
				i18n:'fr',
				rowHeight: 80,
				enableColumnResize:true, 
				enableRowReordering:false, 
				enableSorting:true,
				showColumnMenu:true, 
				showFilter:true,
				showSelectionCheckbox:false, 
				selectWithCheckboxOnly:false,
				//plugins:[new ngGridFlexibleHeightPlugin()],
				columnDefs: [
				             {field:'numAnno', displayName:'Num Anno'}, 
				             {field:'nom', displayName:'Nom User'}, 
				             {field:'mail', displayName:'Mail User'}, 
				             {field:'annotation', displayName:'Annotation'}, 
				             {field:'event', displayName:'Event'}, 
				             {field:'date', displayName:'Date'}, 
				             {field:'lat', displayName:'Latitude'},
				             {field:'lgt', displayName:'Longitude'}
				             ]
		};
		
		$scope.processRequete = function(){
			console.log($scope.strRequete);
			var content = {
					sparql:$scope.strRequete
			};
			BusinessService.getBusinessByRequest(content).success(function(data, status){
				if(data.result=='fail'){
					alert('La requete ne marche pas!!');
				}else{
					$scope.dataAnnotation = data.binding;
					//Filter col names returned
					var objTop = $scope.dataAnnotation[0];
					$scope.colums = [];
				}
			}).error(function(data, status){
				alert('Error serveur');
			});
		};
		
		$scope.init = function(){};

		//Methodes init
		$scope.init();
	}]);

})();