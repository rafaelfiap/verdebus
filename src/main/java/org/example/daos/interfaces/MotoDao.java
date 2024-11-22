package org.example.daos.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.Moto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Moto.
 *
 * @version 1.0
 * @since 1.0
 */
public interface MotoDao {

    /**
     * Busca todas as instâncias de Moto no banco de dados.
     *
     * @return Lista de instâncias de {@link Moto}.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<Moto> findAll() throws SQLException;

    /**
     * Remove uma Moto pelo ID no banco de dados.
     *
     * @param id O ID da Moto a ser removida.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se a Moto com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws NotFoundException, SQLException;

    /**
     * Salva uma nova Moto no banco de dados.
     *
     * @param moto A instância de Moto a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Moto} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se a Moto não puder ser salva.
     */
    Moto save(Moto moto, Connection connection) throws SQLException, NotSavedException;

    /**
     * Atualiza uma Moto existente no banco de dados.
     *
     * @param moto A instância de Moto com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Moto} atualizada.
     * @throws NotFoundException Se a Moto com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    Moto update(Moto moto, Connection connection) throws NotFoundException, SQLException;
}
