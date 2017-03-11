//Angular API for price checking
angular.module('price', [])
.controller('price', function($scope, $http, $interval) {
    $scope.selected.currency = 'BTC';
    $scope.refreshrate = 10000;

    $scope.refreshAverage = function() {
        $http.get('/' + $scope.selected.currency + '/average').
        then(function(response) {
            $scope.average = response.data;
        });
    }

    $scope.fetchMarkets = function() {
        $http.get('/' + $scope.selected.currency + '/markets').
        then(function(response) {
            $scope.markets = response.data;
        });
    }

    $scope.fetchCurrencies = function() {
        $http.get('/currencies').
        then(function(response) {
            $scope.currencies = response.data;
        });
    }

    $scope.switchCurrency = function(currency) {
        $scope.selected.currency = currency;
        refreshAverage();
        fetchMarkets();
    }
    $scope.fetchCurrencies();
    $scope.switchCurrency('BTC');
    $interval(function() {
        $scope.refreshAverage();
    }, $scope.refreshrate);
});