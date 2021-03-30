package mjv.sistemabiblioteca.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mjv.sistemabiblioteca.model.cadastro.Cadastro;

@Repository
public interface CadastroRepository extends CrudRepository<Cadastro, Integer> {

	Optional<Cadastro> findByLogin(String login);

	Optional<Cadastro> findByCpf(String cpf);

}
