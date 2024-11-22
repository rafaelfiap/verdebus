package org.example.daos;

import org.example.daos.interfaces.EstacaoRecargaSolarDao;

/**
 * Factory para criar instâncias de EstacaoRecargaSolarDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link EstacaoRecargaSolarDaoImpl}
 * e retorna a interface {@link EstacaoRecargaSolarDao} para operações de CRUD específicas de EstacaoRecargaSolar.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class EstacaoRecargaSolarDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link EstacaoRecargaSolarDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seus métodos estáticos, seguindo o padrão Singleton implícito.</p>
     */
    private EstacaoRecargaSolarDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link EstacaoRecargaSolarDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link EstacaoRecargaSolarDaoImpl}
     * para operações de persistência de EstacaoRecargaSolar no banco de dados.</p>
     *
     * @return Uma implementação de {@link EstacaoRecargaSolarDao} para EstacaoRecargaSolar.
     * @since 1.0
     */
    public static EstacaoRecargaSolarDao create() {
        return new EstacaoRecargaSolarDaoImpl();
    }
}
