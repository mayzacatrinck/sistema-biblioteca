package mjv.sistemabiblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mjv.sistemabiblioteca.model.locacao.Locacao;
import mjv.sistemabiblioteca.repository.LocacaoRepository;

@Service
public class LocacaoService {

	@Autowired
	private LocacaoRepository locacaoRepository;
	
	/*
	 * @Autowired private LivroService livroService;
	 */
	
	public Locacao gerarLocacao(Locacao locacao) {
		
		
		return locacaoRepository.save(locacao);
	}
	
	public List<Locacao> listarLocacoes() {
		return locacaoRepository.findAll();
	}

}