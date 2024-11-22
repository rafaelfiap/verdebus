package org.example.daos;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.interfaces.MotoDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.Moto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Moto, gerenciando operações de CRUD.
 *
 * @version 1.0
 * @since 1.0
 */
class MotoDaoImpl implements MotoDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de Moto no banco de dados.
     *
     * @return Lista de instâncias de {@link Moto} encontradas no banco de dados.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public List<Moto> findAll() throws SQLException {
        List<Moto> motos = new ArrayList<>();
        final String sql = "SELECT * FROM G_MOTO";
        try (Connection conn = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera pelos resultados e cria instâncias de Moto.
            while (rs.next()) {
                Moto moto = new Moto(
                        rs.getLong("id_moto"),
                        rs.getInt("nr_prefixo"),
                        rs.getString("ds_tipo_licenca"),
                        rs.getString("ds_placa"),
                        rs.getString("ds_modelo"),
                        rs.getString("nm_fabricante"),
                        rs.getInt("nr_ano_fabricacao"),
                        rs.getDouble("qt_capacidade_bateria"),
                        rs.getLong("id_garagem")
                );
                motos.add(moto);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao buscar motos: " + e.getMessage());
            throw e;
        }
        return motos;
    }

    /**
     * Remove uma Moto pelo ID no banco de dados.
     *
     * @param id O ID da Moto a ser removida.
     * @param connection Conexão com o banco de dados.
     * @throws NotFoundException Se a Moto com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws NotFoundException, SQLException {
        final String sql = "DELETE FROM G_MOTO WHERE id_moto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            // Verifica se alguma linha foi alterada. Caso contrário, lança uma exceção.
            if (linhasAfetadas == 0) {
                throw new NotFoundException("Moto não encontrada para o ID fornecido: " + id);
            }
        }
    }

    /**
     * Salva uma nova Moto no banco de dados.
     *
     * @param moto       A instância de Moto a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A Moto salva com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se a Moto não puder ser salva.
     */
    @Override
    public Moto save(Moto moto, Connection connection) throws SQLException, NotSavedException {
        final String sql = """
                BEGIN INSERT INTO G_MOTO (nr_prefixo, ds_tipo_licenca, ds_placa, ds_modelo, nm_fabricante, nr_ano_fabricacao, qt_capacidade_bateria, id_garagem) 
                VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_moto INTO ?; END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setInt(1, moto.getPrefixo());
        call.setString(2, moto.getTipoLicenca());
        call.setString(3, moto.getPlaca());
        call.setString(4, moto.getModelo());
        call.setString(5, moto.getFabricante());
        call.setInt(6, moto.getAnoFabricacao());
        call.setDouble(7, moto.getCapacidadeBateria());
        call.setLong(8, moto.getIdGaragem());
        call.registerOutParameter(9, Types.NUMERIC);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(9);

        // Verifica se a inserção foi bem-sucedida. Caso contrário, lança uma exceção.
        if (linhasAlteradas == 0 || id == 0) {
            throw new NotSavedException("Erro ao salvar a moto: verifique os dados.");
        }

        moto.setIdMoto(id);
        return moto;
    }

    /**
     * Atualiza uma Moto existente no banco de dados.
     *
     * @param moto       A instância de Moto com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A Moto atualizada.
     * @throws NotFoundException Se a Moto com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Moto update(Moto moto, Connection connection) throws NotFoundException, SQLException {
        final String sql = """
                UPDATE G_MOTO 
                SET nr_prefixo = ?, ds_tipo_licenca = ?, ds_placa = ?, ds_modelo = ?, nm_fabricante = ?, nr_ano_fabricacao = ?, qt_capacidade_bateria = ?, id_garagem = ? 
                WHERE id_moto = ?
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, moto.getPrefixo());
        stmt.setString(2, moto.getTipoLicenca());
        stmt.setString(3, moto.getPlaca());
        stmt.setString(4, moto.getModelo());
        stmt.setString(5, moto.getFabricante());
        stmt.setInt(6, moto.getAnoFabricacao());
        stmt.setDouble(7, moto.getCapacidadeBateria());
        stmt.setLong(8, moto.getIdGaragem());
        stmt.setLong(9, moto.getIdMoto());

        int linhasAfetadas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Caso contrário, lança uma exceção.
        if (linhasAfetadas == 0) {
            throw new NotFoundException("Moto não encontrada para o ID fornecido: " + moto.getIdMoto());
        }
        return moto;
    }
}
