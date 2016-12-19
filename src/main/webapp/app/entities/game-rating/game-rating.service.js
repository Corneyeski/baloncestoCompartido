(function() {
    'use strict';
    angular
        .module('baloncestoApp')
        .factory('GameRating', GameRating);

    GameRating.$inject = ['$resource', 'DateUtils'];

    function GameRating ($resource, DateUtils) {
        var resourceUrl =  'api/game-ratings/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.scoreDateTime = DateUtils.convertDateTimeFromServer(data.scoreDateTime);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
