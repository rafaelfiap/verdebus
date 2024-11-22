package org.example.models;

/**
 * Classe que representa uma Estação de Recarga Solar localizada em uma garagem.
 * Além de gerar energia por meio de painéis solares, a estação gerencia o processo de recarga de veículos elétricos.
 *
 * @since 1.0
 * @version 1.2
 */
public class EstacaoRecargaSolar {

    private Long idEstacao; // Identificação única da estação de recarga solar
    private double potenciaMaxima; // Potência máxima da estação em kW
    private int numeroPaineis; // Número de painéis solares na estação
    private double energiaGerada; // Total de energia gerada pela estação em kWh
    private boolean ocupada; // Status de ocupação da estação (true se ocupada, false se disponível)
    private Veiculo veiculoEmRecarga; // Veículo em processo de recarga (se houver)
    private Long idGaragem; // Identificação única da garagem onde a estação está localizada

    /**
     * Construtor completo para inicializar uma Estação de Recarga Solar com todos os atributos.
     *
     * @param idEstacao      O ID exclusivo da estação.
     * @param potenciaMaxima A potência máxima da estação em kW.
     * @param numeroPaineis  O número de painéis solares na estação.
     * @param energiaGerada  A energia total gerada pela estação em kWh.
     * @param ocupada        O status de ocupação da estação (true se ocupada, false se disponível).
     * @param idGaragem      O ID da garagem onde a estação está localizada.
     */
    public EstacaoRecargaSolar(Long idEstacao, double potenciaMaxima, int numeroPaineis, double energiaGerada, boolean ocupada, Long idGaragem) {
        this.idEstacao = idEstacao;
        this.potenciaMaxima = potenciaMaxima;
        this.numeroPaineis = numeroPaineis;
        this.energiaGerada = energiaGerada;
        this.ocupada = ocupada;
        this.idGaragem = idGaragem;
        this.veiculoEmRecarga = null;
    }

    /**
     * Construtor alternativo para criar uma Estação de Recarga Solar sem a necessidade de inicializar
     * energia gerada e status de ocupação.
     * Inicializa o status como disponível (false) e calcula automaticamente a energia gerada com base nos painéis solares.
     *
     * @param idEstacao      Identificador único da estação de recarga solar.
     * @param potenciaMaxima A potência máxima da estação em kW.
     * @param numeroPaineis  O número de painéis solares na estação.
     * @param idGaragem      O ID da garagem onde a estação está localizada.
     * @since 1.2
     */
    public EstacaoRecargaSolar(Long idEstacao, double potenciaMaxima, int numeroPaineis, Long idGaragem) {
        this(idEstacao, potenciaMaxima, numeroPaineis, potenciaMaxima * numeroPaineis, false, idGaragem);
    }


    // Getters e Setters

    /**
     * Obtém o ID exclusivo da estação.
     *
     * @return O ID da estação.
     */
    public Long getIdEstacao() {
        return idEstacao;
    }

    /**
     * Define o ID exclusivo da estação.
     *
     * @param idEstacao O novo ID da estação.
     */
    public void setIdEstacao(Long idEstacao) {
        this.idEstacao = idEstacao;
    }

    /**
     * Obtém a potência máxima da estação.
     *
     * @return A potência máxima da estação em kW.
     */
    public double getPotenciaMaxima() {
        return potenciaMaxima;
    }

    /**
     * Define a potência máxima da estação.
     *
     * @param potenciaMaxima A nova potência máxima da estação em kW.
     */
    public void setPotenciaMaxima(double potenciaMaxima) {
        this.potenciaMaxima = potenciaMaxima;
    }

    /**
     * Obtém o número de painéis solares na estação.
     *
     * @return O número de painéis solares.
     */
    public int getNumeroPaineis() {
        return numeroPaineis;
    }

    /**
     * Define o número de painéis solares na estação.
     *
     * @param numeroPaineis O novo número de painéis solares.
     */
    public void setNumeroPaineis(int numeroPaineis) {
        this.numeroPaineis = numeroPaineis;
    }

    /**
     * Obtém a energia total gerada pela estação.
     *
     * @return A energia total gerada em kWh.
     */
    public double getEnergiaGerada() {
        return energiaGerada;
    }

    /**
     * Atualiza a energia total gerada pela estação.
     */
    private void atualizarEnergiaGerada() {
        this.energiaGerada = calcularEnergiaGerada();
    }

    /**
     * Verifica se a estação está ocupada.
     *
     * @return True se ocupada, false se disponível.
     */
    public boolean isOcupada() {
        return ocupada;
    }

    /**
     * Define o status de ocupação da estação.
     *
     * @param ocupada True para ocupada, false para disponível.
     */
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    /**
     * Obtém o veículo em processo de recarga.
     *
     * @return O veículo atualmente em recarga.
     */
    public Veiculo getVeiculoEmRecarga() {
        return veiculoEmRecarga;
    }

    /**
     * Define o veículo em processo de recarga.
     *
     * @param veiculoEmRecarga O veículo a ser recarregado.
     */
    public void setVeiculoEmRecarga(Veiculo veiculoEmRecarga) {
        this.veiculoEmRecarga = veiculoEmRecarga;
    }

    /**
     * Obtém o ID da garagem onde a estação está localizada.
     *
     * @return O ID da garagem.
     */
    public Long getIdGaragem() {
        return idGaragem;
    }

    /**
     * Define o ID da garagem onde a estação está localizada.
     *
     * @param idGaragem O novo ID da garagem.
     */
    public void setIdGaragem(Long idGaragem) {
        this.idGaragem = idGaragem;
    }

    // Métodos Específicos

    /**
     * Calcula a energia total gerada pela estação com base nos painéis solares.
     *
     * @return A energia gerada em kWh.
     */
    public double calcularEnergiaGerada() {
        return potenciaMaxima * numeroPaineis;
    }

    /**
     * Inicia a recarga de um veículo, se a estação estiver disponível.
     *
     * @param veiculo O veículo a ser recarregado.
     */
    public void iniciarRecarga(Veiculo veiculo) {
        if (!ocupada) {
            ocupada = true;
            veiculoEmRecarga = veiculo;
            System.out.println("Recarga iniciada na estação " + idEstacao + " para o veículo: " + veiculo.toString());
        } else {
            System.out.println("A estação de recarga " + idEstacao + " já está ocupada.");
        }
    }

    /**
     * Finaliza a recarga de um veículo, liberando a estação.
     */
    public void finalizarRecarga() {
        if (ocupada) {
            ocupada = false;
            System.out.println("Recarga finalizada na estação " + idEstacao + " para o veículo: " + veiculoEmRecarga.toString());
            veiculoEmRecarga = null;
        } else {
            System.out.println("A estação de recarga " + idEstacao + " já está disponível.");
        }
    }

    /**
     * Gera uma representação textual do objeto EstacaoRecargaSolar.
     *
     * @return Uma string com os detalhes da estação.
     */
    @Override
    public String toString() {
        return "EstacaoRecargaSolar{" +
                "idEstacao=" + idEstacao +
                ", potenciaMaxima=" + potenciaMaxima +
                ", numeroPaineis=" + numeroPaineis +
                ", energiaGerada=" + energiaGerada +
                ", ocupada=" + ocupada +
                ", veiculoEmRecarga=" + (veiculoEmRecarga != null ? veiculoEmRecarga.toString() : "Nenhum") +
                ", idGaragem=" + idGaragem +
                '}';
    }
}
