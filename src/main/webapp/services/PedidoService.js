app.factory('PedidoService', function($http) {
    var service = {};

    service.listar = function() {
        return $http.get('/ws/pedidos'); 
    };

    service.salvar = function(pedido) {
        return $http.post('/ws/pedidos', pedido); 
    };

    service.deletar = function(id) {
        return $http.delete('/ws/pedidos/' + id);  
    };

    return service;
});
