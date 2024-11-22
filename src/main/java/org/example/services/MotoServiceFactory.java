package org.example.services;

import org.example.services.interfaces.MotoService;

/**
 * Factory para criar instâncias de MotoService.
 *
 * <p>Essa factory cria instâncias da implementação {@link MotoServiceImpl}
 * e retorna a interface {@link MotoService} para operações de serviço em Moto.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class MotoServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link MotoServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private MotoServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link MotoServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link MotoServiceImpl}
     * para operações de serviço de Moto no sistema.</p>
     *
     * @return Uma implementação de {@link MotoService} para Moto.
     * @since 1.0
     */
    public static MotoService create() {
        return new MotoServiceImpl();
    }
}
