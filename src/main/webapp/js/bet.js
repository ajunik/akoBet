/**
 * Created by Arek on 14.01.2017.
 */
var app = angular.module('BetApp', []);

app.controller('BetCtrl', function ($scope, $http) {

    $scope.rounds = [];
    $scope.types = ['1','X', '2'];
    $scope.dateString;
    var currentDate = new Date();
    var date;
    $scope.time;

    for (var i = 1; i < maxRound; i++) {
        $scope.rounds.push(i);
    }

    $("select#123").change(function(){
        var temp = $('#123 option:selected').val() - 1;
        var temp2 = temp*5;
        date = new Date($scope.matches[temp2].date);

        if(date.getTime() < currentDate.getTime()) {
            $scope.time = true;
        } else {
            $scope.time = false;
        }

        var month = date.getMonth()+1;
        var minutes = date.getMinutes();
        if(month.toString().length == 1) {
            month = '0' + month;
        }
        if(minutes.toString().length == 1) {
            minutes = '0' + minutes;
        }
        $scope.dateString = date.getDate() + '-' + month + '-' + date.getFullYear() + ' ' + date.getHours() + ':' + minutes;
    });

    $http.get('/rest/matches/' + league)
        .then(function (success) {
            $scope.matches = success.data;
        }, function (error) {
            console.log('error');
        });

    $scope.compareDate = function(round, now) {
        var bool;
        var temp = round - 1;
        var temp2 = temp*5;
        date = new Date($scope.matches[temp2].date);


        if(date.getTime() > now.getTime()) {
            bool=true;
        } else {
            bool = false;
        }
        return bool;
    };


});