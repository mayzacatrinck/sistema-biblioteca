package mjv.sistemabiblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mjv.sistemabiblioteca.model.cadastro.Livro;
import mjv.sistemabiblioteca.service.LivroService;

@RestController
@RequestMapping(path = "/livros")
public class LivroController {
	
	@Autowired
	private LivroService livroService;
	
	@PostMapping
	public void postCadastrarLivro(@RequestBody Livro livro) {
		livroService.cadastrarLivro(livro);
	}

	@GetMapping("/todos")
	public Iterable<Livro> getListarLivros() {
		return livroService.buscarTodosLivros();
	}

	public void setLivroService(LivroService livroService) {
		this.livroService = livroService;
	}
}
