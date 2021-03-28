package mjv.sistemalivraria.repository;

import org.springframework.data.repository.CrudRepository;

import mjv.sistemalivraria.model.locacao.Locacao;

public interface LocacaoRepository extends CrudRepository<Locacao, Integer> {

}
