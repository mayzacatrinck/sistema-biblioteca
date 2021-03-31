package mjv.sistemabiblioteca.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mjv.sistemabiblioteca.config.JWTConstants;
import mjv.sistemabiblioteca.config.JWTUtils;
import mjv.sistemabiblioteca.model.cadastro.Cadastro;
import mjv.sistemabiblioteca.model.cadastro.Login;
import mjv.sistemabiblioteca.model.cadastro.Sessao;
import mjv.sistemabiblioteca.repository.CadastroRepository;

@Service
public class LoginService {

	@Autowired
	private CadastroRepository cadastroRepository;

	@Autowired
	private PasswordEncoder encoder;

	public Sessao logar(Login login) {

		Optional<Cadastro> usuario = cadastroRepository.findByLoginUsuario(login.getUsuario());
		if (usuario != null) {

			boolean senhaOk = encoder.matches(login.getSenha(), usuario.get().getLogin().getSenha());

			if (senhaOk) {
				Sessao sessao = new Sessao();
				sessao.setLogin(login.getUsuario());

				Date inicio = new Date(System.currentTimeMillis());
				Date fim = new Date(System.currentTimeMillis() + JWTConstants.TOKEN_EXPIRATION);

				sessao.setDataInicio(inicio);
				sessao.setDataFim(fim);

				String token = JWTUtils.creteToken(login.getUsuario(), inicio, fim);

				sessao.setToken(token);
				return sessao;
			} else {
				throw new RuntimeException("Senha inválida");
			}

		}

		throw new RuntimeException("Login inválido");
	}
}
