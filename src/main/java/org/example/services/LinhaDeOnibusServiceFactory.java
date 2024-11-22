package org.example.services;

import org.example.services.interfaces.LinhaDeOnibusService;

/**
 * Factory para criar instâncias de LinhaDeOnibusService.
 *
 * <p>Essa factory cria instâncias da implementação {@link LinhaDeOnibusServiceImpl}
 * e retorna a interface {@link LinhaDeOnibusService} para operações de serviço em LinhaDeOnibus.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class LinhaDeOnibusServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link LinhaDeOnibusServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private LinhaDeOnibusServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link LinhaDeOnibusServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link LinhaDeOnibusServiceImpl}
     * para operações de serviço de LinhaDeOnibus no sistema.</p>
     *
     * @return Uma implementação de {@link LinhaDeOnibusService} para LinhaDeOnibus.
     * @since 1.0
     */
    public static LinhaDeOnibusService create() {
        return new LinhaDeOnibusServiceImpl();
    }
}
