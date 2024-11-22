package org.example.services;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.ConsumoDaoFactory;
import org.example.daos.interfaces.ConsumoDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Consumo;
import org.example.services.interfaces.ConsumoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação do serviço para a entidade Consumo, utilizando ConsumoDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para registros de consumo no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class ConsumoServiceImpl implements ConsumoService {

    private final ConsumoDao dao = ConsumoDaoFactory.create(); // Instância de DAO para Consumo
    private final Logger logger = Logger.getLogger(this.getClass().getName()); // Logger para registrar mensagens e eventos

    /**
     * Cria um novo registro de consumo no banco de dados.
     *
     * @param consumo O objeto Consumo a ser criado.
     * @return O Consumo criado com o ID gerado.
     * @throws NotSavedException Se o Consumo não puder ser salvo.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Consumo create(Consumo consumo) throws UnsupportedServiceOperationException, SQLException, NotSavedException {
        if (consumo.getIdConsumo() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                consumo = this.dao.save(consumo, connection);
                connection.commit();
                return consumo;
            } catch (SQLException | NotSavedException e) {
                connection.rollback();
                logger.warning("Erro ao salvar o consumo: " + e.getMessage());
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException("Consumo já possui um ID e não pode ser criado.");
        }
    }

    /**
     * Retorna uma lista de todos os registros de consumo.
     *
     * @return Lista de todos os registros de consumo.
     */
    @Override
    public List<Consumo> findAll() throws SQLException {
        return this.dao.findAll();
    }

    /**
     * Atualiza um registro de consumo existente no banco de dados.
     *
     * @param consumo O objeto Consumo com os dados atualizados.
     * @return O Consumo atualizado.
     * @throws NotFoundException Se o Consumo não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Consumo update(Consumo consumo) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            consumo = this.dao.update(consumo, connection);
            connection.commit();
            return consumo;
        } catch (SQLException e) {
            connection.rollback();
            logger.warning("Erro ao atualizar o consumo: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Exclui um registro de consumo do banco de dados com base no ‘ID’ fornecido.
     *
     * @param id O ‘ID’ do Consumo a ser excluído.
     * @throws NotFoundException Se o Consumo não for encontrado.
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
            logger.warning("Erro ao excluir o consumo: " + e.getMessage());
            throw e;
        }
    }
}
