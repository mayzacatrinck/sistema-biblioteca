package mjv.sistemabiblioteca.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class LocacaoDto {

	@NotNull(message = "{cliente.notempty}")
	private Integer idCliente;

	private LocalDate dataRetirada;

	@Valid
	private List<LocacaoItemDto> itens;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
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
