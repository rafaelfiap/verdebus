package org.example.daos.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.PontoRecarga;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade PontoRecarga.
 *
 * @version 1.0
 * @since 1.0
 */
public interface PontoRecargaDao {

    /**
     * Busca todas as instâncias de PontoRecarga no banco de dados.
     *
     * @return Lista de instâncias de {@link PontoRecarga}.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<PontoRecarga> findAll() throws SQLException;

    /**
     * Remove um PontoRecarga pelo ID no banco de dados.
     *
     * @param id O ID do PontoRecarga a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se o PontoRecarga com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws NotFoundException, SQLException;

    /**
     * Salva um novo PontoRecarga no banco de dados.
     *
     * @param pontoRecarga A instância de PontoRecarga a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link PontoRecarga} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se o PontoRecarga não puder ser salvo.
     */
    PontoRecarga save(PontoRecarga pontoRecarga, Connection connection) throws SQLException, NotSavedException;

    /**
     * Atualiza um PontoRecarga existente no banco de dados.
     *
     * @param pontoRecarga A instância de PontoRecarga com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link PontoRecarga} atualizada.
     * @throws NotFoundException Se o PontoRecarga com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    PontoRecarga update(PontoRecarga pontoRecarga, Connection connection) throws NotFoundException, SQLException;
}
