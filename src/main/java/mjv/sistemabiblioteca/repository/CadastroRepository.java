package mjv.sistemabiblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mjv.sistemabiblioteca.model.cadastro.Cadastro;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Integer> {

	Optional<Cadastro> findByCpf(String cpf);

	Optional<Cadastro> findByLoginUsuario(String usuario);

}
