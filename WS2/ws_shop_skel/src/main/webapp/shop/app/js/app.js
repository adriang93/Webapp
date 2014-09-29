'use strict';

/* 
 *  The Shop App
 */
var shop = angular.module('Shop', [
    'ngRoute','ProductCatalogueControllers',
    'ProductCatalogueService'
     // More here
]);


shop.config(['$routeProvider',
    function($routeProvider) {  // Injected object $routeProvider
        $routeProvider.
                when('/products', {
                    templateUrl: 'partials/products/products.html',
                    controller:'ProductListCtrl'
                }).
                when('/product-new', {
                    templateUrl: "partials/products/product-new.html",
                    controller:'ProductNewCtrl'
                }). 
                when('/products/:id', {
                    templateUrl: 'partials/products/product-detail.html',
                    controller: 'ProductDetailCtrl'
                }).
                        
                when('/customers', {
                    templateUrl: 'partials/customers/customers.html'
                    //controller: Not used
                }).
                when('/orders', {
                    templateUrl: 'partials/orders/orders.html'
                    //controller: Not used
                }).
                otherwise({
                    redirectTo: '/index.html'
                });

    }]);


