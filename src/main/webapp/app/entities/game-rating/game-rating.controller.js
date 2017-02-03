(function() {
    'use strict';

    angular
        .module('baloncestoApp')
        .controller('GameRatingController', GameRatingController);

    GameRatingController.$inject = ['$scope', '$state', 'GameRating', 'ParseLinks', 'AlertService'];

    function GameRatingController ($scope, $state, GameRating, ParseLinks, AlertService) {
        var vm = this;
        vm.sumGameScore = 0;
        vm.gameRatings = [];
        vm.gameRatingsAvgAll = [];
        vm.loadPage = loadPage;
        vm.page = 0;
        vm.links = {
            last: 0
        };
        vm.predicate = 'id';
        vm.reset = reset;
        vm.reverse = true;

        loadAll();

        function loadAll () {

            GameRating.query({
                page: vm.page,
                size: 20,
                sort: sort()
            }, onSuccess, onError);


            GameRating.sumGame({

            },
                onSuccessSumGame, onError);

            GameRating.avgGameRatingAll({

                },
                onSuccessGameRatingAll, onError);


            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }

            function onSuccess(data, headers) {

                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                for (var i = 0; i < data.length; i++) {
                    vm.gameRatings.push(data[i]);
                }
            }

            function onSuccessGameRatingAll(data, headers) {

                // vm.links = ParseLinks.parse(headers('link'));
                // vm.totalItems = headers('X-Total-Count');
                for (var i = 0; i < data.length; i++) {
                    vm.gameRatingsAvgAll.push(data[i]);
                }
            }

            function onSuccessSumGame(data, headers) {
                //TODO
                console.log(data);
                vm.sumGameScore = data;
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function reset () {
            vm.page = 0;
            vm.gameRatings = [];
            loadAll();
        }

        function loadPage(page) {
            vm.page = page;
            loadAll();
        }
    }
})();
