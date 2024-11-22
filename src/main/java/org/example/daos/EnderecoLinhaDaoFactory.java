package org.example.daos;

import org.example.daos.interfaces.EnderecoDao;
import org.example.models.Endereco;

/**
 * Factory para criar instâncias de EnderecoDao para a entidade Endereco associada a Linhas de ônibus.
 *
 * <p>Essa factory cria instâncias da implementação {@link EnderecoLinhaDaoImpl}
 * e retorna a interface {@link EnderecoDao} para operações de CRUD específicas de Endereços associados a Linhas.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class EnderecoLinhaDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link EnderecoLinhaDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seus métodos estáticos, seguindo o padrão Singleton implícito.</p>
     */
    private EnderecoLinhaDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link EnderecoLinhaDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link EnderecoLinhaDaoImpl}
     * para operações de persistência de Endereços associados a Linhas de ônibus no banco de dados.</p>
     *
     * @return Uma implementação de {@link EnderecoDao} para Endereços de Linhas de ônibus.
     * @since 1.0
     */
    public static EnderecoDao<Endereco, Long> create() {
        return new EnderecoLinhaDaoImpl();
    }
}
