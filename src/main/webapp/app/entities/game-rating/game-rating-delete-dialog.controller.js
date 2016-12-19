(function() {
    'use strict';

    angular
        .module('baloncestoApp')
        .controller('GameRatingDeleteController',GameRatingDeleteController);

    GameRatingDeleteController.$inject = ['$uibModalInstance', 'entity', 'GameRating'];

    function GameRatingDeleteController($uibModalInstance, entity, GameRating) {
        var vm = this;

        vm.gameRating = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            GameRating.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
