package mjv.sistemabiblioteca.livro;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class LivroRequest {

	@NotBlank
	private String isbn;
	
	@NotBlank
	private String titulo;
	
	@NotNull
	@Positive
	private Double valorDiaria;
	
	@NotNull
	@Positive
	private Integer exemplares;
	
	@NotNull
	@Positive
	private Integer reservados;

	public LivroRequest(String isbn, String titulo, Double valorDiaria, Integer exemplares, Integer reservados) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.valorDiaria = valorDiaria;
		this.exemplares = exemplares;
		this.reservados = reservados;
	}

	public Livro toLivro() {
		return new Livro(isbn, titulo, valorDiaria, exemplares, reservados);
	}

	public String getTitulo() {
		return titulo;
	}
}
