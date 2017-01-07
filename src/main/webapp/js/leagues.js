/**
 * Created by Arek on 06.01.2017.
 */

var app = angular.module('LeaguesApp', []);

app.controller('LeaguesCtrl', function ($scope, $http) {
    $scope.currentPage = 0;
    $scope.pageSize = 5;
    $scope.q = '';

    $scope.numberOfPages = function () {
        return Math.ceil($scope.leagues.length / $scope.pageSize);
    };


    $http.get('/rest/leagues')
        .then(function (success) {
            $scope.leagues = success.data;
        }, function (error) {
            console.log('error');
        });

    $http.get('/rest/user')
        .then(function (success) {
            $scope.user = success.data;
        }, function (error) {
            console.log('error');
        });

});

app.filter('startFrom', function () {
    return function (input, start) {
        if (!input || !input.length) {
            return;
        }
        start = +start;
        return input.slice(start);
    }
});