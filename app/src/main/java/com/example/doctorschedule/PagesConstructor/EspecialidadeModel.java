package com.example.doctorschedule.PagesConstructor;

public class EspecialidadeModel {
    private String idE;
    private String nome_especialidade;
    private String idE_clinica;
    private String nomeEClininca;
    private String espEnd;
    private String espETelefone;
    private String espEmail;
    private String medID;
    private String nome_medico;
    private String eCreate_at;

    public String getIdE() {
        return idE;
    }

    public void setIdE(String idE) {
        this.idE = idE;
    }

    public String getNome_especialidade() {
        return nome_especialidade;
    }

    public void setNome_especialidade(String nome_especialidade) {
        this.nome_especialidade = nome_especialidade;
    }

    public String getIdE_clinica() {
        return idE_clinica;
    }

    public void setIdE_clinica(String idE_clinica) {
        this.idE_clinica = idE_clinica;
    }

    public String getNomeEClininca() {
        return nomeEClininca;
    }

    public void setNomeEClininca(String nomeEClininca) {
        this.nomeEClininca = nomeEClininca;
    }

    public String getEspEnd() {
        return espEnd;
    }

    public void setEspEnd(String espEnd) {
        this.espEnd = espEnd;
    }

    public String getEspETelefone() {
        return espETelefone;
    }

    public void setEspETelefone(String espETelefone) {
        this.espETelefone = espETelefone;
    }

    public String getEspEmail() {
        return espEmail;
    }

    public void setEspEmail(String espEmail) {
        this.espEmail = espEmail;
    }

    public String getMedID() {
        return medID;
    }

    public void setMedID(String medID) {
        this.medID = medID;
    }

    public String getNome_medico() { return nome_medico; }

    public void setNome_medico(String nome_medico) { this.nome_medico = nome_medico; }

    public String geteCreate_at() {
        return eCreate_at;
    }

    public void seteCreate_at(String eCreate_at) {
        this.eCreate_at = eCreate_at;
    }

    public String geteUpdate_at() {
        return eUpdate_at;
    }

    public void seteUpdate_at(String eUpdate_at) {
        this.eUpdate_at = eUpdate_at;
    }

    private String eUpdate_at;

    public EspecialidadeModel() {

    }


}
