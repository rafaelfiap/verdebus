package org.example.controller;

import org.example.dtos.MotoDto;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Moto;
import org.example.services.interfaces.MotoService;
import org.example.services.MotoServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

/**
 * Controlador REST para a entidade Moto.
 * Define endpoints para criar, listar, atualizar e deletar motos.
 *
 * @since 1.0
 */
@Path("/rest/moto")
public class MotoController {

    // Instância de MotoService obtida da factory
    private final MotoService motoService = MotoServiceFactory.create();

    /**
     * Endpoint para criar uma nova moto.
     *
     * @param input DTO de entrada para criação de uma moto.
     * @return Resposta HTTP indicando sucesso ou erro.
     * @throws UnsupportedServiceOperationException Se ocorrer um erro na operação.
     */
    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(MotoDto input) throws UnsupportedServiceOperationException {
        if (input.getIdMoto() == null) {
            try {
                // Criação do objeto Moto com base no DTO recebido
                Moto moto = this.motoService.create(
                        new Moto(
                                null,
                                input.getPrefixo(),
                                input.getPlaca(),
                                input.getModelo(),
                                input.getFabricante(),
                                input.getAnoFabricacao(),
                                input.getCapacidadeBateria(),
                                input.getTipoLicenca(),
                                input.getIdGaragem()
                        )
                );
                return Response.status(Response.Status.CREATED)
                        .entity(moto)
                        .build();
            } catch (SQLException | NotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir moto"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novas motos"))
                    .build();
        }
    }

    /**
     * Endpoint para listar todas as motos.
     *
     * @return Resposta HTTP com a lista de todas as motos.
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws SQLException {
        return Response.status(Response.Status.OK)
                .entity(this.motoService.findAll())
                .build();
    }

    /**
     * Endpoint para atualizar uma moto existente.
     *
     * @param id    ID da moto a ser atualizada.
     * @param input DTO de entrada para atualização da moto.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, MotoDto input) {
        try {
            // Atualização do objeto Moto com base no DTO e ID recebidos
            Moto updated = this.motoService.update(
                    new Moto(
                            id,
                            input.getPrefixo(),
                            input.getPlaca(),
                            input.getModelo(),
                            input.getFabricante(),
                            input.getAnoFabricacao(),
                            input.getCapacidadeBateria(),
                            input.getTipoLicenca(),
                            input.getIdGaragem()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar moto"))
                    .build();
        }
    }

    /**
     * Endpoint para excluir uma moto existente.
     *
     * @param id ID da moto a ser excluída.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.motoService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar moto"))
                    .build();
        }
    }
}
