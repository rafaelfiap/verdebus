package org.example.services;

import org.example.services.interfaces.ConsumoService;

/**
 * Factory para criar instâncias de ConsumoService.
 *
 * <p>Essa factory cria instâncias da implementação {@link ConsumoServiceImpl}
 * e retorna a interface {@link ConsumoService} para operações de serviço em Consumo.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class ConsumoServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link ConsumoServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private ConsumoServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link ConsumoServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link ConsumoServiceImpl}
     * para operações de serviço de Consumo no sistema.</p>
     *
     * @return Uma implementação de {@link ConsumoService} para Consumo.
     * @since 1.0
     */
    public static ConsumoService create() {
        return new ConsumoServiceImpl();
    }
}
