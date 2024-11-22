package org.example.services.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Carro;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Carro.
 * Define métodos para criar, atualizar, excluir e buscar carros.
 *
 * @since 1.0
 */
public interface CarroService {

    /**
     * Cria um novo carro no sistema.
     *
     * @param carro A instância de Carro a ser criada.
     * @return O Carro criado com o ‘ID’ gerado.
     * @throws NotSavedException Se o carro não puder ser salvo.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Carro create(Carro carro) throws UnsupportedServiceOperationException, SQLException, NotSavedException;

    /**
     * Retorna uma lista de todos os carros.
     *
     * @return Lista de todos os carros.
     * @throws SQLException Se ocorrer um erro ao buscar os carros.
     */
    List<Carro> findAll() throws SQLException;

    /**
     * Atualiza os dados de um carro existente no sistema.
     *
     * @param carro A instância de Carro com os dados atualizados.
     * @return O Carro atualizado.
     * @throws NotFoundException Se o carro não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Carro update(Carro carro) throws NotFoundException, SQLException;

    /**
     * Exclui um carro do sistema pelo seu ID.
     *
     * @param id O ID do carro a ser excluído.
     * @throws NotFoundException Se o carro não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws NotFoundException, SQLException;
}
