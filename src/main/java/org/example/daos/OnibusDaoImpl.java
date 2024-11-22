package org.example.daos;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.interfaces.OnibusDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.Onibus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Onibus, gerenciando operações de CRUD.
 *
 * @version 1.0
 * @since 1.0
 */
class OnibusDaoImpl implements OnibusDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de Onibus no banco de dados.
     *
     * @return Lista de instâncias de {@link Onibus} encontradas no banco de dados.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public List<Onibus> findAll() throws SQLException {
        final List<Onibus> onibusList = new ArrayList<>();
        final String sql = "SELECT * FROM G_ONIBUS";

        try (Connection conn = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera pelos resultados e cria instâncias de Onibus.
            while (rs.next()) {
                Onibus onibus = new Onibus(
                        rs.getLong("id_onibus"),
                        rs.getInt("nr_prefixo"),
                        rs.getInt("qt_capacidade_passageiros"),
                        rs.getDouble("qt_painel_solar"),
                        rs.getDouble("qt_pelicula_solar"),
                        rs.getString("ds_placa"),
                        rs.getString("ds_modelo"),
                        rs.getString("nm_fabricante"),
                        rs.getInt("nr_ano_fabricacao"),
                        rs.getDouble("qt_capacidade_bateria"),
                        rs.getLong("id_garagem"),
                        rs.getLong("id_linha")
                );
                onibusList.add(onibus);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao buscar ônibus: " + e.getMessage());
            throw e;
        }
        return onibusList;
    }

    /**
     * Remove um Onibus pelo ID no banco de dados.
     *
     * @param id         O ID do Onibus a ser removido.
     * @param connection Conexão ativa com o banco de dados.
     * @throws NotFoundException Se o Onibus com o ID fornecido não for encontrado.
     * @throws SQLException      Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws NotFoundException, SQLException {
        final String sql = "DELETE FROM G_ONIBUS WHERE id_onibus = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            // Verifica se alguma linha foi alterada.
            if (linhasAfetadas == 0) {
                throw new NotFoundException("Ônibus não encontrado para o ID fornecido: " + id);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao excluir ônibus com ID: " + id + ". " + e.getMessage());
            throw e;
        }
    }

    /**
     * Salva um novo Onibus no banco de dados.
     *
     * @param onibus     A instância de Onibus a ser salva.
     * @param connection Conexão ativa com o banco de dados.
     * @return O Onibus salvo com o ID gerado.
     * @throws SQLException      Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se o Onibus não puder ser salvo.
     */
    @Override
    public Onibus save(Onibus onibus, Connection connection) throws SQLException, NotSavedException {
        final String sql = """
                BEGIN INSERT INTO G_ONIBUS (nr_prefixo, qt_capacidade_passageiros, qt_painel_solar, qt_pelicula_solar, ds_placa, 
                                            ds_modelo, nm_fabricante, nr_ano_fabricacao, qt_capacidade_bateria, id_garagem, id_linha) 
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_onibus INTO ?; END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setInt(1, onibus.getPrefixo());
        call.setInt(2, onibus.getCapacidadePassageiros());
        call.setDouble(3, onibus.getPainelSolar());
        call.setDouble(4, onibus.getPeliculaSolar());
        call.setString(5, onibus.getPlaca());
        call.setString(6, onibus.getModelo());
        call.setString(7, onibus.getFabricante());
        call.setInt(8, onibus.getAnoFabricacao());
        call.setDouble(9, onibus.getCapacidadeBateria());
        call.setLong(10, onibus.getIdGaragem());
        call.setLong(11, onibus.getIdLinha());
        call.registerOutParameter(12, Types.NUMERIC);

        int linhasAfetadas = call.executeUpdate();
        long id = call.getLong(12);

        // Verifica se a inserção foi bem-sucedida.
        if (linhasAfetadas == 0 || id == 0) {
            throw new NotSavedException("Erro ao salvar o ônibus: verifique os dados.");
        }

        onibus.setIdOnibus(id);
        return onibus;
    }

    /**
     * Atualiza um Onibus existente no banco de dados.
     *
     * @param onibus     A instância de Onibus com os dados atualizados.
     * @param connection Conexão ativa com o banco de dados.
     * @return O Onibus atualizado.
     * @throws NotFoundException Se o Onibus com o ID fornecido não for encontrado.
     * @throws SQLException      Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Onibus update(Onibus onibus, Connection connection) throws NotFoundException, SQLException {
        final String sql = """
                UPDATE G_ONIBUS 
                SET nr_prefixo = ?, qt_capacidade_passageiros = ?, qt_painel_solar = ?, qt_pelicula_solar = ?, ds_placa = ?, 
                    ds_modelo = ?, nm_fabricante = ?, nr_ano_fabricacao = ?, qt_capacidade_bateria = ?, id_garagem = ?, id_linha = ? 
                WHERE id_onibus = ?
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, onibus.getPrefixo());
        stmt.setInt(2, onibus.getCapacidadePassageiros());
        stmt.setDouble(3, onibus.getPainelSolar());
        stmt.setDouble(4, onibus.getPeliculaSolar());
        stmt.setString(5, onibus.getPlaca());
        stmt.setString(6, onibus.getModelo());
        stmt.setString(7, onibus.getFabricante());
        stmt.setInt(8, onibus.getAnoFabricacao());
        stmt.setDouble(9, onibus.getCapacidadeBateria());
        stmt.setLong(10, onibus.getIdGaragem());
        stmt.setLong(11, onibus.getIdLinha());
        stmt.setLong(12, onibus.getIdOnibus());

        int linhasAfetadas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada.
        if (linhasAfetadas == 0) {
            throw new NotFoundException("Ônibus não encontrado para o ID fornecido: " + onibus.getIdOnibus());
        }
        return onibus;
    }
}
