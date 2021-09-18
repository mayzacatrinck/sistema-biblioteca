package mjv.sistemabiblioteca.usuario;

import mjv.sistemabiblioteca.usuario.endereco.EnderecoResponse;
import mjv.sistemabiblioteca.usuario.endereco.Endereco;
import mjv.sistemabiblioteca.usuario.login.Login;

import javax.persistence.*;

@Entity
@Table(name = "cadastro")
public class Cadastro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, unique = true, length = 11)
	private String cpf;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String telefone;

	@Embedded
	private Login login;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id", nullable = false)
	private Endereco endereco;

	public Cadastro(String cpf, String nome, String email, String telefone, Login login) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.login = login;
	}

	private Cadastro() {
	}

	public Login getLogin() {
		return login;
	}

	public Integer getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoResponse response) {
		this.endereco = new Endereco(response);
	}
}
