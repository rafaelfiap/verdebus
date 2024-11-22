package org.example.daos.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.Endereco;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Endereco.
 *
 * @version 1.0
 * @since 1.0
 */
public interface EnderecoDao<E, L extends Number> {

    /**
     * Busca todas as instâncias de Endereco no banco de dados.
     *
     * @return Lista de instâncias de {@link Endereco}.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<Endereco> findAll() throws SQLException;


    /**
     * Remove um Endereco pelo ID no banco de dados.
     *
     * @param id O ID do Endereco a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se o Endereco com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws NotFoundException, SQLException;

    /**
     * Salva um novo Endereco no banco de dados.
     *
     * @param endereco A instância de Endereco a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Endereco} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se o Endereco não puder ser salvo.
     */
    Endereco save(Endereco endereco, Connection connection) throws SQLException, NotSavedException;

    /**
     * Atualiza um Endereco existente no banco de dados.
     *
     * @param endereco A instância de Endereco com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Endereco} atualizada.
     * @throws NotFoundException Se o Endereco com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    Endereco update(Endereco endereco, Connection connection) throws NotFoundException, SQLException;
}
