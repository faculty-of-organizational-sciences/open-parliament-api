angular.module("app").controller('LanguageSwitchCtrl', ['$translate', '$scope', function ($translate, $scope) {
    $scope.toogleLanguage = function () {
        $translate.use() === 'en' ? $translate.use('rs') : $translate.use('en');
    };
}]);