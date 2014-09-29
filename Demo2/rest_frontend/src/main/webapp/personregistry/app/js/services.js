'use strict';

/* Services */

var personRegService = angular.module('PersonRegService', [] ); 

// Singleton objects, will return return value  of supplied function, NOT the function
// First param is the name of the service then dependncy then factory method
personRegService.factory('PersonRegProxy', ['$http',
    function($http) {

        var url = 'http://localhost:8080/rest_backend/webresources/json';

        return {
            findAll: function() {
                return $http.get(url);
            },
            findRange: function(first, count) {
                return $http.get(url + "/range?fst=" + first + "&count=" + count);
            },
            find: function(id) {
                return $http.get(url + "/" + id);
            },
            update: function(id, person) {
                return $http.put(url + "/" + id, person);
            },
            create: function(person) {
                return $http.post(url, person);
            },
            delete: function(id) {
                return $http.delete(url + "/" + id);
            },
            count: function() {
                return $http.get(url + "/count");
            }
        };
    }]);

