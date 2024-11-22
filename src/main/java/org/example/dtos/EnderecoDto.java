package org.example.dtos;

/**
 * DTO para transferência de dados da entidade Endereco.
 *
 * <p>A classe `EnderecoDto` é usada para transferir dados do endereço entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como ID,
 * logradouro, número, CEP, bairro, cidade, estado e ID de referência associado ao endereço.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public class EnderecoDto {

    // Identificador único do endereço.
    private Long idEndereco;

    // Nome da rua ou avenida.
    private String logradouro;

    // Número do local.
    private Integer numero;

    // Código postal (CEP).
    private String cep;

    // Nome do bairro.
    private String bairro;

    // Nome da cidade.
    private String cidade;

    // Unidade federativa (estado).
    private String uf;

    // ID que referencia a entidade associada ao endereço (ex: Garagem, Linha, etc.).
    private Integer idReferencia;

    // Getters e Setters

    /**
     * Obtém o ID do endereço.
     *
     * @return O ID do endereço.
     * @since 1.0
     */
    public Long getIdEndereco() {
        return idEndereco;
    }

    /**
     * Define o ID do endereço.
     *
     * @param idEndereco O novo ID do endereço.
     * @since 1.0
     */
    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    /**
     * Obtém o logradouro do endereço.
     *
     * @return O logradouro do endereço.
     * @since 1.0
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * Define o logradouro do endereço.
     *
     * @param logradouro O novo logradouro do endereço.
     * @since 1.0
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * Obtém o número do endereço.
     *
     * @return O número do endereço.
     * @since 1.0
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * Define o número do endereço.
     *
     * @param numero O novo número do endereço.
     * @since 1.0
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * Obtém o CEP do endereço.
     *
     * @return O CEP do endereço.
     * @since 1.0
     */
    public String getCep() {
        return cep;
    }

    /**
     * Define o CEP do endereço.
     *
     * @param cep O novo CEP do endereço.
     * @since 1.0
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * Obtém o bairro do endereço.
     *
     * @return O bairro do endereço.
     * @since 1.0
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * Define o bairro do endereço.
     *
     * @param bairro O novo bairro do endereço.
     * @since 1.0
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * Obtém a cidade do endereço.
     *
     * @return A cidade do endereço.
     * @since 1.0
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Define a cidade do endereço.
     *
     * @param cidade A nova cidade do endereço.
     * @since 1.0
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Obtém a unidade federativa (estado) do endereço.
     *
     * @return A unidade federativa (estado).
     * @since 1.0
     */
    public String getUf() {
        return uf;
    }

    /**
     * Define a unidade federativa (estado) do endereço.
     *
     * @param uf A nova unidade federativa (estado).
     * @since 1.0
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * Obtém o ID de referência associado ao endereço.
     *
     * @return O ID de referência associado.
     * @since 1.0
     */
    public Integer getIdReferencia() {
        return idReferencia;
    }

    /**
     * Define o ID de referência associado ao endereço.
     *
     * @param idReferencia O novo ID de referência associado.
     * @since 1.0
     */
    public void setIdReferencia(Integer idReferencia) {
        this.idReferencia = idReferencia;
    }

    /**
     * Retorna uma representação textual do objeto EnderecoDto.
     *
     * @return Uma string com os detalhes do EnderecoDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "EnderecoDto{" +
                "idEndereco=" + idEndereco +
                ", logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", cep='" + cep + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", idReferencia=" + idReferencia +
                '}';
    }
}
