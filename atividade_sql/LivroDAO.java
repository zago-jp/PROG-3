import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void inserir(Livro livro) throws Exception {

        String sql = "INSERT INTO livro (titulo, autor, ano) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());

            stmt.executeUpdate();
        }
    }

    public List<Livro> listar() throws Exception {

        List<Livro> lista = new ArrayList<>();

        String sql = "SELECT * FROM livro";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Livro l = new Livro();
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setAno(rs.getInt("ano"));
                lista.add(l);
            }
        }

        return lista;
    }
}