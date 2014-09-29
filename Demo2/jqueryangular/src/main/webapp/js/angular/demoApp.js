'use strict';

/*
 *  Basic AngularJS demo (no need to match filename and module)
 *  Main module
 */

var demoApp = angular.module('demoApp', ['ngRoute']);

demoApp.controller('demoCtrl', function($scope) {
    // Accessible in page using {{ }}
    $scope.someValues = ["aaa", "bbb", "ccc", "ddd"];
    $scope.listeners = {};
    $scope.listeners.doClick = function(evt) {
        console.log("Button clicked " + evt);
        // Still using JQuery ... just for **NOW**
        $("#holder").append("Button clicked");
    };
    
});

demoApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
                when('/home', {
                    templateUrl: 'partials/home.html',
                    controller: 'homeCtrl'
                }).
                when('/contact', {
                    templateUrl: 'partials/contact.html',
                    controller: 'contactCtrl'
                }).
                otherwise({
                    redirectTo: '/home'
                });
    }]);

demoApp.controller('homeCtrl', function($scope) {
    $scope.data = "Home data";
});

demoApp.controller('contactCtrl', function($scope) {
    $scope.data = "Control data";
});