package org.example.services;

import org.example.services.interfaces.CarroService;

/**
 * Factory para criar instâncias de CarroService.
 *
 * <p>Essa factory cria instâncias da implementação {@link CarroServiceImpl}
 * e retorna a interface {@link CarroService} para operações de serviço em Carro.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class CarroServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link CarroServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private CarroServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link CarroServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link CarroServiceImpl}
     * para operações de serviço de Carro no sistema.</p>
     *
     * @return Uma implementação de {@link CarroService} para Carro.
     * @since 1.0
     */
    public static CarroService create() {
        return new CarroServiceImpl();
    }
}
