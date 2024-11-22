package org.example.controller;

import org.example.dtos.LinhaDeOnibusDto;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.LinhaDeOnibus;
import org.example.services.LinhaDeOnibusServiceFactory;
import org.example.services.interfaces.LinhaDeOnibusService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

/**
 * Controlador REST para a entidade LinhaDeOnibus.
 * Define endpoints para criar, listar, atualizar e deletar linhas de ônibus.
 *
 * @since 1.0
 */
@Path("/rest/linha-onibus")
public class LinhaDeOnibusController {

    // Instância de LinhaDeOnibusService obtida da factory
    private final LinhaDeOnibusService linhaService = LinhaDeOnibusServiceFactory.create();

    /**
     * Endpoint para criar uma nova linha de ônibus.
     *
     * @param input DTO de entrada para criação de uma linha de ônibus.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(LinhaDeOnibusDto input) throws UnsupportedServiceOperationException {
        if (input.getIdLinha() == null) {
            try {
                // Criação do objeto LinhaDeOnibus com base no DTO recebido
                LinhaDeOnibus linha = linhaService.create(
                        new LinhaDeOnibus(
                                null,
                                input.getCodigoLinha(),
                                input.getNome()
                        )
                );
                return Response.status(Response.Status.CREATED)
                        .entity(linha)
                        .build();
            } catch (SQLException | NotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir linha de ônibus"))
                        .build();
            } catch (UnsupportedServiceOperationException e) {
                throw new RuntimeException(e);
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novas linhas de ônibus"))
                    .build();
        }
    }

    /**
     * Endpoint para listar todas as linhas de ônibus.
     *
     * @return Resposta HTTP com a lista de todas as linhas de ônibus.
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            return Response.status(Response.Status.OK)
                    .entity(linhaService.findAll())
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro ao buscar as linhas de ônibus"))
                    .build();
        }
    }

    /**
     * Endpoint para atualizar uma linha de ônibus existente.
     *
     * @param id    ID da linha de ônibus a ser atualizada.
     * @param input DTO de entrada para atualização da linha de ônibus.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, LinhaDeOnibusDto input) {
        try {
            // Atualização do objeto LinhaDeOnibus com base no DTO e ID recebidos
            LinhaDeOnibus updated = linhaService.update(
                    new LinhaDeOnibus(
                            id,
                            input.getCodigoLinha(),
                            input.getNome()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Linha de ônibus não encontrada"))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar linha de ônibus"))
                    .build();
        }
    }

    /**
     * Endpoint para excluir uma linha de ônibus existente.
     *
     * @param id ID da linha de ônibus a ser excluída.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            linhaService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Linha de ônibus não encontrada"))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar linha de ônibus"))
                    .build();
        }
    }
}
