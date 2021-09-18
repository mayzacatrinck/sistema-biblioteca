package mjv.sistemabiblioteca.locacao;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/locacoes")
public class LocacaoController {

	@Autowired
	private LocacaoService locacaoService;

	@PostMapping
	public Integer gerarLocacao(@Valid @RequestBody LocacaoDto locacao) {
		return locacaoService.gerarLocacao(locacao).getId();
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

	@GetMapping("/locacaoDataAgendamento")
	public List<Locacao> locacaoDataAgendamento(String dataAgendamento) {
		LocalDate date = LocalDate.parse(dataAgendamento);
		return locacaoService.buscarLocacaoDataAgendamento(date);
	}

	@GetMapping("/locacaoDataRetirada")
	public List<Locacao> locacaoDataRetirada(String dataRetirada) {
		LocalDate date = LocalDate.parse(dataRetirada);
		return locacaoService.buscarLocacaoDataRetirada(date);
	}

	@GetMapping("/locacaoStatus")
	public List<Locacao> locacaoStatus(String status) {
		return locacaoService.buscarLocacaoStatus(status);
	}

	@GetMapping("/locacaoCliente")
	public List<Locacao> locacaoCliente(Integer idCliente) {
		return locacaoService.buscarLocacaoCliente(idCliente);
	}

}