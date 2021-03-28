package mjv.sistemalivraria.repository;

import org.springframework.data.repository.CrudRepository;

import mjv.sistemalivraria.model.cadastro.Livro;

public interface LivroRepository extends CrudRepository<Livro, Integer> {

}
