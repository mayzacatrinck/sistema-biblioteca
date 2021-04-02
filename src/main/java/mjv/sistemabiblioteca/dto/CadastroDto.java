package mjv.sistemabiblioteca.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import mjv.sistemabiblioteca.model.cadastro.Cadastro;

public class CadastroDto {

	@NotBlank(message = "{nome.notempty}")
	private String nome;

	@NotBlank(message = "{email.notempty}")
	@Email(message = "{email.notvalid}")
	private String email;

	@NotBlank(message = "{cpf.notempty}")
	@CPF(message = "{cpf.notvalid}")
	private String cpf;

	@NotBlank(message = "{telefone.notempty}")
	private String telefone;

	@Valid
	private LoginDto login;

	@NotBlank(message = "{cep.notempty}")
	@Size(min = 8, max = 8, message = "{cep.notvalid}")
	private String cep;

	public Cadastro toCadastro() {
		Cadastro cadastro = new Cadastro();
		cadastro.setCpf(this.getCpf());
		cadastro.setEmail(this.getEmail());
		cadastro.setLogin(this.getLogin().toLogin());
		cadastro.setNome(this.getNome());
		cadastro.setTelefone(this.getTelefone());
		return cadastro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LoginDto getLogin() {
		return login;
	}

	public void setLogin(LoginDto login) {
		this.login = login;
	}

}
