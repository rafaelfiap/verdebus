package org.example.daos;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.interfaces.LinhaDeOnibusDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.LinhaDeOnibus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade LinhaDeOnibus, gerenciando operações de CRUD no banco de dados.
 *
 * @version 1.0
 * @since 1.0
 */
class LinhaDeOnibusDaoImpl implements LinhaDeOnibusDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de LinhaDeOnibus no banco de dados.
     *
     * @return Lista de instâncias de {@link LinhaDeOnibus} encontradas no banco de dados.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public List<LinhaDeOnibus> findAll() throws SQLException {
        final List<LinhaDeOnibus> linhas = new ArrayList<>();
        final String sql = "SELECT * FROM G_LINHA_DE_ONIBUS";
        try (Connection conn = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera pelos resultados e cria instâncias de LinhaDeOnibus.
            while (rs.next()) {
                LinhaDeOnibus linha = new LinhaDeOnibus(
                        rs.getLong("id_linha"),
                        rs.getString("nr_linha"),
                        rs.getString("nm_linha")
                );
                linhas.add(linha);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao buscar linhas de ônibus: " + e.getMessage());
            throw e;
        }
        return linhas;
    }

    /**
     * Remove uma LinhaDeOnibus pelo ID no banco de dados.
     *
     * @param id O ID da LinhaDeOnibus a ser removida.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se a LinhaDeOnibus com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws NotFoundException, SQLException {
        final String sql = "DELETE FROM G_LINHA_DE_ONIBUS WHERE id_linha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            // Verifica se alguma linha foi alterada. Caso contrário, lança uma exceção.
            if (linhasAfetadas == 0) {
                throw new NotFoundException("Linha de Ônibus não encontrada para o ID fornecido: " + id);
            }
        }
    }

    /**
     * Salva uma nova LinhaDeOnibus no banco de dados.
     *
     * @param linha A instância de LinhaDeOnibus a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A LinhaDeOnibus salva com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se a LinhaDeOnibus não puder ser salva.
     */
    @Override
    public LinhaDeOnibus save(LinhaDeOnibus linha, Connection connection) throws SQLException, NotSavedException {
        final String sql = """
                BEGIN INSERT INTO G_LINHA_DE_ONIBUS (nr_linha, nm_linha) 
                VALUES (?, ?) RETURNING id_linha INTO ?; END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, linha.getCodigoLinha());
        call.setString(2, linha.getNome());
        call.registerOutParameter(3, Types.NUMERIC);

        int linhasAfetadas = call.executeUpdate();
        long id = call.getLong(3);

        // Verifica se a inserção foi bem-sucedida. Caso contrário, lança uma exceção.
        if (linhasAfetadas == 0 || id == 0) {
            throw new NotSavedException("Erro ao salvar a linha de ônibus: verifique os dados.");
        }

        linha.setIdLinha(id);
        return linha;
    }

    /**
     * Atualiza uma LinhaDeOnibus existente no banco de dados.
     *
     * @param linha A instância de LinhaDeOnibus com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A LinhaDeOnibus atualizada.
     * @throws NotFoundException Se a LinhaDeOnibus com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public LinhaDeOnibus update(LinhaDeOnibus linha, Connection connection) throws NotFoundException, SQLException {
        final String sql = """
                UPDATE G_LINHA_DE_ONIBUS 
                SET nr_linha = ?, nm_linha = ? 
                WHERE id_linha = ?
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, linha.getCodigoLinha());
        stmt.setString(2, linha.getNome());
        stmt.setLong(3, linha.getIdLinha());

        int linhasAfetadas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Caso contrário, lança uma exceção.
        if (linhasAfetadas == 0) {
            throw new NotFoundException("Linha de Ônibus não encontrada para o ID fornecido: " + linha.getIdLinha());
        }
        return linha;
    }
}
