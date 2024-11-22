package org.example.daos.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.EstacaoRecargaSolar;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade EstacaoRecargaSolar.
 *
 * @version 1.0
 * @since 1.0
 */
public interface EstacaoRecargaSolarDao {

    /**
     * Busca todas as instâncias de EstacaoRecargaSolar no banco de dados.
     *
     * @return Lista de instâncias de {@link EstacaoRecargaSolar}.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<EstacaoRecargaSolar> findAll() throws SQLException;


    /**
     * Remove uma EstacaoRecargaSolar pelo ID no banco de dados.
     *
     * @param id O ID da EstacaoRecargaSolar a ser removida.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se a EstacaoRecargaSolar com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws NotFoundException, SQLException;

    /**
     * Salva uma nova EstacaoRecargaSolar no banco de dados.
     *
     * @param estacaoRecargaSolar A instância de EstacaoRecargaSolar a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link EstacaoRecargaSolar} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se a EstacaoRecargaSolar não puder ser salva.
     */
    EstacaoRecargaSolar save(EstacaoRecargaSolar estacaoRecargaSolar, Connection connection) throws SQLException, NotSavedException;

    /**
     * Atualiza uma EstacaoRecargaSolar existente no banco de dados.
     *
     * @param estacaoRecargaSolar A instância de EstacaoRecargaSolar com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link EstacaoRecargaSolar} atualizada.
     * @throws NotFoundException Se a EstacaoRecargaSolar com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    EstacaoRecargaSolar update(EstacaoRecargaSolar estacaoRecargaSolar, Connection connection) throws NotFoundException, SQLException;
}
