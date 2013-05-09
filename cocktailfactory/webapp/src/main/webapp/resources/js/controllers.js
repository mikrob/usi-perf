'use strict';

/* Controllers */

function WordListCtrl($scope, $http) {
  $http.get('novlex-base.txt.json').success(function(data) {
    $scope.words = data;
  });

  $scope.myFilter = function(word) {
    if($scope.query == undefined) {
      return false;
    }
    var patt = new RegExp($scope.query);
    return patt.test(word.w);
  }
}