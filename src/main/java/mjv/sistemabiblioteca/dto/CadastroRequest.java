package mjv.sistemabiblioteca.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import mjv.sistemabiblioteca.model.cadastro.Login;
import org.hibernate.validator.constraints.br.CPF;

import mjv.sistemabiblioteca.model.cadastro.Cadastro;

public class CadastroRequest {

	@NotBlank
	private String nome;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@CPF
	private String cpf;

	@NotBlank
	private String telefone;

	@Valid
	private LoginRequest login;

	@NotBlank
	@Size(min = 8, max = 8)
	private String cep;

	public CadastroRequest(String nome, String email, String cpf, String telefone, LoginRequest login, String cep) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.login = login;
		this.cep = cep;
	}

	public Cadastro toCadastro() {
		Login login = this.getLogin().toLogin();
		Cadastro cadastro = new Cadastro(cpf, nome, email, telefone, login);
		return cadastro;
	}

	public LoginRequest getLogin() {
		return login;
	}

	public String getCep() {
		return cep;
	}

	public String getCpf() {
		return cpf;
	}
}
