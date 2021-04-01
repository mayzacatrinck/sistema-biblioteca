package mjv.sistemabiblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mjv.sistemabiblioteca.model.locacao.Locacao;
import mjv.sistemabiblioteca.service.LocacaoService;

@RestController
@RequestMapping(path = "/locacoes")
public class LocacaoController {
	
	@Autowired
	private LocacaoService locacaoService;
	
	@PostMapping
	public void postGerarLocacao(@RequestBody Locacao locacao) {
		locacaoService.gerarLocacao(locacao);
	}
	
	@GetMapping("/todos")
	public List<Locacao> getListarLocacoes() {
		return locacaoService.listarLocacoes();
	}

}