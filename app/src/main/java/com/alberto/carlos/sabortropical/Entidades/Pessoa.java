package com.alberto.carlos.sabortropical.Entidades;

import java.io.Serializable;

/**
 * Created by SuporteE6530 on 07/06/2017.
 */

public abstract class Pessoa implements Serializable {

    private long id;
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
    //private String status;


    public Pessoa(long id, String nome, String sobreNome, String dataNascimento, int corPele, int corOlhos, int sexo, String nomePai, String nomeMae, int estadoCivil, String cpf, String identidade) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.dataNascimento = dataNascimento;
        this.corPele = corPele;
        this.corOlhos = corOlhos;
        this.sexo = sexo;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.estadoCivil = estadoCivil;
        this.cpf = cpf;
        this.identidade = identidade;
        //this.status = status;
    }

    public Pessoa(String nome, String sobreNome, String dataNascimento, int corPele, int corOlhos, int sexo, String nomePai, String nomeMae, int estadoCivil, String cpf, String identidade) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.dataNascimento = dataNascimento;
        this.corPele = corPele;
        this.corOlhos = corOlhos;
        this.sexo = sexo;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.estadoCivil = estadoCivil;
        this.cpf = cpf;
        this.identidade = identidade;
        //this.status = status;
    }

    public Pessoa() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
/*

    public String getStatuse() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
*/

}
