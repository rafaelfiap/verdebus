package org.example.daos;

import org.example.daos.interfaces.CarroDao;

/**
 * Factory para criar instâncias de CarroDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link CarroDaoImpl}
 * e retorna a interface {@link CarroDao} para operações de CRUD específicas de Carro.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class CarroDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link CarroDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seus métodos estáticos, seguindo o padrão Singleton implícito.</p>
     */
    private CarroDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link CarroDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link CarroDaoImpl}
     * para operações de persistência de Carro no banco de dados.</p>
     *
     * @return Uma implementação de {@link CarroDao} para Carro.
     * @since 1.0
     */
    public static CarroDao create() {
        return new CarroDaoImpl();
    }
}
