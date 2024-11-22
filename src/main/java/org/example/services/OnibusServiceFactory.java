package org.example.services;

import org.example.services.interfaces.OnibusService;

/**
 * Factory para criar instâncias de OnibusService.
 *
 * <p>Essa factory cria instâncias da implementação {@link OnibusServiceImpl}
 * e retorna a interface {@link OnibusService} para operações de serviço em Onibus.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class OnibusServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link OnibusServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private OnibusServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link OnibusServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link OnibusServiceImpl}
     * para operações de serviço de Onibus no sistema.</p>
     *
     * @return Uma implementação de {@link OnibusService} para Onibus.
     * @since 1.0
     */
    public static OnibusService create() {
        return new OnibusServiceImpl();
    }
}
