package mjv.sistemabiblioteca.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mjv.sistemabiblioteca.model.cadastro.Cadastro;
import mjv.sistemabiblioteca.model.cadastro.Livro;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Integer> {
	Optional<Cadastro> findByTitulo(String Titulo);

	Optional<Cadastro> findByIsbn(String isbn);
}
