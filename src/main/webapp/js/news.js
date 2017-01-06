/**
 * Created by Arek on 02.01.2017.
 */
var app = angular.module('NewsApp', []);

app.controller('NewsCtrl', function ($scope, $http) {
    $scope.currentPage = 0;
    $scope.pageSize = 6;
    $scope.q = '';

    $scope.numberOfPages = function () {
        return Math.ceil($scope.news.length / $scope.pageSize);
    };


        $http.get('/rest/news')
            .then(function (success) {
                $scope.news = success.data;
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