/**
 * Created by Arek on 02.01.2017.
 */
angular.module('NewsApp', [])

    .controller('NewsCtrl', function ($scope, $http) {

        $http.get('/rest/news')
            .then(function (success) {
                $scope.news = success.data;
            }, function (error) {
                console.log('error');
            });

    });