package org.example.dtos;

/**
 * DTO para transferência de dados da entidade PontoRecarga.
 *
 * <p>A classe `PontoRecargaDto` é usada para transferir dados dos pontos de recarga entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID do ponto de recarga,
 * potência, status de ocupação e identificador da linha associada.</p>
 *
 * @version 1.1
 * @since 1.0
 */
public class PontoRecargaDto {

    // Identificação única do ponto de recarga.
    private Long idPonto;

    // Potência de recarga do ponto, em kW.
    private double potencia;

    // Status de ocupação do ponto de recarga.
    private boolean ocupado;

    // Identificador da linha de ônibus associada ao ponto de recarga.
    private Long idLinha;

    /**
     * Construtor completo do DTO.
     *
     * @param idPonto   Identificação única do ponto de recarga.
     * @param potencia  Potência de recarga do ponto, em kW.
     * @param ocupado   Status de ocupação do ponto de recarga.
     * @param idLinha   Identificador da linha de ônibus associada ao ponto de recarga.
     */
    public PontoRecargaDto(Long idPonto, double potencia, boolean ocupado, Long idLinha) {
        this.idPonto = idPonto;
        this.potencia = potencia;
        this.ocupado = ocupado;
        this.idLinha = idLinha;
    }

    // Getters e Setters

    /**
     * Obtém o identificador único do ponto de recarga.
     *
     * @return O ID do ponto de recarga.
     * @since 1.0
     */
    public Long getIdPonto() {
        return idPonto;
    }

    /**
     * Define o identificador único do ponto de recarga.
     *
     * @param idPonto O novo ID do ponto de recarga.
     * @since 1.0
     */
    public void setIdPonto(Long idPonto) {
        this.idPonto = idPonto;
    }

    /**
     * Obtém a potência de recarga do ponto.
     *
     * @return A potência do ponto de recarga em kW.
     * @since 1.0
     */
    public double getPotencia() {
        return potencia;
    }

    /**
     * Define a potência de recarga do ponto.
     *
     * @param potencia A nova potência do ponto de recarga em kW.
     * @since 1.0
     */
    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    /**
     * Verifica se o ponto de recarga está ocupado.
     *
     * @return True se ocupado, false se disponível.
     * @since 1.0
     */
    public boolean isOcupado() {
        return ocupado;
    }

    /**
     * Define o status de ocupação do ponto de recarga.
     *
     * @param ocupado True para ocupado, false para disponível.
     * @since 1.0
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    /**
     * Obtém o identificador da linha de ônibus associada ao ponto de recarga.
     *
     * @return O ID da linha de ônibus.
     * @since 1.0
     */
    public Long getIdLinha() {
        return idLinha;
    }

    /**
     * Define o identificador da linha de ônibus associada ao ponto de recarga.
     *
     * @param idLinha O novo ID da linha de ônibus.
     * @since 1.0
     */
    public void setIdLinha(Long idLinha) {
        this.idLinha = idLinha;
    }

    /**
     * Retorna uma representação textual do objeto PontoRecargaDto.
     *
     * @return Uma string com os detalhes do PontoRecargaDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "PontoRecargaDto{" +
                "idPonto=" + idPonto +
                ", potencia=" + potencia +
                ", ocupado=" + (ocupado ? "Sim" : "Não") +
                ", idLinha=" + idLinha +
                '}';
    }
}
