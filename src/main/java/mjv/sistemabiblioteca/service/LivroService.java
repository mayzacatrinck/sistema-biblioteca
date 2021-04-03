package mjv.sistemabiblioteca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mjv.sistemabiblioteca.exception.CampoObrigatorioException;
import mjv.sistemabiblioteca.model.cadastro.Livro;
import mjv.sistemabiblioteca.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	public Livro cadastrarLivro(Livro livro) {
		if(livro.getIsbn() == null || livro.getIsbn().isEmpty())
			throw new CampoObrigatorioException("ISBN");
		
		if(livro.getTitulo() == null || livro.getTitulo().isEmpty())
			throw new CampoObrigatorioException("Título");
		
		if(livro.getValorDiaria() == null)
			throw new CampoObrigatorioException("Valor Diária");
		
		if(livro.getExemplares() == null)
			throw new CampoObrigatorioException("Exemplares");
				
		if(livro.getReservados() == null)
			throw new CampoObrigatorioException("Reservados");
		
		return livroRepository.save(livro);
	}
	
	public Iterable<Livro> buscarTodosLivros() {
		return livroRepository.findAll();
	}
	
	public Optional<Livro> buscarLivro(Integer id){
		return livroRepository.findById(id);
	}
	
	public Optional<Livro> buscarTitulo(String titulo){
		return livroRepository.findByTitulo(titulo);
	}
	
	public Optional<Livro> buscarIsbn(String isbn){
		return livroRepository.findByIsbn(isbn);
	}
	
}
