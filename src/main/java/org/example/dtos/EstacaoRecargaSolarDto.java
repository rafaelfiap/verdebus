package org.example.dtos;

/**
 * DTO para transferência de dados da entidade EstacaoRecargaSolar.
 *
 * <p>A classe `EstacaoRecargaSolarDto` é usada para transferir dados de estações de recarga solar
 * entre as camadas da aplicação, geralmente entre a camada de serviço e a camada de apresentação.
 * Inclui informações como ID, potência máxima, número de painéis solares, energia gerada, status de ocupação,
 * ID da garagem associada e veículo em recarga.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public class EstacaoRecargaSolarDto {

    // Identificador único da estação de recarga solar.
    private Long idEstacao;

    // Potência máxima da estação em kW.
    private Double potenciaMaxima;

    // Número de painéis solares na estação.
    private Integer numeroPaineis;

    // Total de energia gerada pela estação em kWh.
    private Double energiaGerada;

    // Status de ocupação da estação (true se ocupada, false se disponível).
    private Boolean ocupada;

    // Identificador do veículo atualmente em recarga.
    private Long idVeiculoEmRecarga;

    // Identificador da garagem onde a estação está localizada.
    private Long idGaragem;

    // Getters e Setters

    /**
     * Obtém o ID da estação de recarga solar.
     *
     * @return O ID da estação.
     * @since 1.0
     */
    public Long getIdEstacao() {
        return idEstacao;
    }

    /**
     * Define o ID da estação de recarga solar.
     *
     * @param idEstacao O novo ID da estação.
     * @since 1.0
     */
    public void setIdEstacao(Long idEstacao) {
        this.idEstacao = idEstacao;
    }

    /**
     * Obtém a potência máxima da estação em kW.
     *
     * @return A potência máxima da estação.
     * @since 1.0
     */
    public Double getPotenciaMaxima() {
        return potenciaMaxima;
    }

    /**
     * Define a potência máxima da estação em kW.
     *
     * @param potenciaMaxima A nova potência máxima da estação.
     * @since 1.0
     */
    public void setPotenciaMaxima(Double potenciaMaxima) {
        this.potenciaMaxima = potenciaMaxima;
    }

    /**
     * Obtém o número de painéis solares na estação.
     *
     * @return O número de painéis solares.
     * @since 1.0
     */
    public Integer getNumeroPaineis() {
        return numeroPaineis;
    }

    /**
     * Define o número de painéis solares na estação.
     *
     * @param numeroPaineis O novo número de painéis solares.
     * @since 1.0
     */
    public void setNumeroPaineis(Integer numeroPaineis) {
        this.numeroPaineis = numeroPaineis;
    }

    /**
     * Obtém o total de energia gerada pela estação em kWh.
     *
     * @return O total de energia gerada.
     * @since 1.0
     */
    public Double getEnergiaGerada() {
        return energiaGerada;
    }

    /**
     * Define o total de energia gerada pela estação em kWh.
     *
     * @param energiaGerada O novo total de energia gerada.
     * @since 1.0
     */
    public void setEnergiaGerada(Double energiaGerada) {
        this.energiaGerada = energiaGerada;
    }

    /**
     * Obtém o status de ocupação da estação.
     *
     * @return True se ocupada, false se disponível.
     * @since 1.0
     */
    public Boolean getOcupada() {
        return ocupada;
    }

    /**
     * Define o status de ocupação da estação.
     *
     * @param ocupada O novo status de ocupação.
     * @since 1.0
     */
    public void setOcupada(Boolean ocupada) {
        this.ocupada = ocupada;
    }

    /**
     * Obtém o ID do veículo atualmente em recarga.
     *
     * @return O ID do veículo em recarga.
     * @since 1.0
     */
    public Long getIdVeiculoEmRecarga() {
        return idVeiculoEmRecarga;
    }

    /**
     * Define o ID do veículo atualmente em recarga.
     *
     * @param idVeiculoEmRecarga O novo ID do veículo em recarga.
     * @since 1.0
     */
    public void setIdVeiculoEmRecarga(Long idVeiculoEmRecarga) {
        this.idVeiculoEmRecarga = idVeiculoEmRecarga;
    }

    /**
     * Obtém o ID da garagem associada à estação.
     *
     * @return O ID da garagem associada.
     * @since 1.0
     */
    public Long getIdGaragem() {
        return idGaragem;
    }

    /**
     * Define o ID da garagem associada à estação.
     *
     * @param idGaragem O novo ID da garagem associada.
     * @since 1.0
     */
    public void setIdGaragem(Long idGaragem) {
        this.idGaragem = idGaragem;
    }

    /**
     * Retorna uma representação textual do objeto EstacaoRecargaSolarDto.
     *
     * @return Uma string com os detalhes do EstacaoRecargaSolarDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "EstacaoRecargaSolarDto{" +
                "idEstacao=" + idEstacao +
                ", potenciaMaxima=" + potenciaMaxima +
                ", numeroPaineis=" + numeroPaineis +
                ", energiaGerada=" + energiaGerada +
                ", ocupada=" + ocupada +
                ", idVeiculoEmRecarga=" + idVeiculoEmRecarga +
                ", idGaragem=" + idGaragem +
                '}';
    }
}
