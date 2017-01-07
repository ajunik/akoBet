/**
 * Created by Arek on 06.01.2017.
 */

var app = angular.module('PlayersApp', []);

app.controller('PlayersCtrl', function ($scope, $http) {


    $http.get('/rest/users')
        .then(function (success) {
            $scope.players = success.data;
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