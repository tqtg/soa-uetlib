var app = angular.module('soaApp', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : '/partials/book.html',
            controller  : 'BookCtrl'
        })
        .otherwise({
            redirectTo  : '/'
        });
});