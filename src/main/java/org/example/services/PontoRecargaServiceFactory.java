package org.example.services;

import org.example.services.interfaces.PontoRecargaService;

/**
 * Factory para criar instâncias de PontoRecargaService.
 *
 * <p>Essa factory cria instâncias da implementação {@link PontoRecargaServiceImpl}
 * e retorna a interface {@link PontoRecargaService} para operações de serviço em PontoRecarga.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class PontoRecargaServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link PontoRecargaServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private PontoRecargaServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link PontoRecargaServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link PontoRecargaServiceImpl}
     * para operações de serviço de PontoRecarga no sistema.</p>
     *
     * @return Uma implementação de {@link PontoRecargaService} para PontoRecarga.
     * @since 1.0
     */
    public static PontoRecargaService create() {
        return new PontoRecargaServiceImpl();
    }
}
