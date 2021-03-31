package mjv.sistemabiblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mjv.sistemabiblioteca.exception.BusinessException;
import mjv.sistemabiblioteca.model.cadastro.Cadastro;
import mjv.sistemabiblioteca.repository.CadastroRepository;

@Service
public class CadastroService {
	
	@Autowired
	private CadastroRepository cadastroRepository;

	@Autowired
	private PasswordEncoder encoder;

	public void cadastrarUsuario(Cadastro cadastro) {

		validaCampoNaoNulo(cadastro);

		Optional<Cadastro> buscaCpf = cadastroRepository.findByCpf(cadastro.getCpf());
		Optional<Cadastro> buscaUsuario = cadastroRepository.findByLoginUsuario(cadastro.getLogin().getUsuario());

		if (buscaCpf.isPresent() || buscaUsuario.isPresent()) {
			throw new BusinessException("CPF ou Login já cadastrados.");

		}

		if (cadastro.getCpf().length() > 11) {
			throw new BusinessException("CPF inválido");

		}

		if (cadastro.getLogin().getUsuario().length() > 20) {
			throw new BusinessException("O login não pode conter mais que 20 caracteres");
		}

		validaEndereco(cadastro);

		String senhaCriptografada = encoder.encode(cadastro.getLogin().getSenha());
		cadastro.getLogin().setSenha(senhaCriptografada);

		cadastroRepository.save(cadastro);

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
		return cadastroRepository.findByLoginUsuario(login);
	}

	private void validaCampoNaoNulo(Cadastro usuario) {
		String login = usuario.getLogin().getUsuario();
		String cpf = usuario.getCpf();
		String senha = usuario.getLogin().getSenha();

		if (login == null || login.isEmpty() || cpf == null || cpf.isEmpty() || senha == null || senha.isEmpty()) {
			throw new BusinessException("Usuário não cadastrado. Os campos não podem ser nulos.");
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
			throw new BusinessException("Cadastro não realizado. Insira os campos de Endereço.");
		}
	}

}
