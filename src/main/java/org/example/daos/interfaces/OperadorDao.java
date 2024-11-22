package org.example.daos.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.Operador;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Operador.
 *
 * @version 1.0
 * @since 1.0
 */
public interface OperadorDao {

    /**
     * Busca todas as instâncias de Operador no banco de dados.
     *
     * @return Lista de instâncias de {@link Operador}.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<Operador> findAll() throws SQLException;

    /**
     * Remove um Operador pelo ID no banco de dados.
     *
     * @param id O ID do Operador a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se o Operador com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws NotFoundException, SQLException;

    /**
     * Salva um novo Operador no banco de dados.
     *
     * @param operador A instância de Operador a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Operador} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se o Operador não puder ser salvo.
     */
    Operador save(Operador operador, Connection connection) throws SQLException, NotSavedException;

    /**
     * Atualiza um Operador existente no banco de dados.
     *
     * @param operador A instância de Operador com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Operador} atualizada.
     * @throws NotFoundException Se o Operador com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    Operador update(Operador operador, Connection connection) throws NotFoundException, SQLException;
}
