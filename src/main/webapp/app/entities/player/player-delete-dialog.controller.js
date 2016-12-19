(function() {
    'use strict';

    angular
        .module('baloncestoApp')
        .controller('PlayerDeleteController',PlayerDeleteController);

    PlayerDeleteController.$inject = ['$uibModalInstance', 'entity', 'Player'];

    function PlayerDeleteController($uibModalInstance, entity, Player) {
        var vm = this;

        vm.player = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Player.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
