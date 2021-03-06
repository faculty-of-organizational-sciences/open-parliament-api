var app = angular.module("app", ['ngRoute', 'ngAnimate', 'ui.bootstrap', 'ngResource', 'duScroll', 'pascalprecht.translate', 'ngSanitize']);

var host = "http://localhost:8080/api/api";

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/members-example', {
            templateUrl: 'views/members-example.html',
            controller: 'MembersCtrl',
            controllerAs: 'mctrl'
        })
        .when('/party-example', {
            templateUrl: 'views/party-example.html',
            controller: 'PartyCtrl',
            controllerAs: 'pctrl'
        })
        .when('/session-example', {
            templateUrl: 'views/session-example.html',
            controller: 'SessionCtrl',
            controllerAs: 'sctrl'
        })
}]);

app.config(['$translateProvider', function ($translateProvider) {
    $translateProvider.preferredLanguage('en');
    $translateProvider.useSanitizeValueStrategy('sanitize');
    $translateProvider.useStaticFilesLoader({
        prefix: './languages/',
        suffix: '.json'
    });
}]);

app.value('duScrollDuration', 850);