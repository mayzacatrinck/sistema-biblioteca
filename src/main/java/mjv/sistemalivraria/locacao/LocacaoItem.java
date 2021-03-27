package mjv.sistemalivraria.locacao;

import java.time.LocalDate;

import mjv.sistemalivraria.model.cadastro.Livro;
import mjv.sistemalivraria.model.locacao.Locacao;

public class LocacaoItem {

	private Integer id;
	private LocalDate dataPrevisaoEntrega;
	private LocalDate dataEntrega;
	private Integer diarias;
	private Double valorDiaria;
	private Double valorLocacao;
	private Locacao locacao;
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

	@Override
	public String toString() {
		return "LocacaoItem [id=" + id + ", dataPrevisaoEntrega=" + dataPrevisaoEntrega + ", dataEntrega=" + dataEntrega
				+ ", diarias=" + diarias + ", valorDiaria=" + valorDiaria + ", valorLocacao=" + valorLocacao + "]";
	}

}
