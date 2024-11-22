package org.example.daos;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.interfaces.GaragemDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.Garagem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Garagem, gerenciando operações de CRUD.
 *
 * @version 1.0
 * @since 1.0
 */
class GaragemDaoImpl implements GaragemDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de Garagem no banco de dados.
     *
     * @return Lista de instâncias de {@link Garagem} encontradas no banco de dados.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public List<Garagem> findAll() throws SQLException {
        final List<Garagem> garagens = new ArrayList<>();
        final String sql = "SELECT * FROM G_GARAGEM";
        try (Connection conn = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera pelos resultados e cria instâncias de Garagem.
            while (rs.next()) {
                Garagem garagem = new Garagem(
                        rs.getLong("id_garagem"),
                        rs.getString("nm_garagem"),
                        rs.getInt("qt_capacidade")
                );
                garagens.add(garagem);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao buscar garagens: " + e.getMessage());
            throw e;
        }
        return garagens;
    }

    /**
     * Remove uma Garagem pelo ID no banco de dados.
     *
     * @param id O ID da Garagem a ser removida.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se a Garagem com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws NotFoundException, SQLException {
        final String sql = "DELETE FROM G_GARAGEM WHERE id_garagem = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            // Verifica se alguma linha foi alterada. Caso contrário, lança uma exceção.
            if (linhasAfetadas == 0) {
                throw new NotFoundException("Garagem não encontrada para o ID fornecido: " + id);
            }
        }
    }

    /**
     * Salva uma nova Garagem no banco de dados.
     *
     * @param garagem A instância de Garagem a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A Garagem salva com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se a Garagem não puder ser salva.
     */
    @Override
    public Garagem save(Garagem garagem, Connection connection) throws SQLException, NotSavedException {
        final String sql = """
                BEGIN INSERT INTO G_GARAGEM (nm_garagem, qt_capacidade) 
                VALUES (?, ?) RETURNING id_garagem INTO ?; END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, garagem.getNome());
        call.setInt(2, garagem.getCapacidadeOnibus());
        call.registerOutParameter(3, Types.NUMERIC);

        int linhasAfetadas = call.executeUpdate();
        long id = call.getLong(3);

        // Verifica se a inserção foi bem-sucedida. Caso contrário, lança uma exceção.
        if (linhasAfetadas == 0 || id == 0) {
            throw new NotSavedException("Erro ao salvar a garagem: verifique os dados.");
        }

        garagem.setIdGaragem(id);
        return garagem;
    }

    /**
     * Atualiza uma Garagem existente no banco de dados.
     *
     * @param garagem A instância de Garagem com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A Garagem atualizada.
     * @throws NotFoundException Se a Garagem com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Garagem update(Garagem garagem, Connection connection) throws NotFoundException, SQLException {
        final String sql = """
                UPDATE G_GARAGEM 
                SET nm_garagem = ?, qt_capacidade = ? 
                WHERE id_garagem = ?
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, garagem.getNome());
        stmt.setInt(2, garagem.getCapacidadeOnibus());
        stmt.setLong(3, garagem.getIdGaragem());

        int linhasAfetadas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Caso contrário, lança uma exceção.
        if (linhasAfetadas == 0) {
            throw new NotFoundException("Garagem não encontrada para o ID fornecido: " + garagem.getIdGaragem());
        }
        return garagem;
    }
}
