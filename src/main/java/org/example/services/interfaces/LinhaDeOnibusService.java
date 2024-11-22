package org.example.services.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.LinhaDeOnibus;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade LinhaDeOnibus.
 * Define métodos para criar, atualizar, excluir e buscar linhas de ônibus.
 *
 * @since 1.0
 */
public interface LinhaDeOnibusService {

    /**
     * Cria uma nova linha de ônibus no sistema.
     *
     * @param linhaDeOnibus A instância de LinhaDeOnibus a ser criada.
     * @return A LinhaDeOnibus criada com o ‘ID’ gerado.
     * @throws NotSavedException Se a linha de ônibus não puder ser salva.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    LinhaDeOnibus create(LinhaDeOnibus linhaDeOnibus) throws UnsupportedServiceOperationException, SQLException, NotSavedException;

    /**
     * Retorna uma lista de todas as linhas de ônibus.
     *
     * @return Lista de todas as linhas de ônibus.
     * @throws SQLException Se ocorrer um erro ao buscar as linhas de ônibus.
     */
    List<LinhaDeOnibus> findAll() throws SQLException;

    /**
     * Atualiza os dados de uma linha de ônibus existente no sistema.
     *
     * @param linhaDeOnibus A instância de LinhaDeOnibus com os dados atualizados.
     * @return A LinhaDeOnibus atualizada.
     * @throws NotFoundException Se a linha de ônibus não for encontrada.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    LinhaDeOnibus update(LinhaDeOnibus linhaDeOnibus) throws NotFoundException, SQLException;

    /**
     * Exclui uma linha de ônibus do sistema pelo seu ID.
     *
     * @param id O ID da linha de ônibus a ser excluída.
     * @throws NotFoundException Se a linha de ônibus não for encontrada.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws NotFoundException, SQLException;
}
