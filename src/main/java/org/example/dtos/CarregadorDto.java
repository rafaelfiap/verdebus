package org.example.dtos;

/**
 * DTO para transferência de dados da entidade Carregador.
 *
 * <p>A classe `CarregadorDto` é usada para transferir dados do carregador entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID, potência,
 * status, veículo em recarga (se aplicável) e a estação de recarga solar associada.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public class CarregadorDto {

    // Identificador único do carregador.
    private Long idCarregador;

    // Potência de recarga do carregador em kW.
    private Double potencia;

    // Status do carregador ("Disponível", "Ocupado", "Manutenção").
    private String status;

    // Identificador do veículo atualmente em recarga (se houver).
    private Long idVeiculoEmRecarga;

    // Identificador da estação de recarga solar associada ao carregador.
    private Long idEstacaoRecargaSolar;

    // Getters e Setters

    /**
     * Obtém o ID do carregador.
     *
     * @return O ID do carregador.
     * @since 1.0
     */
    public Long getIdCarregador() {
        return idCarregador;
    }

    /**
     * Define o ID do carregador.
     *
     * @param idCarregador O novo ID do carregador.
     * @since 1.0
     */
    public void setIdCarregador(Long idCarregador) {
        this.idCarregador = idCarregador;
    }

    /**
     * Obtém a potência do carregador em kW.
     *
     * @return A potência do carregador.
     * @since 1.0
     */
    public Double getPotencia() {
        return potencia;
    }

    /**
     * Define a potência do carregador em kW.
     *
     * @param potencia A nova potência do carregador.
     * @since 1.0
     */
    public void setPotencia(Double potencia) {
        this.potencia = potencia;
    }

    /**
     * Obtém o status atual do carregador.
     *
     * @return O status do carregador ("Disponível", "Ocupado", "Manutenção").
     * @since 1.0
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define o status atual do carregador.
     *
     * @param status O novo status do carregador ("Disponível", "Ocupado", "Manutenção").
     * @since 1.0
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Obtém o ID do veículo atualmente em recarga (se houver).
     *
     * @return O ID do veículo em recarga ou null se não houver.
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
     * Obtém o ID da estação de recarga solar associada ao carregador.
     *
     * @return O ID da estação de recarga solar.
     * @since 1.0
     */
    public Long getIdEstacaoRecargaSolar() {
        return idEstacaoRecargaSolar;
    }

    /**
     * Define o ID da estação de recarga solar associada ao carregador.
     *
     * @param idEstacaoRecargaSolar O novo ID da estação de recarga solar.
     * @since 1.0
     */
    public void setIdEstacaoRecargaSolar(Long idEstacaoRecargaSolar) {
        this.idEstacaoRecargaSolar = idEstacaoRecargaSolar;
    }

    /**
     * Retorna uma representação textual do objeto CarregadorDto.
     *
     * @return Uma string com os detalhes do CarregadorDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "CarregadorDto{" +
                "idCarregador=" + idCarregador +
                ", potencia=" + potencia +
                ", status='" + status + '\'' +
                ", idVeiculoEmRecarga=" + idVeiculoEmRecarga +
                ", idEstacaoRecargaSolar=" + idEstacaoRecargaSolar +
                '}';
    }
}
