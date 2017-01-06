/**
 * Created by Arek on 06.01.2017.
 */

var app = angular.module('UsersApp', []);

app.controller('UsersCtrl', function ($scope, $http) {
    $scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.q = '';

    $scope.numberOfPages = function () {
        return Math.ceil($scope.users.length / $scope.pageSize);
    };


    $http.get('/rest/users')
        .then(function (success) {
            $scope.users = success.data;
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