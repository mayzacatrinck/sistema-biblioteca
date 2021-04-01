package mjv.sistemabiblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import mjv.sistemabiblioca.dto.CadastroDto;
import mjv.sistemabiblioteca.exception.BusinessException;
import mjv.sistemabiblioteca.model.cadastro.Cadastro;
import mjv.sistemabiblioteca.model.cadastro.Endereco;
import mjv.sistemabiblioteca.repository.CadastroRepository;

@Service
public class CadastroService {
	
	@Autowired
	private CadastroRepository cadastroRepository;

	@Autowired
	private PasswordEncoder encoder;

	public Cadastro cadastrarUsuario(CadastroDto cadDto) {

		validaCampoNaoNulo(cadDto);

		Optional<Cadastro> buscaCpf = cadastroRepository.findByCpf(cadDto.getCpf());
		Optional<Cadastro> buscaUsuario = cadastroRepository.findByLoginUsuario(cadDto.getLogin().getUsuario());

		if (buscaCpf.isPresent() || buscaUsuario.isPresent()) {
			throw new BusinessException("CPF ou usuário já cadastrados.");

		}

		if (cadDto.getCpf().length() > 11) {
			throw new BusinessException("CPF inválido");

		}

		if (cadDto.getLogin().getUsuario().length() > 20) {
			throw new BusinessException("O login não pode conter mais que 20 caracteres");
		}

		Cadastro cadastro = new Cadastro();
		cadastro.setCpf(cadDto.getCpf());
		cadastro.setEmail(cadDto.getEmail());
		cadastro.setLogin(cadDto.getLogin());
		cadastro.setNome(cadDto.getNome());
		cadastro.setTelefone(cadDto.getTelefone());

		RestTemplate template = new RestTemplate();
		Endereco end = template.getForObject("https://viacep.com.br/ws/{cep}/json", Endereco.class, cadDto.getCep());
		
		cadastro.setEndereco(end);

		String senhaCriptografada = encoder.encode(cadastro.getLogin().getSenha());
		cadastro.getLogin().setSenha(senhaCriptografada);

		return cadastroRepository.save(cadastro);

	}

	public List<Cadastro> buscarTodosUsuarios() {
		return cadastroRepository.findAll();
	}

	public Cadastro buscarUsuarioId(Integer id) {
		return cadastroRepository.findById(id).orElse(null);
	}

	public Cadastro buscaUsuarioCpf(String cpf) {
		return cadastroRepository.findByCpf(cpf).orElse(null);
	}

	public Cadastro buscaUsuarioLogin(String login) {
		return cadastroRepository.findByLoginUsuario(login).orElse(null);
	}

	private void validaCampoNaoNulo(CadastroDto usuario) {
		String login = usuario.getLogin().getUsuario();
		String cpf = usuario.getCpf();
		String senha = usuario.getLogin().getSenha();
		String cep = usuario.getCep();

		if (login == null || login.isEmpty() || cpf == null || cpf.isEmpty() || senha == null || senha.isEmpty() || cep == null || cep.isEmpty()) {
			throw new BusinessException("Usuário não cadastrado. Os campos não podem ser nulos.");
		}
	}

	
	

}
