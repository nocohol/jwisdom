/**
 * Created by caronic on 2016/3/9.
 */
(function(){
    'use strict';
    var services = {};
    services.testService = ['$http', function($http) {
        var service = {};
        service.testFunc = function(){

        };
        return service;
    }];

    angular.module('app.main.services', []).service(services);
})();