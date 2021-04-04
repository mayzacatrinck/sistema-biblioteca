package mjv.sistemabiblioteca.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mjv.sistemabiblioteca.model.cadastro.Livro;
import mjv.sistemabiblioteca.dto.LivroDto;
import mjv.sistemabiblioteca.service.LivroService;

@RestController
@RequestMapping(path = "/livro")
public class LivroController {
	
	@Autowired
	private LivroService livroService;
	
	@PostMapping
	public void postCadastrarLivro(@Valid @RequestBody LivroDto livro) {
		livroService.cadastrarLivro(livro);
	}

	@GetMapping("/todos")
	public Iterable<Livro> getListarLivros() {
		return livroService.buscarTodosLivros();
	}
	
	@GetMapping("/id")
	public Livro getId(@RequestParam Integer id) {
		return livroService.buscarId(id);
	}
	
	@GetMapping("/isbn")
	public Livro getIsbn(@RequestParam String isbn) {
		return livroService.buscarIsbn(isbn);
	}
}
