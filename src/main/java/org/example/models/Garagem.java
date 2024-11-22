package org.example.models;

/**
 * Representa uma garagem onde veículos elétricos, como ônibus, são estacionados e gerenciados.
 *
 * @version 1.2
 * @since 1.0
 */
public class Garagem {
    // Identificação única da garagem
    private Long idGaragem;

    // Nome da garagem
    private String nome;

    // Endereço da garagem
    private Endereco endereco;

    // Capacidade máxima de ônibus que a garagem pode acomodar
    private int capacidadeOnibus;

    /**
     * Construtor completo da classe Garagem.
     *
     * @param idGaragem        Identificador único da garagem.
     * @param nome             Nome da garagem.
     * @param endereco         Endereço completo da garagem.
     * @param capacidadeOnibus Capacidade máxima de ônibus na garagem.
     */
    public Garagem(Long idGaragem, String nome, Endereco endereco, int capacidadeOnibus) {
        this.idGaragem = idGaragem;
        this.nome = nome;
        this.endereco = endereco;
        this.capacidadeOnibus = capacidadeOnibus;
    }

    /**
     * Construtor alternativo para inicializar uma Garagem apenas com ID, nome e capacidade, sem endereço.
     *
     * @param idGaragem        Identificador único da garagem.
     * @param nome             Nome da garagem.
     * @param capacidadeOnibus Capacidade máxima de ônibus na garagem.
     */
    public Garagem(Long idGaragem, String nome, int capacidadeOnibus) {
        this(idGaragem, nome, null, capacidadeOnibus); // Atribui null para endereço
    }

    /**
     * Construtor alternativo para inicializar uma Garagem apenas com capacidade e nome, sem ID ou endereço.
     *
     * @param nome             Nome da garagem.
     * @param capacidadeOnibus Capacidade máxima de ônibus na garagem.
     */
    public Garagem(String nome, int capacidadeOnibus) {
        this(null, nome, null, capacidadeOnibus); // Atribui null para ID e endereço
    }

    // Getters e Setters

    /**
     * Obtém o identificador único da garagem.
     *
     * @return idGaragem - Identificação única da garagem.
     */
    public Long getIdGaragem() {
        return idGaragem;
    }

    /**
     * Define o identificador único da garagem.
     *
     * @param idGaragem - Identificação única da garagem.
     */
    public void setIdGaragem(Long idGaragem) {
        this.idGaragem = idGaragem;
    }

    /**
     * Obtém o nome da garagem.
     *
     * @return nome - Nome da garagem.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da garagem.
     *
     * @param nome - Nome da garagem.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o endereço completo da garagem.
     *
     * @return endereco - Objeto de Endereco contendo detalhes da garagem.
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço completo da garagem.
     *
     * @param endereco - Objeto de Endereco contendo detalhes da garagem.
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * Obtém a capacidade máxima de ônibus que a garagem pode acomodar.
     *
     * @return capacidadeOnibus - Capacidade máxima de ônibus na garagem.
     */
    public int getCapacidadeOnibus() {
        return capacidadeOnibus;
    }

    /**
     * Define a capacidade máxima de ônibus que a garagem pode acomodar.
     *
     * @param capacidadeOnibus - Capacidade máxima de ônibus na garagem.
     */
    public void setCapacidadeOnibus(int capacidadeOnibus) {
        this.capacidadeOnibus = capacidadeOnibus;
    }

    /**
     * Exibe as informações da garagem em formato textual.
     *
     * @return String com as informações da garagem.
     */
    @Override
    public String toString() {
        return "Garagem{" +
                "idGaragem=" + idGaragem +
                ", nome='" + nome + '\'' +
                ", endereco=" + (endereco != null ? endereco.toString() : "Não definido") +
                ", capacidadeOnibus=" + capacidadeOnibus +
                '}';
    }
}
