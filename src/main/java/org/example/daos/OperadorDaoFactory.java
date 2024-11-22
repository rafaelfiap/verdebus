package org.example.daos;

import org.example.daos.interfaces.OperadorDao;

/**
 * Factory para criar instâncias de OperadorDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link OperadorDaoImpl}
 * e retorna a interface {@link OperadorDao} para operações de CRUD específicas de Operador.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class OperadorDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link OperadorDaoFactory}.
     */
    private OperadorDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link OperadorDaoImpl}.
     *
     * @return Uma implementação de {@link OperadorDao} para Operador.
     * @since 1.0
     */
    public static OperadorDao create() {
        return new OperadorDaoImpl();
    }
}
