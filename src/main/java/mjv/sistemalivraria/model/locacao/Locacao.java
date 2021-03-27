package mjv.sistemalivraria.model.locacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import mjv.sistemalivraria.locacao.LocacaoItem;
import mjv.sistemalivraria.locacao.LocacaoStatus;
import mjv.sistemalivraria.model.cadastro.Cadastro;

public class Locacao {

	private Integer id;
	private LocalDate dataAgendamento;
	private LocalDate dataRetirada;
	private LocalDate dataFinalizacao;
	private Double valorTotal;
	
	private Cadastro cadastro;
	private List<LocacaoItem> itens = new ArrayList<LocacaoItem>();
	private LocacaoStatus status;

	public Integer getId() {
		return id;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public LocalDate getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public LocalDate getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(LocalDate dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public Cadastro getCadastro() {
		return cadastro;
	}

	public void setCadastro(Cadastro cadastro) {
		this.cadastro = cadastro;
	}

	public List<LocacaoItem> getItens() {
		return itens;
	}

	public void setItens(List<LocacaoItem> itens) {
		this.itens = itens;
	}

	public LocacaoStatus getStatus() {
		return status;
	}

	public void setStatus(LocacaoStatus status) {
		this.status = status;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	@Override
	public String toString() {
		return "Locacao [id=" + id + ", dataRetirada=" + dataRetirada + ", dataFinalizacao=" + dataFinalizacao
				+ ", valorTotal=" + valorTotal + ", itens=" + itens + ", status=" + status + "]";
	}

}
