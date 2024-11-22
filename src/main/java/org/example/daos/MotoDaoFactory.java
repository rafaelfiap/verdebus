package org.example.daos;

import org.example.daos.interfaces.MotoDao;

/**
 * Factory para criar instâncias de MotoDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link MotoDaoImpl}
 * e retorna a interface {@link MotoDao} para operações de CRUD específicas de Moto.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class MotoDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link MotoDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seus métodos estáticos, seguindo o padrão Singleton implícito.</p>
     */
    private MotoDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link MotoDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link MotoDaoImpl}
     * para operações de persistência de Moto no banco de dados.</p>
     *
     * @return Uma implementação de {@link MotoDao} para Moto.
     * @since 1.0
     */
    public static MotoDao create() {
        return new MotoDaoImpl();
    }
}
