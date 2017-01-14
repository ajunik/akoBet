/**
 * Created by Arek on 14.01.2017.
 */
var app = angular.module('MatchApp', []);

app.controller('MatchCtrl', function ($scope, $http) {


    $http.get('/rest/leagues')
        .then(function (success) {
            $scope.leagues = success.data;
        }, function (error) {
            console.log('error');
        });


});
