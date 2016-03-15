<#import "/spring.ftl" as spring>
<#-- @ftlvariable name="name" type="java.lang.String" -->
<#-- @ftlvariable name="urls" type="org.springframework.web.servlet.resource.ResourceUrlProvider" -->
<#-- @ftlvariable name="rc" type="org.springframework.web.servlet.support.RequestContext" -->
<!DOCTYPE html>
<html lang="en" ng-app="app">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${rc.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="${rc.contextPath}/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rc.contextPath}/css/site.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <title>JWisdom Auto</title>
</head>
<body class="home-template">

<#include "site/main.html">

<script src="${rc.contextPath}${urls.getForLookupPath('/bower_components/jquery/dist/jquery.min.js')}"></script>
<script src="${rc.contextPath}${urls.getForLookupPath('/bower_components/bootstrap/dist/js/bootstrap.min.js')}"></script>
<script src="${rc.contextPath}${urls.getForLookupPath('/bower_components/angular/angular.min.js')}"></script>

<script src="${rc.contextPath}${urls.getForLookupPath('/js/app.js')}"></script>
<script src="${rc.contextPath}${urls.getForLookupPath('/js/main/module.js')}"></script>
<script src="${rc.contextPath}${urls.getForLookupPath('/js/shared/module.js')}"></script>
<script src="${rc.contextPath}${urls.getForLookupPath('/js/main/main.controllers.js')}"></script>
<script src="${rc.contextPath}${urls.getForLookupPath('/js/main/main.directives.js')}"></script>
<script src="${rc.contextPath}${urls.getForLookupPath('/js/main/main.services.js')}"></script>
<script src="${rc.contextPath}${urls.getForLookupPath('/js/main/main.constants.js')}"></script>
<script src="${rc.contextPath}${urls.getForLookupPath('/js/main/main.filters.js')}"></script>

</body>
</html>