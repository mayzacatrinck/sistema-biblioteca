package mjv.sistemabiblioteca.locacao;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mjv.sistemabiblioteca.exception.BusinessException;
import mjv.sistemabiblioteca.exception.CampoObrigatorioException;
import mjv.sistemabiblioteca.exception.RegistroNaoLocalizadoException;
import mjv.sistemabiblioteca.usuario.Cadastro;
import mjv.sistemabiblioteca.livro.Livro;
import mjv.sistemabiblioteca.usuario.CadastroRepository;
import mjv.sistemabiblioteca.livro.LivroRepository;

@Service
public class LocacaoService {

	@Autowired
	private LocacaoRepository locacaoRepository;

	@Autowired
	private CadastroRepository cadastroRepository;

	@Autowired
	private LivroRepository livroRepository;

	public Locacao gerarLocacao(LocacaoDto locDto) {

		Cadastro cliente = cadastroRepository.findById(locDto.getIdCliente())
				.orElseThrow(() -> new RegistroNaoLocalizadoException("Usuário não localizado"));

		Locacao locacao = new Locacao();
		locacao.setCliente(cliente);

		adicionarItem(locDto, locacao);

		if (locDto.getDataRetirada() != null) {
			locacao.setDataRetirada(locDto.getDataRetirada());
			if (locacao.getDataRetirada().equals(LocalDate.now())) {
				locacao.setStatus(LocacaoStatus.EFETIVADA);
			}
		}

		return locacaoRepository.save(locacao);
	}

	private void adicionarItem(LocacaoDto locDto, Locacao locacao) {
		for (LocacaoItemDto itemDto : locDto.getItens()) {
			Livro livro = livroRepository.findById(itemDto.getIdLivro())
					.orElseThrow(() -> new RegistroNaoLocalizadoException("Livro não localizado"));
			if (livro.getExemplares() < 1) {
				throw new RegistroNaoLocalizadoException("O livro não possui exemplares suficientes");
			}

			LocacaoItem item = new LocacaoItem();
			item.setLivro(livro);
			item.setDataPrevisaoEntrega(itemDto.getDataPrevisaoEntrega());
			item.setValorDiaria(livro.getValorDiaria());

			locacao.setDataRetirada(locDto.getDataRetirada());
			calcularLocacao(locacao, item);
			locacao.addItem(item);

			livro.incrementarReservado();

			livroRepository.save(livro);

		}
	}

	private Integer calcularDiarias(LocalDate dataRetirada, LocalDate dataEntrega) {
		Period period = Period.between(dataEntrega, dataRetirada);
		int diff = Math.abs(period.getDays());
		return ++diff;
	}

	private void calcularLocacao(Locacao locacao, LocacaoItem item) {
		if (locacao.getDataRetirada() != null) {

			LocalDate dataEntrega = null;

			if (item.getDataEntrega() != null) {
				dataEntrega = item.getDataEntrega();
			} else {
				dataEntrega = item.getDataPrevisaoEntrega();
			}

			item.setDiarias(calcularDiarias(locacao.getDataRetirada(), dataEntrega));
			item.setValorLocacao(item.getValorDiaria() * item.getDiarias());
		}
	}

	public Locacao retirarLivro(Integer locacaoId) {

		if (locacaoId == null) {
			throw new CampoObrigatorioException("Preencha o id da Locação");
		}

		Locacao locacao = locacaoRepository.findById(locacaoId)
				.orElseThrow(() -> new RegistroNaoLocalizadoException("Locação não localizada"));

		validaStatusRetiradaLivro(locacao);

		locacao.setDataRetirada(LocalDate.now());
		locacao.setStatus(LocacaoStatus.EFETIVADA);

		for (LocacaoItem locacaoItem : locacao.getItens()) {
			calcularLocacao(locacao, locacaoItem);
		}

		locacao.calculaValorTotal();

		return locacaoRepository.save(locacao);
	}

	private void validaStatusRetiradaLivro(Locacao locacao) {
		if (locacao.getStatus() == LocacaoStatus.EFETIVADA || locacao.getStatus() == LocacaoStatus.FINALIZADA) {
			throw new BusinessException("Locação já encontra-se como efetivada ou finalizada");
		}
	}

	public void entregarLivro(EntregaItemDto itemEntrega) {

		Locacao locacao = locacaoRepository.findById(itemEntrega.getIdLocacao())
				.orElseThrow(() -> new RegistroNaoLocalizadoException("Locação não localizada"));

		validaStatusEntregaLivro(locacao);

		LocacaoItem locacaoItemEncontrado = localizarItem(itemEntrega, locacao);

		if (locacaoItemEncontrado.getDataEntrega() != null) {
			throw new BusinessException("Item já entregue");
		}

		locacaoItemEncontrado.setDataEntrega(LocalDate.now());
		locacaoItemEncontrado.getLivro().decrementarReservado();

		calcularLocacao(locacao, locacaoItemEncontrado);

		finalizarLocacao(locacao);

		locacaoRepository.save(locacao);
	}

	private void validaStatusEntregaLivro(Locacao locacao) {
		if (locacao.getStatus() == LocacaoStatus.FINALIZADA) {
			throw new BusinessException("Locação já foi finalizada");
		}

		if (locacao.getStatus() != LocacaoStatus.EFETIVADA) {
			throw new BusinessException("Locação não foi efetivada");
		}
	}

	private LocacaoItem localizarItem(EntregaItemDto itemEntrega, Locacao locacao) {
		LocacaoItem locacaoItemEncontrado = null;

		for (LocacaoItem item : locacao.getItens()) {
			if (itemEntrega.getIdItem() == item.getId()) {
				locacaoItemEncontrado = item;
				break;
			}
		}

		if (locacaoItemEncontrado == null) {
			throw new RegistroNaoLocalizadoException("Id do item não encontrado");
		}
		return locacaoItemEncontrado;
	}

	private void finalizarLocacao(Locacao locacao) {

		for (LocacaoItem locacaoItem : locacao.getItens()) {
			if (locacaoItem.getDataEntrega() == null) {
				return;
			}
		}

		locacao.calculaValorTotal();

		locacao.setDataFinalizacao(LocalDate.now());
		locacao.setStatus(LocacaoStatus.FINALIZADA);
	}

	public Locacao buscarLocacao(Integer id) {
		return locacaoRepository.findById(id)
				.orElseThrow(() -> new RegistroNaoLocalizadoException("Locação não localizada"));
	}

	public List<Locacao> buscarLocacaoDataAgendamento(LocalDate dataAgendamento) {
		return locacaoRepository.findByDataAgendamento(dataAgendamento);
	}

	public List<Locacao> buscarLocacaoDataRetirada(LocalDate dataRetirada) {
		return locacaoRepository.findByDataRetirada(dataRetirada);
	}

	public List<Locacao> buscarLocacaoStatus(String status) {
		return locacaoRepository.findByStatusList(status);
	}

	public List<Locacao> buscarLocacaoCliente(Integer id) {
		return locacaoRepository.findByClienteId(id);
	}
}