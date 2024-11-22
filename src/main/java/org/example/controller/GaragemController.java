package org.example.controller;

import org.example.dtos.GaragemDto;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Garagem;
import org.example.services.interfaces.GaragemService;
import org.example.services.GaragemServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

/**
 * Controlador REST para a entidade Garagem.
 * Define endpoints para criar, listar, atualizar e deletar garagens.
 *
 * @since 1.0
 */
@Path("/rest/garagem")
public class GaragemController {

    // Instância de GaragemService obtida da factory
    private final GaragemService garagemService = GaragemServiceFactory.create();

    /**
     * Endpoint para criar uma nova garagem.
     *
     * @param input DTO de entrada para criação de uma garagem.
     * @return Resposta HTTP indicando sucesso ou erro.
     * @throws UnsupportedServiceOperationException Se ocorrer um erro na operação.
     */
    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(GaragemDto input) throws UnsupportedServiceOperationException {
        if (input.getIdGaragem() == null) {
            try {
                // Criação do objeto Garagem com base no DTO recebido
                Garagem garagem = this.garagemService.create(
                        new Garagem(
                                null,
                                input.getNome(),
                                input.getCapacidadeOnibus()
                        )
                );
                return Response.status(Response.Status.CREATED)
                        .entity(garagem)
                        .build();
            } catch (SQLException | NotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir garagem"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novas garagens"))
                    .build();
        }
    }

    /**
     * Endpoint para listar todas as garagens.
     *
     * @return Resposta HTTP com a lista de todas as garagens.
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws SQLException {
        return Response.status(Response.Status.OK)
                .entity(this.garagemService.findAll())
                .build();
    }

    /**
     * Endpoint para atualizar uma garagem existente.
     *
     * @param id    ID da garagem a ser atualizada.
     * @param input DTO de entrada para atualização da garagem.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, GaragemDto input) {
        try {
            // Atualização do objeto Garagem com base no DTO e ID recebidos
            Garagem updated = this.garagemService.update(
                    new Garagem(
                            id,
                            input.getNome(),
                            input.getCapacidadeOnibus()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar garagem"))
                    .build();
        }
    }

    /**
     * Endpoint para excluir uma garagem existente.
     *
     * @param id ID da garagem a ser excluída.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.garagemService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar garagem"))
                    .build();
        }
    }
}
