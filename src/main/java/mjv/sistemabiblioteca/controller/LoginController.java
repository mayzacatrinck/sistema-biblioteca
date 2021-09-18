package mjv.sistemabiblioteca.controller;

import mjv.sistemabiblioteca.dto.LoginRequest;
import mjv.sistemabiblioteca.model.cadastro.Sessao;
import mjv.sistemabiblioteca.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Sessao logar(@Valid @RequestBody LoginRequest login) {
        return loginService.logar(login);
    }

}
