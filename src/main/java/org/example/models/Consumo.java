package org.example.models;

/**
 * Classe que representa o consumo de energia de um veículo.
 * Contém informações como consumo médio por quilômetro, distância percorrida, energia total consumida e o identificador do ônibus relacionado.
 *
 * @version 1.2
 * @since 1.0
 */
public class Consumo {

    private Long idConsumo; // Identificação única para o registro de consumo
    private double consumoPorKm; // Consumo médio de energia em kWh por quilômetro
    private double distanciaPercorrida; // Distância total percorrida em quilômetros
    private double energiaTotalConsumida; // Total de energia consumida em kWh
    private Long idOnibus; // Identificador do ônibus relacionado ao consumo

    /**
     * Construtor completo para inicializar um registro de consumo com todos os atributos principais.
     * Este construtor permite criar um registro completo, incluindo a distância percorrida e a energia total consumida.
     *
     * @param idConsumo            Identificador único para o registro de consumo.
     * @param consumoPorKm         Consumo médio de energia em kWh por quilômetro.
     * @param distanciaPercorrida  Distância total percorrida pelo veículo.
     * @param energiaTotalConsumida Total de energia consumida em kWh.
     * @param idOnibus             Identificador do ônibus relacionado ao consumo.
     * @since 1.0
     */
    public Consumo(Long idConsumo, double consumoPorKm, double distanciaPercorrida, double energiaTotalConsumida, Long idOnibus) {
        this.idConsumo = idConsumo;
        this.consumoPorKm = consumoPorKm;
        this.distanciaPercorrida = distanciaPercorrida;
        this.energiaTotalConsumida = energiaTotalConsumida;
        this.idOnibus = idOnibus;
    }

    /**
     * Construtor alternativo para criar um registro de consumo sem a necessidade de inicializar
     * distância percorrida e energia total consumida.
     * Este construtor inicializa os valores de distância percorrida e energia total consumida como zero.
     *
     * @param idConsumo    Identificador único para o registro de consumo.
     * @param consumoPorKm Consumo médio de energia em kWh por quilômetro.
     * @param idOnibus     Identificador do ônibus relacionado ao consumo.
     * @since 1.2
     */
    public Consumo(Long idConsumo, double consumoPorKm, Long idOnibus) {
        // Utiliza o construtor completo, inicializando distância percorrida e energia consumida como 0.
        this(idConsumo, consumoPorKm, 0, 0, idOnibus);
    }

    // Getters e Setters

    /**
     * Obtém o identificador único do registro de consumo.
     *
     * @return Identificador único do registro de consumo.
     */
    public Long getIdConsumo() {
        return idConsumo;
    }

    /**
     * Define o identificador único do registro de consumo.
     *
     * @param idConsumo Identificador único do registro de consumo.
     */
    public void setIdConsumo(Long idConsumo) {
        this.idConsumo = idConsumo;
    }

    /**
     * Obtém o consumo médio de energia por quilômetro.
     *
     * @return Consumo médio de energia em kWh por quilômetro.
     */
    public double getConsumoPorKm() {
        return consumoPorKm;
    }

    /**
     * Define o consumo médio de energia por quilômetro.
     *
     * @param consumoPorKm Consumo médio de energia em kWh por quilômetro.
     */
    public void setConsumoPorKm(double consumoPorKm) {
        this.consumoPorKm = consumoPorKm;
    }

    /**
     * Obtém a distância total percorrida pelo veículo.
     *
     * @return Distância total percorrida em quilômetros.
     */
    public double getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    /**
     * Define a distância total percorrida pelo veículo.
     * Este método também atualiza automaticamente o total de energia consumida.
     *
     * @param distanciaPercorrida Distância total percorrida em quilômetros.
     */
    public void setDistanciaPercorrida(double distanciaPercorrida) {
        this.distanciaPercorrida = distanciaPercorrida;
        atualizarEnergiaTotalConsumida(); // Atualiza o consumo total de energia baseado na nova distância.
    }

    /**
     * Obtém o total de energia consumida em kWh.
     *
     * @return Total de energia consumida em kWh.
     */
    public double getEnergiaTotalConsumida() {
        return energiaTotalConsumida;
    }

    /**
     * Obtém o identificador do ônibus relacionado ao consumo.
     *
     * @return Identificador do ônibus relacionado ao consumo.
     */
    public Long getIdOnibus() {
        return idOnibus;
    }

    /**
     * Define o identificador do ônibus relacionado ao consumo.
     *
     * @param idOnibus Identificador do ônibus relacionado ao consumo.
     */
    public void setIdOnibus(Long idOnibus) {
        this.idOnibus = idOnibus;
    }

    // Métodos Específicos

    /**
     * Calcula e atualiza o total de energia consumida com base na distância percorrida.
     * Este método é chamado automaticamente sempre que a distância percorrida é atualizada.
     */
    private void atualizarEnergiaTotalConsumida() {
        this.energiaTotalConsumida = this.distanciaPercorrida * this.consumoPorKm;
    }

    /**
     * Registra um novo trajeto, adicionando à distância percorrida e atualizando o consumo de energia.
     *
     * @param distancia Distância do trajeto em quilômetros.
     */
    public void registrarTrajeto(double distancia) {
        this.distanciaPercorrida += distancia;
        atualizarEnergiaTotalConsumida(); // Atualiza o total de energia consumida com base na nova distância.
        System.out.println("Trajeto registrado: " + distancia + " km. Energia total consumida: " + energiaTotalConsumida + " kWh.");
    }

    /**
     * Gera uma representação textual do objeto Consumo.
     * Inclui detalhes sobre consumo médio, distância percorrida, energia consumida e o identificador do ônibus.
     *
     * @return Uma string com os detalhes do consumo.
     */
    @Override
    public String toString() {
        return "Consumo{" +
                "idConsumo=" + idConsumo +
                ", consumoPorKm=" + consumoPorKm +
                ", distanciaPercorrida=" + distanciaPercorrida +
                ", energiaTotalConsumida=" + energiaTotalConsumida +
                ", idOnibus=" + idOnibus +
                '}';
    }
}
