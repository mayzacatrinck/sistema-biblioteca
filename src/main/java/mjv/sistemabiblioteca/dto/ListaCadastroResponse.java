package mjv.sistemabiblioteca.dto;

import mjv.sistemabiblioteca.model.cadastro.Cadastro;

public class ListaCadastroResponse {

    private String cpf;
    private String nome;
    private String email;
    private String telefone;

    public ListaCadastroResponse(Cadastro cadastro) {
        this.cpf = cadastro.getCpf();
        this.nome = cadastro.getNome();
        this.email = cadastro.getEmail();
        this.telefone = cadastro.getTelefone();
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
}
