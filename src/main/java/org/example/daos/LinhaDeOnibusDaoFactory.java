package org.example.daos;

import org.example.daos.interfaces.LinhaDeOnibusDao;

/**
 * Factory para criar instâncias de LinhaDeOnibusDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link LinhaDeOnibusDaoImpl}
 * e retorna a interface {@link LinhaDeOnibusDao} para operações de CRUD específicas de LinhaDeOnibus.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class LinhaDeOnibusDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link LinhaDeOnibusDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seus métodos estáticos, seguindo o padrão Singleton implícito.</p>
     */
    private LinhaDeOnibusDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link LinhaDeOnibusDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link LinhaDeOnibusDaoImpl}
     * para operações de persistência de LinhaDeOnibus no banco de dados.</p>
     *
     * @return Uma implementação de {@link LinhaDeOnibusDao} para LinhaDeOnibus.
     * @since 1.0
     */
    public static LinhaDeOnibusDao create() {
        return new LinhaDeOnibusDaoImpl();
    }
}
