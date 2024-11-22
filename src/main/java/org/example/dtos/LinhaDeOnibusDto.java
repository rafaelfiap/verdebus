package org.example.dtos;

/**
 * DTO para transferência de dados da entidade LinhaDeOnibus.
 *
 * <p>A classe `LinhaDeOnibusDto` é usada para transferir dados de linhas de ônibus entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID, código, nome
 * e o endereço associado à linha.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public class LinhaDeOnibusDto {

    // Identificador único da linha de ônibus.
    private Long idLinha;

    // Código da linha de ônibus (exemplo: "2709-10").
    private String codigoLinha;

    // Nome da linha de ônibus.
    private String nome;

    // Endereço associado à linha de ônibus.
    private EnderecoDto endereco;

    // Getters e Setters

    /**
     * Obtém o identificador único da linha de ônibus.
     *
     * @return O ID da linha de ônibus.
     * @since 1.0
     */
    public Long getIdLinha() {
        return idLinha;
    }

    /**
     * Define o identificador único da linha de ônibus.
     *
     * @param idLinha O novo ID da linha de ônibus.
     * @since 1.0
     */
    public void setIdLinha(Long idLinha) {
        this.idLinha = idLinha;
    }

    /**
     * Obtém o código da linha de ônibus.
     *
     * @return O código da linha de ônibus.
     * @since 1.0
     */
    public String getCodigoLinha() {
        return codigoLinha;
    }

    /**
     * Define o código da linha de ônibus.
     *
     * @param codigoLinha O novo código da linha de ônibus.
     * @since 1.0
     */
    public void setCodigoLinha(String codigoLinha) {
        this.codigoLinha = codigoLinha;
    }

    /**
     * Obtém o nome da linha de ônibus.
     *
     * @return O nome da linha de ônibus.
     * @since 1.0
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da linha de ônibus.
     *
     * @param nome O novo nome da linha de ônibus.
     * @since 1.0
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o endereço associado à linha de ônibus.
     *
     * @return O endereço da linha de ônibus.
     * @since 1.0
     */
    public EnderecoDto getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço associado à linha de ônibus.
     *
     * @param endereco O novo endereço da linha de ônibus.
     * @since 1.0
     */
    public void setEndereco(EnderecoDto endereco) {
        this.endereco = endereco;
    }

    /**
     * Retorna uma representação textual do objeto LinhaDeOnibusDto.
     *
     * @return Uma string com os detalhes do LinhaDeOnibusDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "LinhaDeOnibusDto{" +
                "idLinha=" + idLinha +
                ", codigoLinha='" + codigoLinha + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco=" + (endereco != null ? endereco.toString() : "Não informado") +
                '}';
    }
}
