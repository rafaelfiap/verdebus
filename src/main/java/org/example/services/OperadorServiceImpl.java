package org.example.services;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.OperadorDaoFactory;
import org.example.daos.interfaces.OperadorDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Operador;
import org.example.services.interfaces.OperadorService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação do serviço para a entidade Operador, utilizando OperadorDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para operadores no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class OperadorServiceImpl implements OperadorService {

    private final OperadorDao dao = OperadorDaoFactory.create(); // Instância de DAO para Operador
    private final Logger logger = Logger.getLogger(this.getClass().getName()); // Logger para registrar mensagens e eventos

    /**
     * Cria um novo Operador no banco de dados.
     *
     * @param operador O objeto Operador a ser criado.
     * @return O Operador criado com o ID gerado.
     * @throws NotSavedException Se o Operador não puder ser salvo.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Operador create(Operador operador) throws UnsupportedServiceOperationException, SQLException, NotSavedException {
        if (operador.getIdOperador() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                operador = this.dao.save(operador, connection);
                connection.commit();
                return operador;
            } catch (SQLException | NotSavedException e) {
                connection.rollback();
                logger.warning("Erro ao salvar o operador: " + e.getMessage());
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException("Operador já possui um ID e não pode ser criado.");
        }
    }

    /**
     * Retorna uma lista de todos os operadores.
     *
     * @return Lista de todos os operadores.
     */
    @Override
    public List<Operador> findAll() throws SQLException {
        return this.dao.findAll();
    }

    /**
     * Atualiza um Operador existente no banco de dados.
     *
     * @param operador O objeto Operador com os dados atualizados.
     * @return O Operador atualizado.
     * @throws NotFoundException Se o Operador não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Operador update(Operador operador) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            operador = this.dao.update(operador, connection);
            connection.commit();
            return operador;
        } catch (SQLException e) {
            connection.rollback();
            logger.warning("Erro ao atualizar o operador: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Exclui um Operador do banco de dados com base no ‘ID’ fornecido.
     *
     * @param id O ‘ID’ do Operador a ser excluído.
     * @throws NotFoundException Se o Operador não for encontrado.
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
            logger.warning("Erro ao excluir o operador: " + e.getMessage());
            throw e;
        }
    }
}
