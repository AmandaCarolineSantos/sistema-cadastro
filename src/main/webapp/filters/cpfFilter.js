
var app = angular.module('javaeeAngular');

app.filter('cpf', function() {
    return function(cpf) {
        if (!cpf || cpf.length !== 11) return cpf;
        return cpf.substr(0, 3) + '.' + cpf.substr(3, 3) + '.' + cpf.substr(6, 3) + '-' + cpf.substr(9, 2);
    };
});
