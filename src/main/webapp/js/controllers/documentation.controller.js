angular.module("app").controller('DocsCtrl', ['$scope', '$http', function ($scope, $http) {

    $http.get('documentations/documentation.json').success(function (data) {
        $scope.docs = data.docs;
    }).error(function () {
        console.log('Error, GET documentation.json failed.');
    });

    $scope.formatJson = function (text) {
        return JSON.stringify(JSON.parse(text), null, 2);
    }

}]);