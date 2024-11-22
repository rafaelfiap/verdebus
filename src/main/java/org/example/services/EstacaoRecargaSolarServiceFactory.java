package org.example.services;

import org.example.services.interfaces.EstacaoRecargaSolarService;

/**
 * Factory para criar instâncias de EstacaoRecargaSolarService.
 *
 * <p>Essa factory cria instâncias da implementação {@link EstacaoRecargaSolarServiceImpl}
 * e retorna a interface {@link EstacaoRecargaSolarService} para operações de serviço em EstacaoRecargaSolar.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class EstacaoRecargaSolarServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link EstacaoRecargaSolarServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private EstacaoRecargaSolarServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link EstacaoRecargaSolarServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link EstacaoRecargaSolarServiceImpl}
     * para operações de serviço de EstacaoRecargaSolar no sistema.</p>
     *
     * @return Uma implementação de {@link EstacaoRecargaSolarService} para EstacaoRecargaSolar.
     * @since 1.0
     */
    public static EstacaoRecargaSolarService create() {
        return new EstacaoRecargaSolarServiceImpl();
    }
}
