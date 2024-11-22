package org.example.dtos;

/**
 * DTO para transferência de dados da entidade Garagem.
 *
 * <p>A classe `GaragemDto` é usada para transferir dados de garagens entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID,
 * nome, endereço e capacidade máxima de ônibus que a garagem pode acomodar.</p>
 *
 * @version 1.1
 * @since 1.0
 */
public class GaragemDto {

    // Identificador único da garagem.
    private Long idGaragem;

    // Nome da garagem.
    private String nome;

    // Endereço completo da garagem.
    private EnderecoDto endereco;

    // Capacidade máxima de ônibus que a garagem pode acomodar.
    private Integer capacidadeOnibus;

    // Getters e Setters

    /**
     * Obtém o identificador único da garagem.
     *
     * @return O ID da garagem.
     * @since 1.0
     */
    public Long getIdGaragem() {
        return idGaragem;
    }

    /**
     * Define o identificador único da garagem.
     *
     * @param idGaragem O novo ID da garagem.
     * @since 1.0
     */
    public void setIdGaragem(Long idGaragem) {
        this.idGaragem = idGaragem;
    }

    /**
     * Obtém o nome da garagem.
     *
     * @return O nome da garagem.
     * @since 1.1
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da garagem.
     *
     * @param nome O novo nome da garagem.
     * @since 1.1
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o endereço completo da garagem.
     *
     * @return O endereço da garagem.
     * @since 1.0
     */
    public EnderecoDto getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço completo da garagem.
     *
     * @param endereco O novo endereço da garagem.
     * @since 1.0
     */
    public void setEndereco(EnderecoDto endereco) {
        this.endereco = endereco;
    }

    /**
     * Obtém a capacidade máxima de ônibus que a garagem pode acomodar.
     *
     * @return A capacidade máxima de ônibus.
     * @since 1.0
     */
    public Integer getCapacidadeOnibus() {
        return capacidadeOnibus;
    }

    /**
     * Define a capacidade máxima de ônibus que a garagem pode acomodar.
     *
     * @param capacidadeOnibus A nova capacidade máxima de ônibus.
     * @since 1.0
     */
    public void setCapacidadeOnibus(Integer capacidadeOnibus) {
        this.capacidadeOnibus = capacidadeOnibus;
    }

    /**
     * Retorna uma representação textual do objeto GaragemDto.
     *
     * @return Uma string com os detalhes do GaragemDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "GaragemDto{" +
                "idGaragem=" + idGaragem +
                ", nome='" + nome + '\'' +
                ", endereco=" + (endereco != null ? endereco.toString() : "Nenhum") +
                ", capacidadeOnibus=" + capacidadeOnibus +
                '}';
    }
}
