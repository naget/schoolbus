/**
 * Created by t on 2016/12/2.
 */
var app=angular.module("myApp",[]);
app.controller("adCtrl",function ($scope,$http) {
    // $scope.getNews = function () {
        $http.get("/news/toUser").success(function (ad) {
            $scope.ad=ad;
        });
    // }
    // getNews();

});