(function() {
    'use strict';

    angular
        .module('baloncestoApp')
        .controller('GameDetailController', GameDetailController);

    GameDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Game', 'Team', 'GameRating'];

    function GameDetailController($scope, $rootScope, $stateParams, previousState, entity, Game, Team, GameRating) {
        var vm = this;

        vm.game = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('baloncestoApp:gameUpdate', function(event, result) {
            vm.game = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
