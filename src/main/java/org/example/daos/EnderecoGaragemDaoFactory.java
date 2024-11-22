package org.example.daos;

import org.example.daos.interfaces.EnderecoDao;
import org.example.models.Endereco;

/**
 * Factory para criar instâncias de EnderecoGaragemDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link EnderecoGaragemDaoImpl}
 * e retorna a interface {@link EnderecoDao} para operações de CRUD específicas de EnderecoGaragem.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class EnderecoGaragemDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link EnderecoGaragemDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seus métodos estáticos, seguindo o padrão Singleton implícito.</p>
     */
    private EnderecoGaragemDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link EnderecoGaragemDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link EnderecoGaragemDaoImpl}
     * para operações de persistência de EnderecoGaragem no banco de dados.</p>
     *
     * @return Uma implementação de {@link EnderecoDao} para EnderecoGaragem.
     * @since 1.0
     */
    public static EnderecoDao<Endereco, Long> create() {
        return new EnderecoGaragemDaoImpl();
    }
}
