package mjv.sistemabiblioca.dto;

import java.time.LocalDate;
import java.util.List;

public class LocacaoDto {

	private Integer idCadastro;
	private LocalDate dataRetirada;
	private List<LocacaoItemDto> itens;

	public Integer getIdCadastro() {
		return idCadastro;
	}

	public void setIdCadastro(Integer idCadastro) {
		this.idCadastro = idCadastro;
	}

	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public LocalDate getDataRetirada() {
		return dataRetirada;
	}

	public List<LocacaoItemDto> getItens() {
		return itens;
	}

	public void setItens(List<LocacaoItemDto> itens) {
		this.itens = itens;
	}

}
