package mjv.sistemabiblioteca.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import mjv.sistemabiblioteca.dto.DetalheDoCadastroResponse;
import mjv.sistemabiblioteca.dto.ListaCadastroResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mjv.sistemabiblioteca.dto.CadastroRequest;
import mjv.sistemabiblioteca.model.cadastro.Cadastro;
import mjv.sistemabiblioteca.service.CadastroService;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {

	@Autowired
	private CadastroService cadastroService;

	private static final Logger log = LoggerFactory.getLogger(CadastroController.class);

	@PostMapping
	public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody CadastroRequest request, UriComponentsBuilder builder) {

		Cadastro cadastro = cadastroService.cadastrarUsuario(request);
		log.info("Usuário cadastrado! {}", cadastro.getNome());
		URI uri = builder.path("/cadastros/{id}").build(cadastro.getId());

		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/todos")
	public ResponseEntity<List<ListaCadastroResponse>> listarUsuarios() {

		List<Cadastro> cadastros = cadastroService.buscarTodosUsuarios();
		List<ListaCadastroResponse> lista = cadastros.stream().map(ListaCadastroResponse::new).collect(Collectors.toList());

		return ResponseEntity.ok().body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalheDoCadastroResponse> buscarUsuarioId(@PathVariable Integer id) {

		Cadastro cadastro = cadastroService.buscarUsuarioId(id);
		log.info("Usuário de id {} encontrado com sucesso!", cadastro.getId());

		return ResponseEntity.ok(new DetalheDoCadastroResponse(cadastro));
	}

	@GetMapping("/cpf")
	public ResponseEntity<DetalheDoCadastroResponse> buscarUsuarioCpf(@RequestParam String cpf) {

		Cadastro cadastro = cadastroService.buscaUsuarioCpf(cpf);
		log.info("Usuário de cpf {} encontado com sucesso!", cadastro.getCpf());

		return ResponseEntity.ok(new DetalheDoCadastroResponse(cadastro));
	}

	@GetMapping("/login")
	public ResponseEntity<DetalheDoCadastroResponse> buscarUsuarioLogin(@RequestParam String login) {

		Cadastro cadastro = cadastroService.buscaUsuarioLogin(login);
		log.info("Usuário de login {} encontrado com sucesso!", cadastro.getLogin().getUsuario());

		return ResponseEntity.ok(new DetalheDoCadastroResponse(cadastro));
	}

}
