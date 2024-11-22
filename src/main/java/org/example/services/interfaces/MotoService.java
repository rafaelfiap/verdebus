package org.example.services.interfaces;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.models.Moto;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Moto.
 * Define métodos para criar, atualizar, excluir e buscar motos.
 *
 * @since 1.0
 */
public interface MotoService {

    /**
     * Cria uma nova moto no sistema.
     *
     * @param moto A instância de Moto a ser criada.
     * @return A Moto criada com o ‘ID’ gerado.
     * @throws NotSavedException Se a moto não puder ser salva.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Moto create(Moto moto) throws UnsupportedServiceOperationException, SQLException, NotSavedException;

    /**
     * Retorna uma lista de todas as motos.
     *
     * @return Lista de todas as motos.
     * @throws SQLException Se ocorrer um erro ao buscar as motos.
     */
    List<Moto> findAll() throws SQLException;

    /**
     * Atualiza os dados de uma moto existente no sistema.
     *
     * @param moto A instância de Moto com os dados atualizados.
     * @return A Moto atualizada.
     * @throws NotFoundException Se a moto não for encontrada.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Moto update(Moto moto) throws NotFoundException, SQLException;

    /**
     * Exclui uma moto do sistema pelo seu ID.
     *
     * @param id O ID da moto a ser excluída.
     * @throws NotFoundException Se a moto não for encontrada.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws NotFoundException, SQLException;
}
