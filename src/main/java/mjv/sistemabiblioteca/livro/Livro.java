package mjv.sistemabiblioteca.livro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "livro")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String isbn;

	@Column(nullable = false)
	private String titulo;

	@Column(name = "valor_diaria", nullable = false)
	private Double valorDiaria;

	@Column(nullable = false)
	private Integer exemplares;

	@Column(nullable = false)
	private Integer reservados;

	public Livro(String isbn, String titulo, Double valorDiaria, Integer exemplares, Integer reservados) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.valorDiaria = valorDiaria;
		this.exemplares = exemplares;
		this.reservados = reservados;
	}

	private Livro() {
	}

	public Integer getId() {
		return id;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public Double getValorDiaria() {
		return valorDiaria;
	}

	public Integer getExemplares() {
		return exemplares;
	}

	public Integer getReservados() {
		return reservados;
	}

	public void incrementarReservado() {
		exemplares--;
		reservados++;
	}

	public void decrementarReservado() {
		exemplares++;
		reservados--;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", valorDiaria=" + valorDiaria + "]";
	}

}
