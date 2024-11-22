package org.example.models;

/**
 * Classe Onibus que representa um ônibus elétrico equipado com sistemas de geração de energia solar.
 * Esta classe herda os atributos e métodos básicos de Veiculo e adiciona atributos específicos
 * como capacidade de passageiros, painel solar, película solar, ID da linha de ônibus e ID da garagem associada.
 *
 * @since 1.0
 * @version 1.2
 */
public class Onibus extends Veiculo {

    private Long idOnibus;  // Identificador único do ônibus
    private int prefixo;  // Prefixo para identificação interna do ônibus
    private int capacidadePassageiros;  // Capacidade máxima de passageiros do ônibus
    private double painelSolar;  // Capacidade de geração de energia do painel solar em kW
    private double peliculaSolar;  // Capacidade de geração de energia das películas solares em kW
    private Long idLinha;  // ID da linha de ônibus associada
    private Long idGaragem;  // ID da garagem associada ao ônibus

    /**
     * Construtor completo para inicializar um Onibus com todos os atributos.
     *
     * @param idOnibus           Identificador único do ônibus.
     * @param prefixo            Prefixo para identificação interna do ônibus.
     * @param placa              Placa do veículo.
     * @param modelo             Modelo do veículo.
     * @param fabricante         Fabricante do veículo.
     * @param anoFabricacao      Ano de fabricação do veículo.
     * @param capacidadeBateria  Capacidade máxima da bateria em kWh.
     * @param capacidadePassageiros Capacidade máxima de passageiros do ônibus.
     * @param painelSolar        Capacidade de geração de energia do painel solar em kW.
     * @param peliculaSolar      Capacidade de geração de energia das películas solares em kW.
     * @param idLinha            ID da linha de ônibus associada.
     * @param idGaragem          ID da garagem associada ao ônibus.
     */
    public Onibus(Long idOnibus, int prefixo, String placa, String modelo, String fabricante, int anoFabricacao,
                  double capacidadeBateria, int capacidadePassageiros, double painelSolar, double peliculaSolar,
                  Long idLinha, Long idGaragem) {
        super(placa, modelo, fabricante, anoFabricacao, capacidadeBateria);
        this.idOnibus = idOnibus;
        this.prefixo = prefixo;
        this.capacidadePassageiros = capacidadePassageiros;
        this.painelSolar = painelSolar;
        this.peliculaSolar = peliculaSolar;
        this.idLinha = idLinha;
        this.idGaragem = idGaragem;
    }

    /**
     * Construtor alternativo para criar um Ônibus sem associar imediatamente a uma garagem.
     *
     * @param idOnibus           Identificador único do ônibus.
     * @param prefixo            Prefixo para identificação interna do ônibus.
     * @param placa              Placa do veículo.
     * @param modelo             Modelo do veículo.
     * @param fabricante         Fabricante do veículo.
     * @param anoFabricacao      Ano de fabricação do veículo.
     * @param capacidadeBateria  Capacidade máxima da bateria em kWh.
     * @param capacidadePassageiros Capacidade máxima de passageiros do ônibus.
     * @param painelSolar        Capacidade de geração de energia do painel solar em kW.
     * @param peliculaSolar      Capacidade de geração de energia das películas solares em kW.
     */
    public Onibus(Long idOnibus, int prefixo, String placa, String modelo, String fabricante, int anoFabricacao,
                  double capacidadeBateria, int capacidadePassageiros, double painelSolar, double peliculaSolar) {
        this(idOnibus, prefixo, placa, modelo, fabricante, anoFabricacao, capacidadeBateria, capacidadePassageiros, painelSolar, peliculaSolar, null, null);
    }

    /**
     * Construtor adicional para criar um Ônibus com apenas atributos básicos e associar diretamente à garagem.
     *
     * @param idOnibus           Identificador único do ônibus.
     * @param prefixo            Prefixo para identificação interna do ônibus.
     * @param capacidadePassageiros Capacidade máxima de passageiros do ônibus.
     * @param painelSolar        Capacidade de geração de energia do painel solar em kW.
     * @param peliculaSolar      Capacidade de geração de energia das películas solares em kW.
     * @param placa              Placa do veículo.
     * @param modelo             Modelo do veículo.
     * @param fabricante         Fabricante do veículo.
     * @param anoFabricacao      Ano de fabricação do veículo.
     * @param capacidadeBateria  Capacidade máxima da bateria em kWh.
     * @param idGaragem          ID da garagem associada.
     * @param idLinha            ID da linha associada.
     */
    public Onibus(Long idOnibus, int prefixo, int capacidadePassageiros, double painelSolar, double peliculaSolar,
                  String placa, String modelo, String fabricante, int anoFabricacao, double capacidadeBateria,
                  Long idGaragem, Long idLinha) {
        this(idOnibus, prefixo, placa, modelo, fabricante, anoFabricacao, capacidadeBateria, capacidadePassageiros, painelSolar, peliculaSolar, idLinha, idGaragem);
    }

    // Getters e Setters

    /**
     * Obtém o identificador único do ônibus.
     *
     * @return O ID do ônibus.
     */
    public Long getIdOnibus() {
        return idOnibus;
    }

    /**
     * Define o identificador único do ônibus.
     *
     * @param idOnibus O novo ID do ônibus.
     */
    public void setIdOnibus(Long idOnibus) {
        this.idOnibus = idOnibus;
    }

    /**
     * Obtém o prefixo do ônibus.
     *
     * @return O prefixo do ônibus.
     */
    public int getPrefixo() {
        return prefixo;
    }

    /**
     * Define o prefixo do ônibus.
     *
     * @param prefixo O novo prefixo.
     */
    public void setPrefixo(int prefixo) {
        this.prefixo = prefixo;
    }

    /**
     * Obtém a capacidade máxima de passageiros do ônibus.
     *
     * @return A capacidade de passageiros do ônibus.
     */
    public int getCapacidadePassageiros() {
        return capacidadePassageiros;
    }

    /**
     * Define a capacidade máxima de passageiros do ônibus.
     *
     * @param capacidadePassageiros A nova capacidade de passageiros.
     */
    public void setCapacidadePassageiros(int capacidadePassageiros) {
        this.capacidadePassageiros = capacidadePassageiros;
    }

    /**
     * Obtém a capacidade de geração de energia do painel solar.
     *
     * @return A capacidade do painel solar em kW.
     */
    public double getPainelSolar() {
        return painelSolar;
    }

    /**
     * Define a capacidade de geração de energia do painel solar.
     *
     * @param painelSolar A nova capacidade do painel solar em kW.
     */
    public void setPainelSolar(double painelSolar) {
        this.painelSolar = painelSolar;
    }

    /**
     * Obtém a capacidade de geração de energia das películas solares.
     *
     * @return A capacidade das películas solares em kW.
     */
    public double getPeliculaSolar() {
        return peliculaSolar;
    }

    /**
     * Define a capacidade de geração de energia das películas solares.
     *
     * @param peliculaSolar A nova capacidade das películas solares em kW.
     */
    public void setPeliculaSolar(double peliculaSolar) {
        this.peliculaSolar = peliculaSolar;
    }

    /**
     * Obtém o ID da linha de ônibus associada.
     *
     * @return O ID da linha de ônibus associada.
     */
    public Long getIdLinha() {
        return idLinha;
    }

    /**
     * Define o ID da linha de ônibus associada.
     *
     * @param idLinha O novo ID da linha de ônibus associada.
     */
    public void setIdLinha(Long idLinha) {
        this.idLinha = idLinha;
    }

    /**
     * Obtém o ID da garagem associada ao ônibus.
     *
     * @return O ID da garagem associada.
     */
    public Long getIdGaragem() {
        return idGaragem;
    }

    /**
     * Define o ID da garagem associada ao ônibus.
     *
     * @param idGaragem O novo ID da garagem associada.
     */
    public void setIdGaragem(Long idGaragem) {
        this.idGaragem = idGaragem;
    }

    // Métodos Específicos

    /**
     * Gera energia a partir dos painéis e películas solares.
     * A energia gerada é adicionada ao nível de carga atual do ônibus, respeitando o limite da bateria.
     */
    public void gerarEnergiaSolar() {
        double energiaGerada = painelSolar + peliculaSolar;
        setNivelCargaAtual(Math.min(getNivelCargaAtual() + energiaGerada, getCapacidadeBateria()));
        System.out.println("Energia solar gerada: " + energiaGerada + " kW adicionados à carga atual.");
    }

    /**
     * Carrega a bateria do ônibus até o limite da capacidade.
     *
     * @param quantidade Quantidade de carga a ser adicionada em kWh.
     */
    @Override
    public void carregar(double quantidade) {
        setNivelCargaAtual(Math.min(getNivelCargaAtual() + quantidade, getCapacidadeBateria()));
        System.out.println("Carregando ônibus: Carga atual é " + getNivelCargaAtual() + " kWh.");
    }

    /**
     * Retorna uma representação textual do ônibus.
     *
     * @return Uma String contendo as informações do ônibus.
     */
    @Override
    public String toString() {
        return super.toString() + ", ID Ônibus: " + idOnibus + ", Prefixo: " + prefixo +
                ", Capacidade de Passageiros: " + capacidadePassageiros +
                ", Painel Solar: " + painelSolar + " kW, Película Solar: " + peliculaSolar + " kW" +
                ", ID Linha: " + idLinha + ", ID Garagem: " + idGaragem;
    }
}
