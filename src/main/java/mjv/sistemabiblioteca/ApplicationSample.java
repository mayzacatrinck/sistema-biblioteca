package mjv.sistemabiblioteca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mjv.sistemabiblioteca.model.cadastro.Cadastro;
import mjv.sistemabiblioteca.model.cadastro.Endereco;
import mjv.sistemabiblioteca.model.cadastro.Login;
import mjv.sistemabiblioteca.service.CadastroService;

@Component
public class ApplicationSample {

	@Autowired
	private CadastroService cadastroService;

	/*
	 * @Autowired private CadastroRepository cadastroRepository;
	 * 
	 * @Autowired private LivroRepository livroRepository;
	 * 
	 * @Autowired private LocacaoRepository locacaoRepository;
	 */

	public void usuarioPadrao() {
		Cadastro cadastro = new Cadastro();
		cadastro.setNome("Usuario Padrao");
		cadastro.setCpf("124578896");
		cadastro.setTelefone("478785475");
		cadastro.setEmail("padrao@gmail.com");
		
		Login login = new Login();
		login.setUsuario("mjv");
		login.setSenha("123");
		
		cadastro.setLogin(login);

		Endereco endereco = new Endereco();
		endereco.setCep("12455");
		endereco.setBairro("Campina");
		endereco.setLogradouro("Rua Nova");
		endereco.setLocalidade("Rio de Janeiro");
		endereco.setIbge(45);
		endereco.setUf("RJ");

		cadastro.setEndereco(endereco);

		cadastroService.cadastrarUsuario(cadastro);
	}

	/*
	 * public void cadastrarLivroSample() {
	 * 
	 * Livro livro = new Livro(); livro.setIsbn("4567");
	 * livro.setTitulo("Primeiro Livro"); livro.setReservados(10);
	 * livro.setExemplares(140); livro.setValorDiaria(10.00);
	 * 
	 * livroRepository.save(livro);
	 * 
	 * }
	 * 
	 * public Livro consultarLivroSample() { Optional<Livro> livro =
	 * livroRepository.findById(1); if (livro != null)
	 * System.out.println(livro.get().getTitulo()); else cadastrarLivroSample();
	 * 
	 * return livro.get(); }
	 * 
	 * public Cadastro consultarCadastroSample() { Cadastro usuario =
	 * cadastroRepository.findById(1).orElse(null); if (usuario == null) {
	 * criarCadastroSample(); usuario = cadastroRepository.findById(1).orElse(null);
	 * }
	 * 
	 * return usuario; }
	 * 
	 * public void gerarLocacaoSample() { Livro livro = consultarLivroSample();
	 * Cadastro cadastro = consultarCadastroSample();
	 * 
	 * Locacao locacao = new Locacao(); locacao.setCadastro(cadastro);
	 * 
	 * locacao.addItem(livro);
	 * 
	 * locacaoRepository.save(locacao);
	 * 
	 * }
	 */
}
