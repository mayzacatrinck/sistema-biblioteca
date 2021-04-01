package mjv.sistemabiblioteca.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mjv.sistemabiblioca.dto.LocacaoDto;
import mjv.sistemabiblioca.dto.LocacaoItemDto;
import mjv.sistemabiblioteca.exception.RegistroNaoLocalizadoException;
import mjv.sistemabiblioteca.locacao.LocacaoItem;
import mjv.sistemabiblioteca.model.cadastro.Cadastro;
import mjv.sistemabiblioteca.model.cadastro.Livro;
import mjv.sistemabiblioteca.model.locacao.Locacao;
import mjv.sistemabiblioteca.repository.CadastroRepository;
import mjv.sistemabiblioteca.repository.LivroRepository;
import mjv.sistemabiblioteca.repository.LocacaoRepository;

@Service
public class LocacaoService {

	@Autowired
	private LocacaoRepository locacaoRepository;

	@Autowired
	private CadastroRepository cadastroRepository;

	@Autowired
	private LivroRepository livroRepository;

	public void gerarLocacao(LocacaoDto locDto) {

		Cadastro cad = cadastroRepository.findById(locDto.getIdCadastro()).orElse(null);
		if (cad == null)
			throw new RegistroNaoLocalizadoException("Cadastro");

		Locacao locacao = new Locacao();
		locacao.setCadastro(cad);
		locacao.setDataRetirada(locDto.getDataRetirada());

		for (LocacaoItemDto i : locDto.getItens()) {
			i.getDataPrevisaoEntrega();

			Livro livro = livroRepository.findById(i.getIdLivro()).orElse(null);

			LocacaoItem item = new LocacaoItem();
			item.setLivro(livro);
			item.setDataPrevisaoEntrega(i.getDataPrevisaoEntrega());
			item.setValorDiaria(livro.getValorDiaria());
			item.setDiarias(calcularDiarias(locacao.getDataRetirada(), i.getDataPrevisaoEntrega()));
			item.setValorLocacao(item.getValorDiaria() * item.getDiarias());

			locacao.addItem(item);

			livro.incrementarReservado();

			livroRepository.save(livro);

		}

		locacaoRepository.save(locacao);
	}

	private Integer calcularDiarias(LocalDate dataRetirada, LocalDate dataPrevisaoEntrega) {
		Period period = Period.between(dataPrevisaoEntrega, dataRetirada);
		int diff = Math.abs(period.getDays());
		return diff;
	}

}