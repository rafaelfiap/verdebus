package org.example.daos;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.interfaces.OperadorDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.Operador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Operador, gerenciando operações de CRUD no banco de dados.
 *
 * <p>Fornece métodos para gerenciar operadores associados às garagens, incluindo inserção, atualização,
 * exclusão e recuperação de dados.</p>
 *
 * @version 1.0
 * @since 1.0
 */
class OperadorDaoImpl implements OperadorDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de Operador no banco de dados.
     *
     * @return Lista de instâncias de {@link Operador} encontradas no banco de dados.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public List<Operador> findAll() throws SQLException {
        final List<Operador> operadores = new ArrayList<>();
        final String sql = "SELECT * FROM G_OPERADOR";
        try (Connection conn = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera pelos resultados e cria instâncias de Operador.
            while (rs.next()) {
                Operador operador = new Operador(
                        rs.getLong("id_operador"),
                        rs.getString("nm_operador"),
                        rs.getString("nr_cpf"),
                        rs.getLong("id_garagem")
                );
                operadores.add(operador);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao buscar operadores: " + e.getMessage());
            throw e;
        }
        return operadores;
    }

    /**
     * Remove um Operador pelo ID no banco de dados.
     *
     * @param id         O ID do Operador a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se o Operador com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws NotFoundException, SQLException {
        final String sql = "DELETE FROM G_OPERADOR WHERE id_operador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            // Verifica se alguma linha foi alterada. Caso contrário, lança uma exceção.
            if (linhasAfetadas == 0) {
                throw new NotFoundException("Operador não encontrado para o ID fornecido: " + id);
            }
        }
    }

    /**
     * Salva um novo Operador no banco de dados.
     *
     * @param operador   A instância de Operador a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return O Operador salvo com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se o Operador não puder ser salvo.
     */
    @Override
    public Operador save(Operador operador, Connection connection) throws SQLException, NotSavedException {
        final String sql = """
                BEGIN INSERT INTO G_OPERADOR (nm_operador, nr_cpf, id_garagem) 
                VALUES (?, ?, ?) RETURNING id_operador INTO ?; END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, operador.getNome());
        call.setString(2, operador.getCpf());
        call.setLong(3, operador.getIdGaragem());
        call.registerOutParameter(4, Types.NUMERIC);

        int linhasAfetadas = call.executeUpdate();
        long id = call.getLong(4);

        // Verifica se a inserção foi bem-sucedida. Caso contrário, lança uma exceção.
        if (linhasAfetadas == 0 || id == 0) {
            throw new NotSavedException("Erro ao salvar o operador: verifique os dados.");
        }

        operador.setIdOperador(id);
        return operador;
    }

    /**
     * Atualiza um Operador existente no banco de dados.
     *
     * @param operador   A instância de Operador com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return O Operador atualizado.
     * @throws NotFoundException Se o Operador com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Operador update(Operador operador, Connection connection) throws NotFoundException, SQLException {
        final String sql = """
                UPDATE G_OPERADOR 
                SET nm_operador = ?, nr_cpf = ?, id_garagem = ? 
                WHERE id_operador = ?
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, operador.getNome());
        stmt.setString(2, operador.getCpf());
        stmt.setLong(3, operador.getIdGaragem());
        stmt.setLong(4, operador.getIdOperador());

        int linhasAfetadas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Caso contrário, lança uma exceção.
        if (linhasAfetadas == 0) {
            throw new NotFoundException("Operador não encontrado para o ID fornecido: " + operador.getIdOperador());
        }
        return operador;
    }
}
