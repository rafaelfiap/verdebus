package org.example.daos;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.interfaces.PontoRecargaDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.PontoRecarga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade PontoRecarga, gerenciando operações de CRUD.
 *
 * @version 1.0
 * @since 1.0
 */
class PontoRecargaDaoImpl implements PontoRecargaDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de PontoRecarga no banco de dados.
     *
     * @return Lista de instâncias de {@link PontoRecarga} encontradas no banco de dados.
     * @throws SQLException Se ocorrer algum erro ao acessar o banco de dados.
     */
    @Override
    public List<PontoRecarga> findAll() throws SQLException {
        List<PontoRecarga> pontosRecarga = new ArrayList<>();
        final String sql = "SELECT id_ponto, qt_potencia, st_ocupado, id_linha FROM G_PONTO_RECARGA";

        try (Connection conn = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera pelos resultados e cria instâncias de PontoRecarga.
            while (rs.next()) {
                PontoRecarga pontoRecarga = new PontoRecarga(
                        rs.getLong("id_ponto"),
                        rs.getDouble("qt_potencia"),
                        rs.getString("st_ocupado").equals("S"),
                        rs.getLong("id_linha")
                );
                pontosRecarga.add(pontoRecarga);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao recuperar pontos de recarga: " + e.getMessage());
            throw e;
        }
        return pontosRecarga;
    }

    /**
     * Remove um PontoRecarga pelo ID no banco de dados.
     *
     * @param id O ID do PontoRecarga a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se o PontoRecarga com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws NotFoundException, SQLException {
        final String sql = "DELETE FROM G_PONTO_RECARGA WHERE id_ponto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int linhasAlteradas = stmt.executeUpdate();

            // Verifica se alguma linha foi alterada. Caso contrário, lança exceção.
            if (linhasAlteradas == 0) {
                throw new NotFoundException("Ponto de Recarga não encontrado para o ID fornecido: " + id);
            }
        }
    }

    /**
     * Salva um novo PontoRecarga no banco de dados.
     *
     * @param pontoRecarga A instância de PontoRecarga a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return O PontoRecarga salvo com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se o PontoRecarga não puder ser salvo.
     */
    @Override
    public PontoRecarga save(PontoRecarga pontoRecarga, Connection connection) throws SQLException, NotSavedException {
        final String sql = """
                BEGIN 
                INSERT INTO G_PONTO_RECARGA (qt_potencia, st_ocupado, id_linha) 
                VALUES (?, ?, ?) 
                RETURNING id_ponto INTO ?; 
                END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setDouble(1, pontoRecarga.getPotencia());
        call.setString(2, pontoRecarga.isOcupado() ? "S" : "N");
        call.setLong(3, pontoRecarga.getIdLinha());
        call.registerOutParameter(4, Types.NUMERIC);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(4);

        // Verifica se a inserção foi bem-sucedida. Caso contrário, lança exceção.
        if (linhasAlteradas == 0 || id == 0) {
            throw new NotSavedException("Erro ao salvar o ponto de recarga: verifique os dados.");
        }

        pontoRecarga.setIdPonto(id);
        return pontoRecarga;
    }

    /**
     * Atualiza um PontoRecarga existente no banco de dados.
     *
     * @param pontoRecarga A instância de PontoRecarga com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return O PontoRecarga atualizado.
     * @throws NotFoundException Se o PontoRecarga com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public PontoRecarga update(PontoRecarga pontoRecarga, Connection connection) throws NotFoundException, SQLException {
        final String sql = """
                UPDATE G_PONTO_RECARGA 
                SET qt_potencia = ?, st_ocupado = ?, id_linha = ? 
                WHERE id_ponto = ?
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, pontoRecarga.getPotencia());
            stmt.setString(2, pontoRecarga.isOcupado() ? "S" : "N");
            stmt.setLong(3, pontoRecarga.getIdLinha());
            stmt.setLong(4, pontoRecarga.getIdPonto());

            int linhasAlteradas = stmt.executeUpdate();

            // Verifica se alguma linha foi alterada. Caso contrário, lança exceção.
            if (linhasAlteradas == 0) {
                throw new NotFoundException("Ponto de Recarga não encontrado para o ID fornecido: " + pontoRecarga.getIdPonto());
            }
        }
        return pontoRecarga;
    }
}
