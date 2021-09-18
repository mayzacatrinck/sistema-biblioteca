package mjv.sistemabiblioteca.usuario.login;

import mjv.sistemabiblioteca.config.JWTConstants;
import mjv.sistemabiblioteca.config.JWTUtils;
import mjv.sistemabiblioteca.exception.BusinessException;
import mjv.sistemabiblioteca.usuario.Cadastro;
import mjv.sistemabiblioteca.usuario.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class LoginService {

	@Autowired
	private CadastroRepository cadastroRepository;

	@Autowired
	private PasswordEncoder encoder;

	public Sessao logar(LoginRequest login) {

		Optional<Cadastro> usuario = Optional.ofNullable(cadastroRepository.findByLoginUsuario(login.getUsuario())
				.orElseThrow(() -> new BusinessException("Login inválido")));

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
			throw new BusinessException("Senha inválida");
		}
	}
}
