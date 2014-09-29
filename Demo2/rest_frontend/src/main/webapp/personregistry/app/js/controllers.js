'use strict';

/* 
 * A module for all controllers used by the app.
 * Required by PersonReg module
 * Your controller should be responsible for binding model data 
 * to views using $scope. It does not contain logic to 
 * fetch the data or manipulating it.
 * 
 * Also possible to handle (create) "listeners" for events.
 * 
 * Controllers are connected to elements (partials) in personReg.config
 * Also possible to override and add other controller to specific (sub) element
 * 
 * NO DOM manipulation code here use the "binding" mechanism.
 */

var personRegControllers = angular.module('PersonRegControllers', []);


// Add a controller named 'PersonRegCtrl', then dependency list. Objects injected
// as params
personRegControllers.controller('PersonListCtrl', ['$scope', 'PersonRegProxy',
    function($scope, PersonsRegProxy) {
        $scope.orderProp = 'id';
        $scope.pageSize = '10';
        $scope.currentPage = 0;
        PersonsRegProxy.count()
                .success(function(count) {
                    $scope.count = count.value;
                }).error(function() {
            console.log("count: error");
        });
        getRange();
        $scope.$watch('currentPage', function() {
            getRange();
        });
        function getRange() {
            var fst = $scope.pageSize * $scope.currentPage;
            PersonsRegProxy.findRange(fst, $scope.pageSize)
                    .success(function(persons) {
                        $scope.persons = persons;
                    }).error(function() {
                console.log("findRange: error");
            });
        }
    }]);

personRegControllers.controller('PersonDetailCtrl', ['$scope',
    '$location', '$routeParams', 'PersonRegProxy',
    function($scope, $location, $routeParams, PersonRegProxy) {
        PersonRegProxy.find($routeParams.id)
                .success(function(person) {
                    $scope.person = person;
                }).error(function() {
            console.log("selectByPk: error");
        });

        // A listener
        $scope.update = function() {
            PersonRegProxy.update($routeParams.id, $scope.person)
                    .success(function() {
                        $location.path('/persons');
                    }).error(function() {
                ; // TODO;
            });
            //$location.path("persons");
        };
        // A listener
        $scope.delete = function() {
            // Really delete?? message
            PersonRegProxy.delete($routeParams.id)
                    .success(function() {
                        $location.path('/persons');
                    }).error(function() {
                ; // TODO;
            });
        };
    }]);


personRegControllers.controller('PersonNewCtrl', ['$scope',
    '$location', 'PersonRegProxy',
    function($scope, $location, PersonRegProxy) {
        $scope.save = function() {
            PersonRegProxy.create($scope.person)
                    .success(function() {
                        $location.path('/persons');
                    }).error(function() {
                ; // TODO;
            });
        };
    }]);

// General navigation controller
personRegControllers.controller('NavigationCtrl', ['$scope', '$location',
    function($scope, $location) {
        $scope.navigate = function(url) {
            $location.path(url);
        };
    }]);
