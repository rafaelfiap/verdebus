package org.example.services;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.CarregadorDaoFactory;
import org.example.daos.interfaces.CarregadorDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Carregador;
import org.example.services.interfaces.CarregadorService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação do serviço para a entidade Carregador, utilizando CarregadorDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para carregadores no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class CarregadorServiceImpl implements CarregadorService {

    private final CarregadorDao dao = CarregadorDaoFactory.create();
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Cria um novo Carregador no banco de dados.
     *
     * @param carregador O objeto Carregador a ser criado.
     * @return O Carregador criado com o ID gerado.
     * @throws NotSavedException Se o Carregador não puder ser salvo.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Carregador create(Carregador carregador) throws UnsupportedServiceOperationException, SQLException, NotSavedException {
        if (carregador.getIdCarregador() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                carregador = this.dao.save(carregador, connection);
                connection.commit();
                return carregador;
            } catch (SQLException | NotSavedException e) {
                connection.rollback();
                logger.warning("Erro ao salvar o carregador: " + e.getMessage());
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException("Carregador já possui um ID e não pode ser criado.");
        }
    }

    /**
     * Retorna uma lista de todos os carregadores.
     *
     * @return Lista de todos os carregadores.
     */
    @Override
    public List<Carregador> findAll() throws SQLException {
        return this.dao.findAll();
    }

    /**
     * Atualiza um Carregador existente no banco de dados.
     *
     * @param carregador O objeto Carregador com os dados atualizados.
     * @return O Carregador atualizado.
     * @throws NotFoundException Se o Carregador não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Carregador update(Carregador carregador) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            carregador = this.dao.update(carregador, connection);
            connection.commit();
            return carregador;
        } catch (SQLException e) {
            connection.rollback();
            logger.warning("Erro ao atualizar o carregador: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Exclui um Carregador do banco de dados com base no ‘ID’ fornecido.
     *
     * @param id O ‘ID’ do Carregador a ser excluído.
     * @throws NotFoundException Se o Carregador não for encontrado.
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
            logger.warning("Erro ao excluir o carregador: " + e.getMessage());
            throw e;
        }
    }
}