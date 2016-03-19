/**
 * Created by caronic on 2016/3/9.
 */
(function(){
    'use strict';
    var controllers = {};
    controllers.TestController = ['$scope', 'showcaseService',
    function($scope, showcaseService){
        $scope.modificationCases = [];
        showcaseService.getShowcaseList().then(function(response){
            $scope.modificationCases = response.data.result;
        }, function(data) {

        });
    }];

    angular.module('app.main.controllers', []).controller(controllers);
})();