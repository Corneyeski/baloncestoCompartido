(function() {
    'use strict';

    angular
        .module('baloncestoApp')
        .controller('FavouritePlayerDialogController', FavouritePlayerDialogController);

    FavouritePlayerDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'FavouritePlayer', 'User', 'Player'];

    function FavouritePlayerDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, FavouritePlayer, User, Player) {
        var vm = this;

        vm.favouritePlayer = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.users = User.query();
        vm.players = Player.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.favouritePlayer.id !== null) {
                FavouritePlayer.update(vm.favouritePlayer, onSaveSuccess, onSaveError);
            } else {
                FavouritePlayer.save(vm.favouritePlayer, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('baloncestoApp:favouritePlayerUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.favouriteDateTime = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
