package mjv.sistemabiblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mjv.sistemabiblioteca.dto.CadastroDto;
import mjv.sistemabiblioteca.exception.BusinessException;
import mjv.sistemabiblioteca.model.cadastro.Cadastro;
import mjv.sistemabiblioteca.model.cadastro.Endereco;
import mjv.sistemabiblioteca.repository.CadastroRepository;

@Service
public class CadastroService {

	@Autowired
	private CadastroRepository cadastroRepository;

	@Autowired
	private CepService cepService;

	@Autowired
	private PasswordEncoder encoder;

	public Cadastro cadastrarUsuario(CadastroDto cadDto) {

		validaCampoUnico(cadDto);

		Cadastro cadastro = cadDto.toCadastro();

		Endereco endereco = cepService.consultaCep(cadDto.getCep());

		cadastro.setEndereco(endereco);

		String senhaCriptografada = encoder.encode(cadDto.getLogin().getSenha());
		cadastro.getLogin().setSenha(senhaCriptografada);

		return cadastroRepository.save(cadastro);

	}

	private void validaCampoUnico(CadastroDto cadDto) {
		Optional<Cadastro> buscaCpf = cadastroRepository.findByCpf(cadDto.getCpf());
		Optional<Cadastro> buscaUsuario = cadastroRepository.findByLoginUsuario(cadDto.getLogin().getUsuario());

		if (buscaCpf.isPresent() || buscaUsuario.isPresent()) {
			throw new BusinessException("CPF ou Usuário já cadastrados.");

		}
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

}
