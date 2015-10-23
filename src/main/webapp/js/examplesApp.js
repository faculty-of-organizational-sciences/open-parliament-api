/**
 * Created by Branislav Vidojevic on 23/10/2015.
 */
/**
 * Created by Branislav Vidojevic on 15/10/2015.
 */

var host = "http://localhost:9090/api";

var app = angular.module("examplesApp", ['ngAnimate', 'ui.bootstrap', 'ngResource']);

app.controller('ExampleCtrl', ['$scope', 'memberService', '$http', function ($scope, memberService, $http) {

    $scope.maxSize = 1;
    $scope.totalItems = 11;
    $scope.itemsPerPage = 10;
    $scope.currentPage = 1;
    $scope.count = 0;

    memberService.MembersR.query({page: '1'}, function (data) {
        $scope.members = data;
    });

    $scope.getMembers = function (n, text) {
        memberService.MembersR.query({page: n.toString(), query: text}, function (data) {
            $scope.members = data;
            $scope.count = (n - 1) * 10;
            $scope.totalItems = n * $scope.itemsPerPage + 1;
        });
    };

    $scope.maxSize1 = 1;
    $scope.totalItems1 = 11;
    $scope.itemsPerPage1 = 10;
    $scope.currentPage1 = 1;
    $scope.count1 = 0;

    $scope.showSpeechText = function(speech) {
        $scope.text = speech.text;
        $scope.date = speech.sessionDate;
    };

    $scope.getSpeeches = function (member, pageNum) {
        memberService.SpeechesR.query({id: member.toString(), page: pageNum.toString()}, function (data) {
            $scope.speeches = data;
            $scope.count1 = (pageNum - 1) * 10;
            $scope.id = member;
            $scope.totalItems1 = pageNum * $scope.itemsPerPage1 + 1;
        });

    };

    $scope.showBio = function (member) {
        $scope.bio = member;
        $scope.getSpeeches(member.id, 1);
    };

}]);

app.service('memberService', ['$resource', function ($resource) {
    this.MembersR = $resource(host + '/members');
    this.SpeechesR = $resource(host + '/members/:id/speeches');
}]);

app.service('sessionService', ['$resource', function ($resource) {
    this.sessionR = $resource(host + '/sessions/:id');
}]);
