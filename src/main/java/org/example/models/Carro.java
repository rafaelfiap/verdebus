package org.example.models;

/**
 * Classe que representa um Carro elétrico, herdando de Veiculo.
 * Além dos atributos herdados de Veiculo, o Carro possui um ID exclusivo, prefixo, número de portas e uma referência ao ID da garagem associada.
 *
 * @since 1.0
 * @version 1.2
 * @see Veiculo
 */
public class Carro extends Veiculo {
    private Long idCarro; // Identificador único do carro
    private int prefixo; // Prefixo para identificação interna do carro
    private int numeroPortas; // Número de portas do carro
    private Long idGaragem; // ID da garagem associada ao carro

    /**
     * Construtor completo para inicializar um Carro com todos os atributos.
     *
     * @param idCarro           O ID exclusivo do carro.
     * @param prefixo           O prefixo para identificação interna do carro.
     * @param placa             A placa do carro.
     * @param modelo            O modelo do carro.
     * @param fabricante        O fabricante do carro.
     * @param anoFabricacao     O ano de fabricação do carro.
     * @param capacidadeBateria A capacidade máxima da bateria em kWh.
     * @param numeroPortas      O número de portas do carro.
     * @param idGaragem         O ID da garagem associada ao carro.
     */
    public Carro(Long idCarro, int prefixo, String placa, String modelo, String fabricante, int anoFabricacao,
                 double capacidadeBateria, int numeroPortas, Long idGaragem) {
        super(placa, modelo, fabricante, anoFabricacao, capacidadeBateria);
        this.idCarro = idCarro;
        this.prefixo = prefixo;
        this.numeroPortas = numeroPortas;
        this.idGaragem = idGaragem;
    }

    /**
     * Construtor alternativo para criar um Carro sem associar imediatamente uma garagem.
     *
     * @param idCarro           O ID exclusivo do carro.
     * @param prefixo           O prefixo para identificação interna do carro.
     * @param placa             A placa do carro.
     * @param modelo            O modelo do carro.
     * @param fabricante        O fabricante do carro.
     * @param anoFabricacao     O ano de fabricação do carro.
     * @param capacidadeBateria A capacidade máxima da bateria em kWh.
     * @param numeroPortas      O número de portas do carro.
     */
    public Carro(Long idCarro, int prefixo, String placa, String modelo, String fabricante, int anoFabricacao,
                 double capacidadeBateria, int numeroPortas) {
        this(idCarro, prefixo, placa, modelo, fabricante, anoFabricacao, capacidadeBateria, numeroPortas, null); // ID da garagem como null.
    }

    /**
     * Construtor adicional para criar um Carro com apenas atributos básicos e associar diretamente à garagem.
     *
     * @param idCarro       O ID exclusivo do carro.
     * @param prefixo       O prefixo para identificação interna do carro.
     * @param numeroPortas  O número de portas do carro.
     * @param placa         A placa do carro.
     * @param modelo        O modelo do carro.
     * @param fabricante    O fabricante do carro.
     * @param anoFabricacao O ano de fabricação do carro.
     * @param capacidadeBateria A capacidade máxima da bateria em kWh.
     * @param idGaragem     O ID da garagem associada.
     */
    public Carro(Long idCarro, int prefixo, int numeroPortas, String placa, String modelo, String fabricante,
                 int anoFabricacao, double capacidadeBateria, Long idGaragem) {
        this(idCarro, prefixo, placa, modelo, fabricante, anoFabricacao, capacidadeBateria, numeroPortas, idGaragem);
    }

    // Getters e Setters

    /**
     * Obtém o ID exclusivo do carro.
     *
     * @return O ID do carro.
     */
    public Long getIdCarro() {
        return idCarro;
    }

    /**
     * Define o ID exclusivo do carro.
     *
     * @param idCarro O novo ID do carro.
     */
    public void setIdCarro(Long idCarro) {
        this.idCarro = idCarro;
    }

    /**
     * Obtém o prefixo do carro.
     *
     * @return O prefixo do carro.
     */
    public int getPrefixo() {
        return prefixo;
    }

    /**
     * Define o prefixo do carro.
     *
     * @param prefixo O novo prefixo do carro.
     */
    public void setPrefixo(int prefixo) {
        this.prefixo = prefixo;
    }

    /**
     * Obtém o número de portas do carro.
     *
     * @return O número de portas do carro.
     */
    public int getNumeroPortas() {
        return numeroPortas;
    }

    /**
     * Define o número de portas do carro.
     *
     * @param numeroPortas O novo número de portas do carro.
     */
    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

    /**
     * Obtém o ID da garagem associada ao carro.
     *
     * @return O ID da garagem associada.
     */
    public Long getIdGaragem() {
        return idGaragem;
    }

    /**
     * Define o ID da garagem associada ao carro.
     *
     * @param idGaragem O novo ID da garagem associada.
     */
    public void setIdGaragem(Long idGaragem) {
        this.idGaragem = idGaragem;
    }

    // Métodos Específicos

    /**
     * Implementação do método carregar da classe Veiculo.
     * Carrega a bateria do carro até o limite da capacidade.
     *
     * @param quantidade Quantidade de carga a ser adicionada em kWh.
     */
    @Override
    public void carregar(double quantidade) {
        setNivelCargaAtual(Math.min(getNivelCargaAtual() + quantidade, getCapacidadeBateria()));
        System.out.println("Carregando carro: Carga atual é " + getNivelCargaAtual() + " kWh.");
    }

    /**
     * Gera uma representação textual do objeto Carro.
     *
     * @return Uma string com os detalhes do carro.
     */
    @Override
    public String toString() {
        return "Carro{" +
                "idCarro=" + idCarro +
                ", prefixo=" + prefixo +
                ", numeroPortas=" + numeroPortas +
                ", placa='" + getPlaca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", fabricante='" + getFabricante() + '\'' +
                ", anoFabricacao=" + getAnoFabricacao() +
                ", capacidadeBateria=" + getCapacidadeBateria() +
                ", idGaragem=" + (idGaragem != null ? idGaragem : "Nenhuma") +
                '}';
    }
}
