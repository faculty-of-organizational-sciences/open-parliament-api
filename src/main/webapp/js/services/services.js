angular.module("app").service('memberService', ['$resource', function ($resource) {
    this.MembersR = $resource(host + '/members');
    this.SpeechesR = $resource(host + '/members/:id/speeches');
}]);

angular.module("app").service('sessionService', ['$resource', function ($resource) {
    this.sessionsR = $resource(host + '/sessions');
    this.sessionSpeechesR = $resource(host + '/sessions/:id/speeches');
}]);

angular.module("app").service('partyService', ['$resource', function ($resource) {
    this.PartiesR = $resource(host + '/parties');
    this.PartyMembersR = $resource(host + '/parties/:id/members');
}]);
