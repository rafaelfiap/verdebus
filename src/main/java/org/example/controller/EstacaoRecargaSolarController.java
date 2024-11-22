package org.example.controller;

import org.example.dtos.EstacaoRecargaSolarDto;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.EstacaoRecargaSolar;
import org.example.services.EstacaoRecargaSolarServiceFactory;
import org.example.services.interfaces.EstacaoRecargaSolarService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

/**
 * Controlador REST para a entidade EstacaoRecargaSolar.
 * Define endpoints para criar, listar, atualizar e deletar estações de recarga solar.
 *
 * @since 1.0
 */
@Path("/rest/estacao-recarga-solar")
public class EstacaoRecargaSolarController {

    // Instância de EstacaoRecargaSolarService obtida da factory
    private final EstacaoRecargaSolarService estacaoService = EstacaoRecargaSolarServiceFactory.create();

    /**
     * Endpoint para criar uma nova estação de recarga solar.
     *
     * @param input DTO de entrada para criação de uma estação de recarga solar.
     * @return Resposta HTTP indicando sucesso ou erro.
     * @throws UnsupportedServiceOperationException Se ocorrer um erro na operação.
     */
    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(EstacaoRecargaSolarDto input) throws UnsupportedServiceOperationException {
        if (input.getIdEstacao() == null) {
            try {
                // Criação do objeto EstacaoRecargaSolar com base no DTO recebido
                EstacaoRecargaSolar estacao = estacaoService.create(
                        new EstacaoRecargaSolar(
                                null,
                                input.getPotenciaMaxima(),
                                input.getNumeroPaineis(),
                                input.getEnergiaGerada(),
                                input.getOcupada(),
                                input.getIdGaragem()
                        )
                );
                return Response.status(Response.Status.CREATED)
                        .entity(estacao)
                        .build();
            } catch (SQLException | NotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir estação de recarga solar"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novas estações"))
                    .build();
        }
    }

    /**
     * Endpoint para listar todas as estações de recarga solar.
     *
     * @return Resposta HTTP com a lista de todas as estações de recarga solar.
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            return Response.status(Response.Status.OK)
                    .entity(estacaoService.findAll())
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro ao buscar as estações de recarga solar"))
                    .build();
        }
    }

    /**
     * Endpoint para atualizar uma estação de recarga solar existente.
     *
     * @param id    ID da estação de recarga solar a ser atualizada.
     * @param input DTO de entrada para atualização da estação de recarga solar.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, EstacaoRecargaSolarDto input) {
        try {
            // Atualização do objeto EstacaoRecargaSolar com base no DTO e ID recebidos
            EstacaoRecargaSolar updated = estacaoService.update(
                    new EstacaoRecargaSolar(
                            id,
                            input.getPotenciaMaxima(),
                            input.getNumeroPaineis(),
                            input.getEnergiaGerada(),
                            input.getOcupada(),
                            input.getIdGaragem()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Estação de recarga solar não encontrada"))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar estação de recarga solar"))
                    .build();
        }
    }

    /**
     * Endpoint para excluir uma estação de recarga solar existente.
     *
     * @param id ID da estação de recarga solar a ser excluída.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            estacaoService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Estação de recarga solar não encontrada"))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar estação de recarga solar"))
                    .build();
        }
    }
}
