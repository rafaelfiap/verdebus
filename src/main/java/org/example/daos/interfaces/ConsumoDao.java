package org.example.daos.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.Consumo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Consumo.
 *
 * @version 1.0
 * @since 1.0
 */
public interface ConsumoDao {

    /**
     * Busca todas as instâncias de Consumo no banco de dados.
     *
     * @return Lista de instâncias de {@link Consumo}.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<Consumo> findAll() throws SQLException;


    /**
     * Remove um registro de Consumo pelo ID no banco de dados.
     *
     * @param id O ID do registro de Consumo a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se o registro de Consumo com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws NotFoundException, SQLException;

    /**
     * Salva um novo registro de Consumo no banco de dados.
     *
     * @param consumo A instância de Consumo a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Consumo} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se o registro de Consumo não puder ser salvo.
     */
    Consumo save(Consumo consumo, Connection connection) throws SQLException, NotSavedException;

    /**
     * Atualiza um registro de Consumo existente no banco de dados.
     *
     * @param consumo A instância de Consumo com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Consumo} atualizada.
     * @throws NotFoundException Se o registro de Consumo com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    Consumo update(Consumo consumo, Connection connection) throws NotFoundException, SQLException;
}
