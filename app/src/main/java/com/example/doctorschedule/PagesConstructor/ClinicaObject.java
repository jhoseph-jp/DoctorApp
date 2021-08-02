package com.example.doctorschedule.PagesConstructor;

public class ClinicaObject {
    private String id;
    private String nome_clinica;
    private String endereco;
    private String telefone;
    private String email;
    private String created_at;
    private String updated_at;

    public ClinicaObject() {

    }

   public String getId() {
        return id;
    }

   public void setId(String id) {
        this.id = id;
    }

    public String getNome_clinica() {
        return nome_clinica;
    }

    public void setNome_clinica(String nome_clinica) {
        this.nome_clinica = nome_clinica;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
