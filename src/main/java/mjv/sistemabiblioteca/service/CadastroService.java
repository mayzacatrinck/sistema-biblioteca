package mjv.sistemabiblioteca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mjv.sistemabiblioteca.model.cadastro.Cadastro;
import mjv.sistemabiblioteca.repository.CadastroRepository;

@Service
public class CadastroService {

	@Autowired
	private CadastroRepository cadastroRepository;

	public Cadastro cadastrarUsuario(Cadastro usuario) {

		if (usuario.getLogin() == null || usuario.getCpf() == null || usuario.getSenha() == null) {
			System.out.println("Usuário não cadastrado. Os campos não podem ser nulos.");
		}

		if (usuario.getLogin().length() > 20) {
			System.out.println("O login não pode conter mais que 20 caracteres");
		}

		if (usuario.getCpf().length() > 11) {
			System.out.println("CPF inválido");
		}

		Optional<Cadastro> buscaLogin = cadastroRepository.findByLogin(usuario.getLogin());

		if (buscaLogin.isPresent()) {
			System.out.println("Login ja cadastrado");
		}

		Optional<Cadastro> buscaCpf = cadastroRepository.findByCpf(usuario.getCpf());

		if (buscaCpf.isPresent()) {
			System.out.println("CPF já cadastrado");
		}

		return cadastroRepository.save(usuario);
	}

}
