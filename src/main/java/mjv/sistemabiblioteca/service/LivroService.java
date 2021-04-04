package mjv.sistemabiblioteca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mjv.sistemabiblioteca.dto.LivroDto;
import mjv.sistemabiblioteca.exception.BusinessException;
import mjv.sistemabiblioteca.model.cadastro.Livro;
import mjv.sistemabiblioteca.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	public Livro cadastrarLivro(LivroDto livroDto) {
		
		verificaExistenciaLivro(livroDto);
		
		Livro livro = livroDto.cadastraLivro();
		
		return livroRepository.save(livro);
	}
	
	private void verificaExistenciaLivro(LivroDto livroDto) {		
		Optional<Livro> buscaTitulo = Optional.ofNullable(buscarTitulo(livroDto.getTitulo()));
		
		if(buscaTitulo.isPresent()) {
			throw new BusinessException("Título já cadastrado.");
		}
	}
	
	public Iterable<Livro> buscarTodosLivros() {
		return livroRepository.findAll();
	}
	
	public Livro buscarId(Integer id){
		return livroRepository.findById(id).orElse(null);
	}
	
	public Livro buscarTitulo(String titulo){
		return livroRepository.findByTitulo(titulo).orElse(null);
	}
	
	public Livro buscarIsbn(String isbn){
		return livroRepository.findByIsbn(isbn).orElse(null);
	}
}
