angular.module('ionic.example', ['ionic'])

    .controller('MapCtrl', function ($scope, $http, $ionicLoading, $compile) {

        function initialize() {
            var myLatlng = new google.maps.LatLng(50.07116, 19.942859);
            var mapOptions = {
                center: myLatlng,
                zoom: 18,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("map"),
                mapOptions);

            var markIcon = {
                url: 'http://images.clipartpanda.com/google-location-icon-color_icons_green_home.png',
                scaledSize: new google.maps.Size(30, 50)
            };
            var marker = new google.maps.Marker({
                position: myLatlng,
                map: map,
                title: 'Politechnika Krakowska',
                icon: markIcon
            });


            $http.get('/rest/map')
                .then(function (success) {
                    var bookPoints = success.data;
                    var markerIcon;
                    bookPoints.forEach(function (place) {
                        if (place.name == "STS") {
                            markerIcon = {
                                url: "http://maps.google.com/mapfiles/ms/icons/blue-dot.png",
                                scaledSize: new google.maps.Size(40, 40)
                            };
                        } else if (place.name == "TOTOLOTEK") {
                            markerIcon = {
                                url: "http://maps.google.com/mapfiles/ms/icons/red-dot.png",
                                scaledSize: new google.maps.Size(40, 40)
                            };
                        } else if (place.name == "FORTUNA") {
                            markerIcon = {
                                url: "http://maps.google.com/mapfiles/ms/icons/yellow-dot.png",
                                scaledSize: new google.maps.Size(40, 40)
                            };
                        } else {
                            markerIcon = {
                                url: "http://maps.google.com/mapfiles/ms/icons/blue-dot.png",
                                scaledSize: new google.maps.Size(40, 40)
                            };
                        }
                        marker = new google.maps.Marker({
                            position: new google.maps.LatLng(place.latitude, place.longitude),
                            map: map,
                            title: place.name,
                            icon: markerIcon,
                            clickable: true
                        });
                        var message = "<h4>" + place.name + "</h4><p>" + place.address + "</p>";
                        addInfoWindow(marker, message);
                    });
                }, function (error) {
                    console.log('error add markers');
                });


            $scope.map = map;
        }

        function addInfoWindow(marker, message) {

            var infoWindow = new google.maps.InfoWindow({
                content: message
            });

            google.maps.event.addListener(marker, 'click', function () {
                infoWindow.open(map, marker);
            });
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

    });