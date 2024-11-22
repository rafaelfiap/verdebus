package org.example.models;

/**
 * Classe que representa uma Linha de Ônibus.
 * Cada linha possui um identificador único, um código, um nome e um endereço associado.
 *
 * @since 1.0
 * @version 1.2
 */
public class LinhaDeOnibus {
    private Long idLinha; // Identificação única da linha de ônibus
    private String codigoLinha; // Código da linha de ônibus (exemplo: "2709-10")
    private String nome; // Nome da linha de ônibus
    private Endereco endereco; // Endereço associado à linha de ônibus

    /**
     * Construtor completo para inicializar uma Linha de Ônibus com todos os atributos.
     *
     * @param idLinha     Identificador único da linha de ônibus
     * @param codigoLinha Código da linha de ônibus
     * @param nome        Nome da linha de ônibus
     * @param endereco    Endereço associado à linha de ônibus
     */
    public LinhaDeOnibus(Long idLinha, String codigoLinha, String nome, Endereco endereco) {
        this.idLinha = idLinha;
        this.codigoLinha = codigoLinha;
        this.nome = nome;
        this.endereco = endereco;
    }

    /**
     * Construtor alternativo para inicializar uma Linha de Ônibus com ID, código e nome, sem endereço.
     *
     * @param idLinha     Identificador único da linha de ônibus.
     * @param codigoLinha Código da linha de ônibus.
     * @param nome        Nome da linha de ônibus.
     */
    public LinhaDeOnibus(Long idLinha, String codigoLinha, String nome) {
        this(idLinha, codigoLinha, nome, null); // Atribui null para o endereço
    }


    // Getters e Setters

    /**
     * Obtém o identificador único da linha de ônibus.
     *
     * @return idLinha - Identificação única da linha de ônibus
     */
    public Long getIdLinha() {
        return idLinha;
    }

    /**
     * Define o identificador único da linha de ônibus.
     *
     * @param idLinha - Identificação única da linha de ônibus
     */
    public void setIdLinha(Long idLinha) {
        this.idLinha = idLinha;
    }

    /**
     * Obtém o código da linha de ônibus.
     *
     * @return codigoLinha - Código da linha de ônibus
     */
    public String getCodigoLinha() {
        return codigoLinha;
    }

    /**
     * Define o código da linha de ônibus.
     *
     * @param codigoLinha - Código da linha de ônibus
     */
    public void setCodigoLinha(String codigoLinha) {
        this.codigoLinha = codigoLinha;
    }

    /**
     * Obtém o nome da linha de ônibus.
     *
     * @return nome - Nome da linha de ônibus
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da linha de ônibus.
     *
     * @param nome - Nome da linha de ônibus
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o endereço associado à linha de ônibus.
     *
     * @return endereco - Endereço da linha de ônibus
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço associado à linha de ônibus.
     *
     * @param endereco - Novo endereço da linha de ônibus
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * Exibe as informações da linha de ônibus em formato textual.
     *
     * @return String com as informações da linha de ônibus
     */
    @Override
    public String toString() {
        return "Linha de Ônibus - ID: " + idLinha +
                ", Código: " + codigoLinha +
                ", Nome: " + nome +
                ", Endereço: " + (endereco != null ? endereco.toString() : "Não informado");
    }
}
