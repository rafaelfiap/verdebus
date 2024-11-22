package org.example.dtos;

/**
 * DTO para transferência de dados da entidade Carro.
 *
 * <p>A classe `CarroDto` é usada para transferir dados do carro entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID,
 * prefixo, número de portas, garagem associada, além de atributos do veículo como placa, modelo e
 * capacidade da bateria.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public class CarroDto {

    // Identificador único do carro.
    private Long idCarro;

    // Prefixo interno para identificação do carro.
    private int prefixo;

    // Número de portas do carro.
    private int numeroPortas;

    // Placa do carro.
    private String placa;

    // Modelo do carro.
    private String modelo;

    // Fabricante do carro.
    private String fabricante;

    // Ano de fabricação do carro.
    private int anoFabricacao;

    // Capacidade máxima da bateria em kWh.
    private double capacidadeBateria;

    // Identificador da garagem associada ao carro.
    private Long idGaragem;

    // Getters e Setters

    /**
     * Obtém o ID do carro.
     *
     * @return O ID do carro.
     * @since 1.0
     */
    public Long getIdCarro() {
        return idCarro;
    }

    /**
     * Define o ID do carro.
     *
     * @param idCarro O novo ID do carro.
     * @since 1.0
     */
    public void setIdCarro(Long idCarro) {
        this.idCarro = idCarro;
    }

    /**
     * Obtém o prefixo do carro.
     *
     * @return O prefixo do carro.
     * @since 1.0
     */
    public int getPrefixo() {
        return prefixo;
    }

    /**
     * Define o prefixo do carro.
     *
     * @param prefixo O novo prefixo do carro.
     * @since 1.0
     */
    public void setPrefixo(int prefixo) {
        this.prefixo = prefixo;
    }

    /**
     * Obtém o número de portas do carro.
     *
     * @return O número de portas do carro.
     * @since 1.0
     */
    public int getNumeroPortas() {
        return numeroPortas;
    }

    /**
     * Define o número de portas do carro.
     *
     * @param numeroPortas O novo número de portas do carro.
     * @since 1.0
     */
    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

    /**
     * Obtém a placa do carro.
     *
     * @return A placa do carro.
     * @since 1.0
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Define a placa do carro.
     *
     * @param placa A nova placa do carro.
     * @since 1.0
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Obtém o modelo do carro.
     *
     * @return O modelo do carro.
     * @since 1.0
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Define o modelo do carro.
     *
     * @param modelo O novo modelo do carro.
     * @since 1.0
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtém o fabricante do carro.
     *
     * @return O fabricante do carro.
     * @since 1.0
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * Define o fabricante do carro.
     *
     * @param fabricante O novo fabricante do carro.
     * @since 1.0
     */
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    /**
     * Obtém o ano de fabricação do carro.
     *
     * @return O ano de fabricação do carro.
     * @since 1.0
     */
    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    /**
     * Define o ano de fabricação do carro.
     *
     * @param anoFabricacao O novo ano de fabricação do carro.
     * @since 1.0
     */
    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    /**
     * Obtém a capacidade máxima da bateria do carro em kWh.
     *
     * @return A capacidade da bateria.
     * @since 1.0
     */
    public double getCapacidadeBateria() {
        return capacidadeBateria;
    }

    /**
     * Define a capacidade máxima da bateria do carro em kWh.
     *
     * @param capacidadeBateria A nova capacidade da bateria.
     * @since 1.0
     */
    public void setCapacidadeBateria(double capacidadeBateria) {
        this.capacidadeBateria = capacidadeBateria;
    }

    /**
     * Obtém o ID da garagem associada ao carro.
     *
     * @return O ID da garagem associada.
     * @since 1.0
     */
    public Long getIdGaragem() {
        return idGaragem;
    }

    /**
     * Define o ID da garagem associada ao carro.
     *
     * @param idGaragem O novo ID da garagem associada.
     * @since 1.0
     */
    public void setIdGaragem(Long idGaragem) {
        this.idGaragem = idGaragem;
    }

    /**
     * Retorna uma representação textual do objeto CarroDto.
     *
     * @return Uma string com os detalhes do CarroDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "CarroDto{" +
                "idCarro=" + idCarro +
                ", prefixo=" + prefixo +
                ", numeroPortas=" + numeroPortas +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", anoFabricacao=" + anoFabricacao +
                ", capacidadeBateria=" + capacidadeBateria +
                ", idGaragem=" + idGaragem +
                '}';
    }
}
