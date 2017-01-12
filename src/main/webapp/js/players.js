/**
 * Created by Arek on 06.01.2017.
 */

var app = angular.module('PlayersApp', []);

app.controller('PlayersCtrl', function ($scope, $http) {

    $scope.busyPlaces = busy;
    $scope.fullPlaces = cap;

    $http.get('/rest/players/' + id)
        .then(function (success) {
            $scope.players = success.data;
        }, function (error) {
            console.log('error');
        });

    $scope.generateTimetable = function () {

    };

});