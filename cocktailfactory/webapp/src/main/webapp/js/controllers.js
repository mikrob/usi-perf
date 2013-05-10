'use strict';

/* Controllers */

function CocktailListCtrl($scope, $http) {
  $http.get('services/cocktail/list').success(function (data) {
    $scope.cocktails = data;
  });
  $scope.change = function () {
    var url = ($scope.query ? ('services/cocktail/filter/' + $scope.query) : 'services/cocktail/list');
    $http.get(url).
      success(function (data) {
        $scope.cocktails = data;
      });
  };
}