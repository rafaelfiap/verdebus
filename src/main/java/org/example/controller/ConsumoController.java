package org.example.controller;

import org.example.dtos.ConsumoDto;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Consumo;
import org.example.services.interfaces.ConsumoService;
import org.example.services.ConsumoServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

/**
 * Controlador REST para a entidade Consumo.
 * Define endpoints para criar, listar, atualizar e deletar registros de consumo de energia.
 *
 * @since 1.0
 */
@Path("/rest/consumo")
public class ConsumoController {

    // Instância de ConsumoService obtida da factory
    private final ConsumoService consumoService = ConsumoServiceFactory.create();

    /**
     * Endpoint para criar um novo registro de consumo.
     *
     * @param input DTO de entrada para criação de um registro de consumo.
     * @return Resposta HTTP indicando sucesso ou erro.
     * @throws UnsupportedServiceOperationException Se ocorrer um erro na operação.
     */
    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(ConsumoDto input) throws UnsupportedServiceOperationException {
        if (input.getIdConsumo() == null) {
            try {
                // Criação do objeto Consumo com base no DTO recebido
                Consumo consumo = this.consumoService.create(
                        new Consumo(
                                null,
                                input.getConsumoPorKm(),
                                input.getDistanciaPercorrida(),
                                input.getEnergiaTotalConsumida(),
                                input.getIdOnibus()
                        )
                );
                return Response.status(Response.Status.CREATED)
                        .entity(consumo)
                        .build();
            } catch (SQLException | NotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir registro de consumo"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novos registros de consumo"))
                    .build();
        }
    }

    /**
     * Endpoint para listar todos os registros de consumo.
     *
     * @return Resposta HTTP com a lista de todos os registros de consumo.
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws SQLException {
        return Response.status(Response.Status.OK)
                .entity(this.consumoService.findAll())
                .build();
    }

    /**
     * Endpoint para atualizar um registro de consumo existente.
     *
     * @param id    ID do registro de consumo a ser atualizado.
     * @param input DTO de entrada para atualização do registro de consumo.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, ConsumoDto input) {
        try {
            // Atualização do objeto Consumo com base no DTO e ID recebidos
            Consumo updated = this.consumoService.update(
                    new Consumo(
                            id,
                            input.getConsumoPorKm(),
                            input.getDistanciaPercorrida(),
                            input.getEnergiaTotalConsumida(),
                            input.getIdOnibus()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar registro de consumo"))
                    .build();
        }
    }

    /**
     * Endpoint para excluir um registro de consumo existente.
     *
     * @param id ID do registro de consumo a ser excluído.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.consumoService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar registro de consumo"))
                    .build();
        }
    }
}
