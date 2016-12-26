/**
 * Created by t on 2016/12/11.
 */
var app=angular.module("ticketsApp",[])
app.controller("ticketsCtrl",function ($http, $scope) {
    $scope.sale=function () {
        var id=$scope.nowbus.message.id;
        var number=1;
        $http({
            method:"post",
            url:"/info/sale",
            data:{id:id,number:number}
        }).success(function (message) {
            $scope.message=message;
        })
    }
    $scope.search=function () {
        var time=$( "#datepicker" ).val();
        $http({
            method:"get",
            url:"/info/showSpecialInfo",
            data:{time:time}
        }).success(function (message) {
            $scope.message=message;
        });
    }


})