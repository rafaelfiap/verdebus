package org.example.daos;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.interfaces.ConsumoDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.Consumo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Consumo, gerenciando operações de CRUD.
 *
 * @version 1.0
 * @since 1.0
 */
class ConsumoDaoImpl implements ConsumoDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de Consumo no banco de dados.
     *
     * @return Lista de instâncias de {@link Consumo} encontradas no banco de dados.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public List<Consumo> findAll() throws SQLException {
        List<Consumo> consumos = new ArrayList<>();
        final String sql = "SELECT * FROM G_CONSUMO";
        try (Connection conn = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera pelos resultados e cria instâncias de Consumo.
            while (rs.next()) {
                Consumo consumo = new Consumo(
                        rs.getLong("id_consumo"),
                        rs.getDouble("qt_consumo_por_km"),
                        rs.getDouble("qt_distancia_percorrida"),
                        rs.getDouble("qt_energia_total_consumida"),
                        rs.getLong("id_onibus")
                );
                consumos.add(consumo);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao buscar consumos: " + e.getMessage());
            throw e;
        }
        return consumos;
    }

    /**
     * Remove um Consumo pelo ID no banco de dados.
     *
     * @param id O ID do Consumo a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se o Consumo com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws NotFoundException, SQLException {
        final String sql = "DELETE FROM G_CONSUMO WHERE id_consumo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            // Verifica se alguma linha foi alterada. Caso contrário, lança uma exceção.
            if (linhasAfetadas == 0) {
                throw new NotFoundException("Consumo não encontrado para o ID fornecido: " + id);
            }
        }
    }

    /**
     * Salva um novo Consumo no banco de dados.
     *
     * @param consumo    A instância de Consumo a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return O Consumo salvo com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se o Consumo não puder ser salvo.
     */
    @Override
    public Consumo save(Consumo consumo, Connection connection) throws SQLException, NotSavedException {
        final String sql = """
                BEGIN INSERT INTO G_CONSUMO (qt_consumo_por_km, qt_distancia_percorrida, qt_energia_total_consumida, id_onibus) 
                VALUES (?, ?, ?, ?) RETURNING id_consumo INTO ?; END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setDouble(1, consumo.getConsumoPorKm());
        call.setDouble(2, consumo.getDistanciaPercorrida());
        call.setDouble(3, consumo.getEnergiaTotalConsumida());
        call.setLong(4, consumo.getIdOnibus());
        call.registerOutParameter(5, Types.NUMERIC);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(5);

        // Verifica se a inserção foi bem-sucedida. Caso contrário, lança uma exceção.
        if (linhasAlteradas == 0 || id == 0) {
            throw new NotSavedException("Erro ao salvar o consumo: verifique os dados.");
        }

        consumo.setIdConsumo(id);
        return consumo;
    }

    /**
     * Atualiza um Consumo existente no banco de dados.
     *
     * @param consumo    A instância de Consumo com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return O Consumo atualizado.
     * @throws NotFoundException Se o Consumo com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Consumo update(Consumo consumo, Connection connection) throws NotFoundException, SQLException {
        final String sql = """
                UPDATE G_CONSUMO 
                SET qt_consumo_por_km = ?, qt_distancia_percorrida = ?, qt_energia_total_consumida = ?, id_onibus = ? 
                WHERE id_consumo = ?
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, consumo.getConsumoPorKm());
            stmt.setDouble(2, consumo.getDistanciaPercorrida());
            stmt.setDouble(3, consumo.getEnergiaTotalConsumida());
            stmt.setLong(4, consumo.getIdOnibus());
            stmt.setLong(5, consumo.getIdConsumo());

            int linhasAfetadas = stmt.executeUpdate();

            // Verifica se alguma linha foi alterada.
            if (linhasAfetadas == 0) {
                throw new NotFoundException("Consumo não encontrado para o ID fornecido: " + consumo.getIdConsumo());
            }
        }
        return consumo;
    }
}
