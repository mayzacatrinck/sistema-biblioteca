package mjv.sistemabiblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mjv.sistemabiblioteca.model.cadastro.Cadastro;
import mjv.sistemabiblioteca.repository.CadastroRepository;
import mjv.sistemabiblioteca.service.CadastroService;

@RestController
@RequestMapping(path = "/cadastros")
public class CadastroController {
	
	@Autowired
	private CadastroService cadastroService;
	
	@Autowired
	private CadastroRepository repository;

	@PostMapping
	public void postCadastrarUsuario(@RequestBody Cadastro cadastro) {
		cadastroService.cadastrarUsuario(cadastro);
	}
	
	@GetMapping
	public Iterable<Cadastro> list(){
		return repository.findAll();
	}

}
