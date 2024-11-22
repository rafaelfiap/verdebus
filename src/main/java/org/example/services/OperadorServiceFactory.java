package org.example.services;

import org.example.services.interfaces.OperadorService;

/**
 * Factory para criar instâncias de OperadorService.
 *
 * <p>Essa factory cria instâncias da implementação {@link OperadorServiceImpl}
 * e retorna a interface {@link OperadorService} para operações de serviço em Operador.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class OperadorServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link OperadorServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private OperadorServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link OperadorServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link OperadorServiceImpl}
     * para operações de serviço de Operador no sistema.</p>
     *
     * @return Uma implementação de {@link OperadorService} para Operador.
     * @since 1.0
     */
    public static OperadorService create() {
        return new OperadorServiceImpl();
    }
}
