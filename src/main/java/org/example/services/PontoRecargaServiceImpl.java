package org.example.services;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.PontoRecargaDaoFactory;
import org.example.daos.interfaces.PontoRecargaDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.PontoRecarga;
import org.example.services.interfaces.PontoRecargaService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação do serviço para a entidade Ponto de Recarga, utilizando PontoRecargaDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para pontos de recarga no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class PontoRecargaServiceImpl implements PontoRecargaService {

    private final PontoRecargaDao dao = PontoRecargaDaoFactory.create();
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Cria um novo Ponto de Recarga no banco de dados.
     *
     * @param pontoRecarga O objeto Ponto de Recarga a ser criado.
     * @return O Ponto de Recarga criado com o ID gerado.
     * @throws NotSavedException Se o Ponto de Recarga não puder ser salvo.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public PontoRecarga create(PontoRecarga pontoRecarga) throws UnsupportedServiceOperationException, SQLException, NotSavedException {
        if (pontoRecarga.getIdPonto() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                pontoRecarga = this.dao.save(pontoRecarga, connection);
                connection.commit();
                return pontoRecarga;
            } catch (SQLException | NotSavedException e) {
                connection.rollback();
                logger.warning("Erro ao salvar o ponto de recarga: " + e.getMessage());
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException("Ponto de Recarga já possui um ID e não pode ser criado.");
        }
    }

    /**
     * Retorna uma lista de todos os pontos de recarga.
     *
     * @return Lista de todos os pontos de recarga.
     */
    @Override
    public List<PontoRecarga> findAll() throws SQLException {
        return this.dao.findAll();
    }

    /**
     * Atualiza um Ponto de Recarga existente no banco de dados.
     *
     * @param pontoRecarga O objeto Ponto de Recarga com os dados atualizados.
     * @return O Ponto de Recarga atualizado.
     * @throws NotFoundException Se o Ponto de Recarga não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public PontoRecarga update(PontoRecarga pontoRecarga) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            pontoRecarga = this.dao.update(pontoRecarga, connection);
            connection.commit();
            return pontoRecarga;
        } catch (SQLException e) {
            connection.rollback();
            logger.warning("Erro ao atualizar o ponto de recarga: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Exclui um Ponto de Recarga do banco de dados com base no ‘ID’ fornecido.
     *
     * @param id O ‘ID’ do Ponto de Recarga a ser excluído.
     * @throws NotFoundException Se o Ponto de Recarga não for encontrado.
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
            logger.warning("Erro ao excluir o ponto de recarga: " + e.getMessage());
            throw e;
        }
    }
}
