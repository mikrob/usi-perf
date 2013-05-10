'use strict';

/* Controllers */

function CocktailListCtrl($scope, $http) {
    $http.get('services/cocktail/list').success(function (data) {
        $scope.cocktails = data;
    });
    $scope.change = function () {
      $http.get('services/cocktail/filter/' + $scope.query).success(function (data) {
        $scope.cocktails = data;
      });
    };
}