package mjv.sistemabiblioteca.model.cadastro;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Login {

	@Column(nullable = false, unique = true, length = 20)
	private String usuario;

	@Column(nullable = false)
	private String senha;

	public Login(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	private Login() {
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public String getUsuario() {
		return usuario;
	}
}