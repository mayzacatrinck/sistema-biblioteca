package mjv.sistemabiblioteca.service;

import mjv.sistemabiblioteca.dto.CadastroRequest;
import mjv.sistemabiblioteca.dto.EnderecoResponse;
import mjv.sistemabiblioteca.exception.BusinessException;
import mjv.sistemabiblioteca.exception.RegistroNaoLocalizadoException;
import mjv.sistemabiblioteca.model.cadastro.Cadastro;
import mjv.sistemabiblioteca.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroService {

	@Autowired
	private CadastroRepository cadastroRepository;

	@Autowired
	private CepService cepService;

	@Autowired
	private PasswordEncoder encoder;

	public Cadastro cadastrarUsuario(CadastroRequest cadastroRequest) {

		validaCampoUnico(cadastroRequest);

		Cadastro cadastro = cadastroRequest.toCadastro();

		EnderecoResponse enderecoResponse = cepService.consultaCep(cadastroRequest.getCep());

		cadastro.setEndereco(enderecoResponse);

		String senhaCriptografada = encoder.encode(cadastroRequest.getLogin().getSenha());
		cadastro.getLogin().setSenha(senhaCriptografada);

		return cadastroRepository.save(cadastro);

	}

	private void validaCampoUnico(CadastroRequest cadastroRequest) {
		Optional<Cadastro> buscaCpf = cadastroRepository.findByCpf(cadastroRequest.getCpf());
		Optional<Cadastro> buscaUsuario = cadastroRepository.findByLoginUsuario(cadastroRequest.getLogin().getUsuario());

		if (buscaCpf.isPresent() || buscaUsuario.isPresent()) {
			throw new BusinessException("CPF ou Usuário já cadastrados.");

		}
	}

	public List<Cadastro> buscarTodosUsuarios() {
		return cadastroRepository.findAll();
	}

	public Cadastro buscarUsuarioId(Integer id) {
		return cadastroRepository.findById(id).orElseThrow(() -> new RegistroNaoLocalizadoException("Usuário não localizado"));
	}

	public Cadastro buscaUsuarioCpf(String cpf) {
		return cadastroRepository.findByCpf(cpf).orElseThrow(() -> new RegistroNaoLocalizadoException("CPF não localizado"));
	}

	public Cadastro buscaUsuarioLogin(String login) {
		return cadastroRepository.findByLoginUsuario(login).orElseThrow(() -> new RegistroNaoLocalizadoException("Login não localizada"));
	}

}
