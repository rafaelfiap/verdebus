package org.example.controller;

import org.example.dtos.OnibusDto;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Onibus;
import org.example.services.interfaces.OnibusService;
import org.example.services.OnibusServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

/**
 * Controlador REST para a entidade Ônibus.
 * Define endpoints para criar, listar, atualizar e deletar ônibus elétricos.
 *
 * @since 1.0
 */
@Path("/rest/onibus")
public class OnibusController {

    // Instância de OnibusService obtida da factory
    private final OnibusService onibusService = OnibusServiceFactory.create();

    /**
     * Endpoint para criar um novo ônibus.
     *
     * @param input DTO de entrada para criação de um ônibus.
     * @return Resposta HTTP indicando sucesso ou erro.
     * @throws UnsupportedServiceOperationException Se ocorrer um erro na operação.
     */
    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(OnibusDto input) throws UnsupportedServiceOperationException {
        if (input.getIdOnibus() == null) {
            try {
                // Criação do objeto Onibus com base no DTO recebido
                Onibus onibus = this.onibusService.create(
                        new Onibus(
                                null,
                                input.getPrefixo(),
                                input.getPlaca(),
                                input.getModelo(),
                                input.getFabricante(),
                                input.getAnoFabricacao(),
                                input.getCapacidadeBateria(),
                                input.getCapacidadePassageiros(),
                                input.getPainelSolar(),
                                input.getPeliculaSolar(),
                                input.getIdLinha(),
                                input.getIdGaragem()
                        )
                );
                return Response.status(Response.Status.CREATED)
                        .entity(onibus)
                        .build();
            } catch (SQLException | NotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir ônibus"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novos ônibus"))
                    .build();
        }
    }

    /**
     * Endpoint para listar todos os ônibus.
     *
     * @return Resposta HTTP com a lista de todos os ônibus.
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws SQLException {
        return Response.status(Response.Status.OK)
                .entity(this.onibusService.findAll())
                .build();
    }

    /**
     * Endpoint para atualizar um ônibus existente.
     *
     * @param id    ID do ônibus a ser atualizado.
     * @param input DTO de entrada para atualização do ônibus.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, OnibusDto input) {
        try {
            // Atualização do objeto Onibus com base no DTO e ID recebidos
            Onibus updated = this.onibusService.update(
                    new Onibus(
                            id,
                            input.getPrefixo(),
                            input.getPlaca(),
                            input.getModelo(),
                            input.getFabricante(),
                            input.getAnoFabricacao(),
                            input.getCapacidadeBateria(),
                            input.getCapacidadePassageiros(),
                            input.getPainelSolar(),
                            input.getPeliculaSolar(),
                            input.getIdLinha(),
                            input.getIdGaragem()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar ônibus"))
                    .build();
        }
    }

    /**
     * Endpoint para excluir um ônibus existente.
     *
     * @param id ID do ônibus a ser excluído.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.onibusService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar ônibus"))
                    .build();
        }
    }
}
