package br.com.sistemacadastro.service;

import br.com.sistemacadastro.model.Pedido;

import javax.ejb.Stateless;

@Stateless
public class PedidoService extends AbstractService<Pedido, Long> {

    public PedidoService() {
        super(Pedido.class);
    }
}
