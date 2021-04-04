package mjv.sistemabiblioteca.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mjv.sistemabiblioteca.dto.EntregaItemDto;
import mjv.sistemabiblioteca.dto.LocacaoDto;
import mjv.sistemabiblioteca.model.locacao.Locacao;
import mjv.sistemabiblioteca.service.LocacaoService;

@RestController
@RequestMapping(path = "/locacoes")
public class LocacaoController {

	@Autowired
	private LocacaoService locacaoService;

	@PostMapping
	public void gerarLocacao(@Valid @RequestBody LocacaoDto locacao) {
		locacaoService.gerarLocacao(locacao);
	}

	@GetMapping("/consulta")
	public Locacao buscarLocacaoId(Integer idLocacao) {
		return locacaoService.buscarLocacao(idLocacao);
	}

	@PutMapping("/retirarLivros/{idLocacao}")
	public Locacao retirarLivro(Integer idLocacao) {
		return locacaoService.retirarLivro(idLocacao);
	}

	@PutMapping("/entregarLivros/{idItem}")
	public void entregarLivro(@Valid @RequestBody EntregaItemDto idItem) {
		locacaoService.entregarLivro(idItem);
	}

}