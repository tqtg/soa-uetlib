app = angular.module('soaApp', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/partials/book.html',
                controller: 'BookCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });