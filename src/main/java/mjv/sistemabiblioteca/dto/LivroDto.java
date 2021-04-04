package mjv.sistemabiblioteca.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import mjv.sistemabiblioteca.model.cadastro.Livro;

public class LivroDto {	
	@NotBlank(message = "{isbn.notempty}")
	private String isbn;
	
	@NotBlank(message = "{titulo.notempty}")
	private String titulo;
	
	@NotNull(message = "{valorDiaria.notempty}")
	// @Size(min = 0, max = 100, message = "{valorDiaria.notvalid}")
	private Double valorDiaria;
	
	@NotNull(message = "{exemplares.notempty}")
	// @Size(min = 1, max = 10, message = "{exemplares.notvalid}")
	private Integer exemplares;
	
	@NotNull(message = "{reservados.notempty}")
	// @Size(min = 0, max = 10, message = "{reservados.notvalid}")
	private Integer reservados;
	
	public Livro cadastraLivro() {		
		Livro livro = new Livro();
		livro.setIsbn(this.getIsbn());
		livro.setTitulo(this.getTitulo()); 
		livro.setValorDiaria(this.getValorDiaria());
		livro.setExemplares(this.getExemplares());
		livro.setReservados(this.getReservados());
		return livro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {	
		return isbn;	
	}

	public void setIsbn(String isbn) {	
		this.isbn = isbn;	
	}

	public Double getValorDiaria() {	
		return valorDiaria;	
	}

	public void setValorDiaria(Double valorDiaria) {	
		this.valorDiaria = valorDiaria;	
	}

	public Integer getExemplares() {	
		return exemplares;	
	}

	public void setExemplares(Integer exemplares) {	
		this.exemplares = exemplares;	
	}

	public Integer getReservados() {	
		return reservados;	
	}

	public void setReservados(Integer reservados) {	
		this.reservados = reservados;	
	}	
	
}
