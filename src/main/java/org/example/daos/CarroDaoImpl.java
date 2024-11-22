package org.example.daos;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.interfaces.CarroDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.Carro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Carro, gerenciando operações de CRUD.
 *
 * @version 1.0
 * @since 1.0
 */
class CarroDaoImpl implements CarroDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de Carro no banco de dados.
     *
     * @return Lista de instâncias de {@link Carro} encontradas no banco de dados.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public List<Carro> findAll() throws SQLException {
        final List<Carro> carros = new ArrayList<>();
        final String sql = "SELECT * FROM G_CARRO";
        try (Connection conn = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera pelos resultados e cria instâncias de Carro.
            while (rs.next()) {
                Carro carro = new Carro(
                        rs.getLong("id_carro"),
                        rs.getInt("nr_prefixo"),
                        rs.getInt("qt_portas"),
                        rs.getString("ds_placa"),
                        rs.getString("ds_modelo"),
                        rs.getString("nm_fabricante"),
                        rs.getInt("nr_ano_fabricacao"),
                        rs.getDouble("qt_capacidade_bateria"),
                        rs.getLong("id_garagem")
                );
                carros.add(carro);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao buscar carros: " + e.getMessage());
            throw e;
        }
        return carros;
    }

    /**
     * Remove um Carro pelo ID no banco de dados.
     *
     * @param id         O ID do Carro a ser removido.
     * @param connection Conexão ativa com o banco de dados.
     * @throws NotFoundException Se o Carro com o ID fornecido não for encontrado.
     * @throws SQLException      Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws NotFoundException, SQLException {
        final String sql = "DELETE FROM G_CARRO WHERE id_carro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();

            // Verifica se a exclusão foi bem-sucedida.
            if (rowsAffected == 0) {
                throw new NotFoundException("Carro não encontrado para o ID fornecido: " + id);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao excluir carro com ID: " + id + ". " + e.getMessage());
            throw e;
        }
    }

    /**
     * Salva um novo Carro no banco de dados.
     *
     * @param carro      A instância de Carro a ser salva.
     * @param connection Conexão ativa com o banco de dados.
     * @return O Carro salvo com o ID gerado.
     * @throws SQLException      Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se o Carro não puder ser salvo.
     */
    @Override
    public Carro save(Carro carro, Connection connection) throws SQLException, NotSavedException {
        final String sql = """
                BEGIN INSERT INTO G_CARRO (nr_prefixo, qt_portas, ds_placa, ds_modelo, nm_fabricante, nr_ano_fabricacao, 
                                           qt_capacidade_bateria, id_garagem) 
                VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_carro INTO ?; END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setInt(1, carro.getPrefixo());
        call.setInt(2, carro.getNumeroPortas());
        call.setString(3, carro.getPlaca());
        call.setString(4, carro.getModelo());
        call.setString(5, carro.getFabricante());
        call.setInt(6, carro.getAnoFabricacao());
        call.setDouble(7, carro.getCapacidadeBateria());
        call.setLong(8, carro.getIdGaragem());
        call.registerOutParameter(9, Types.NUMERIC);

        int rowsAffected = call.executeUpdate();
        long id = call.getLong(9);

        // Verifica se a inserção foi bem-sucedida.
        if (rowsAffected == 0 || id == 0) {
            throw new NotSavedException("Erro ao salvar o carro: Nenhuma linha foi afetada.");
        }

        carro.setIdCarro(id);
        return carro;
    }

    /**
     * Atualiza um Carro existente no banco de dados.
     *
     * @param carro      A instância de Carro com os dados atualizados.
     * @param connection Conexão ativa com o banco de dados.
     * @return O Carro atualizado.
     * @throws NotFoundException Se o Carro com o ID fornecido não for encontrado.
     * @throws SQLException      Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Carro update(Carro carro, Connection connection) throws NotFoundException, SQLException {
        final String sql = """
                UPDATE G_CARRO 
                SET nr_prefixo = ?, qt_portas = ?, ds_placa = ?, ds_modelo = ?, nm_fabricante = ?, 
                    nr_ano_fabricacao = ?, qt_capacidade_bateria = ?, id_garagem = ? 
                WHERE id_carro = ?
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, carro.getPrefixo());
        stmt.setInt(2, carro.getNumeroPortas());
        stmt.setString(3, carro.getPlaca());
        stmt.setString(4, carro.getModelo());
        stmt.setString(5, carro.getFabricante());
        stmt.setInt(6, carro.getAnoFabricacao());
        stmt.setDouble(7, carro.getCapacidadeBateria());
        stmt.setLong(8, carro.getIdGaragem());
        stmt.setLong(9, carro.getIdCarro());

        int rowsAffected = stmt.executeUpdate();

        // Verifica se a atualização foi bem-sucedida.
        if (rowsAffected == 0) {
            throw new NotFoundException("Carro não encontrado para o ID fornecido: " + carro.getIdCarro());
        }
        return carro;
    }
}
