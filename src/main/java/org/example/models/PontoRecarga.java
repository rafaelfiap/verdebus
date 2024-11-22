package org.example.models;

public class PontoRecarga {
    // Identificação única do ponto de recarga
    private Long idPonto;

    // Potência de recarga do ponto, em kW
    private double potencia;

    // Status de ocupação do ponto de recarga (true se ocupado, false se disponível)
    private boolean ocupado;

    // Identificador da linha de ônibus associada ao ponto de recarga
    private Long idLinha;

    // Linha de ônibus à qual o ponto de recarga está associado
    private LinhaDeOnibus linha;

    /**
     * Construtor da classe PontoRecarga.
     *
     * @param idPonto  Identificador único do ponto de recarga
     * @param potencia Potência de recarga do ponto, em kW
     * @param ocupado  Status de ocupação do ponto (true se ocupado, false se disponível)
     * @param idLinha  Identificador da linha de ônibus associada ao ponto de recarga
     */
    public PontoRecarga(Long idPonto, double potencia, boolean ocupado, Long idLinha) {
        this.idPonto = idPonto;
        this.potencia = potencia;
        this.ocupado = ocupado;
        this.idLinha = idLinha;
    }

    // Getters e Setters

    /**
     * Obtém o identificador único do ponto de recarga.
     *
     * @return idPonto - Identificação única do ponto de recarga
     */
    public Long getIdPonto() {
        return idPonto;
    }

    /**
     * Define o identificador único do ponto de recarga.
     *
     * @param idPonto - Identificação única do ponto de recarga
     */
    public void setIdPonto(Long idPonto) {
        this.idPonto = idPonto;
    }

    /**
     * Obtém a potência de recarga do ponto.
     *
     * @return potencia - Potência do ponto de recarga em kW
     */
    public double getPotencia() {
        return potencia;
    }

    /**
     * Define a potência de recarga do ponto.
     *
     * @param potencia - Potência do ponto de recarga em kW
     */
    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    /**
     * Verifica se o ponto de recarga está ocupado.
     *
     * @return ocupado - True se ocupado, false se disponível
     */
    public boolean isOcupado() {
        return ocupado;
    }

    /**
     * Define o status de ocupação do ponto de recarga.
     *
     * @param ocupado - True para ocupado, false para disponível
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    /**
     * Obtém o identificador da linha de ônibus associada ao ponto de recarga.
     *
     * @return idLinha - Identificador da linha de ônibus
     */
    public Long getIdLinha() {
        return idLinha;
    }

    /**
     * Define o identificador da linha de ônibus associada ao ponto de recarga.
     *
     * @param idLinha - Identificador da linha de ônibus
     */
    public void setIdLinha(Long idLinha) {
        this.idLinha = idLinha;
    }

    /**
     * Obtém a linha de ônibus associada ao ponto de recarga.
     *
     * @return linha - Linha de ônibus à qual o ponto de recarga pertence
     */
    public LinhaDeOnibus getLinha() {
        return linha;
    }

    /**
     * Define a linha de ônibus associada ao ponto de recarga.
     *
     * @param linha - Linha de ônibus associada ao ponto de recarga
     */
    public void setLinha(LinhaDeOnibus linha) {
        this.linha = linha;
    }

    // Métodos Específicos

    /**
     * Inicia a recarga no ponto, definindo-o como ocupado.
     */
    public void iniciarRecarga() {
        if (!ocupado) {
            ocupado = true;
            System.out.println("Recarga iniciada no ponto " + idPonto + " com potência de " + potencia + " kW.");
        } else {
            System.out.println("O ponto de recarga " + idPonto + " já está ocupado.");
        }
    }

    /**
     * Finaliza a recarga no ponto, liberando-o para o próximo veículo.
     */
    public void finalizarRecarga() {
        if (ocupado) {
            ocupado = false;
            System.out.println("Recarga finalizada no ponto " + idPonto + ". Ponto agora disponível.");
        } else {
            System.out.println("O ponto de recarga " + idPonto + " já está disponível.");
        }
    }

    /**
     * Exibe as informações do ponto de recarga em formato textual.
     *
     * @return String com as informações do ponto de recarga
     */
    @Override
    public String toString() {
        return "Ponto de Recarga - ID: " + idPonto + ", Potência: " + potencia + " kW, Ocupado: " + (ocupado ? "Sim" : "Não") +
                ", Linha: " + (linha != null ? linha.getNome() : "Não atribuída") + ", ID Linha: " + idLinha;
    }
}
