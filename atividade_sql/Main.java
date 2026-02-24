import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {

            LivroDAO dao = new LivroDAO();

            // Inserindo dois livros
            Livro livro1 = new Livro("Dom Casmurro", "Machado de Assis", 1899);
            Livro livro2 = new Livro("O Hobbit", "J.R.R. Tolkien", 1937);

            dao.inserir(livro1);
            dao.inserir(livro2);

            // Listando livros
            List<Livro> livros = dao.listar();

            System.out.println("=== LISTA DE LIVROS ===");
            for (Livro l : livros) {
                System.out.println(l);
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}