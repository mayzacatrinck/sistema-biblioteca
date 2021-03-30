package mjv.sistemabiblioteca.service;

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
}
