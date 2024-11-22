package org.example.services.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.EstacaoRecargaSolar;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade EstacaoRecargaSolar.
 * Define métodos para criar, atualizar, excluir e buscar estações de recarga solar.
 *
 * @since 1.0
 */
public interface EstacaoRecargaSolarService {

    /**
     * Cria uma nova estação de recarga solar no sistema.
     *
     * @param estacao A instância de EstacaoRecargaSolar a ser criada.
     * @return A EstacaoRecargaSolar criada com o ‘ID’ gerado.
     * @throws NotSavedException Se a estação não puder ser salva.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    EstacaoRecargaSolar create(EstacaoRecargaSolar estacao) throws UnsupportedServiceOperationException, SQLException, NotSavedException;

    /**
     * Retorna uma lista de todas as estações de recarga solar.
     *
     * @return Lista de todas as estações de recarga solar.
     * @throws SQLException Se ocorrer um erro ao buscar as estações.
     */
    List<EstacaoRecargaSolar> findAll() throws SQLException;

    /**
     * Atualiza os dados de uma estação de recarga solar existente no sistema.
     *
     * @param estacao A instância de EstacaoRecargaSolar com os dados atualizados.
     * @return A EstacaoRecargaSolar atualizada.
     * @throws NotFoundException Se a estação não for encontrada.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    EstacaoRecargaSolar update(EstacaoRecargaSolar estacao) throws NotFoundException, SQLException;

    /**
     * Exclui uma estação de recarga solar do sistema pelo seu ID.
     *
     * @param id O ID da estação a ser excluída.
     * @throws NotFoundException Se a estação não for encontrada.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws NotFoundException, SQLException;
}
