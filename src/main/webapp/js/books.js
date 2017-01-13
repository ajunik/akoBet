var app = angular.module('BooksApp', []);

app.controller('BooksCtrl', function ($scope, $http) {

    $scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.q = '';

    $scope.numberOfPages = function () {
        return Math.ceil($scope.books.length / $scope.pageSize);
    };

    $http.get('/rest/map')
        .then(function (success) {
            $scope.books = success.data;
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