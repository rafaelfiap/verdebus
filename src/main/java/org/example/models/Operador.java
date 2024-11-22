package org.example.models;

/**
 * Classe que representa um Operador no sistema.
 * Contém atributos essenciais como nome, CPF e o identificador da garagem onde está alocado.
 * É utilizada para gerenciar os operadores vinculados às garagens no sistema de transporte.
 *
 * @version 1.1
 * @since 1.0
 */
public class Operador {

    private Long idOperador; // Identificação única do operador
    private String nome; // Nome completo do operador
    private String cpf; // CPF único do operador
    private Long idGaragem; // Identificador da garagem onde o operador está alocado

    /**
     * Construtor completo para inicializar um Operador com todos os atributos necessários.
     *
     * @param idOperador Identificação única do operador.
     * @param nome       Nome completo do operador.
     * @param cpf        CPF único do operador.
     * @param idGaragem  Identificação da garagem onde o operador está alocado.
     */
    public Operador(Long idOperador, String nome, String cpf, Long idGaragem) {
        this.idOperador = idOperador;
        this.nome = nome;
        this.cpf = cpf;
        this.idGaragem = idGaragem;
    }

    // Getters e Setters

    /**
     * Obtém o identificador único do operador.
     *
     * @return idOperador - Identificação única do operador.
     */
    public Long getIdOperador() {
        return idOperador;
    }

    /**
     * Define o identificador único do operador.
     *
     * @param idOperador Identificação única do operador.
     */
    public void setIdOperador(Long idOperador) {
        this.idOperador = idOperador;
    }

    /**
     * Obtém o nome completo do operador.
     *
     * @return Nome completo do operador.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome completo do operador.
     *
     * @param nome Nome completo do operador.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o CPF único do operador.
     *
     * @return CPF único do operador.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF único do operador.
     *
     * @param cpf CPF único do operador.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Obtém o identificador da garagem onde o operador está alocado.
     *
     * @return idGaragem - Identificação da garagem associada.
     */
    public Long getIdGaragem() {
        return idGaragem;
    }

    /**
     * Define o identificador da garagem onde o operador está alocado.
     *
     * @param idGaragem Identificação da garagem associada.
     */
    public void setIdGaragem(Long idGaragem) {
        this.idGaragem = idGaragem;
    }

    /**
     * Retorna uma descrição textual com os detalhes do operador.
     *
     * @return String representando as informações do operador.
     */
    @Override
    public String toString() {
        return "Operador{" +
                "idOperador=" + idOperador +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", idGaragem=" + idGaragem +
                '}';
    }
}
