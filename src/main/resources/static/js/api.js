//Angular API for price checking
angular.module('price', [])
.controller('price', function($scope, $http, $interval) {
    $scope.selectedcurrency = 'BTC';
    $scope.refreshrate = 10000;
    $scope.markets = [];
    $scope.refreshAverage = function() {
        $http.get('/' + $scope.selectedcurrency + '/all/price').
        then(function(response) {
            $scope.price = response.data;
            $scope.marketprices = response.data.prices;
        });
    }

    $scope.fetchMarkets = function() {
        $http.get('/' + $scope.selectedcurrency + '/markets').
        then(function(response) {
            $scope.markets = response.data;
            console.log($scope.markets);
        });
    }

    $scope.fetchCurrencies = function() {
        $http.get('/currencies').
        then(function(response) {
            $scope.currencies = response.data;
        });
    }


    $scope.fetchCurrencies();

    $scope.switchCurrency = function(currency) {
        $scope.selectedcurrency = currency;
        $scope.refreshAverage();
    }
    $interval(function() {
        $scope.refreshAverage();
    }, $scope.refreshrate);

    $scope.refreshAverage();
});