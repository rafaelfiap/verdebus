package org.example.controller;

import org.example.dtos.CarregadorDto;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Carregador;
import org.example.services.interfaces.CarregadorService;
import org.example.services.CarregadorServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

/**
 * Controlador REST para a entidade Carregador.
 * Define endpoints para criar, listar, atualizar e deletar carregadores.
 *
 * @since 1.0
 */
@Path("/rest/carregador")
public class CarregadorController {

    // Instância de CarregadorService obtida da factory
    private final CarregadorService carregadorService = CarregadorServiceFactory.create();

    /**
     * Endpoint para criar um novo carregador.
     *
     * @param input DTO de entrada para criação de um carregador.
     * @return Resposta HTTP indicando sucesso ou erro.
     * @throws UnsupportedServiceOperationException Se ocorrer um erro na operação.
     */
    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(CarregadorDto input) throws UnsupportedServiceOperationException {
        if (input.getIdCarregador() == null) {
            try {
                // Criação do objeto Carregador com base no DTO recebido
                Carregador carregador = this.carregadorService.create(
                        new Carregador(
                                null,
                                input.getPotencia(),
                                input.getStatus(),
                                input.getIdEstacaoRecargaSolar()
                        )
                );
                return Response.status(Response.Status.CREATED)
                        .entity(carregador)
                        .build();
            } catch (SQLException | NotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir carregador"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novos carregadores"))
                    .build();
        }
    }

    /**
     * Endpoint para listar todos os carregadores.
     *
     * @return Resposta HTTP com a lista de todos os carregadores.
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws SQLException {
        return Response.status(Response.Status.OK)
                .entity(this.carregadorService.findAll())
                .build();
    }

    /**
     * Endpoint para atualizar um carregador existente.
     *
     * @param id    ID do carregador a ser atualizado.
     * @param input DTO de entrada para atualização do carregador.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, CarregadorDto input) {
        try {
            // Atualização do objeto Carregador com base no DTO e ID recebidos
            Carregador updated = this.carregadorService.update(
                    new Carregador(
                            id,
                            input.getPotencia(),
                            input.getStatus(),
                            input.getIdEstacaoRecargaSolar()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar carregador"))
                    .build();
        }
    }

    /**
     * Endpoint para excluir um carregador existente.
     *
     * @param id ID do carregador a ser excluído.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.carregadorService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar carregador"))
                    .build();
        }
    }
}
