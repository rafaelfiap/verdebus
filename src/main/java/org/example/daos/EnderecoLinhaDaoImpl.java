package org.example.daos;

import org.example.config.DatabaseConnectionFactory;
import org.example.daos.interfaces.EnderecoDao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.models.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Endereco associada a Linhas de Ônibus, gerenciando operações de CRUD.
 *
 * @version 1.0
 * @since 1.0
 */
class EnderecoLinhaDaoImpl implements EnderecoDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de Endereco associadas a Linhas de Ônibus no banco de dados.
     *
     * @return Lista de instâncias de {@link Endereco} encontradas no banco de dados.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public List<Endereco> findAll() throws SQLException {
        final List<Endereco> enderecosLinha = new ArrayList<>();
        final String sql = "SELECT * FROM G_ENDERECO_LINHA";

        try (Connection conn = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Itera pelos resultados e cria instâncias de Endereco.
            while (rs.next()) {
                Endereco endereco = new Endereco(
                        rs.getLong("id_endereco_linha"),
                        rs.getString("ds_logradouro"),
                        rs.getInt("nr_numero"),
                        rs.getString("nr_cep"),
                        rs.getString("nm_bairro"),
                        rs.getString("nm_cidade"),
                        rs.getString("sg_uf"),
                        rs.getInt("id_linha")
                );
                enderecosLinha.add(endereco);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao buscar endereços de linha: " + e.getMessage());
            throw e;
        }
        return enderecosLinha;
    }

    /**
     * Remove um Endereco associado a Linha pelo ID no banco de dados.
     *
     * @param id         O ID do Endereco a ser removido.
     * @param connection Conexão ativa com o banco de dados.
     * @throws NotFoundException Se o Endereco com o ID fornecido não for encontrado.
     * @throws SQLException      Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws NotFoundException, SQLException {
        final String sql = "DELETE FROM G_ENDERECO_LINHA WHERE id_endereco_linha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            // Verifica se alguma linha foi alterada.
            if (linhasAfetadas == 0) {
                throw new NotFoundException("Endereço de Linha não encontrado para o ID fornecido: " + id);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao excluir endereço de linha com ID: " + id + ". " + e.getMessage());
            throw e;
        }
    }

    /**
     * Salva um novo Endereco associado a Linha no banco de dados.
     *
     * @param endereco   A instância de Endereco a ser salva.
     * @param connection Conexão ativa com o banco de dados.
     * @return O Endereco salvo com o ID gerado.
     * @throws SQLException      Se ocorrer um erro ao acessar o banco de dados.
     * @throws NotSavedException Se o Endereco não puder ser salvo.
     */
    @Override
    public Endereco save(Endereco endereco, Connection connection) throws SQLException, NotSavedException {
        final String sql = """
                BEGIN INSERT INTO G_ENDERECO_LINHA (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_linha) 
                VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id_endereco_linha INTO ?; END;
                """;
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, endereco.getLogradouro());
        call.setInt(2, endereco.getNumero());
        call.setString(3, endereco.getCep());
        call.setString(4, endereco.getBairro());
        call.setString(5, endereco.getCidade());
        call.setString(6, endereco.getUf());
        call.setLong(7, endereco.getIdReferencia());
        call.registerOutParameter(8, Types.NUMERIC);

        int linhasAfetadas = call.executeUpdate();
        long id = call.getLong(8);

        // Verifica se a inserção foi bem-sucedida.
        if (linhasAfetadas == 0 || id == 0) {
            throw new NotSavedException("Erro ao salvar o endereço de linha: verifique os dados.");
        }

        endereco.setIdEndereco(id);
        return endereco;
    }

    /**
     * Atualiza um Endereco associado a Linha existente no banco de dados.
     *
     * @param endereco   A instância de Endereco com os dados atualizados.
     * @param connection Conexão ativa com o banco de dados.
     * @return O Endereco atualizado.
     * @throws NotFoundException Se o Endereco com o ID fornecido não for encontrado.
     * @throws SQLException      Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Endereco update(Endereco endereco, Connection connection) throws NotFoundException, SQLException {
        final String sql = """
                UPDATE G_ENDERECO_LINHA 
                SET ds_logradouro = ?, nr_numero = ?, nr_cep = ?, nm_bairro = ?, nm_cidade = ?, sg_uf = ?, id_linha = ? 
                WHERE id_endereco_linha = ?
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, endereco.getLogradouro());
        stmt.setInt(2, endereco.getNumero());
        stmt.setString(3, endereco.getCep());
        stmt.setString(4, endereco.getBairro());
        stmt.setString(5, endereco.getCidade());
        stmt.setString(6, endereco.getUf());
        stmt.setLong(7, endereco.getIdReferencia());
        stmt.setLong(8, endereco.getIdEndereco());

        int linhasAfetadas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada.
        if (linhasAfetadas == 0) {
            throw new NotFoundException("Endereço de Linha não encontrado para o ID fornecido: " + endereco.getIdEndereco());
        }
        return endereco;
    }
}
