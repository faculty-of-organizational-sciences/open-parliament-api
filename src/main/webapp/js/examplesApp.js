/**
 * Created by Branislav Vidojevic on 15/10/2015.
 */

var host = "http://localhost:8080/api";

var app = angular.module("examplesApp", ['ngRoute', 'ngAnimate', 'ui.bootstrap', 'ngResource']);

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

app.controller('MembersCtrl', ['memberService', function (memberService) {

    var vm = this;

    vm.mpag = {
        maxSize: 3,
        totalItems: 0,
        itemsPerPage: 10,
        currentPage: 1,
        count: 0
    };

    memberService.MembersR.get({page: '1'}, function (data) {
        vm.members = data.dataArray;
        vm.mpag.totalItems = data.count;
    }, function () {
        vm.members = {};
        vm.mpag.totalItems = 0;
    });

    vm.getMembers = function (n, text) {
        memberService.MembersR.get({page: n.toString(), query: text}, function (data) {

            vm.members = data.dataArray;
            vm.mpag.count = (n - 1) * 10;
            vm.mpag.totalItems = data.count;

            vm.selectedRow = null;
        }, function () {
            vm.members = {};
            vm.mpag.totalItems = 0;
        });
    };

    vm.mpag1 = {
        maxSize: 3,
        totalItems: 0,
        itemsPerPage: 10,
        currentPage: 1,
        count: 0
    };

    vm.showSpeechText = function (speech, index) {

        vm.selectedRowSpeech = index;

        vm.text = speech.text;
        vm.date = speech.sessionDate;
        vm.agenda = speech.sessionAgenda;
    };

    vm.getSpeeches = function (memberId, pageNum) {

        if (pageNum == null || pageNum == undefined) {
            pageNum = 1;
        }

        memberService.SpeechesR.get({id: memberId.toString(), page: pageNum.toString()}, function (data) {

            vm.speeches = data.dataArray;
            vm.mpag1.count = (pageNum - 1) * 10;
            vm.id = memberId;
            vm.mpag1.totalItems = data.count;

            vm.agenda = {};

            vm.selectedRowSpeech = null;
        }, function () {
            vm.speeches = {};
            vm.mpag1.totalItems = 0;
        });

    };

    vm.getSpeechesQuery = function (memberId, pageNum, qtext) {

        if (pageNum == null || pageNum == undefined) {
            pageNum = 1;
        }

        memberService.SpeechesR.get({id: memberId.toString(), page: pageNum.toString(), qtext: qtext}, function (data) {
            vm.speeches = data.dataArray;
            vm.mpag1.count = (pageNum - 1) * 10;
            vm.id = memberId;
            vm.mpag1.totalItems = data.count;
        }, function () {
            vm.speeches = {};
            vm.mpag1.totalItems = 0;
        });
    };

    vm.showBio = function (member, index) {
        vm.mpag1.currentPage = 1;

        vm.selectedRow = index;
        vm.selectedRowSpeech = null;

        vm.bio = member;
        vm.getSpeeches(member.id, 1);
    };

}]);

app.controller('PartyCtrl', ['partyService', function (partyService) {

    var vm = this;

    vm.pag = {
        maxSize: 3,
        totalItems: 0,
        itemsPerPage: 10,
        currentPage: 1,
        count: 0
    };

    partyService.PartiesR.get({page: '1'}, function (data) {
        vm.parties = data.dataArray;
        vm.pag.totalItems = data.count;
    }, function () {
        vm.parties = {};
        vm.pag.totalItems = 0;
    });

    vm.getParties = function (n, name) {
        partyService.PartiesR.get({page: n.toString(), query: name}, function (data) {
            vm.selectedRow = null;
            vm.parties = data.dataArray;
            vm.pag.count = (n - 1) * 10;
            vm.pag.totalItems = data.count;

            vm.pag.currentPage1 = 1;
            vm.members = {};
        }, function () {
            vm.parties = {};
            vm.pag.totalItems = 0;
        });
    };

    vm.pag1 = {
        maxSize: 3,
        totalItems: 0,
        itemsPerPage: 10,
        currentPage: 1,
        count: 0
    };

    vm.getMembers = function (id, n, index) {

        vm.selectedRow = index;

        if (n == null || n == undefined) {
            n = 1;
        }

        partyService.PartyMembersR.get({page: n.toString(), id: id}, function (data) {
            vm.members = data.dataArray;
            vm.pag1.count = (n - 1) * 10;
            vm.id = id;
            vm.pag1.totalItems = data.count;
        }, function () {
            vm.members = {};
            vm.pag1.totalItems = 0;
        });
    };
}]);

app.controller('SessionCtrl', ['sessionService', function (sessionService) {

    var vm = this;

    vm.spag = {
        maxSize: 3,
        totalItems: 0,
        itemsPerPage: 10,
        currentPage: 1,
        count: 0
    };

    sessionService.sessionsR.get({page: '1'}, function (data) {
        vm.sessions = data.dataArray;
        vm.spag.totalItems = data.count;
    });

    vm.getSessions = function (n) {
        sessionService.sessionsR.get({page: n.toString()}, function (data) {

            vm.selectedRow = null;
            vm.sessions = data.dataArray;
            vm.spag.count = (n - 1) * 10;
            vm.spag.totalItems = data.count;
            vm.spag.currentPage1 = 1;
        }, function () {
            vm.sessions = {};
            vm.spag.totalItems = 0;
        });
    };

    vm.spag1 = {
        maxSize: 3,
        totalItems: 0,
        itemsPerPage: 10,
        currentPage: 1,
        count: 0
    };

    vm.getSpeeches = function (session, pageNum, index) {

        if (pageNum == null || pageNum == undefined) {
            pageNum = 1;
        }
        sessionService.sessionSpeechesR.get({id: session.id, page: pageNum.toString()}, function (data) {
            vm.session = session;
            vm.speeches = data.dataArray;
            vm.spag1.count = (pageNum - 1) * 10;
            vm.id = session.id;
            vm.spag1.totalItems = data.count;

            vm.selectedRow = index;
            vm.selectedRowSpeech = null;

            vm.agenda = session.agenda;
            vm.transcript = session.transcriptText;
        }, function () {
            vm.speeches = {};
            vm.spag1.totalItems = 0;
        });
    };

    vm.showSpeechText = function (speech, index) {

        vm.selectedRowSpeech = index;

        vm.text = speech.text;
        vm.date = speech.sessionDate;
    };

}]);


app.service('memberService', ['$resource', function ($resource) {
    this.MembersR = $resource(host + '/members');
    this.SpeechesR = $resource(host + '/members/:id/speeches');
}]);

app.service('sessionService', ['$resource', function ($resource) {
    this.sessionsR = $resource(host + '/sessions');
    this.sessionSpeechesR = $resource(host + '/sessions/:id/speeches');
}]);

app.service('partyService', ['$resource', function ($resource) {
    this.PartiesR = $resource(host + '/parties');
    this.PartyMembersR = $resource(host + '/parties/:id/members');
}]);
