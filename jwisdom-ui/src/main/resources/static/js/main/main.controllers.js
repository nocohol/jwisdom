/**
 * Created by caronic on 2016/3/9.
 */
(function(){
    'use strict';
    var controllers = {};
    controllers.TestController = ['$scope', '$http',
    function($scope, $http){
        $scope.sayHello = function() {
            alert('hello!');
        }
    }];

    angular.module('app.main.controllers', []).controller(controllers);
})();