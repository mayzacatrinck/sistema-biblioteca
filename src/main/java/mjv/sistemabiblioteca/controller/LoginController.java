package mjv.sistemabiblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mjv.sistemabiblioteca.model.cadastro.Login;
import mjv.sistemabiblioteca.model.cadastro.Sessao;
import mjv.sistemabiblioteca.service.LoginService;

@RestController
@RequestMapping(path = "/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping
	public Sessao logar(@RequestBody Login login) {
		return loginService.logar(login);
	}

}
