package org.example.daos;

import org.example.daos.interfaces.GaragemDao;

/**
 * Factory para criar instâncias de GaragemDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link GaragemDaoImpl}
 * e retorna a interface {@link GaragemDao} para operações de CRUD específicas de Garagem.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class GaragemDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link GaragemDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seus métodos estáticos, seguindo o padrão Singleton implícito.</p>
     */
    private GaragemDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link GaragemDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link GaragemDaoImpl}
     * para operações de persistência de Garagem no banco de dados.</p>
     *
     * @return Uma implementação de {@link GaragemDao} para Garagem.
     * @since 1.0
     */
    public static GaragemDao create() {
        return new GaragemDaoImpl();
    }
}
