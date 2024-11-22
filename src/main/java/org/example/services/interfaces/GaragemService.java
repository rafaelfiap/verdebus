package org.example.services.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Garagem;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Garagem.
 * Define métodos para criar, atualizar, excluir e buscar garagens.
 *
 * @since 1.0
 */
public interface GaragemService {

    /**
     * Cria uma nova garagem no sistema.
     *
     * @param garagem A instância de Garagem a ser criada.
     * @return A Garagem criada com o ‘ID’ gerado.
     * @throws NotSavedException Se a garagem não puder ser salva.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Garagem create(Garagem garagem) throws UnsupportedServiceOperationException, SQLException, NotSavedException;

    /**
     * Retorna uma lista de todas as garagens.
     *
     * @return Lista de todas as garagens.
     * @throws SQLException Se ocorrer um erro ao buscar as garagens.
     */
    List<Garagem> findAll() throws SQLException;

    /**
     * Atualiza os dados de uma garagem existente no sistema.
     *
     * @param garagem A instância de Garagem com os dados atualizados.
     * @return A Garagem atualizada.
     * @throws NotFoundException Se a garagem não for encontrada.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Garagem update(Garagem garagem) throws NotFoundException, SQLException;

    /**
     * Exclui uma garagem do sistema pelo seu ID.
     *
     * @param id O ID da garagem a ser excluída.
     * @throws NotFoundException Se a garagem não for encontrada.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws NotFoundException, SQLException;
}
