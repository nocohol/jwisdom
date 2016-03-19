/**
 * Created by caronic on 2016/3/9.
 */
(function(){
    'use strict';
    var directives = {};
    directives.showcasesPanel = [function() {
        var directive = {};
        directive.restrict = "E";
        directive.transclude = true;
        directive.scope = {
            'cases': '=list'
        };
        directive.controller = function($scope) {
            $scope.sayHello = function(pictureName) {
                alert('hello! ' + pictureName);
            }
        };
        directive.templateUrl = "views/ShowcasesTpl.html";
        return directive;
    }];

    angular.module('app.main.directives', []).directive(directives);
})();