package org.example.daos.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.LinhaDeOnibus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade LinhaDeOnibus.
 *
 * @version 1.0
 * @since 1.0
 */
public interface LinhaDeOnibusDao {

    /**
     * Busca todas as instâncias de LinhaDeOnibus no banco de dados.
     *
     * @return Lista de instâncias de {@link LinhaDeOnibus}.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<LinhaDeOnibus> findAll() throws SQLException;

    /**
     * Remove uma LinhaDeOnibus pelo ID no banco de dados.
     *
     * @param id O ID da LinhaDeOnibus a ser removida.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se a LinhaDeOnibus com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws NotFoundException, SQLException;

    /**
     * Salva uma nova LinhaDeOnibus no banco de dados.
     *
     * @param linhaDeOnibus A instância de LinhaDeOnibus a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link LinhaDeOnibus} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se a LinhaDeOnibus não puder ser salva.
     */
    LinhaDeOnibus save(LinhaDeOnibus linhaDeOnibus, Connection connection) throws SQLException, NotSavedException;

    /**
     * Atualiza uma LinhaDeOnibus existente no banco de dados.
     *
     * @param linhaDeOnibus A instância de LinhaDeOnibus com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link LinhaDeOnibus} atualizada.
     * @throws NotFoundException Se a LinhaDeOnibus com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    LinhaDeOnibus update(LinhaDeOnibus linhaDeOnibus, Connection connection) throws NotFoundException, SQLException;
}
