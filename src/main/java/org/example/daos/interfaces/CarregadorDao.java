package org.example.daos.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.Carregador;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Carregador.
 *
 * @version 1.0
 * @since 1.0
 */
public interface CarregadorDao {

    /**
     * Busca todas as instâncias de Carregador no banco de dados.
     *
     * @return Lista de instâncias de {@link Carregador}.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<Carregador> findAll() throws SQLException;

    /**
     * Remove um Carregador pelo ID no banco de dados.
     *
     * @param id O ID do Carregador a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se o Carregador com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws NotFoundException, SQLException;

    /**
     * Salva um novo Carregador no banco de dados.
     *
     * @param carregador A instância de Carregador a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Carregador} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se o Carregador não puder ser salvo.
     */
    Carregador save(Carregador carregador, Connection connection) throws SQLException, NotSavedException;

    /**
     * Atualiza um Carregador existente no banco de dados.
     *
     * @param carregador A instância de Carregador com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Carregador} atualizada.
     * @throws NotFoundException Se o Carregador com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    Carregador update(Carregador carregador, Connection connection) throws NotFoundException, SQLException;
}

