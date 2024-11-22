package org.example.services;

import org.example.services.interfaces.EnderecoService;

/**
 * Factory para criar instâncias de EnderecoService.
 *
 * <p>Essa factory cria instâncias da implementação {@link EnderecoLinhaServiceImpl}
 * e retorna a interface {@link EnderecoService} para operações de serviço em Endereco.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class EnderecoLinhaServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link EnderecoLinhaServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private EnderecoLinhaServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link EnderecoLinhaServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link EnderecoLinhaServiceImpl}
     * para operações de serviço de Endereco no sistema.</p>
     *
     * @return Uma implementação de {@link EnderecoService} para Endereco.
     * @since 1.0
     */
    public static EnderecoService create() {
        return new EnderecoLinhaServiceImpl();
    }
}
