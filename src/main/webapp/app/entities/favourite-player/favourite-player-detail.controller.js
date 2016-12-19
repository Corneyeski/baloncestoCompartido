(function() {
    'use strict';

    angular
        .module('baloncestoApp')
        .controller('FavouritePlayerDetailController', FavouritePlayerDetailController);

    FavouritePlayerDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'FavouritePlayer', 'User', 'Player'];

    function FavouritePlayerDetailController($scope, $rootScope, $stateParams, previousState, entity, FavouritePlayer, User, Player) {
        var vm = this;

        vm.favouritePlayer = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('baloncestoApp:favouritePlayerUpdate', function(event, result) {
            vm.favouritePlayer = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
