package br.com.sistemacadastro.ws;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.sistemacadastro.model.Cliente;
import br.com.sistemacadastro.service.ClienteService;

@Named
@Path("clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    private ClienteService clienteService;

    @GET
    @Path("listar")
    public List<Cliente> listarClientes() {
        return clienteService.findAll();
    }

    @GET
    @Path("buscar/{codigo}")
    public Response listarClientes(@PathParam("codigo") Long codigo) {
        Optional<Cliente> clienteOpt = clienteService.findById(codigo);
        return clienteOpt.map(Response::ok)
                         .orElse(Response.status(Response.Status.NOT_FOUND))
                         .build();
    }

    @POST
    @Path("cadastrar")
    public Response salvar(Cliente cliente) {
        try {
            clienteService.save(cliente);
            return Response.ok().build();
        } catch (ConstraintViolationException cvex) {
            final Set<String> erros = cvex.getConstraintViolations().stream()
                                           .map(ConstraintViolation::getMessage)
                                           .collect(Collectors.toSet());
            return Response.status(Response.Status.BAD_REQUEST).entity(erros).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Instabilidade no serviço").build();
        }
    }
}
