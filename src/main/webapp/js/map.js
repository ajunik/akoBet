angular.module('ionic.example', ['ionic'])

    .controller('MapCtrl', function ($scope, $ionicLoading, $compile) {
        function initialize() {
            var myLatlng = new google.maps.LatLng(50.07116, 19.942859);

            var mapOptions = {
                center: myLatlng,
                zoom: 18,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("map"),
                mapOptions);

            //Marker + infowindow + angularjs compiled ng-click
            var contentString = "<div><a ng-click='clickTest()'>Click me!</a></div>";
            var compiled = $compile(contentString)($scope);

            var infowindow = new google.maps.InfoWindow({
                content: compiled[0]
            });

            var marker = new google.maps.Marker({
                position: myLatlng,
                map: map,
                title: 'Uluru (Ayers Rock)'
            });

            google.maps.event.addListener(marker, 'click', function () {
                infowindow.open(map, marker);
            });

            $scope.map = map;
        }

        google.maps.event.addDomListener(window, 'load', initialize);

        $scope.centerOnMe = function () {

            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {
                    var pos = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude
                    };

                    $scope.map.setCenter(pos);
                }, function () {
                    handleLocationError(true, infoWindow, map.getCenter());
                });
            }
        };

        $scope.clickTest = function () {
            alert('Example of infowindow with ng-click')
        };

    });