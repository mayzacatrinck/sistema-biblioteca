package mjv.sistemabiblioteca.usuario;

import mjv.sistemabiblioteca.usuario.endereco.EnderecoResponse;

public class DetalheDoCadastroResponse {

    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private EnderecoResponse endereco;

    public DetalheDoCadastroResponse(Cadastro cadastro) {
        this.nome = cadastro.getNome();
        this.email = cadastro.getEmail();
        this.cpf = cadastro.getCpf();
        this.telefone = cadastro.getTelefone();
        this.endereco = new EnderecoResponse(cadastro.getEndereco());
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public EnderecoResponse getEndereco() {
        return endereco;
    }
}
