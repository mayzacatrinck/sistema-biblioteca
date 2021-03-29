package mjv.sistemalivraria.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mjv.sistemalivraria.model.locacao.Locacao;

@Repository
public interface LocacaoRepository extends CrudRepository<Locacao, Integer> {

}
