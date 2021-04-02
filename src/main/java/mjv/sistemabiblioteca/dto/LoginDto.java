package mjv.sistemabiblioteca.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import mjv.sistemabiblioteca.model.cadastro.Login;

public class LoginDto {

	@NotBlank(message = "{usuario.notempty}")
	@Size(max = 20, message = "{usuario.notvalid}")
	private String usuario;

	@NotBlank(message = "{senha.notempty}")
	private String senha;

	public Login toLogin() {
		Login login = new Login();
		login.setUsuario(this.getUsuario());
		login.setSenha(this.getSenha());
		return login;

	}

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

}