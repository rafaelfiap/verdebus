package org.example.services;

import org.example.services.interfaces.GaragemService;

/**
 * Factory para criar instâncias de GaragemService.
 *
 * <p>Essa factory cria instâncias da implementação {@link GaragemServiceImpl}
 * e retorna a interface {@link GaragemService} para operações de serviço em Garagem.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class GaragemServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link GaragemServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private GaragemServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link GaragemServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link GaragemServiceImpl}
     * para operações de serviço de Garagem no sistema.</p>
     *
     * @return Uma implementação de {@link GaragemService} para Garagem.
     * @since 1.0
     */
    public static GaragemService create() {
        return new GaragemServiceImpl();
    }
}
