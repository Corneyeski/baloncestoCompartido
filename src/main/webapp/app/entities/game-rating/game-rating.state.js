(function() {
    'use strict';

    angular
        .module('baloncestoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('game-rating', {
            parent: 'entity',
            url: '/game-rating',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'baloncestoApp.gameRating.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/game-rating/game-ratings.html',
                    controller: 'GameRatingController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('gameRating');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })

            .state('game-rating-sum', {
                parent: 'entity',
                url: '/game-rating-sum',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'baloncestoApp.gameRating.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/game-rating/game-ratings-sum.html',
                        controller: 'GameRatingController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('gameRating');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })

            .state('game-rating-avg', {
                parent: 'entity',
                url: '/game-rating/avg/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'baloncestoApp.gameRating.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/game-rating/game-ratings-avg.html',
                        controller: 'GameRatingController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('gameRating');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
        .state('game-rating-detail', {
            parent: 'entity',
            url: '/game-rating/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'baloncestoApp.gameRating.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/game-rating/game-rating-detail.html',
                    controller: 'GameRatingDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('gameRating');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'GameRating', function($stateParams, GameRating) {
                    return GameRating.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'game-rating',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('game-rating-detail.edit', {
            parent: 'game-rating-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/game-rating/game-rating-dialog.html',
                    controller: 'GameRatingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['GameRating', function(GameRating) {
                            return GameRating.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('game-rating.new', {
            parent: 'game-rating',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/game-rating/game-rating-dialog.html',
                    controller: 'GameRatingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                score: null,
                                scoreDateTime: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('game-rating', null, { reload: 'game-rating' });
                }, function() {
                    $state.go('game-rating');
                });
            }]
        })
        .state('game-rating.edit', {
            parent: 'game-rating',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/game-rating/game-rating-dialog.html',
                    controller: 'GameRatingDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['GameRating', function(GameRating) {
                            return GameRating.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('game-rating', null, { reload: 'game-rating' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('game-rating.delete', {
            parent: 'game-rating',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/game-rating/game-rating-delete-dialog.html',
                    controller: 'GameRatingDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['GameRating', function(GameRating) {
                            return GameRating.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('game-rating', null, { reload: 'game-rating' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
