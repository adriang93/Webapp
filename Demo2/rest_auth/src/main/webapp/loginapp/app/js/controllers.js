'use strict';

/* Controllers for the app */

var loginAppControllers = angular.module('LoginAppControllers', []);

loginAppControllers.controller('LoginCtrl', ['$scope', 'Auth', '$location',
    function($scope, Auth, $location) {

        $scope.login = function() {
            Auth.setCredentials($scope.user.name, $scope.user.passwd);
            $location.path("/api");
        };

        $scope.logout = function() {
            Auth.clearCredentials();
            $location.path("/login");
        };
    }
]);

loginAppControllers.controller('CallApiCtrl', ['$scope', 'APIProxy',
    function($scope, APIProxy) {  
        $scope.returnvalue = "undefined";
        $scope.update = function() {
            APIProxy.update()
                    .success(function() {
                    })
                    .error(function(json) {
                        $scope.message = json;
                    });
        };
        $scope.get = function() {
            APIProxy.get()
                    .success(function(json) {
                        $scope.returnvalue = json;
                    })
                    .error(function(json) {
                        $scope.message = json;
                    });
        };
    }
]);


