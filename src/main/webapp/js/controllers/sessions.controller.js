angular.module("app").controller('SessionCtrl', ['sessionService', function (sessionService) {

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