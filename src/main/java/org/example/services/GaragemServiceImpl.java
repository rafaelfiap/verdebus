package org.example.services;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.GaragemDaoFactory;
import org.example.daos.interfaces.GaragemDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Garagem;
import org.example.services.interfaces.GaragemService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação do serviço para a entidade Garagem, utilizando GaragemDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para garagens no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class GaragemServiceImpl implements GaragemService {

    private final GaragemDao dao = GaragemDaoFactory.create();
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Cria uma nova Garagem no banco de dados.
     *
     * @param garagem O objeto Garagem a ser criado.
     * @return A Garagem criada com o ID gerado.
     * @throws NotSavedException Se a Garagem não puder ser salva.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Garagem create(Garagem garagem) throws UnsupportedServiceOperationException, SQLException, NotSavedException {
        if (garagem.getIdGaragem() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                garagem = this.dao.save(garagem, connection);
                connection.commit();
                return garagem;
            } catch (SQLException | NotSavedException e) {
                connection.rollback();
                logger.warning("Erro ao salvar a garagem: " + e.getMessage());
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException("Garagem já possui um ID e não pode ser criada.");
        }
    }

    /**
     * Retorna uma lista de todas as garagens.
     *
     * @return Lista de todas as garagens.
     */
    @Override
    public List<Garagem> findAll() throws SQLException {
        return this.dao.findAll();
    }

    /**
     * Atualiza uma Garagem existente no banco de dados.
     *
     * @param garagem O objeto Garagem com os dados atualizados.
     * @return A Garagem atualizada.
     * @throws NotFoundException Se a Garagem não for encontrada.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Garagem update(Garagem garagem) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            garagem = this.dao.update(garagem, connection);
            connection.commit();
            return garagem;
        } catch (SQLException e) {
            connection.rollback();
            logger.warning("Erro ao atualizar a garagem: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Exclui uma Garagem do banco de dados com base no ‘ID’ fornecido.
     *
     * @param id O ‘ID’ da Garagem a ser excluída.
     * @throws NotFoundException Se a Garagem não for encontrada.
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
            logger.warning("Erro ao excluir a garagem: " + e.getMessage());
            throw e;
        }
    }
}
