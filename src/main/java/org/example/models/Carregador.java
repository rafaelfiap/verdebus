package org.example.models;

/**
 * Classe que representa um Carregador para veículos elétricos.
 * Esta classe contém atributos e métodos relacionados à gestão de carregadores,
 * incluindo potência, status, veículo atualmente em recarga e o ID da estação de recarga associada.
 *
 * @version 1.2
 * @since 1.0
 */
public class Carregador {

    // Atributos da classe
    private Long idCarregador; // Identificação única do carregador
    private double potencia; // Potência de recarga do carregador em kW
    private String status; // Status atual do carregador ("Disponível", "Ocupado", "Manutenção")
    private Veiculo veiculoEmRecarga; // Veículo atualmente em recarga no carregador (se houver)
    private Long idEstacaoRecargaSolar; // ID da estação de recarga solar associada

    /**
     * Construtor completo para inicializar um Carregador com todos os atributos.
     * Este construtor permite configurar todas as informações necessárias para o carregador.
     *
     * @param idCarregador            Identificação única do carregador.
     * @param potencia                Potência de recarga do carregador em kW.
     * @param status                  Status inicial do carregador ("Disponível", "Ocupado", "Manutenção").
     * @param idEstacaoRecargaSolar   Identificação da estação de recarga solar associada.
     * @param veiculoEmRecarga        Veículo atualmente em recarga, ou null caso nenhum esteja em recarga.
     * @since 1.2
     */
    public Carregador(Long idCarregador, double potencia, String status, Long idEstacaoRecargaSolar, Veiculo veiculoEmRecarga) {
        this.idCarregador = idCarregador;
        this.potencia = potencia;
        this.status = status;
        this.idEstacaoRecargaSolar = idEstacaoRecargaSolar;
        this.veiculoEmRecarga = veiculoEmRecarga;
    }

    /**
     * Construtor alternativo para inicializar um Carregador sem veículo em recarga.
     * Este construtor configura o carregador com valores iniciais, exceto o veículo em recarga.
     *
     * @param idCarregador          Identificação única do carregador.
     * @param potencia              Potência do carregador em kW.
     * @param status                Status inicial do carregador ("Disponível", "Ocupado", "Manutenção").
     * @param idEstacaoRecargaSolar Identificação da estação de recarga solar associada.
     */
    public Carregador(Long idCarregador, double potencia, String status, Long idEstacaoRecargaSolar) {
        this(idCarregador, potencia, status, idEstacaoRecargaSolar, null); // Inicialmente, nenhum veículo está em recarga.
    }

    // Getters e Setters

    /**
     * Obtém o identificador único do carregador.
     *
     * @return idCarregador - Identificação única do carregador.
     */
    public Long getIdCarregador() {
        return idCarregador;
    }

    /**
     * Define o identificador único do carregador.
     *
     * @param idCarregador Identificação única do carregador.
     */
    public void setIdCarregador(Long idCarregador) {
        this.idCarregador = idCarregador;
    }

    /**
     * Obtém a potência de recarga do carregador.
     *
     * @return Potência do carregador em kW.
     */
    public double getPotencia() {
        return potencia;
    }

    /**
     * Define a potência de recarga do carregador.
     *
     * @param potencia Potência do carregador em kW.
     */
    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    /**
     * Obtém o status atual do carregador.
     *
     * @return Status do carregador ("Disponível", "Ocupado", "Manutenção").
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define o status atual do carregador.
     *
     * @param status Novo status do carregador ("Disponível", "Ocupado", "Manutenção").
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Obtém o veículo atualmente em recarga (se houver).
     *
     * @return Veículo em recarga ou null, caso nenhum esteja em recarga.
     */
    public Veiculo getVeiculoEmRecarga() {
        return veiculoEmRecarga;
    }

    /**
     * Define o veículo que está em recarga no carregador.
     *
     * @param veiculoEmRecarga Veículo em processo de recarga.
     */
    public void setVeiculoEmRecarga(Veiculo veiculoEmRecarga) {
        this.veiculoEmRecarga = veiculoEmRecarga;
    }

    /**
     * Obtém o identificador da estação de recarga solar associada ao carregador.
     *
     * @return ID da estação de recarga solar.
     */
    public Long getIdEstacaoRecargaSolar() {
        return idEstacaoRecargaSolar;
    }

    /**
     * Define o identificador da estação de recarga solar associada ao carregador.
     *
     * @param idEstacaoRecargaSolar ID da estação de recarga solar.
     */
    public void setIdEstacaoRecargaSolar(Long idEstacaoRecargaSolar) {
        this.idEstacaoRecargaSolar = idEstacaoRecargaSolar;
    }

    // Métodos específicos

    /**
     * Inicia a recarga de um veículo, se o status do carregador for "Disponível".
     * Atualiza o status do carregador para "Ocupado" e associa o veículo em recarga.
     *
     * @param veiculo Veículo a ser recarregado.
     */
    public void iniciarRecarga(Veiculo veiculo) {
        if ("Disponível".equalsIgnoreCase(status)) {
            this.status = "Ocupado";
            this.veiculoEmRecarga = veiculo;
            System.out.println("Recarga iniciada no carregador " + idCarregador + " para o veículo: " + veiculo);
        } else {
            System.out.println("Carregador " + idCarregador + " não está disponível. Status atual: " + status);
        }
    }

    /**
     * Finaliza a recarga do veículo, liberando o carregador e atualizando seu status para "Disponível".
     */
    public void finalizarRecarga() {
        if ("Ocupado".equalsIgnoreCase(status)) {
            System.out.println("Recarga finalizada no carregador " + idCarregador + " para o veículo: " + veiculoEmRecarga);
            this.status = "Disponível";
            this.veiculoEmRecarga = null;
        } else {
            System.out.println("Carregador " + idCarregador + " não está ocupado. Status atual: " + status);
        }
    }

    /**
     * Retorna uma descrição textual do Carregador.
     *
     * @return String representando as informações do Carregador.
     */
    @Override
    public String toString() {
        return "Carregador{" +
                "idCarregador=" + idCarregador +
                ", potencia=" + potencia +
                ", status='" + status + '\'' +
                ", veiculoEmRecarga=" + veiculoEmRecarga +
                ", idEstacaoRecargaSolar=" + idEstacaoRecargaSolar +
                '}';
    }
}
