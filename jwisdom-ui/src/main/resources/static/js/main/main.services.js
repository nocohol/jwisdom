/**
 * Created by caronic on 2016/3/9.
 */
(function(){
    'use strict';
    var services = {};
    services.showcaseService = ['$http', function($http) {
        var service = {};
        service.getShowcaseList = function(){
            return $http.get('data/showcases.json')
                .then(function(response) {
                    return response;
                }, function(response) {
                    return response;
                });
        };
        return service;
    }];

    angular.module('app.main.services', []).factory(services);
})();