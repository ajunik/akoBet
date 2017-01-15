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
    $scope.typesForRound;
    $scope.showForm;

    for (var i = 1; i <= maxRound; i++) {
        $scope.rounds.push(i);
    }

    $("select#123").change(function(){
        var temp = $('#123 option:selected').val() - 1;
        var temp2 = temp*5;
        date = new Date($scope.matches[temp2].date);

        if (date.getTime() > currentDate.getTime()) {
            $scope.time = true;
        } else {
            $scope.time = false;
        }

        $scope.typesForRound = false;
        $scope.userTypes.forEach(function (userType) {
            if (userType.round == $('#123 option:selected').val()) {
                $scope.typesForRound = true;
            }
        });

        if ($scope.time && !$scope.typesForRound) {
            $scope.showForm = true;
        } else {
            $scope.showForm = false;
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

    $http.get('/rest/types/' + user)
        .then(function (success) {
            $scope.userTypes = success.data;
        }, function (error) {
            console.log('error');
        });



});