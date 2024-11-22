package org.example.services;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.LinhaDeOnibusDaoFactory;
import org.example.daos.interfaces.LinhaDeOnibusDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.LinhaDeOnibus;
import org.example.services.interfaces.LinhaDeOnibusService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação do serviço para a entidade LinhaDeOnibus, utilizando LinhaDeOnibusDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para linhas de ônibus no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class LinhaDeOnibusServiceImpl implements LinhaDeOnibusService {

    private final LinhaDeOnibusDao dao = LinhaDeOnibusDaoFactory.create(); // Instância de DAO para LinhaDeOnibus
    private final Logger logger = Logger.getLogger(this.getClass().getName()); // Logger para registrar mensagens e eventos

    /**
     * Cria uma nova LinhaDeOnibus no banco de dados.
     *
     * @param linhaDeOnibus O objeto LinhaDeOnibus a ser criado.
     * @return A LinhaDeOnibus criada com o ID gerado.
     * @throws NotSavedException Se a LinhaDeOnibus não puder ser salva.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public LinhaDeOnibus create(LinhaDeOnibus linhaDeOnibus) throws UnsupportedServiceOperationException, SQLException, NotSavedException {
        if (linhaDeOnibus.getIdLinha() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                linhaDeOnibus = this.dao.save(linhaDeOnibus, connection);
                connection.commit();
                return linhaDeOnibus;
            } catch (SQLException | NotSavedException e) {
                connection.rollback();
                logger.warning("Erro ao salvar a linha de ônibus: " + e.getMessage());
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException("Linha de ônibus já possui um ID e não pode ser criada.");
        }
    }

    /**
     * Retorna uma lista de todas as linhas de ônibus.
     *
     * @return Lista de todas as linhas de ônibus.
     */
    @Override
    public List<LinhaDeOnibus> findAll() throws SQLException {
        return this.dao.findAll();
    }

    /**
     * Atualiza uma LinhaDeOnibus existente no banco de dados.
     *
     * @param linhaDeOnibus O objeto LinhaDeOnibus com os dados atualizados.
     * @return A LinhaDeOnibus atualizada.
     * @throws NotFoundException Se a LinhaDeOnibus não for encontrada.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public LinhaDeOnibus update(LinhaDeOnibus linhaDeOnibus) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            linhaDeOnibus = this.dao.update(linhaDeOnibus, connection);
            connection.commit();
            return linhaDeOnibus;
        } catch (SQLException e) {
            connection.rollback();
            logger.warning("Erro ao atualizar a linha de ônibus: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Exclui uma LinhaDeOnibus do banco de dados com base no ‘ID’ fornecido.
     *
     * @param id O ‘ID’ da LinhaDeOnibus a ser excluída.
     * @throws NotFoundException Se a LinhaDeOnibus não for encontrada.
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
            logger.warning("Erro ao excluir a linha de ônibus: " + e.getMessage());
            throw e;
        }
    }
}
