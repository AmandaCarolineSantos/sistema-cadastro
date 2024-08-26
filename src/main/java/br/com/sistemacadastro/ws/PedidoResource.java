package br.com.sistemacadastro.ws;

import br.com.sistemacadastro.model.Cliente;
import br.com.sistemacadastro.model.Pedido;
import br.com.sistemacadastro.service.ClienteService;
import br.com.sistemacadastro.service.PedidoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    private PedidoService pedidoService;

    @Inject
    private ClienteService clienteService;

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id) {
        Optional<Pedido> pedidoOpt = pedidoService.findById(id);
        return pedidoOpt.map(Response::ok)
                        .orElse(Response.status(Response.Status.NOT_FOUND))
                        .build();
    }

    @GET
    public List<Pedido> listar() {
        return pedidoService.findAll().stream()
                .peek(pedido -> pedido.getCliente().getNome())  // Força o carregamento do cliente
                .collect(Collectors.toList());
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        pedidoService.remove(id);
        return Response.noContent().build();
    }

    @POST
    public Response adicionar(Pedido pedido) {
        Optional<Cliente> clienteOpt = Optional.ofNullable(pedido.getCliente())
                                               .flatMap(c -> clienteService.findById(c.getId()));
        clienteOpt.ifPresent(pedido::setCliente);
        pedidoService.save(pedido);
        return Response.status(Response.Status.CREATED).entity(pedido).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Pedido pedido) {
        pedido.setId(id);
        pedidoService.update(pedido);
        return Response.ok(pedido).build();
    }
}
