package org.example.daos;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.interfaces.CarregadorDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.Carregador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Carregador, gerenciando operações de CRUD no banco de dados.
 *
 * @version 1.0
 * @since 1.0
 */
class CarregadorDaoImpl implements CarregadorDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de Carregador no banco de dados.
     *
     * @return Lista de instâncias de {@link Carregador} encontradas no banco de dados.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public List<Carregador> findAll() throws SQLException {
        final List<Carregador> carregadores = new ArrayList<>();
        final String sql = "SELECT * FROM G_CARREGADOR";
        try (Connection conn = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera pelos resultados e cria instâncias de Carregador.
            while (rs.next()) {
                Carregador carregador = new Carregador(
                        rs.getLong("id_carregador"),
                        rs.getDouble("qt_potencia"),
                        rs.getString("st_status"),
                        rs.getLong("id_estacao")
                );
                carregadores.add(carregador);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao buscar carregadores: " + e.getMessage());
            throw e;
        }
        return carregadores;
    }

    /**
     * Remove um Carregador pelo ID no banco de dados.
     *
     * @param id O ID do Carregador a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se o Carregador com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws NotFoundException, SQLException {
        final String sql = "DELETE FROM G_CARREGADOR WHERE id_carregador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            // Verifica se alguma linha foi alterada. Caso contrário, lança uma exceção.
            if (linhasAfetadas == 0) {
                throw new NotFoundException("Carregador não encontrado para o ID fornecido: " + id);
            }
        }
    }

    /**
     * Salva um novo Carregador no banco de dados.
     *
     * @param carregador A instância de Carregador a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return O Carregador salvo com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se o Carregador não puder ser salvo.
     */
    @Override
    public Carregador save(Carregador carregador, Connection connection) throws SQLException, NotSavedException {
        final String sql = """
                BEGIN INSERT INTO G_CARREGADOR (st_status, qt_potencia, id_estacao) 
                VALUES (?, ?, ?) RETURNING id_carregador INTO ?; END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, carregador.getStatus());
        call.setDouble(2, carregador.getPotencia());
        call.setLong(3, carregador.getIdEstacaoRecargaSolar());
        call.registerOutParameter(4, Types.NUMERIC);

        int linhasAfetadas = call.executeUpdate();
        long id = call.getLong(4);

        // Verifica se a inserção foi bem-sucedida. Caso contrário, lança uma exceção.
        if (linhasAfetadas == 0 || id == 0) {
            throw new NotSavedException("Erro ao salvar o carregador: verifique os dados.");
        }

        carregador.setIdCarregador(id);
        return carregador;
    }

    /**
     * Atualiza um Carregador existente no banco de dados.
     *
     * @param carregador A instância de Carregador com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return O Carregador atualizado.
     * @throws NotFoundException Se o Carregador com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Carregador update(Carregador carregador, Connection connection) throws NotFoundException, SQLException {
        final String sql = """
                UPDATE G_CARREGADOR 
                SET st_status = ?, qt_potencia = ?, id_estacao = ? 
                WHERE id_carregador = ?
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, carregador.getStatus());
        stmt.setDouble(2, carregador.getPotencia());
        stmt.setLong(3, carregador.getIdEstacaoRecargaSolar());
        stmt.setLong(4, carregador.getIdCarregador());

        int linhasAfetadas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Caso contrário, lança uma exceção.
        if (linhasAfetadas == 0) {
            throw new NotFoundException("Carregador não encontrado para o ID fornecido: " + carregador.getIdCarregador());
        }
        return carregador;
    }
}
