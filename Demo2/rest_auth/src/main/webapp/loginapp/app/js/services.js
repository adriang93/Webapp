'use strict';

/* Services */

var loginAppService = angular.module('LoginAppService', []);

// Used to call the protected URLS (only works if logged in)
loginAppService.factory('APIProxy', ['$http',
    function($http) {

        var url = 'http://localhost:8080/rest_auth/webresources/private';

        return {
            update: function() {
                return $http.put(url, 111);
            },
            get: function() {
                return $http.get(url);
            }
        };
    }]);

loginAppService.factory('Auth', ['$base64', '$http',
    function(base64, $http) {
     
        return {
            setCredentials: function(username, password) {
                // Auth dta just set in local app , Server not contacted
                var encoded = base64.encode(username + ':' + password);
                $http.defaults.headers.common.Authorization = 'Basic ' + encoded;

            },
            clearCredentials: function() {
                document.execCommand("ClearAuthenticationCache"); // TODO not standard
                //$cookieStore.remove('authdata');
                $http.defaults.headers.common.Authorization = 'Basic ';
            }
        };
    }]);


