package mjv.sistemabiblioteca.locacao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import mjv.sistemabiblioteca.livro.Livro;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "locacao_item")
public class LocacaoItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "data_previsao_entrega")
	private LocalDate dataPrevisaoEntrega;
	
	@Column(name = "data_entrega")
	private LocalDate dataEntrega;
	
	private Integer diarias;
	
	@Column(name = "valor_diaria")
	private Double valorDiaria;
	
	@Column(name = "valor_locacao")
	private Double valorLocacao;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "locacao_id", nullable = false)
	private Locacao locacao;
	
	@ManyToOne
	@JoinColumn(name = "livro_id", nullable = false)
	private Livro livro;

	public Integer getId() {
		return id;
	}

	public LocalDate getDataPrevisaoEntrega() {
		return dataPrevisaoEntrega;
	}

	public void setDataPrevisaoEntrega(LocalDate dataPrevisaoEntrega) {
		this.dataPrevisaoEntrega = dataPrevisaoEntrega;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getDiarias() {
		return diarias;
	}

	public Double getValorLocacao() {
		return valorLocacao;
	}
	
	public void setDiarias(Integer diarias) {
		this.diarias = diarias;
	}
	
	public void setValorLocacao(Double valorLocacao) {
		this.valorLocacao = valorLocacao;
	}

	@Override
	public String toString() {
		return "LocacaoItem [id=" + id + ", dataPrevisaoEntrega=" + dataPrevisaoEntrega + ", dataEntrega=" + dataEntrega
				+ ", diarias=" + diarias + ", valorDiaria=" + valorDiaria + ", valorLocacao=" + valorLocacao + "]";
	}

}
