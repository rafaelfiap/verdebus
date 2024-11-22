package org.example.services.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Endereco;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Endereco.
 * Define métodos para criar, atualizar, excluir e buscar endereços.
 *
 * @since 1.0
 */
public interface EnderecoService {

    /**
     * Cria um novo endereço no sistema.
     *
     * @param endereco A instância de Endereco a ser criada.
     * @return O Endereco criado com o ‘ID’ gerado.
     * @throws NotSavedException Se o endereço não puder ser salvo.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Endereco create(Endereco endereco) throws UnsupportedServiceOperationException, SQLException, NotSavedException;

    /**
     * Retorna uma lista de todos os endereços.
     *
     * @return Lista de todos os endereços.
     * @throws SQLException Se ocorrer um erro ao buscar os endereços.
     */
    List<Endereco> findAll() throws SQLException;

    /**
     * Atualiza os dados de um endereço existente no sistema.
     *
     * @param endereco A instância de Endereco com os dados atualizados.
     * @return O Endereco atualizado.
     * @throws NotFoundException Se o endereço não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Endereco update(Endereco endereco) throws NotFoundException, SQLException;

    /**
     * Exclui um endereço do sistema pelo seu ID.
     *
     * @param id O ID do endereço a ser excluído.
     * @throws NotFoundException Se o endereço não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws NotFoundException, SQLException;
}
