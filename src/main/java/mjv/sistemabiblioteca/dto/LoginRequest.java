package mjv.sistemabiblioteca.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import mjv.sistemabiblioteca.model.cadastro.Login;

public class LoginRequest {

	@NotBlank
	@Size(max = 20)
	private String usuario;

	@NotBlank
	private String senha;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Login toLogin() {
		return new Login(usuario, senha);
	}


}