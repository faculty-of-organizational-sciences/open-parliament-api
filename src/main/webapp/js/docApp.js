/**
 * Created by Branislav Vidojevic on 15/10/2015.
 */

var host = "http://localhost:9090/api";

var app = angular.module("app", ['duScroll', 'ngResource']);


//default scroll duration
app.value('duScrollDuration', 850);

app.controller('DocsCtrl', ['$scope', '$http', function ($scope, $http) {

    $http.get('documentation.json').success(function (data) {
        $scope.docs = data.docs;
    }).error(function () {
        console.log('Error, GET documentation.json failed.');
    });

    $scope.formatJson = function (text) {
        return JSON.stringify(JSON.parse(text), null, 2);
    }

}]);
