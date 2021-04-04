package mjv.sistemabiblioteca.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class LocacaoItemDto {

	@NotNull(message = "{livro.notempty}")
	private Integer idLivro;
	
	@NotNull(message = "{dataprevisaoentrega.notempty}")
	private LocalDate dataPrevisaoEntrega;

	public Integer getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(Integer idLivro) {
		this.idLivro = idLivro;
	}

	public LocalDate getDataPrevisaoEntrega() {
		return dataPrevisaoEntrega;
	}

	public void setDataPrevisaoEntrega(LocalDate dataPrevisaoEntrega) {
		this.dataPrevisaoEntrega = dataPrevisaoEntrega;
	}

}
