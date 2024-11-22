package org.example.daos;

import org.example.daos.interfaces.ConsumoDao;

/**
 * Factory para criar instâncias de ConsumoDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link ConsumoDaoImpl}
 * e retorna a interface {@link ConsumoDao} para operações de CRUD específicas de Consumo.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class ConsumoDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link ConsumoDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seus métodos estáticos, seguindo o padrão Singleton implícito.</p>
     */
    private ConsumoDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link ConsumoDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link ConsumoDaoImpl}
     * para operações de persistência de Consumo no banco de dados.</p>
     *
     * @return Uma implementação de {@link ConsumoDao} para Consumo.
     * @since 1.0
     */
    public static ConsumoDao create() {
        return new ConsumoDaoImpl();
    }
}
