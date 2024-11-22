package org.example.services.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Operador;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Operador.
 * Define métodos para criar, atualizar, excluir e buscar operadores.
 *
 * @since 1.0
 */
public interface OperadorService {

    /**
     * Cria um novo operador no sistema.
     *
     * @param operador A instância de Operador a ser criada.
     * @return O Operador criado com o ‘ID’ gerado.
     * @throws NotSavedException Se o operador não puder ser salvo.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Operador create(Operador operador) throws UnsupportedServiceOperationException, SQLException, NotSavedException;

    /**
     * Retorna uma lista de todos os operadores.
     *
     * @return Lista de todos os operadores.
     * @throws SQLException Se ocorrer um erro ao buscar os operadores.
     */
    List<Operador> findAll() throws SQLException;

    /**
     * Atualiza os dados de um operador existente no sistema.
     *
     * @param operador A instância de Operador com os dados atualizados.
     * @return O Operador atualizado.
     * @throws NotFoundException Se o operador não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Operador update(Operador operador) throws NotFoundException, SQLException;

    /**
     * Exclui um operador do sistema pelo seu ID.
     *
     * @param id O ID do operador a ser excluído.
     * @throws NotFoundException Se o operador não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws NotFoundException, SQLException;
}
