package mjv.sistemalivraria;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mjv.sistemalivraria.model.cadastro.Cadastro;
import mjv.sistemalivraria.model.cadastro.Endereco;
import mjv.sistemalivraria.model.cadastro.Livro;
import mjv.sistemalivraria.model.locacao.Locacao;
import mjv.sistemalivraria.repository.CadastroRepository;
import mjv.sistemalivraria.repository.LivroRepository;
import mjv.sistemalivraria.repository.LocacaoRepository;

@Component
public class ApplicationSample {
	
	@Autowired
	private CadastroRepository cadastroRepository;

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private LocacaoRepository locacaoRepository;
	
	public void criarCadastroSample() {
		Cadastro cadastro = new Cadastro();
		cadastro.setNome("Ana da Silva");
		cadastro.setCpf("14578963147");
		cadastro.setLogin("anadasilva");
		cadastro.setTelefone("999999999");
		cadastro.setSenha("123");
		cadastro.setEmail("anadasilva@gmail.com");

		Endereco endereco = new Endereco();
		endereco.setCep("21547895");
		endereco.setBairro("Campina");
		endereco.setLogradouro("Rua Nova");
		endereco.setLocalidade("Rio de Janeiro");
		endereco.setIbge(45);
		endereco.setUf("RJ");

		cadastro.setEndereco(endereco);

		cadastroRepository.save(cadastro);
	}

	public void cadastrarLivroSample() {

		Livro livro = new Livro();
		livro.setIsbn("4567");
		livro.setTitulo("Primeiro Livro");
		livro.setReservados(10);
		livro.setExemplares(140);
		livro.setValorDiaria(10.00);

		livroRepository.save(livro);

	}

	public Livro consultarLivroSample() {
		Optional<Livro> livro = livroRepository.findById(1);
		if (livro != null)
			System.out.println(livro.get().getTitulo());
		else
			cadastrarLivroSample();

		return livro.get();
	}

	public Cadastro consultarCadastroSample() {
		Optional<Cadastro> cadastro = cadastroRepository.findById(1);
		if (cadastro != null)
			System.out.println(cadastro.get().getNome() + " " + cadastro.get().getEndereco().getLocalidade());
		else
			criarCadastroSample();
		return cadastro.get();
	}

	public void gerarLocacaoSample() {
		Livro livro = consultarLivroSample();
		Cadastro cadastro = consultarCadastroSample();

		Locacao locacao = new Locacao();
		locacao.setCadastro(cadastro);

		locacao.addItem(livro);

		locacaoRepository.save(locacao);

	}

}
