package org.example.daos;

import org.example.daos.interfaces.CarregadorDao;

/**
 * Factory para criar instâncias de CarregadorDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link CarregadorDaoImpl}
 * e retorna a interface {@link CarregadorDao} para operações de CRUD específicas de Carregador.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class CarregadorDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link CarregadorDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seus métodos estáticos, seguindo o padrão Singleton implícito.</p>
     */
    private CarregadorDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link CarregadorDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link CarregadorDaoImpl}
     * para operações de persistência de Carregador no banco de dados.</p>
     *
     * @return Uma implementação de {@link CarregadorDao} para Carregador.
     * @since 1.0
     */
    public static CarregadorDao create() {
        return new CarregadorDaoImpl();
    }
}
