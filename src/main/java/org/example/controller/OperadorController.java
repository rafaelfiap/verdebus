package org.example.controller;

import org.example.dtos.OperadorDto;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Operador;
import org.example.services.interfaces.OperadorService;
import org.example.services.OperadorServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

/**
 * Controlador REST para a entidade Operador.
 * Define endpoints para criar, listar, atualizar e deletar operadores no sistema.
 *
 * @since 1.0
 */
@Path("/rest/operador")
public class OperadorController {

    // Instância de OperadorService obtida da factory
    private final OperadorService operadorService = OperadorServiceFactory.create();

    /**
     * Endpoint para criar um novo operador.
     *
     * @param input DTO de entrada para criação de um operador.
     * @return Resposta HTTP indicando sucesso ou erro.
     * @throws UnsupportedServiceOperationException Se ocorrer um erro na operação.
     */
    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(OperadorDto input) throws UnsupportedServiceOperationException {
        if (input.getIdOperador() == null) {
            try {
                // Criação do objeto Operador com base no DTO recebido
                Operador operador = this.operadorService.create(
                        new Operador(
                                null,
                                input.getNome(),
                                input.getCpf(),
                                input.getIdGaragem()
                        )
                );
                return Response.status(Response.Status.CREATED)
                        .entity(operador)
                        .build();
            } catch (SQLException | NotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir operador"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novos operadores"))
                    .build();
        }
    }

    /**
     * Endpoint para listar todos os operadores.
     *
     * @return Resposta HTTP com a lista de todos os operadores.
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws SQLException {
        return Response.status(Response.Status.OK)
                .entity(this.operadorService.findAll())
                .build();
    }

    /**
     * Endpoint para atualizar um operador existente.
     *
     * @param id    ID do operador a ser atualizado.
     * @param input DTO de entrada para atualização do operador.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, OperadorDto input) {
        try {
            // Atualização do objeto Operador com base no DTO e ID recebidos
            Operador updated = this.operadorService.update(
                    new Operador(
                            id,
                            input.getNome(),
                            input.getCpf(),
                            input.getIdGaragem()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Operador não encontrado"))
                    .build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar operador"))
                    .build();
        }
    }

    /**
     * Endpoint para excluir um operador existente.
     *
     * @param id ID do operador a ser excluído.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.operadorService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Operador não encontrado"))
                    .build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar operador"))
                    .build();
        }
    }
}
