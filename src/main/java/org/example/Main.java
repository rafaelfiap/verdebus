package org.example;

import org.example.models.Onibus;
import org.example.models.Consumo;

public class Main {
    public static void main(String[] args) {
        // Criando um objeto Onibus com atributos completos
        // ID: 1, Prefixo: 101, Placa: ABC1234, Modelo: Modelo X, Fabricante: Fabricante Y, Ano: 2022
        // Capacidade da bateria: 300 kWh, Capacidade de passageiros: 50
        // Painel solar: 10 kW, Película solar: 5 kW, ID da linha: 1, ID da garagem: 1
        Onibus onibus = new Onibus(
                1L, 101, "ABC1234", "Modelo X", "Fabricante Y", 2022,
                300.0, 50, 10.0, 5.0, 1L, 1L
        );

        // Configurando o nível inicial de carga da bateria do ônibus para 200 kWh
        onibus.setNivelCargaAtual(200.0);

        // Testando a geração de energia solar
        // A energia gerada pelo painel solar e pela película solar será adicionada à carga atual da bateria
        onibus.gerarEnergiaSolar();
        System.out.println("Nível de carga após geração solar: " + onibus.getNivelCargaAtual() + " kWh.");

        // Criando um registro de consumo para o ônibus
        // ID do consumo: 1, Consumo médio: 2.5 kWh/km, ID do ônibus: 1
        Consumo consumo = new Consumo(1L, 2.5, 0.0, 0.0, onibus.getIdOnibus());

        // Registrando um trajeto no consumo do ônibus
        // Distância percorrida: 50 km
        double distanciaPercorrida = 50.0;
        consumo.registrarTrajeto(distanciaPercorrida);

        // Atualizando o nível de carga do ônibus com base no consumo registrado
        // Energia consumida é calculada como distância percorrida * consumo médio
        double energiaConsumida = distanciaPercorrida * consumo.getConsumoPorKm();
        onibus.setNivelCargaAtual(onibus.getNivelCargaAtual() - energiaConsumida);

        // Exibindo informações detalhadas após o trajeto
        System.out.println("Trajeto registrado: " + distanciaPercorrida + " km.");
        System.out.println("Energia consumida: " + energiaConsumida + " kWh.");
        System.out.println("Nível de carga após viagem: " + onibus.getNivelCargaAtual() + " kWh.");
        System.out.println("Consumo registrado: " + consumo);
    }
}
