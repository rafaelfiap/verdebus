package org.example.services.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.PontoRecarga;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade PontoRecarga.
 * Define métodos para criar, atualizar, excluir e buscar pontos de recarga.
 *
 * @since 1.0
 */
public interface PontoRecargaService {

    /**
     * Cria um novo ponto de recarga no sistema.
     *
     * @param pontoRecarga A instância de PontoRecarga a ser criada.
     * @return O PontoRecarga criado com o ‘ID’ gerado.
     * @throws NotSavedException Se o ponto de recarga não puder ser salvo.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    PontoRecarga create(PontoRecarga pontoRecarga) throws UnsupportedServiceOperationException, SQLException, NotSavedException;

    /**
     * Retorna uma lista de todos os pontos de recarga.
     *
     * @return Lista de todos os pontos de recarga.
     * @throws SQLException Se ocorrer um erro ao buscar os pontos de recarga.
     */
    List<PontoRecarga> findAll() throws SQLException;

    /**
     * Atualiza os dados de um ponto de recarga existente no sistema.
     *
     * @param pontoRecarga A instância de PontoRecarga com os dados atualizados.
     * @return O PontoRecarga atualizado.
     * @throws NotFoundException Se o ponto de recarga não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    PontoRecarga update(PontoRecarga pontoRecarga) throws NotFoundException, SQLException;

    /**
     * Exclui um ponto de recarga do sistema pelo seu ID.
     *
     * @param id O ID do ponto de recarga a ser excluído.
     * @throws NotFoundException Se o ponto de recarga não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws NotFoundException, SQLException;
}
