package mjv.sistemabiblioteca.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Integer> {

	Optional<Cadastro> findByCpf(String cpf);

	Optional<Cadastro> findByLoginUsuario(String usuario);

}
