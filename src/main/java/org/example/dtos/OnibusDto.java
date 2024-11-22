package org.example.dtos;

/**
 * DTO para transferência de dados da entidade Ônibus.
 *
 * <p>A classe `OnibusDto` é usada para transferir dados de ônibus elétricos entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID, prefixo,
 * capacidade de passageiros, painel solar, película solar, placa, modelo, fabricante, capacidade da bateria,
 * ID da linha associada e ID da garagem associada.</p>
 *
 * @version 1.1
 * @since 1.0
 */
public class OnibusDto {

    // Identificador único do ônibus.
    private Long idOnibus;

    // Prefixo para identificação interna do ônibus.
    private int prefixo;

    // Placa do ônibus.
    private String placa;

    // Modelo do ônibus.
    private String modelo;

    // Fabricante do ônibus.
    private String fabricante;

    // Ano de fabricação do ônibus.
    private int anoFabricacao;

    // Capacidade máxima da bateria do ônibus em kWh.
    private double capacidadeBateria;

    // Capacidade máxima de passageiros do ônibus.
    private int capacidadePassageiros;

    // Capacidade de geração de energia do painel solar em kW.
    private double painelSolar;

    // Capacidade de geração de energia das películas solares em kW.
    private double peliculaSolar;

    // Identificador da linha de ônibus associada.
    private Long idLinha;

    // Identificador da garagem associada ao ônibus.
    private Long idGaragem;

    // Getters e Setters

    /**
     * Obtém o identificador único do ônibus.
     *
     * @return O ID do ônibus.
     * @since 1.0
     */
    public Long getIdOnibus() {
        return idOnibus;
    }

    /**
     * Define o identificador único do ônibus.
     *
     * @param idOnibus O novo ID do ônibus.
     * @since 1.0
     */
    public void setIdOnibus(Long idOnibus) {
        this.idOnibus = idOnibus;
    }

    /**
     * Obtém o prefixo do ônibus.
     *
     * @return O prefixo do ônibus.
     * @since 1.0
     */
    public int getPrefixo() {
        return prefixo;
    }

    /**
     * Define o prefixo do ônibus.
     *
     * @param prefixo O novo prefixo do ônibus.
     * @since 1.0
     */
    public void setPrefixo(int prefixo) {
        this.prefixo = prefixo;
    }

    /**
     * Obtém a placa do ônibus.
     *
     * @return A placa do ônibus.
     * @since 1.1
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Define a placa do ônibus.
     *
     * @param placa A nova placa do ônibus.
     * @since 1.1
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Obtém o modelo do ônibus.
     *
     * @return O modelo do ônibus.
     * @since 1.1
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Define o modelo do ônibus.
     *
     * @param modelo O novo modelo do ônibus.
     * @since 1.1
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtém o fabricante do ônibus.
     *
     * @return O fabricante do ônibus.
     * @since 1.1
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * Define o fabricante do ônibus.
     *
     * @param fabricante O novo fabricante do ônibus.
     * @since 1.1
     */
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    /**
     * Obtém o ano de fabricação do ônibus.
     *
     * @return O ano de fabricação do ônibus.
     * @since 1.1
     */
    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    /**
     * Define o ano de fabricação do ônibus.
     *
     * @param anoFabricacao O novo ano de fabricação do ônibus.
     * @since 1.1
     */
    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    /**
     * Obtém a capacidade máxima da bateria do ônibus.
     *
     * @return A capacidade máxima da bateria em kWh.
     * @since 1.1
     */
    public double getCapacidadeBateria() {
        return capacidadeBateria;
    }

    /**
     * Define a capacidade máxima da bateria do ônibus.
     *
     * @param capacidadeBateria A nova capacidade da bateria em kWh.
     * @since 1.1
     */
    public void setCapacidadeBateria(double capacidadeBateria) {
        this.capacidadeBateria = capacidadeBateria;
    }

    /**
     * Obtém a capacidade máxima de passageiros do ônibus.
     *
     * @return A capacidade de passageiros.
     * @since 1.0
     */
    public int getCapacidadePassageiros() {
        return capacidadePassageiros;
    }

    /**
     * Define a capacidade máxima de passageiros do ônibus.
     *
     * @param capacidadePassageiros A nova capacidade de passageiros.
     * @since 1.0
     */
    public void setCapacidadePassageiros(int capacidadePassageiros) {
        this.capacidadePassageiros = capacidadePassageiros;
    }

    /**
     * Obtém a capacidade de geração de energia do painel solar.
     *
     * @return A capacidade do painel solar em kW.
     * @since 1.0
     */
    public double getPainelSolar() {
        return painelSolar;
    }

    /**
     * Define a capacidade de geração de energia do painel solar.
     *
     * @param painelSolar A nova capacidade do painel solar em kW.
     * @since 1.0
     */
    public void setPainelSolar(double painelSolar) {
        this.painelSolar = painelSolar;
    }

    /**
     * Obtém a capacidade de geração de energia das películas solares.
     *
     * @return A capacidade das películas solares em kW.
     * @since 1.0
     */
    public double getPeliculaSolar() {
        return peliculaSolar;
    }

    /**
     * Define a capacidade de geração de energia das películas solares.
     *
     * @param peliculaSolar A nova capacidade das películas solares em kW.
     * @since 1.0
     */
    public void setPeliculaSolar(double peliculaSolar) {
        this.peliculaSolar = peliculaSolar;
    }

    /**
     * Obtém o identificador da linha de ônibus associada.
     *
     * @return O ID da linha associada.
     * @since 1.0
     */
    public Long getIdLinha() {
        return idLinha;
    }

    /**
     * Define o identificador da linha de ônibus associada.
     *
     * @param idLinha O novo ID da linha associada.
     * @since 1.0
     */
    public void setIdLinha(Long idLinha) {
        this.idLinha = idLinha;
    }

    /**
     * Obtém o identificador da garagem associada ao ônibus.
     *
     * @return O ID da garagem associada.
     * @since 1.0
     */
    public Long getIdGaragem() {
        return idGaragem;
    }

    /**
     * Define o identificador da garagem associada ao ônibus.
     *
     * @param idGaragem O novo ID da garagem associada.
     * @since 1.0
     */
    public void setIdGaragem(Long idGaragem) {
        this.idGaragem = idGaragem;
    }

    /**
     * Retorna uma representação textual do objeto OnibusDto.
     *
     * @return Uma string com os detalhes do OnibusDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "OnibusDto{" +
                "idOnibus=" + idOnibus +
                ", prefixo=" + prefixo +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", anoFabricacao=" + anoFabricacao +
                ", capacidadeBateria=" + capacidadeBateria +
                ", capacidadePassageiros=" + capacidadePassageiros +
                ", painelSolar=" + painelSolar +
                ", peliculaSolar=" + peliculaSolar +
                ", idLinha=" + idLinha +
                ", idGaragem=" + (idGaragem != null ? idGaragem : "Nenhuma") +
                '}';
    }
}
