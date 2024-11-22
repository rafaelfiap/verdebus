package org.example.services.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Carregador;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Carregador.
 * Define métodos para criar, atualizar, excluir e buscar carregadores.
 *
 * @since 1.0
 */
public interface CarregadorService {

    /**
     * Cria um novo carregador no sistema.
     *
     * @param carregador A instância de Carregador a ser criada.
     * @return O Carregador criado com o ‘ID’ gerado.
     * @throws NotSavedException Se o carregador não puder ser salvo.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Carregador create(Carregador carregador) throws UnsupportedServiceOperationException, SQLException, NotSavedException;

    /**
     * Retorna uma lista de todos os carregadores.
     *
     * @return Lista de todos os carregadores.
     */
    List<Carregador> findAll() throws SQLException;

    /**
     * Atualiza os dados de um carregador existente no sistema.
     *
     * @param carregador A instância de Carregador com os dados atualizados.
     * @return O Carregador atualizado.
     * @throws NotFoundException Se o carregador não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Carregador update(Carregador carregador) throws NotFoundException, SQLException;

    /**
     * Exclui um carregador do sistema pelo seu ID.
     *
     * @param id O ID do carregador a ser excluído.
     * @throws NotFoundException Se o carregador não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws NotFoundException, SQLException;
}
