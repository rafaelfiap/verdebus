package org.example.services;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.EnderecoGaragemDaoFactory;
import org.example.daos.interfaces.EnderecoDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Endereco;
import org.example.services.interfaces.EnderecoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação do serviço para a entidade Endereco, utilizando EnderecoGaragemDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para endereços no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class EnderecoGaragemServiceImpl implements EnderecoService {

    private final EnderecoDao<Endereco, Long> dao = EnderecoGaragemDaoFactory.create(); // Instância de DAO para Endereco
    private final Logger logger = Logger.getLogger(this.getClass().getName()); // Logger para registrar mensagens e eventos

    /**
     * Cria um novo Endereco no banco de dados.
     *
     * @param endereco O objeto Endereco a ser criado.
     * @return O Endereco criado com o ID gerado.
     * @throws NotSavedException Se o Endereco não puder ser salvo.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Endereco create(Endereco endereco) throws UnsupportedServiceOperationException, SQLException, NotSavedException {
        if (endereco.getIdEndereco() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                endereco = this.dao.save(endereco, connection);
                connection.commit();
                return endereco;
            } catch (SQLException | NotSavedException e) {
                connection.rollback();
                logger.warning("Erro ao salvar o endereço: " + e.getMessage());
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException("Endereço já possui um ID e não pode ser criado.");
        }
    }

    /**
     * Retorna uma lista de todos os endereços.
     *
     * @return Lista de todos os endereços.
     */
    @Override
    public List<Endereco> findAll() throws SQLException {
        return this.dao.findAll();
    }

    /**
     * Atualiza um Endereco existente no banco de dados.
     *
     * @param endereco O objeto Endereco com os dados atualizados.
     * @return O Endereco atualizado.
     * @throws NotFoundException Se o Endereco não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Endereco update(Endereco endereco) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            endereco = this.dao.update(endereco, connection);
            connection.commit();
            return endereco;
        } catch (SQLException e) {
            connection.rollback();
            logger.warning("Erro ao atualizar o endereço: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Exclui um Endereco do banco de dados com base no ‘ID’ fornecido.
     *
     * @param id O ‘ID’ do Endereco a ser excluído.
     * @throws NotFoundException Se o Endereco não for encontrado.
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
            logger.warning("Erro ao excluir o endereço: " + e.getMessage());
            throw e;
        }
    }
}
