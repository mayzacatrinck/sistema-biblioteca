package mjv.sistemalivraria.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mjv.sistemalivraria.model.cadastro.Cadastro;

@Repository
public interface CadastroRepository extends CrudRepository<Cadastro, Integer> {

}
