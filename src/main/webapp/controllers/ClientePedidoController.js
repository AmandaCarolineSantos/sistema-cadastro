// Filtro personalizado para formatar datas
app.filter('dateInput', function($filter) {
    return function(input) {
        if(input == null){ return ""; }
        var _date = $filter('date')(new Date(input), 'yyyy-MM-dd');
        return _date.toUpperCase();
    };
});

// Controlador para Clientes e Pedidos
app.controller('ClientePedidoController', function($scope, $filter, $timeout, ClienteService, PedidoService) {
    $scope.clientes = [];
    $scope.pedidos = [];
    $scope.cliente = {};
    $scope.pedido = {
        dataPedido: new Date(),
        descricao: '' // Inicializa a descrição como uma string vazia
    };
    $scope.mensagem = '';
    $scope.erro = '';

    // Função para redefinir o estado do formulário
    $scope.resetForm = function(form) {
        if (form) {
            form.$setPristine();
            form.$setUntouched();
        }
    };

    // Função para limpar mensagens após 5 segundos
    $scope.limparMensagens = function() {
        $timeout(function() {
            $scope.mensagem = '';
            $scope.erro = '';
        }, 5000);
    };

    // Funções relacionadas a Clientes
    $scope.listarClientes = function() {
        ClienteService.listar().then(function(response) {
            $scope.clientes = response.data;
            $scope.resetForm($scope.form);
        }).catch(function(error) {
            $scope.erro = "Erro ao listar clientes: " + (error.data || 'Erro desconhecido');
            $scope.limparMensagens();
        });
    };

    $scope.salvarCliente = function() {
        ClienteService.salvar($scope.cliente).then(function() {
            $scope.listarClientes();
            $scope.cliente = {};
            $scope.mensagem = "Cliente salvo com sucesso!";
            $scope.erro = '';
            $scope.resetForm($scope.form);
            $scope.limparMensagens();
        }).catch(function(error) {
            $scope.erro = "Erro ao salvar cliente: " + (error.data || 'Erro desconhecido');
            $scope.mensagem = '';
            $scope.limparMensagens();
        });
    };

    $scope.editarCliente = function(cliente) {
        $scope.cliente = angular.copy(cliente);
    };

    $scope.deletarCliente = function(id) {
        if (confirm("Tem certeza que deseja deletar este cliente?")) {
            ClienteService.deletar(id).then(function() {
                $scope.listarClientes();
                $scope.mensagem = "Cliente deletado com sucesso!";
                $scope.erro = '';
                $scope.limparMensagens();
            }).catch(function(error) {
                $scope.erro = "Erro ao deletar cliente: " + (error.data || 'Erro desconhecido');
                $scope.mensagem = '';
                $scope.limparMensagens();
            });
        }
    };

    // Funções relacionadas a Pedidos
    $scope.listarPedidos = function() {
        PedidoService.listar().then(function(response) {
            $scope.pedidos = response.data;
            $scope.resetForm($scope.pedidoForm);
            $scope.pedido.descricao = '';
        }).catch(function(error) {
            $scope.erro = "Erro ao listar pedidos: " + (error.data || 'Erro desconhecido');
            $scope.limparMensagens();
        });
    };

    $scope.salvarPedido = function() {
        var pedidoData = angular.copy($scope.pedido);
        if (pedidoData.dataPedido) {
            pedidoData.dataPedido = $filter('date')(new Date(pedidoData.dataPedido), 'yyyy-MM-dd');
        }

        if (pedidoData.clienteId) {
            pedidoData.cliente = { id: pedidoData.clienteId };
            delete pedidoData.clienteId;
        }

        PedidoService.salvar(pedidoData).then(function(response) {
            $scope.listarPedidos();
            $scope.pedido = {
                dataPedido: new Date(),
                descricao: '' 
            };
            $scope.mensagem = "Pedido salvo com sucesso!";
            $scope.erro = '';
            $scope.resetForm($scope.pedidoForm);
            $scope.limparMensagens();
        }).catch(function(error) {
            $scope.erro = "Erro ao salvar pedido: " + (error.data || 'Erro desconhecido');
            $scope.mensagem = '';
            $scope.limparMensagens();
        });
    };

    $scope.editarPedido = function(pedido) {
        $scope.pedido = angular.copy(pedido);
        $scope.pedido.clienteId = pedido.cliente.id;

        if ($scope.pedido.dataPedido) {
            var parts = $scope.pedido.dataPedido.split('/');
            var day = parseInt(parts[0], 10);
            var month = parseInt(parts[1], 10) - 1;
            var year = parseInt(parts[2], 10);
            $scope.pedido.dataPedido = new Date(year, month, day);
        }
    };

    $scope.deletarPedido = function(id) {
        if (confirm("Tem certeza que deseja deletar este pedido?")) {
            PedidoService.deletar(id).then(function() {
                $scope.listarPedidos();
                $scope.mensagem = "Pedido deletado com sucesso!";
                $scope.erro = '';
                $scope.limparMensagens();
            }).catch(function(error) {
                $scope.erro = "Erro ao deletar pedido: " + (error.data || 'Erro desconhecido');
                $scope.mensagem = '';
                $scope.limparMensagens();
            });
        }
    };

    // Inicializa a lista de clientes e pedidos
    $scope.listarClientes();
    $scope.listarPedidos();
});
