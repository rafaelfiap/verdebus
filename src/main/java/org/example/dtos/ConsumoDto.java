package org.example.dtos;

/**
 * DTO para transferência de dados da entidade Consumo.
 *
 * <p>A classe `ConsumoDto` é usada para transferir dados do consumo entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como ID,
 * consumo médio por quilômetro, distância percorrida, energia total consumida e o identificador
 * do ônibus relacionado ao consumo.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public class ConsumoDto {

    // Identificador único do registro de consumo.
    private Long idConsumo;

    // Consumo médio de energia em kWh por quilômetro.
    private double consumoPorKm;

    // Distância total percorrida em quilômetros.
    private double distanciaPercorrida;

    // Total de energia consumida em kWh.
    private double energiaTotalConsumida;

    // Identificador do ônibus relacionado ao consumo.
    private Long idOnibus;

    // Getters e Setters

    /**
     * Obtém o ID do registro de consumo.
     *
     * @return O ID do registro de consumo.
     * @since 1.0
     */
    public Long getIdConsumo() {
        return idConsumo;
    }

    /**
     * Define o ID do registro de consumo.
     *
     * @param idConsumo O novo ID do registro de consumo.
     * @since 1.0
     */
    public void setIdConsumo(Long idConsumo) {
        this.idConsumo = idConsumo;
    }

    /**
     * Obtém o consumo médio por quilômetro.
     *
     * @return O consumo médio em kWh por quilômetro.
     * @since 1.0
     */
    public double getConsumoPorKm() {
        return consumoPorKm;
    }

    /**
     * Define o consumo médio por quilômetro.
     *
     * @param consumoPorKm O novo consumo médio em kWh por quilômetro.
     * @since 1.0
     */
    public void setConsumoPorKm(double consumoPorKm) {
        this.consumoPorKm = consumoPorKm;
    }

    /**
     * Obtém a distância total percorrida.
     *
     * @return A distância total percorrida em quilômetros.
     * @since 1.0
     */
    public double getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    /**
     * Define a distância total percorrida.
     *
     * @param distanciaPercorrida A nova distância total percorrida em quilômetros.
     * @since 1.0
     */
    public void setDistanciaPercorrida(double distanciaPercorrida) {
        this.distanciaPercorrida = distanciaPercorrida;
    }

    /**
     * Obtém o total de energia consumida.
     *
     * @return O total de energia consumida em kWh.
     * @since 1.0
     */
    public double getEnergiaTotalConsumida() {
        return energiaTotalConsumida;
    }

    /**
     * Define o total de energia consumida.
     *
     * @param energiaTotalConsumida O novo total de energia consumida em kWh.
     * @since 1.0
     */
    public void setEnergiaTotalConsumida(double energiaTotalConsumida) {
        this.energiaTotalConsumida = energiaTotalConsumida;
    }

    /**
     * Obtém o ID do ônibus relacionado ao consumo.
     *
     * @return O ID do ônibus relacionado ao consumo.
     * @since 1.0
     */
    public Long getIdOnibus() {
        return idOnibus;
    }

    /**
     * Define o ID do ônibus relacionado ao consumo.
     *
     * @param idOnibus O novo ID do ônibus relacionado ao consumo.
     * @since 1.0
     */
    public void setIdOnibus(Long idOnibus) {
        this.idOnibus = idOnibus;
    }

    /**
     * Retorna uma representação textual do objeto ConsumoDto.
     *
     * @return Uma string com os detalhes do ConsumoDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "ConsumoDto{" +
                "idConsumo=" + idConsumo +
                ", consumoPorKm=" + consumoPorKm +
                ", distanciaPercorrida=" + distanciaPercorrida +
                ", energiaTotalConsumida=" + energiaTotalConsumida +
                ", idOnibus=" + idOnibus +
                '}';
    }
}
