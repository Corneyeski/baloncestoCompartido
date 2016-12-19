(function() {
    'use strict';

    angular
        .module('baloncestoApp')
        .controller('PlayerDetailController', PlayerDetailController);

    PlayerDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Player', 'Team', 'FavouritePlayer'];

    function PlayerDetailController($scope, $rootScope, $stateParams, previousState, entity, Player, Team, FavouritePlayer) {
        var vm = this;

        vm.player = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('baloncestoApp:playerUpdate', function(event, result) {
            vm.player = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
