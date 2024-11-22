package org.example.controller;

import org.example.dtos.CarroDto;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Carro;
import org.example.services.interfaces.CarroService;
import org.example.services.CarroServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

/**
 * Controlador REST para a entidade Carro.
 * Define endpoints para criar, listar, atualizar e deletar carros.
 *
 * @since 1.0
 */
@Path("/rest/carro")
public class CarroController {

    // Instância de CarroService obtida da factory
    private final CarroService carroService = CarroServiceFactory.create();

    /**
     * Endpoint para criar um novo carro.
     *
     * @param input DTO de entrada para criação de um carro.
     * @return Resposta HTTP indicando sucesso ou erro.
     * @throws UnsupportedServiceOperationException Se ocorrer um erro na operação.
     */
    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(CarroDto input) throws UnsupportedServiceOperationException {
        if (input.getIdCarro() == null) {
            try {
                // Criação do objeto Carro com base no DTO recebido
                Carro carro = this.carroService.create(
                        new Carro(
                                null,
                                input.getPrefixo(),
                                input.getPlaca(),
                                input.getModelo(),
                                input.getFabricante(),
                                input.getAnoFabricacao(),
                                input.getCapacidadeBateria(),
                                input.getNumeroPortas(),
                                input.getIdGaragem()
                        )
                );
                return Response.status(Response.Status.CREATED)
                        .entity(carro)
                        .build();
            } catch (SQLException | NotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir carro"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novos carros"))
                    .build();
        }
    }

    /**
     * Endpoint para listar todos os carros.
     *
     * @return Resposta HTTP com a lista de todos os carros.
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws SQLException {
        return Response.status(Response.Status.OK)
                .entity(this.carroService.findAll())
                .build();
    }

    /**
     * Endpoint para atualizar um carro existente.
     *
     * @param id    ID do carro a ser atualizado.
     * @param input DTO de entrada para atualização do carro.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, CarroDto input) {
        try {
            // Atualização do objeto Carro com base no DTO e ID recebidos
            Carro updated = this.carroService.update(
                    new Carro(
                            id,
                            input.getPrefixo(),
                            input.getPlaca(),
                            input.getModelo(),
                            input.getFabricante(),
                            input.getAnoFabricacao(),
                            input.getCapacidadeBateria(),
                            input.getNumeroPortas(),
                            input.getIdGaragem()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar carro"))
                    .build();
        }
    }

    /**
     * Endpoint para excluir um carro existente.
     *
     * @param id ID do carro a ser excluído.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.carroService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar carro"))
                    .build();
        }
    }
}
