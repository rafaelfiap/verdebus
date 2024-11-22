package org.example.daos.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.Carro;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Carro.
 *
 * @version 1.0
 * @since 1.0
 */
public interface CarroDao {

    /**
     * Busca todas as instâncias de Carro no banco de dados.
     *
     * @return Lista de instâncias de {@link Carro}.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<Carro> findAll() throws SQLException;

    /**
     * Remove um Carro pelo ID no banco de dados.
     *
     * @param id O ID do Carro a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se o Carro com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws NotFoundException, SQLException;

    /**
     * Salva um novo Carro no banco de dados.
     *
     * @param carro A instância de Carro a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Carro} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se o Carro não puder ser salvo.
     */
    Carro save(Carro carro, Connection connection) throws SQLException, NotSavedException;

    Carro update(Carro carro, Connection connection) throws NotFoundException, SQLException;

    /**
     * Atualiza um Carro existente no banco de dados.
     *
     * @param carro A instância de Carro com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Carro} atualizada.
     * @throws NotFoundException Se o Carro com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
}
