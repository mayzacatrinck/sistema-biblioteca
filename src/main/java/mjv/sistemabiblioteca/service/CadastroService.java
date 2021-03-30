package mjv.sistemabiblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mjv.sistemabiblioteca.exception.ValidationException;
import mjv.sistemabiblioteca.model.cadastro.Cadastro;
import mjv.sistemabiblioteca.repository.CadastroRepository;

@Service
public class CadastroService {

	@Autowired
	private CadastroRepository cadastroRepository;

	public Cadastro cadastrarUsuario(Cadastro usuario) {

		validaNaoNulo(usuario);

		Optional<Cadastro> buscaCpf = cadastroRepository.findByCpf(usuario.getCpf());
		Optional<Cadastro> buscaLogin = cadastroRepository.findByLogin(usuario.getLogin());
		if (buscaCpf.isPresent() || buscaLogin.isPresent()) {
			throw new ValidationException("CPF ou Login já cadastrados.");
		}

		if (usuario.getCpf().length() > 11) {
			throw new ValidationException("CPF inválido");
		}

		if (usuario.getLogin().length() > 20) {
			throw new ValidationException("O login não pode conter mais que 20 caracteres");
		}

		validaEndereco(usuario);

		usuario = cadastroRepository.save(usuario);

		return usuario;
	}

	public List<Cadastro> buscarTodosUsuarios() {
		return cadastroRepository.findAll();
	}

	public Optional<Cadastro> buscarUsuarioId(Integer id) {
		return cadastroRepository.findById(id);
	}

	public Optional<Cadastro> buscaUsuarioCpf(String cpf) {
		return cadastroRepository.findByCpf(cpf);
	}

	public Optional<Cadastro> buscaUsuarioLogin(String login) {
		return cadastroRepository.findByLogin(login);
	}

	private void validaNaoNulo(Cadastro usuario) {
		String login = usuario.getLogin();
		String cpf = usuario.getCpf();
		String senha = usuario.getSenha();

		if (login == null || login.isEmpty() || cpf == null || cpf.isEmpty() || senha == null || senha.isEmpty()) {
			throw new ValidationException("Usuário não cadastrado. Os campos não podem ser nulos.");
		}
	}

	private void validaEndereco(Cadastro usuario) {
		String cep = usuario.getEndereco().getCep();
		String logradouro = usuario.getEndereco().getLogradouro();
		String bairro = usuario.getEndereco().getBairro();
		String localidade = usuario.getEndereco().getLocalidade();
		String uf = usuario.getEndereco().getUf();

		if (usuario.getEndereco() == null || cep == null || cep.isEmpty() || logradouro == null || logradouro.isEmpty()
				|| bairro == null || bairro.isEmpty() || localidade == null || localidade.isEmpty() || uf == null
				|| uf.isEmpty()) {
			throw new ValidationException("Cadastro não realizado. Insira os campos de Endereço.");
		}
	}

}
