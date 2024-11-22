package org.example.daos;

import org.example.daos.interfaces.OnibusDao;

/**
 * Factory para criar instâncias de OnibusDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link OnibusDaoImpl}
 * e retorna a interface {@link OnibusDao} para operações de CRUD específicas de Ônibus.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class OnibusDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link OnibusDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seus métodos estáticos, seguindo o padrão Singleton implícito.</p>
     */
    private OnibusDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link OnibusDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link OnibusDaoImpl}
     * para operações de persistência de Ônibus no banco de dados.</p>
     *
     * @return Uma implementação de {@link OnibusDao} para Ônibus.
     * @since 1.0
     */
    public static OnibusDao create() {
        return new OnibusDaoImpl();
    }
}
