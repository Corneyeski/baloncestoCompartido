(function() {
    'use strict';

    angular
        .module('baloncestoApp')
        .controller('GameRatingDialogController', GameRatingDialogController);

    GameRatingDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'GameRating', 'User', 'Game'];

    function GameRatingDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, GameRating, User, Game) {
        var vm = this;

        vm.gameRating = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.users = User.query();
        vm.games = Game.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.gameRating.id !== null) {
                GameRating.update(vm.gameRating, onSaveSuccess, onSaveError);
            } else {
                GameRating.save(vm.gameRating, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('baloncestoApp:gameRatingUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.scoreDateTime = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
