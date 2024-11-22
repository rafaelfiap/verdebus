package org.example.controller;

import org.example.dtos.EnderecoDto;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Endereco;
import org.example.services.EnderecoGaragemServiceFactory;
import org.example.services.interfaces.EnderecoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

/**
 * Controlador REST para a entidade EnderecoGaragem.
 * Define endpoints para criar, listar, atualizar e deletar endereços associados a garagens.
 *
 * @since 1.0
 */
@Path("/rest/endereco-garagem")
public class EnderecoGaragemController {

    // Instância de EnderecoService obtida da factory
    private final EnderecoService enderecoService = EnderecoGaragemServiceFactory.create();

    /**
     * Endpoint para criar um novo endereço de garagem.
     *
     * @param input DTO de entrada para criação de um endereço de garagem.
     * @return Resposta HTTP indicando sucesso ou erro.
     * @throws UnsupportedServiceOperationException Se ocorrer um erro na operação.
     */
    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(EnderecoDto input) throws UnsupportedServiceOperationException {
        if (input.getIdEndereco() == null) {
            try {
                // Criação do objeto Endereco com base no DTO recebido
                Endereco endereco = enderecoService.create(
                        new Endereco(
                                null,
                                input.getLogradouro(),
                                input.getNumero(),
                                input.getBairro(),
                                input.getCidade(),
                                input.getUf(),
                                input.getIdReferencia()
                        )
                );
                return Response.status(Response.Status.CREATED)
                        .entity(endereco)
                        .build();
            } catch (SQLException | NotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir endereço de garagem"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novos endereços"))
                    .build();
        }
    }

    /**
     * Endpoint para listar todos os endereços de garagens.
     *
     * @return Resposta HTTP com a lista de todos os endereços de garagens.
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            return Response.status(Response.Status.OK)
                    .entity(enderecoService.findAll())
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro ao buscar os endereços de garagens"))
                    .build();
        }
    }

    /**
     * Endpoint para atualizar um endereço de garagem existente.
     *
     * @param id    ID do endereço de garagem a ser atualizado.
     * @param input DTO de entrada para atualização do endereço de garagem.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, EnderecoDto input) {
        try {
            // Atualização do objeto Endereco com base no DTO e ID recebidos
            Endereco updated = enderecoService.update(
                    new Endereco(
                            id,
                            input.getLogradouro(),
                            input.getNumero(),
                            input.getBairro(),
                            input.getCidade(),
                            input.getUf(),
                            input.getIdReferencia()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Endereço de garagem não encontrado"))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar endereço de garagem"))
                    .build();
        }
    }

    /**
     * Endpoint para excluir um endereço de garagem existente.
     *
     * @param id ID do endereço de garagem a ser excluído.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            enderecoService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Endereço de garagem não encontrado"))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar endereço de garagem"))
                    .build();
        }
    }
}
