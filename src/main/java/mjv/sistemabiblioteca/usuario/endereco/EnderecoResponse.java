package mjv.sistemabiblioteca.usuario.endereco;

public class EnderecoResponse {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private Integer ibge;

    public EnderecoResponse(Endereco endereco) {
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
        this.bairro = endereco.getBairro();
        this.localidade = endereco.getLocalidade();
        this.uf = endereco.getUf();
        this.ibge = endereco.getIbge();
    }

    private EnderecoResponse() {
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
