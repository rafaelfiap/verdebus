package org.example.services;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.OnibusDaoFactory;
import org.example.daos.interfaces.OnibusDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Onibus;
import org.example.services.interfaces.OnibusService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação do serviço para a entidade Ônibus, utilizando OnibusDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para ônibus no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class OnibusServiceImpl implements OnibusService {

    private final OnibusDao dao = OnibusDaoFactory.create();
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Cria um novo Ônibus no banco de dados.
     *
     * @param onibus O objeto Ônibus a ser criado.
     * @return O Ônibus criado com o ID gerado.
     * @throws NotSavedException Se o Ônibus não puder ser salvo.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Onibus create(Onibus onibus) throws UnsupportedServiceOperationException, SQLException, NotSavedException {
        if (onibus.getIdOnibus() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                onibus = this.dao.save(onibus, connection);
                connection.commit();
                return onibus;
            } catch (SQLException | NotSavedException e) {
                connection.rollback();
                logger.warning("Erro ao salvar o ônibus: " + e.getMessage());
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException("Ônibus já possui um ID e não pode ser criado.");
        }
    }

    /**
     * Retorna uma lista de todos os ônibus.
     *
     * @return Lista de todos os ônibus.
     */
    @Override
    public List<Onibus> findAll() throws SQLException {
        return this.dao.findAll();
    }

    /**
     * Atualiza um Ônibus existente no banco de dados.
     *
     * @param onibus O objeto Ônibus com os dados atualizados.
     * @return O Ônibus atualizado.
     * @throws NotFoundException Se o Ônibus não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Onibus update(Onibus onibus) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            onibus = this.dao.update(onibus, connection);
            connection.commit();
            return onibus;
        } catch (SQLException e) {
            connection.rollback();
            logger.warning("Erro ao atualizar o ônibus: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Exclui um Ônibus do banco de dados com base no ‘ID’ fornecido.
     *
     * @param id O ‘ID’ do Ônibus a ser excluído.
     * @throws NotFoundException Se o Ônibus não for encontrado.
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
            logger.warning("Erro ao excluir o ônibus: " + e.getMessage());
            throw e;
        }
    }
}
