package mjv.sistemabiblioteca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mjv.sistemabiblioteca.dto.CadastroDto;
import mjv.sistemabiblioteca.dto.LoginDto;
import mjv.sistemabiblioteca.service.CadastroService;

@Component
public class ApplicationSample {

	@Autowired
	private CadastroService cadastroService;

	public void usuarioPadrao() {
		CadastroDto cadastro = new CadastroDto();
		cadastro.setNome("Usuario Padrao");
		cadastro.setCpf("12574486557");
		cadastro.setTelefone("478785475");
		cadastro.setEmail("padrao@gmail.com");
		cadastro.setCep("23045040");

		LoginDto login = new LoginDto();
		login.setUsuario("mjv");
		login.setSenha("123");

		cadastro.setLogin(login);

		cadastroService.cadastrarUsuario(cadastro);
	}

}
