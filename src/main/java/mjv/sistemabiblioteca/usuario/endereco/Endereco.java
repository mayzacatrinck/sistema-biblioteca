package mjv.sistemabiblioteca.usuario.endereco;

import javax.persistence.*;

@Entity
@Table(name = "endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String cep;

	@Column(nullable = false)
	private String logradouro;

	@Column(nullable = false)
	private String bairro;

	@Column(nullable = false)
	private String localidade;

	@Column(nullable = false)
	private String uf;

	private Integer ibge;

	public Endereco(EnderecoResponse response) {
		this.cep = response.getCep();
		this.logradouro = response.getLogradouro();
		this.bairro = response.getBairro();
		this.localidade = response.getLocalidade();
		this.uf = response.getUf();
		this.ibge = response.getIbge();
	}

	private Endereco() {
	}

	public Integer getId() {
		return id;
	}

	public String getCep() {
		return cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public String getUf() {
		return uf;
	}

	public Integer getIbge() {
		return ibge;
	}
}
