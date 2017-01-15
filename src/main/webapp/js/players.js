/**
 * Created by Arek on 06.01.2017.
 */

var app = angular.module('PlayersApp', []);

app.controller('PlayersCtrl', function ($scope, $http) {

    $scope.busyPlaces = busy;
    $scope.fullPlaces = cap;
    $scope.rounds = [];
    $scope.roundDuels = [];

    for (var i = 1; i < $scope.busyPlaces; i++) {
        $scope.rounds.push(i);
    }

    $http.get('/rest/scheduler/' + id)
        .then(function (success) {
            $scope.allDuels = success.data;
        }, function (error) {
            console.log('error');
        });

    $http.get('/rest/players/' + id)
        .then(function (success) {
            $scope.players = success.data;
            $scope.numberOfMatches = $scope.players[1].matches;
        }, function (error) {
            console.log('error');
        });


});