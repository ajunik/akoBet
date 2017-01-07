/**
 * Created by Arek on 07.01.2017.
 */

var app = angular.module('HomeApp', []);

app.controller('HomeCtrl', function ($scope, $http) {

    $http.get('/rest/user')
        .then(function (success) {
            $scope.user = success.data;
        }, function (error) {
            console.log('error');
        });

});