package org.example.daos;

import org.example.daos.interfaces.PontoRecargaDao;

/**
 * Factory para criar instâncias de PontoRecargaDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link PontoRecargaDaoImpl}
 * e retorna a interface {@link PontoRecargaDao} para operações de CRUD específicas de PontoRecarga.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class PontoRecargaDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link PontoRecargaDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seus métodos estáticos, seguindo o padrão Singleton implícito.</p>
     */
    private PontoRecargaDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link PontoRecargaDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link PontoRecargaDaoImpl}
     * para operações de persistência de PontoRecarga no banco de dados.</p>
     *
     * @return Uma implementação de {@link PontoRecargaDao} para PontoRecarga.
     * @since 1.0
     */
    public static PontoRecargaDao create() {
        return new PontoRecargaDaoImpl();
    }
}
