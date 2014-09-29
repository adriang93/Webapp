'use strict';

/* 
 * This is the root element of the application used to auto 
 * bootstrap the application (used with ngApp directive, 
 * see index.html, html-tag)
 * 
 * Create and register an Angular module.
 * All modules that should be available to an application must be 
 * registered using this mechanism
 * A module is a collection of services, directives, filters, 
 * and configuration information.
 * 
 */

// Module (reference to) is personReg with name 'PersonReg'. Then a list of required modules
// (one native Angular; 'ngRoute' and 2 application specific modules)
    var personReg = angular.module('PersonReg', [
        'ngRoute',
        'PersonRegControllers',
        'PersonRegService'
        /*'PersonRegFilters'*/
    ]);

// Config our application.
// Connect routes (URLs), partials and controllers
// $routProvider (from ngRoute module) as dependency, injected
// as param to function (so we'll get an object to use in function)
// File angular-route.js needed.
personReg.config(['$routeProvider' /*, '$locationProvider'*/,
    function($routeProvider /*, $locationProvider*/) {  // Injected object $routeProvider
        $routeProvider.
                when('/persons', {
                    templateUrl: 'partials/person-list.html',
                    controller: 'PersonListCtrl'
                }).
                when('/persons/:id', {
                    templateUrl: 'partials/person-detail.html',
                    controller: 'PersonDetailCtrl'
                }).
                when('/person', {
                    templateUrl: 'partials/person-new.html',
                    controller: 'PersonNewCtrl'
                }).
                otherwise({
                    redirectTo: '/persons'
                });
        //$locationProvider.html5Mode(true);
    }]);


