(function() {
    'use strict';
    angular
        .module('baloncestoApp')
        .factory('FavouritePlayer', FavouritePlayer);

    FavouritePlayer.$inject = ['$resource', 'DateUtils'];

    function FavouritePlayer ($resource, DateUtils) {
        var resourceUrl =  'api/favourite-players/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.favouriteDateTime = DateUtils.convertDateTimeFromServer(data.favouriteDateTime);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
