package org.example.services;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.MotoDaoFactory;
import org.example.daos.interfaces.MotoDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Moto;
import org.example.services.interfaces.MotoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação do serviço para a entidade Moto, utilizando MotoDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para motos no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class MotoServiceImpl implements MotoService {

    private final MotoDao dao = MotoDaoFactory.create();
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Cria uma nova Moto no banco de dados.
     *
     * @param moto O objeto Moto a ser criado.
     * @return A Moto criada com o ID gerado.
     * @throws NotSavedException Se a Moto não puder ser salva.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Moto create(Moto moto) throws UnsupportedServiceOperationException, SQLException, NotSavedException {
        if (moto.getIdMoto() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                moto = this.dao.save(moto, connection);
                connection.commit();
                return moto;
            } catch (SQLException | NotSavedException e) {
                connection.rollback();
                logger.warning("Erro ao salvar a moto: " + e.getMessage());
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException("Moto já possui um ID e não pode ser criada.");
        }
    }

    /**
     * Retorna uma lista de todas as motos.
     *
     * @return Lista de todas as motos.
     */
    @Override
    public List<Moto> findAll() throws SQLException {
        return this.dao.findAll();
    }

    /**
     * Atualiza uma Moto existente no banco de dados.
     *
     * @param moto O objeto Moto com os dados atualizados.
     * @return A Moto atualizada.
     * @throws NotFoundException Se a Moto não for encontrada.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Moto update(Moto moto) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            moto = this.dao.update(moto, connection);
            connection.commit();
            return moto;
        } catch (SQLException e) {
            connection.rollback();
            logger.warning("Erro ao atualizar a moto: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Exclui uma Moto do banco de dados com base no ‘ID’ fornecido.
     *
     * @param id O ‘ID’ da Moto a ser excluída.
     * @throws NotFoundException Se a Moto não for encontrada.
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
            logger.warning("Erro ao excluir a moto: " + e.getMessage());
            throw e;
        }
    }
}
