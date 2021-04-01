package mjv.sistemabiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mjv.sistemabiblioteca.model.locacao.Locacao;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Integer> {

}
