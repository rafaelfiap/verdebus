package org.example.services.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Consumo;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Consumo.
 * Define métodos para criar, atualizar, excluir e buscar consumos.
 *
 * @since 1.0
 */
public interface ConsumoService {

    /**
     * Cria um novo registro de consumo no sistema.
     *
     * @param consumo A instância de Consumo a ser criada.
     * @return O Consumo criado com o ‘ID’ gerado.
     * @throws NotSavedException Se o registro de consumo não puder ser salvo.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Consumo create(Consumo consumo) throws UnsupportedServiceOperationException, SQLException, NotSavedException;

    /**
     * Retorna uma lista de todos os registros de consumo.
     *
     * @return Lista de todos os registros de consumo.
     * @throws SQLException Se ocorrer um erro ao buscar os registros de consumo.
     */
    List<Consumo> findAll() throws SQLException;

    /**
     * Atualiza os dados de um registro de consumo existente no sistema.
     *
     * @param consumo A instância de Consumo com os dados atualizados.
     * @return O Consumo atualizado.
     * @throws NotFoundException Se o registro de consumo não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Consumo update(Consumo consumo) throws NotFoundException, SQLException;

    /**
     * Exclui um registro de consumo do sistema pelo seu ID.
     *
     * @param id O ID do registro de consumo a ser excluído.
     * @throws NotFoundException Se o registro de consumo não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws NotFoundException, SQLException;
}
