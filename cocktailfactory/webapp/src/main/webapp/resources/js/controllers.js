'use strict';

/* Controllers */

function CocktailListCtrl($scope, $http) {
  $http.get('services/cocktail/filter/' + $scope.query).success(function(data) {
    $scope.cocktails = data;
  });
}