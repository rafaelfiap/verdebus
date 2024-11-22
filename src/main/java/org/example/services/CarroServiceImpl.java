package org.example.services;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.CarroDaoFactory;
import org.example.daos.interfaces.CarroDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Carro;
import org.example.services.interfaces.CarroService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação do serviço para a entidade Carro, utilizando CarroDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para carros no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class CarroServiceImpl implements CarroService {

    private final CarroDao dao = CarroDaoFactory.create();
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Cria um novo Carro no banco de dados.
     *
     * @param carro O objeto Carro a ser criado.
     * @return O Carro criado com o ID gerado.
     * @throws NotSavedException Se o Carro não puder ser salvo.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Carro create(Carro carro) throws UnsupportedServiceOperationException, SQLException, NotSavedException {
        if (carro.getIdCarro() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                carro = this.dao.save(carro, connection);
                connection.commit();
                return carro;
            } catch (SQLException | NotSavedException e) {
                connection.rollback();
                logger.warning("Erro ao salvar o carro: " + e.getMessage());
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException("Carro já possui um ID e não pode ser criado.");
        }
    }

    /**
     * Retorna uma lista de todos os carros.
     *
     * @return Lista de todos os carros.
     */
    @Override
    public List<Carro> findAll() throws SQLException {
        return this.dao.findAll();
    }

    /**
     * Atualiza um Carro existente no banco de dados.
     *
     * @param carro O objeto Carro com os dados atualizados.
     * @return O Carro atualizado.
     * @throws NotFoundException Se o Carro não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Carro update(Carro carro) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            carro = this.dao.update(carro, connection);
            connection.commit();
            return carro;
        } catch (SQLException e) {
            connection.rollback();
            logger.warning("Erro ao atualizar o carro: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Exclui um Carro do banco de dados com base no ‘ID’ fornecido.
     *
     * @param id O ‘ID’ do Carro a ser excluído.
     * @throws NotFoundException Se o Carro não for encontrado.
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
            logger.warning("Erro ao excluir o carro: " + e.getMessage());
            throw e;
        }
    }
}
