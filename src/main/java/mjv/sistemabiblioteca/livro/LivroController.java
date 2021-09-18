package mjv.sistemabiblioteca.livro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroController {
	
	@Autowired
	private LivroService livroService;
	private static final Logger log = LoggerFactory.getLogger(LivroController.class);
	
	@PostMapping
	public ResponseEntity<?> cadastrarLivro(@Valid @RequestBody LivroRequest request, UriComponentsBuilder builder) {

		Livro livro = livroService.cadastrarLivro(request);
		log.info("Livro cadastrado com sucesso! Título {}", livro.getTitulo());
		URI uri = builder.path("/livros/{id}").build(livro.getId());

		return ResponseEntity.created(uri).build();

	}

	@GetMapping("/todos")
	public ResponseEntity<List<DetalhesLivroResponse>> listarLivros() {

		List<Livro> livros = livroService.buscarTodosLivros();
		List<DetalhesLivroResponse> lista = livros.stream().map(DetalhesLivroResponse::new).collect(Collectors.toList());

		return ResponseEntity.ok().body(lista);


	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesLivroResponse> buscarLivroId(@PathVariable Integer id) {

		Livro livro = livroService.buscarId(id);
		log.info("Livro encontrado com sucesso! Id {}", livro.getId());

		return ResponseEntity.ok(new DetalhesLivroResponse(livro));
	}
	
	@GetMapping("/isbn")
	public ResponseEntity<DetalhesLivroResponse> buscarLivroIsbn(@RequestParam String isbn) {

		Livro livro = livroService.buscarIsbn(isbn);
		log.info("Livro encontado com sucesso! Isbn {}", livro.getIsbn());

		return ResponseEntity.ok(new DetalhesLivroResponse(livro));
	}
}
