/**
 * Created by Arek on 14.01.2017.
 */
var app = angular.module('ResultApp', []);

app.controller('ResultCtrl', function ($scope, $http) {

    $scope.results = ['1', 'X', '2'];


    $http.get('/rest/matches/all')
        .then(function (success) {
            $scope.matches = success.data;
        }, function (error) {
            console.log('error');
        });


});