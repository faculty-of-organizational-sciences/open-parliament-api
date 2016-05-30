angular.module("app").controller('MembersCtrl', ['memberService', function (memberService) {

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