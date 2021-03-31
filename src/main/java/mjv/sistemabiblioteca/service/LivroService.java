package mjv.sistemabiblioteca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mjv.sistemabiblioteca.model.cadastro.Livro;
import mjv.sistemabiblioteca.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	public Livro cadastrarLivro(Livro livro) {
		
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
