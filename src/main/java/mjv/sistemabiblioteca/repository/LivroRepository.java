package mjv.sistemabiblioteca.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mjv.sistemabiblioteca.model.cadastro.Livro;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Integer> {
	Optional<Livro> findByTitulo(String Titulo);

	Optional<Livro> findByIsbn(String isbn);
}
