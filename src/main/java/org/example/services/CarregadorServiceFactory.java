package org.example.services;

import org.example.services.interfaces.CarregadorService;

/**
 * Factory para criar instâncias de CarregadorService.
 *
 * <p>Essa factory cria instâncias da implementação {@link CarregadorServiceImpl}
 * e retorna a interface {@link CarregadorService} para operações de serviço em Carregador.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class CarregadorServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link CarregadorServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private CarregadorServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link CarregadorServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link CarregadorServiceImpl}
     * para operações de serviço de Carregador no sistema.</p>
     *
     * @return Uma implementação de {@link CarregadorService} para Carregador.
     * @since 1.0
     */
    public static CarregadorService create() {
        return new CarregadorServiceImpl();
    }
}
