(function() {
    'use strict';
    angular
        .module('baloncestoApp')
        .factory('Game', Game);

    Game.$inject = ['$resource', 'DateUtils'];

    function Game ($resource, DateUtils) {
        var resourceUrl =  'api/games/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.startTime = DateUtils.convertLocalDateFromServer(data.startTime);
                        data.finishTime = DateUtils.convertLocalDateFromServer(data.finishTime);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.startTime = DateUtils.convertLocalDateToServer(copy.startTime);
                    copy.finishTime = DateUtils.convertLocalDateToServer(copy.finishTime);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.startTime = DateUtils.convertLocalDateToServer(copy.startTime);
                    copy.finishTime = DateUtils.convertLocalDateToServer(copy.finishTime);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
