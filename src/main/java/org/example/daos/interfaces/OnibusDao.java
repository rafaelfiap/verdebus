package org.example.daos.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.Onibus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Onibus.
 *
 * @version 1.0
 * @since 1.0
 */
public interface OnibusDao {

    /**
     * Busca todas as instâncias de Onibus no banco de dados.
     *
     * @return Lista de instâncias de {@link Onibus}.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<Onibus> findAll() throws SQLException;

    /**
     * Remove um Onibus pelo ID no banco de dados.
     *
     * @param id O ID do Onibus a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se o Onibus com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws NotFoundException, SQLException;

    /**
     * Salva um novo Onibus no banco de dados.
     *
     * @param onibus A instância de Onibus a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Onibus} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se o Onibus não puder ser salvo.
     */
    Onibus save(Onibus onibus, Connection connection) throws SQLException, NotSavedException;

    /**
     * Atualiza um Onibus existente no banco de dados.
     *
     * @param onibus A instância de Onibus com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Onibus} atualizada.
     * @throws NotFoundException Se o Onibus com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    Onibus update(Onibus onibus, Connection connection) throws NotFoundException, SQLException;
}
