package org.example.controller;

import org.example.dtos.EnderecoDto;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Endereco;
import org.example.services.EnderecoLinhaServiceFactory;
import org.example.services.interfaces.EnderecoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

/**
 * Controlador REST para a entidade EnderecoLinha.
 * Define endpoints para criar, listar, atualizar e deletar endereços associados a linhas de ônibus.
 *
 * @since 1.0
 */
@Path("/rest/endereco-linha")
public class EnderecoLinhaController {

    // Instância de EnderecoService obtida da factory
    private final EnderecoService enderecoService = EnderecoLinhaServiceFactory.create();

    /**
     * Endpoint para criar um novo endereço de linha.
     *
     * @param input DTO de entrada para criação de um endereço de linha.
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
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir endereço de linha"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novos endereços"))
                    .build();
        }
    }

    /**
     * Endpoint para listar todos os endereços de linhas.
     *
     * @return Resposta HTTP com a lista de todos os endereços de linhas.
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
                    .entity(Map.of("mensagem", "Erro ao buscar os endereços de linhas"))
                    .build();
        }
    }

    /**
     * Endpoint para atualizar um endereço de linha existente.
     *
     * @param id    ID do endereço de linha a ser atualizado.
     * @param input DTO de entrada para atualização do endereço de linha.
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
                    .entity(Map.of("mensagem", "Endereço de linha não encontrado"))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar endereço de linha"))
                    .build();
        }
    }

    /**
     * Endpoint para excluir um endereço de linha existente.
     *
     * @param id ID do endereço de linha a ser excluído.
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
                    .entity(Map.of("mensagem", "Endereço de linha não encontrado"))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar endereço de linha"))
                    .build();
        }
    }
}
