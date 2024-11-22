package org.example.daos;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.interfaces.EstacaoRecargaSolarDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.EstacaoRecargaSolar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade EstacaoRecargaSolar, gerenciando operações de CRUD no banco de dados.
 *
 * <p>Gerencia as estações de recarga solar associadas às garagens, incluindo inserção, atualização,
 * exclusão e recuperação de dados.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public class EstacaoRecargaSolarDaoImpl implements EstacaoRecargaSolarDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de EstacaoRecargaSolar no banco de dados.
     *
     * @return Lista de instâncias de {@link EstacaoRecargaSolar} encontradas no banco de dados.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public List<EstacaoRecargaSolar> findAll() throws SQLException {
        final List<EstacaoRecargaSolar> estacoes = new ArrayList<>();
        final String sql = "SELECT * FROM G_ESTACAO_RECARGA_SOLAR";
        try (Connection conn = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera pelos resultados e cria instâncias de EstacaoRecargaSolar.
            while (rs.next()) {
                EstacaoRecargaSolar estacao = new EstacaoRecargaSolar(
                        rs.getLong("id_estacao"),
                        rs.getDouble("qt_potencia_maxima"),
                        rs.getInt("qt_paineis"),
                        rs.getDouble("qt_energia_gerada"),
                        rs.getString("st_ocupada").equals("S"),
                        rs.getLong("id_garagem")
                );
                estacoes.add(estacao);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao buscar estações de recarga solar: " + e.getMessage());
            throw e;
        }
        return estacoes;
    }

    /**
     * Remove uma EstacaoRecargaSolar pelo ID no banco de dados.
     *
     * @param id         O ID da EstacaoRecargaSolar a ser removida.
     * @param connection Conexão ativa com o banco de dados.
     * @throws NotFoundException Se a estação com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws NotFoundException, SQLException {
        final String sql = "DELETE FROM G_ESTACAO_RECARGA_SOLAR WHERE id_estacao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            // Verifica se alguma linha foi alterada. Caso contrário, lança uma exceção.
            if (linhasAfetadas == 0) {
                throw new NotFoundException("Estação de Recarga Solar não encontrada para o ID fornecido: " + id);
            }
        }
    }

    /**
     * Salva uma nova EstacaoRecargaSolar no banco de dados.
     *
     * @param estacao    A instância de EstacaoRecargaSolar a ser salva.
     * @param connection Conexão ativa com o banco de dados.
     * @return A EstacaoRecargaSolar salva com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se a EstacaoRecargaSolar não puder ser salva.
     */
    @Override
    public EstacaoRecargaSolar save(EstacaoRecargaSolar estacao, Connection connection) throws SQLException, NotSavedException {
        final String sql = """
                BEGIN INSERT INTO G_ESTACAO_RECARGA_SOLAR (qt_potencia_maxima, qt_paineis, qt_energia_gerada, st_ocupada, id_garagem) 
                VALUES (?, ?, ?, ?, ?) RETURNING id_estacao INTO ?; END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setDouble(1, estacao.getPotenciaMaxima());
        call.setInt(2, estacao.getNumeroPaineis());
        call.setDouble(3, estacao.getEnergiaGerada());
        call.setString(4, estacao.isOcupada() ? "S" : "N");
        call.setLong(5, estacao.getIdGaragem());
        call.registerOutParameter(6, Types.NUMERIC);

        int linhasAfetadas = call.executeUpdate();
        long id = call.getLong(6);

        // Verifica se a inserção foi bem-sucedida. Caso contrário, lança uma exceção.
        if (linhasAfetadas == 0 || id == 0) {
            throw new NotSavedException("Erro ao salvar a EstacaoRecargaSolar: verifique os dados.");
        }

        estacao.setIdEstacao(id);
        return estacao;
    }

    /**
     * Atualiza uma EstacaoRecargaSolar existente no banco de dados.
     *
     * @param estacao    A instância de EstacaoRecargaSolar com os dados atualizados.
     * @param connection Conexão ativa com o banco de dados.
     * @return A EstacaoRecargaSolar atualizada.
     * @throws NotFoundException Se a EstacaoRecargaSolar com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public EstacaoRecargaSolar update(EstacaoRecargaSolar estacao, Connection connection) throws NotFoundException, SQLException {
        final String sql = """
                UPDATE G_ESTACAO_RECARGA_SOLAR 
                SET qt_potencia_maxima = ?, qt_paineis = ?, qt_energia_gerada = ?, st_ocupada = ?, id_garagem = ? 
                WHERE id_estacao = ?
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setDouble(1, estacao.getPotenciaMaxima());
        stmt.setInt(2, estacao.getNumeroPaineis());
        stmt.setDouble(3, estacao.getEnergiaGerada());
        stmt.setString(4, estacao.isOcupada() ? "S" : "N");
        stmt.setLong(5, estacao.getIdGaragem());
        stmt.setLong(6, estacao.getIdEstacao());

        int linhasAfetadas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Caso contrário, lança uma exceção.
        if (linhasAfetadas == 0) {
            throw new NotFoundException("EstacaoRecargaSolar não encontrada para o ID fornecido: " + estacao.getIdEstacao());
        }
        return estacao;
    }
}
