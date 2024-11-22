package org.example.services.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Onibus;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Ônibus.
 * Define métodos para criar, atualizar, excluir e buscar ônibus.
 *
 * @since 1.0
 */
public interface OnibusService {

    /**
     * Cria um novo ônibus no sistema.
     *
     * @param onibus A instância de Ônibus a ser criada.
     * @return O Ônibus criado com o ‘ID’ gerado.
     * @throws NotSavedException Se o ônibus não puder ser salvo.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Onibus create(Onibus onibus) throws UnsupportedServiceOperationException, SQLException, NotSavedException;

    /**
     * Retorna uma lista de todos os ônibus.
     *
     * @return Lista de todos os ônibus.
     * @throws SQLException Se ocorrer um erro ao buscar os ônibus.
     */
    List<Onibus> findAll() throws SQLException;

    /**
     * Atualiza os dados de um ônibus existente no sistema.
     *
     * @param onibus A instância de Ônibus com os dados atualizados.
     * @return O Ônibus atualizado.
     * @throws NotFoundException Se o ônibus não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Onibus update(Onibus onibus) throws NotFoundException, SQLException;

    /**
     * Exclui um ônibus do sistema pelo seu ID.
     *
     * @param id O ID do ônibus a ser excluído.
     * @throws NotFoundException Se o ônibus não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws NotFoundException, SQLException;
}
