package mjv.sistemabiblioteca.livro;

public class DetalhesLivroResponse {

    private String isbn;
    private String titulo;
    private Double valorDiaria;
    private Integer exemplares;
    private Integer reservados;

    public DetalhesLivroResponse(Livro livro) {
        this.isbn = livro.getIsbn();
        this.titulo = livro.getTitulo();
        this.valorDiaria = livro.getValorDiaria();
        this.exemplares = livro.getExemplares();
        this.reservados = livro.getReservados();
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
}
