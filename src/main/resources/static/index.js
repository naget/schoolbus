/**
 * Created by t on 2016/12/6.
 */
var app=angular.module("myApp",[]);
app.controller("indexCtrl",function ($scope,$http) {
    // $scope.getLoginMessage = function () {
    //     $http({
    //         methed: 'post',
    //         url:'/login'
    //
    //
    //     }).success(function (message) {
    //         $scope.message=message;
    //         alert(message);
    //     })
    // }
    // $scope.getLoginMessage();
    $scope.getTodayBuses=function () {
        $http.get("/info/showInfo").success(function (todayBusesList) {
            $scope.todayBusesList=todayBusesList;

        })
    }

    $scope.getNowBus=function () {
        $http.get("/info/showOneInfo").success(function (nowBus) {
            $scope.nowbus=nowBus;
        })
    }
    $scope.getNowBus();
    $scope.getAdmessage=function () {
        $http({
            methed:"post",
            url:"/news/formAdmin"
        }).success(function (message) {
            alert(message);
        })
    }
    $scope.getAdmessage();


    }

)