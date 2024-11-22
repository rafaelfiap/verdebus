package org.example.daos.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.Garagem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Garagem.
 *
 * @version 1.0
 * @since 1.0
 */
public interface GaragemDao {

    /**
     * Busca todas as instâncias de Garagem no banco de dados.
     *
     * @return Lista de instâncias de {@link Garagem}.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<Garagem> findAll() throws SQLException;

    /**
     * Remove uma Garagem pelo ID no banco de dados.
     *
     * @param id O ID da Garagem a ser removida.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se a Garagem com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws NotFoundException, SQLException;

    /**
     * Salva uma nova Garagem no banco de dados.
     *
     * @param garagem A instância de Garagem a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Garagem} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se a Garagem não puder ser salva.
     */
    Garagem save(Garagem garagem, Connection connection) throws SQLException, NotSavedException;

    /**
     * Atualiza uma Garagem existente no banco de dados.
     *
     * @param garagem A instância de Garagem com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Garagem} atualizada.
     * @throws NotFoundException Se a Garagem com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    Garagem update(Garagem garagem, Connection connection) throws NotFoundException, SQLException;
}
