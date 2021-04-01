package mjv.sistemabiblioteca.model.locacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import mjv.sistemabiblioteca.locacao.LocacaoItem;
import mjv.sistemabiblioteca.locacao.LocacaoStatus;
import mjv.sistemabiblioteca.model.cadastro.Cadastro;

@Entity
@Table(name = "locacao")
public class Locacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "data_agendamento")
	private LocalDate dataAgendamento;

	@Column(name = "data_retirada")
	private LocalDate dataRetirada;

	@Column(name = "data_finalizacao")
	private LocalDate dataFinalizacao;

	@Column(name = "valor_total")
	private Double valorTotal = 0.0;

	@ManyToOne
	@JoinColumn(name = "cadastro_id", nullable = false)
	private Cadastro cadastro;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "locacao")
	private List<LocacaoItem> itens = new ArrayList<LocacaoItem>();

	@Enumerated(EnumType.STRING)
	private LocacaoStatus status;

	public void addItem(LocacaoItem item) {
		item.setLocacao(this);
		this.valorTotal = this.valorTotal + item.getValorLocacao();
		itens.add(item);
	}

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

	@PrePersist
	private void prePersist() {
		this.dataAgendamento = LocalDate.now();
		this.status = LocacaoStatus.RESERVADA;
	}

	@Override
	public String toString() {
		return "Locacao [id=" + id + ", dataRetirada=" + dataRetirada + ", dataFinalizacao=" + dataFinalizacao
				+ ", valorTotal=" + valorTotal + ", itens=" + itens + ", status=" + status + "]";
	}

}
