angular.module('javaeeAngular', ['ngRoute', 'ngMessages'])
.config(function($routeProvider) {

    $routeProvider
        .when('/cliente-pedido', {
            templateUrl : '/pages/cliente-pedido.html',
            controller: 'ClientePedidoController'
        })
        .otherwise({
            redirectTo: '/cliente-pedido'
        });
});
