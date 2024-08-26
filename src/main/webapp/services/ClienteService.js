app.factory('ClienteService', function($http) {
    var service = {};

    service.listar = function() {
        return $http.get('/ws/clientes/listar');  
    };

    service.salvar = function(cliente) {
        return $http.post('/ws/clientes/cadastrar', cliente);  
    };


    return service;
});
