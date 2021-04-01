package mjv.sistemabiblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mjv.sistemabiblioteca.dto.LocacaoDto;
import mjv.sistemabiblioteca.service.LocacaoService;

@RestController
@RequestMapping(path = "/locacoes")
public class LocacaoController {

	@Autowired
	private LocacaoService locacaoService;

	@PostMapping
	public void postGerarLocacao(@RequestBody LocacaoDto locacao) {
		locacaoService.gerarLocacao(locacao);
	}

}