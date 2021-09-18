package mjv.sistemabiblioteca.locacao;

import javax.validation.constraints.NotNull;

public class EntregaItemDto {

	@NotNull(message = "{locacao.notempty}")
	private Integer idLocacao;
	
	@NotNull(message = "{item.notempty}")
	private Integer idItem;

	public void setIdLocacao(Integer idLocacao) {
		this.idLocacao = idLocacao;
	}

	public Integer getIdLocacao() {
		return idLocacao;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

}