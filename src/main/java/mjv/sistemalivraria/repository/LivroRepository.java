package mjv.sistemalivraria.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mjv.sistemalivraria.model.cadastro.Livro;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Integer> {

}
