package org.example.dtos;

/**
 * DTO para transferência de dados da entidade Operador.
 *
 * <p>A classe `OperadorDto` é usada para transferir dados dos operadores entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID do operador,
 * nome, CPF e ID da garagem associada.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public class OperadorDto {

    // Identificador único do operador.
    private Long idOperador;

    // Nome completo do operador.
    private String nome;

    // CPF único do operador.
    private String cpf;

    // Identificador da garagem onde o operador está alocado.
    private Long idGaragem;

    // Getters e Setters

    /**
     * Obtém o identificador único do operador.
     *
     * @return O ID do operador.
     * @since 1.0
     */
    public Long getIdOperador() {
        return idOperador;
    }

    /**
     * Define o identificador único do operador.
     *
     * @param idOperador O novo ID do operador.
     * @since 1.0
     */
    public void setIdOperador(Long idOperador) {
        this.idOperador = idOperador;
    }

    /**
     * Obtém o nome completo do operador.
     *
     * @return O nome do operador.
     * @since 1.0
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome completo do operador.
     *
     * @param nome O novo nome do operador.
     * @since 1.0
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o CPF único do operador.
     *
     * @return O CPF do operador.
     * @since 1.0
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF único do operador.
     *
     * @param cpf O novo CPF do operador.
     * @since 1.0
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Obtém o identificador da garagem onde o operador está alocado.
     *
     * @return O ID da garagem associada.
     * @since 1.0
     */
    public Long getIdGaragem() {
        return idGaragem;
    }

    /**
     * Define o identificador da garagem onde o operador está alocado.
     *
     * @param idGaragem O novo ID da garagem associada.
     * @since 1.0
     */
    public void setIdGaragem(Long idGaragem) {
        this.idGaragem = idGaragem;
    }

    /**
     * Retorna uma representação textual do objeto OperadorDto.
     *
     * @return Uma string com os detalhes do OperadorDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "OperadorDto{" +
                "idOperador=" + idOperador +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", idGaragem=" + (idGaragem != null ? idGaragem : "Nenhuma") +
                '}';
    }
}
