/**
 * Created by caronic on 2016/3/9.
 */
(function(){
    'use strict';
    var directives = {};
    directives.test = ['$http', function($http) {
        var directive = {};
        directive.restrict = 'E';
        directive.replace = true;
        directive.templateUrl = '';
        return directive;
    }];

    directives.showcasesPanel = [function() {
        var directive = {};
        directive.restrict = "E";
        directive.scope = {
            cases: '=list'
        }
        directive.templateUrl = "views/ShowcasesTpl.html"
        return directive;
    }];

    angular.module('app.main.directives', []).directive(directives);
})();