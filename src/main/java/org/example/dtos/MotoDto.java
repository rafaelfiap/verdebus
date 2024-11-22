package org.example.dtos;

/**
 * DTO para transferência de dados da entidade Moto.
 *
 * <p>A classe `MotoDto` é usada para transferir dados de motos elétricas entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID da moto,
 * prefixo, tipo de licença, ID da garagem, placa, modelo, fabricante, ano de fabricação e capacidade da bateria.</p>
 *
 * @version 1.1
 * @since 1.0
 */
public class MotoDto {

    // Identificação única da moto.
    private Long idMoto;

    // Prefixo para identificação interna da moto.
    private int prefixo;

    // Tipo de licença da moto (ex.: "A" para licenças comuns de motocicletas).
    private String tipoLicenca;

    // Identificação da garagem associada à moto.
    private Long idGaragem;

    // Placa da moto.
    private String placa;

    // Modelo da moto.
    private String modelo;

    // Fabricante da moto.
    private String fabricante;

    // Ano de fabricação da moto.
    private int anoFabricacao;

    // Capacidade máxima da bateria em kWh.
    private double capacidadeBateria;

    // Getters e Setters

    /**
     * Obtém o identificador único da moto.
     *
     * @return O ID da moto.
     * @since 1.0
     */
    public Long getIdMoto() {
        return idMoto;
    }

    /**
     * Define o identificador único da moto.
     *
     * @param idMoto O novo ID da moto.
     * @since 1.0
     */
    public void setIdMoto(Long idMoto) {
        this.idMoto = idMoto;
    }

    /**
     * Obtém o prefixo da moto.
     *
     * @return O prefixo da moto.
     * @since 1.0
     */
    public int getPrefixo() {
        return prefixo;
    }

    /**
     * Define o prefixo da moto.
     *
     * @param prefixo O novo prefixo da moto.
     * @since 1.0
     */
    public void setPrefixo(int prefixo) {
        this.prefixo = prefixo;
    }

    /**
     * Obtém o tipo de licença da moto.
     *
     * @return O tipo de licença da moto.
     * @since 1.0
     */
    public String getTipoLicenca() {
        return tipoLicenca;
    }

    /**
     * Define o tipo de licença da moto.
     *
     * @param tipoLicenca O novo tipo de licença da moto.
     * @since 1.0
     */
    public void setTipoLicenca(String tipoLicenca) {
        this.tipoLicenca = tipoLicenca;
    }

    /**
     * Obtém o ID da garagem associada à moto.
     *
     * @return O ID da garagem.
     * @since 1.0
     */
    public Long getIdGaragem() {
        return idGaragem;
    }

    /**
     * Define o ID da garagem associada à moto.
     *
     * @param idGaragem O novo ID da garagem.
     * @since 1.0
     */
    public void setIdGaragem(Long idGaragem) {
        this.idGaragem = idGaragem;
    }

    /**
     * Obtém a placa da moto.
     *
     * @return A placa da moto.
     * @since 1.1
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Define a placa da moto.
     *
     * @param placa A nova placa da moto.
     * @since 1.1
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Obtém o modelo da moto.
     *
     * @return O modelo da moto.
     * @since 1.1
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Define o modelo da moto.
     *
     * @param modelo O novo modelo da moto.
     * @since 1.1
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtém o fabricante da moto.
     *
     * @return O fabricante da moto.
     * @since 1.1
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * Define o fabricante da moto.
     *
     * @param fabricante O novo fabricante da moto.
     * @since 1.1
     */
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    /**
     * Obtém o ano de fabricação da moto.
     *
     * @return O ano de fabricação da moto.
     * @since 1.1
     */
    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    /**
     * Define o ano de fabricação da moto.
     *
     * @param anoFabricacao O novo ano de fabricação da moto.
     * @since 1.1
     */
    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    /**
     * Obtém a capacidade máxima da bateria da moto.
     *
     * @return A capacidade máxima da bateria em kWh.
     * @since 1.1
     */
    public double getCapacidadeBateria() {
        return capacidadeBateria;
    }

    /**
     * Define a capacidade máxima da bateria da moto.
     *
     * @param capacidadeBateria A nova capacidade da bateria em kWh.
     * @since 1.1
     */
    public void setCapacidadeBateria(double capacidadeBateria) {
        this.capacidadeBateria = capacidadeBateria;
    }

    /**
     * Retorna uma representação textual do objeto MotoDto.
     *
     * @return Uma string com os detalhes do MotoDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "MotoDto{" +
                "idMoto=" + idMoto +
                ", prefixo=" + prefixo +
                ", tipoLicenca='" + tipoLicenca + '\'' +
                ", idGaragem=" + (idGaragem != null ? idGaragem : "Nenhuma") +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", anoFabricacao=" + anoFabricacao +
                ", capacidadeBateria=" + capacidadeBateria +
                '}';
    }
}
