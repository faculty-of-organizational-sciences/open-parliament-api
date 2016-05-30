angular.module("app").controller('PartyCtrl', ['partyService', function (partyService) {

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