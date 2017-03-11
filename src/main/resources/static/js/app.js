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

    $scope.rates = {};
    $http.get('http://api.fixer.io/latest?base=ZAR')
        .then(function(res) {
            $scope.rates = res.data.rates;
            $scope.toType = $scope.rates.USD;
            $scope.fromType = $scope.rates.USD;
            $scope.fromValue = 1;
            $scope.forExConvert();
        });
    $scope.forExConvert = function() {
        $scope.toValue = ($scope.fromValue * $scope.price.price) * ($scope.toType * (1 / $scope.fromType));
        $scope.toValue = $scope.toValue.toFixed(2);
    };

    $scope.fetchCurrencies();

    $scope.switchCurrency = function(currency) {
        $scope.selectedcurrency = currency;
        $scope.refreshAverage();
        $scope.forExConvert();
    }
    $interval(function() {
        $scope.refreshAverage();
    }, $scope.refreshrate);

    $scope.refreshAverage();
});