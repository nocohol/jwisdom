/**
 * Created by caronic on 2016/3/9.
 */
(function(){
    'use strict';
    var filters = {};
    filters.TestFilter = ['$scope', '$http',
        function($scope, $http){

        }
    ];

    angular.module('app.main.filters', []).filter(filters);
})();