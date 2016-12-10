/**
 * Created by t on 2016/12/6.
 */
var app=angular.module("myApp",[]);
app.controller("indexCtrl",function ($scope,$http) {
    $scope.getLoginMessage = function () {
        $http({
            methed: 'post',
            url:'/login'


        }).success(function (message) {
            $scope.message=message;
            alert(message);
        })
    }

    }
)