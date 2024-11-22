
package org.example.services;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.EstacaoRecargaSolarDaoFactory;
import org.example.daos.interfaces.EstacaoRecargaSolarDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.EstacaoRecargaSolar;
import org.example.services.interfaces.EstacaoRecargaSolarService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação do serviço para a entidade EstacaoRecargaSolar, utilizando EstacaoRecargaSolarDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para estações de recarga solar no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class EstacaoRecargaSolarServiceImpl implements EstacaoRecargaSolarService {

    private final EstacaoRecargaSolarDao dao = EstacaoRecargaSolarDaoFactory.create();
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Cria uma nova EstacaoRecargaSolar no banco de dados.
     *
     * @param estacao O objeto EstacaoRecargaSolar a ser criado.
     * @return A EstacaoRecargaSolar criada com o ID gerado.
     * @throws NotSavedException Se a EstacaoRecargaSolar não puder ser salva.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public EstacaoRecargaSolar create(EstacaoRecargaSolar estacao) throws UnsupportedServiceOperationException, SQLException, NotSavedException {
        if (estacao.getIdEstacao() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                estacao = this.dao.save(estacao, connection);
                connection.commit();
                return estacao;
            } catch (SQLException | NotSavedException e) {
                connection.rollback();
                logger.warning("Erro ao salvar a estação de recarga solar: " + e.getMessage());
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException("Estação de recarga solar já possui um ID e não pode ser criada.");
        }
    }

    /**
     * Retorna uma lista de todas as estações de recarga solar.
     *
     * @return Lista de todas as estações de recarga solar.
     */
    @Override
    public List<EstacaoRecargaSolar> findAll() throws SQLException {
        return this.dao.findAll();
    }

    /**
     * Atualiza uma EstacaoRecargaSolar existente no banco de dados.
     *
     * @param estacao O objeto EstacaoRecargaSolar com os dados atualizados.
     * @return A EstacaoRecargaSolar atualizada.
     * @throws NotFoundException Se a EstacaoRecargaSolar não for encontrada.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public EstacaoRecargaSolar update(EstacaoRecargaSolar estacao) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            estacao = this.dao.update(estacao, connection);
            connection.commit();
            return estacao;
        } catch (SQLException e) {
            connection.rollback();
            logger.warning("Erro ao atualizar a estação de recarga solar: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Exclui uma EstacaoRecargaSolar do banco de dados com base no ‘ID’ fornecido.
     *
     * @param id O ‘ID’ da EstacaoRecargaSolar a ser excluída.
     * @throws NotFoundException Se a EstacaoRecargaSolar não for encontrada.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public void deleteById(Long id) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            this.dao.deleteById(id, connection);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            logger.warning("Erro ao excluir a estação de recarga solar: " + e.getMessage());
            throw e;
        }
    }
}
