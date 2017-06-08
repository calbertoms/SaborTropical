package com.alberto.carlos.sabortropical.Entidades;

/**
 * Created by SuporteE6530 on 07/06/2017.
 */

public abstract class Pessoa {

    private String nome;
    private String sobreNome;
    private String dataNascimento;
    private int corPele;
    private int corOlhos;
    private int sexo;
    private String nomePai;
    private String nomeMae;
    private int estadoCivil;
    private String cpf;
    private String identidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getCorPele() {
        return corPele;
    }

    public void setCorPele(int corPele) {
        this.corPele = corPele;
    }

    public int getCorOlhos() {
        return corOlhos;
    }

    public void setCorOlhos(int corOlhos) {
        this.corOlhos = corOlhos;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public int getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(int estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIdentidade() {
        return identidade;
    }

    public void setIdentidade(String identidade) {
        this.identidade = identidade;
    }

}
