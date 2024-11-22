package org.example.models;

/**
 * Classe Moto que representa uma moto elétrica usada para apoio operacional interno.
 * Esta classe herda os atributos e métodos básicos de Veiculo, incluindo o monitoramento de consumo de energia,
 * e adiciona atributos específicos como prefixo de identificação interna, tipo de licença e o ID da garagem.
 *
 * @since 1.0
 * @version 1.2
 */
public class Moto extends Veiculo {

    // Identificação única da moto, usada para persistência e referência no sistema
    private Long idMoto;

    // Prefixo usado para identificação interna da moto dentro da empresa
    private int prefixo;

    // Tipo de licença da moto (exemplo: "A" para motocicletas padrão)
    private String tipoLicenca;

    // Identificação da garagem à qual a moto está associada
    private Long idGaragem;

    /**
     * Construtor principal para inicializar uma Moto com todos os atributos necessários.
     *
     * @param idMoto           Identificador único da moto.
     * @param prefixo          Prefixo para identificação interna da moto.
     * @param placa            Placa do veículo.
     * @param modelo           Modelo do veículo.
     * @param fabricante       Fabricante do veículo.
     * @param anoFabricacao    Ano de fabricação do veículo.
     * @param capacidadeBateria Capacidade máxima da bateria em kWh.
     * @param tipoLicenca      Tipo de licença da moto (ex.: "A" ou "A2").
     * @param idGaragem        ID da garagem associada.
     * @since 1.1
     */
    public Moto(Long idMoto, int prefixo, String placa, String modelo, String fabricante, int anoFabricacao,
                double capacidadeBateria, String tipoLicenca, Long idGaragem) {
        // Chama o construtor da classe pai (Veiculo) para inicializar atributos comuns
        super(placa, modelo, fabricante, anoFabricacao, capacidadeBateria);
        this.idMoto = idMoto;
        this.prefixo = prefixo;
        this.tipoLicenca = tipoLicenca;
        this.idGaragem = idGaragem;
    }

    /**
     * Construtor alternativo para criar uma Moto sem associar imediatamente uma garagem.
     *
     * @param idMoto           Identificador único da moto.
     * @param prefixo          Prefixo para identificação interna da moto.
     * @param placa            Placa do veículo.
     * @param modelo           Modelo do veículo.
     * @param fabricante       Fabricante do veículo.
     * @param anoFabricacao    Ano de fabricação do veículo.
     * @param capacidadeBateria Capacidade máxima da bateria em kWh.
     * @param tipoLicenca      Tipo de licença da moto (ex.: "A" ou "A2").
     * @since 1.2
     */
    public Moto(Long idMoto, int prefixo, String placa, String modelo, String fabricante, int anoFabricacao,
                double capacidadeBateria, String tipoLicenca) {
        // Delegação para o construtor principal, com a garagem como null
        this(idMoto, prefixo, placa, modelo, fabricante, anoFabricacao, capacidadeBateria, tipoLicenca, null);
    }

    /**
     * Construtor simplificado para criar uma Moto com atributos básicos e associá-la diretamente a uma garagem.
     *
     * @param idMoto       Identificador único da moto.
     * @param prefixo      Prefixo para identificação interna da moto.
     * @param tipoLicenca  Tipo de licença da moto (ex.: "A").
     * @param idGaragem    ID da garagem associada.
     * @since 1.2
     */
    public Moto(Long idMoto, int prefixo, String tipoLicenca, Long idGaragem) {
        // Delegação para o construtor principal, com valores padrão para outros atributos
        this(idMoto, prefixo, null, null, null, 0, 0, tipoLicenca, idGaragem);
    }

    /**
     * Construtor alternativo para inicializar uma instância de Moto com atributos básicos.
     * Este construtor pode ser utilizado em contextos onde apenas os dados essenciais da moto são necessários,
     * como durante uma operação de transferência ou carregamento parcial de dados.
     *
     * @param idMoto             Identificação única da moto.
     * @param nrPrefixo          Prefixo para identificação interna da moto.
     * @param dsTipoLicenca      Tipo de licença da moto (ex.: "A" ou "A2").
     * @param dsPlaca            Placa do veículo.
     * @param dsModelo           Modelo da moto.
     * @param nmFabricante       Fabricante da moto.
     * @param nrAnoFabricacao    Ano de fabricação da moto.
     * @param qtCapacidadeBateria Capacidade total da bateria da moto em kWh.
     * @param idGaragem          ID da garagem associada à moto.
     *
     */
    public Moto(long idMoto, int nrPrefixo, String dsTipoLicenca, String dsPlaca, String dsModelo,
                String nmFabricante, int nrAnoFabricacao, double qtCapacidadeBateria, long idGaragem) {
    }

    // Getters e Setters para acessar e modificar os atributos da classe

    /**
     * Obtém o identificador único da moto.
     *
     * @return idMoto - Identificação única da moto.
     * @since 1.0
     */
    public Long getIdMoto() {
        return idMoto;
    }

    /**
     * Define o identificador único da moto.
     *
     * @param idMoto - Identificação única da moto.
     * @since 1.0
     */
    public void setIdMoto(Long idMoto) {
        this.idMoto = idMoto;
    }

    /**
     * Obtém o prefixo da moto.
     *
     * @return prefixo - Prefixo para identificação interna.
     * @since 1.0
     */
    public int getPrefixo() {
        return prefixo;
    }

    /**
     * Define o prefixo da moto.
     *
     * @param prefixo - Prefixo para identificação interna.
     * @since 1.0
     */
    public void setPrefixo(int prefixo) {
        this.prefixo = prefixo;
    }

    /**
     * Obtém o tipo de licença da moto.
     *
     * @return tipoLicenca - Tipo de licença da moto.
     * @since 1.0
     */
    public String getTipoLicenca() {
        return tipoLicenca;
    }

    /**
     * Define o tipo de licença da moto.
     *
     * @param tipoLicenca - Tipo de licença da moto.
     * @since 1.0
     */
    public void setTipoLicenca(String tipoLicenca) {
        this.tipoLicenca = tipoLicenca;
    }

    /**
     * Obtém o ID da garagem associada à moto.
     *
     * @return idGaragem - ID da garagem associada.
     * @since 1.1
     */
    public Long getIdGaragem() {
        return idGaragem;
    }

    /**
     * Define o ID da garagem associada à moto.
     *
     * @param idGaragem - ID da garagem associada.
     * @since 1.1
     */
    public void setIdGaragem(Long idGaragem) {
        this.idGaragem = idGaragem;
    }

    /**
     * Método para monitorar o consumo de energia da moto.
     * Calcula o consumo com base na diferença entre a capacidade da bateria e o nível de carga atual.
     *
     * @return consumoSimulado - Consumo estimado da moto em kWh.
     * @since 1.0
     */
    public double monitorarConsumo() {
        double consumoSimulado = getCapacidadeBateria() - getNivelCargaAtual();
        System.out.println("Monitorando consumo da moto: Consumo atual estimado em " + consumoSimulado + " kWh.");
        return consumoSimulado;
    }

    /**
     * Implementação do método carregar da classe Veiculo.
     * Carrega a bateria da moto até o limite da capacidade.
     *
     * @param quantidade - Quantidade de carga a ser adicionada em kWh.
     * @since 1.0
     */
    @Override
    public void carregar(double quantidade) {
        setNivelCargaAtual(Math.min(getNivelCargaAtual() + quantidade, getCapacidadeBateria()));
        System.out.println("Carregando moto: Carga atual é " + getNivelCargaAtual() + " kWh.");
    }

    /**
     * Exibe as informações da moto em formato textual.
     *
     * @return String com as informações da moto.
     * @since 1.1
     */
    @Override
    public String toString() {
        return super.toString() + ", ID Moto: " + idMoto + ", Prefixo: " + prefixo +
                ", Tipo de Licença: " + tipoLicenca + ", ID Garagem: " + (idGaragem != null ? idGaragem : "Nenhuma");
    }
}
