var app = angular.module('soaApp', ['ngRoute', 'ui.bootstrap', 'ngDialog', 'angucomplete-alt']);

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