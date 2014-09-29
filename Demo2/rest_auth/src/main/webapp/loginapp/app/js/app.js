'use strict';

/*
 * The app
 * 
 * NOTE: angular-base64 (base64) is not in the standard distribution must be added
 */
var loginApp = angular.module('LoginApp', ['LoginAppControllers', 'LoginAppService',
    'ngRoute', 'base64', 'ngCookies']);


loginApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
                when('/login', {
                    templateUrl: 'partials/login.html',
                    controller: 'LoginCtrl'
                }).
                when('/api', {
                    templateUrl: 'partials/callApi.html',
                    controller: 'CallApiCtrl'
                }).
                otherwise({
                    redirectTo: '/'
                });
    }]);


