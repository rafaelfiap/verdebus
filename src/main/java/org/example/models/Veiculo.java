package org.example.models;

/**
 * Classe abstrata Veiculo que representa a estrutura base para todos os veículos elétricos.
 * Contém atributos comuns como placa, modelo, fabricante, ano de fabricação, capacidade de bateria
 * e nível de carga atual.
 *
 * @since 1.0
 * @version 1.1
 */
public abstract class Veiculo {
    // Placa do veículo
    private String placa;

    // Modelo do veículo
    private String modelo;

    // Fabricante do veículo
    private String fabricante;

    // Ano de fabricação do veículo
    private int anoFabricacao;

    // Capacidade total da bateria do veículo em kWh
    private double capacidadeBateria;

    // Nível atual de carga da bateria em kWh
    private double nivelCargaAtual;

    /**
     * Construtor da classe Veiculo.
     *
     * @param placa             Placa do veículo
     * @param modelo            Modelo do veículo
     * @param fabricante        Fabricante do veículo
     * @param anoFabricacao     Ano de fabricação do veículo
     * @param capacidadeBateria Capacidade máxima da bateria em kWh
     * @since 1.0
     */
    public Veiculo(String placa, String modelo, String fabricante, int anoFabricacao, double capacidadeBateria) {
        this.placa = placa;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.anoFabricacao = anoFabricacao;
        this.capacidadeBateria = capacidadeBateria;
        this.nivelCargaAtual = 0.0; // Inicialmente, o nível de carga é zero
    }

    public Veiculo() {
        // Construtor padrão
    }

    // Getters e Setters

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public double getCapacidadeBateria() {
        return capacidadeBateria;
    }

    public void setCapacidadeBateria(double capacidadeBateria) {
        this.capacidadeBateria = capacidadeBateria;
    }

    public double getNivelCargaAtual() {
        return nivelCargaAtual;
    }

    public void setNivelCargaAtual(double nivelCargaAtual) {
        this.nivelCargaAtual = nivelCargaAtual;
    }

    // Métodos Abstratos e Específicos

    /**
     * Método abstrato para carregar a bateria do veículo.
     *
     * @param quantidade - Quantidade de carga a ser adicionada em kWh
     * @since 1.0
     */
    public abstract void carregar(double quantidade);

    /**
     * Verifica o status da bateria do veículo, mostrando o nível de carga em relação à capacidade total.
     *
     * @return String com o nível de carga atual e a capacidade total da bateria
     * @since 1.0
     */
    public String verificarStatusBateria() {
        return "Nível de carga atual: " + nivelCargaAtual + " kWh / Capacidade total: " + capacidadeBateria + " kWh";
    }

    /**
     * Exibe as principais informações do veículo em formato textual.
     *
     * @return String com as informações do veículo
     * @since 1.0
     */
    @Override
    public String toString() {
        return "Veículo - Placa: " + placa + ", Modelo: " + modelo + ", Fabricante: " + fabricante +
                ", Ano: " + anoFabricacao + ", Capacidade da Bateria: " + capacidadeBateria + " kWh, Nível de Carga: " + nivelCargaAtual + " kWh";
    }
}
