package mjv.sistemabiblioteca.livro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {
	Optional<Livro> findByTitulo(String Titulo);

	Optional<Livro> findByIsbn(String isbn);
}
