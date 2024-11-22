package org.example.controller;

import org.example.dtos.PontoRecargaDto;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.PontoRecarga;
import org.example.services.PontoRecargaServiceFactory;
import org.example.services.interfaces.PontoRecargaService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

/**
 * Controlador REST para a entidade PontoRecarga.
 * Define endpoints para criar, listar, atualizar e deletar pontos de recarga.
 *
 * @since 1.0
 */
@Path("/rest/ponto-recarga")
public class PontoRecargaController {

    // Instância de PontoRecargaService obtida da factory
    private final PontoRecargaService pontoRecargaService = PontoRecargaServiceFactory.create();

    /**
     * Endpoint para criar um novo ponto de recarga.
     *
     * @param input DTO de entrada para criação de um ponto de recarga.
     * @return Resposta HTTP indicando sucesso ou erro.
     * @throws UnsupportedServiceOperationException Se ocorrer um erro na operação.
     */
    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(PontoRecargaDto input) throws UnsupportedServiceOperationException {
        if (input.getIdPonto() == null) {
            try {
                // Criação do objeto PontoRecarga com base no DTO recebido
                PontoRecarga pontoRecarga = this.pontoRecargaService.create(
                        new PontoRecarga(
                                null,
                                input.getPotencia(),
                                input.isOcupado(),
                                input.getIdLinha()
                        )
                );
                return Response.status(Response.Status.CREATED)
                        .entity(pontoRecarga)
                        .build();
            } catch (SQLException | NotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir ponto de recarga"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novos pontos de recarga"))
                    .build();
        }
    }

    /**
     * Endpoint para listar todos os pontos de recarga.
     *
     * @return Resposta HTTP com a lista de todos os pontos de recarga.
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws SQLException {
        return Response.status(Response.Status.OK)
                .entity(this.pontoRecargaService.findAll())
                .build();
    }

    /**
     * Endpoint para atualizar um ponto de recarga existente.
     *
     * @param id    ID do ponto de recarga a ser atualizado.
     * @param input DTO de entrada para atualização do ponto de recarga.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, PontoRecargaDto input) {
        try {
            // Atualização do objeto PontoRecarga com base no DTO e ID recebidos
            PontoRecarga updated = this.pontoRecargaService.update(
                    new PontoRecarga(
                            id,
                            input.getPotencia(),
                            input.isOcupado(),
                            input.getIdLinha()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Ponto de recarga não encontrado"))
                    .build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar ponto de recarga"))
                    .build();
        }
    }

    /**
     * Endpoint para excluir um ponto de recarga existente.
     *
     * @param id ID do ponto de recarga a ser excluído.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.pontoRecargaService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Ponto de recarga não encontrado"))
                    .build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar ponto de recarga"))
                    .build();
        }
    }
}
