package mjv.sistemabiblioteca.livro;

import mjv.sistemabiblioteca.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	public Livro cadastrarLivro(LivroRequest livroRequest) {
		
		verificaExistenciaLivro(livroRequest);
		
		Livro livro = livroRequest.toLivro();
		
		return livroRepository.save(livro);
	}
	
	private void verificaExistenciaLivro(LivroRequest livroRequest) {
		Optional<Livro> buscaTitulo = Optional.ofNullable(buscarTitulo(livroRequest.getTitulo()));
		
		if(buscaTitulo.isPresent()) {
			throw new BusinessException("Título já cadastrado.");
		}
	}
	
	public List<Livro> buscarTodosLivros() {
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
