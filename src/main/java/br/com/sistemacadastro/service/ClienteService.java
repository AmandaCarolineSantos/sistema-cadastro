package br.com.sistemacadastro.service;

import javax.inject.Named;
import br.com.sistemacadastro.model.Cliente;

@Named
public class ClienteService extends AbstractService<Cliente, Long> {

    public ClienteService() {
        super(Cliente.class);
    }
}


