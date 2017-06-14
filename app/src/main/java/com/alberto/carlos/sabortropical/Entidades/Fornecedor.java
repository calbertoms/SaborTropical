package com.alberto.carlos.sabortropical.Entidades;

import java.io.Serializable;

/**
 * Created by carlos.alberto on 11/06/2017.
 */

public class Fornecedor implements Serializable {

    private long id;
    private int contrato;
    private String nome;
    private int status;
    private String nomeResponsavel;
    private String cnpj;
    private String inscEstadual;
    private float credito;
    private String banco;
    private String agencia;
    private String contaCorrente;
    private int tipoPagamento;
    private int desempenho;
    private Endereco endereco;

    public Fornecedor (long id, int contrato, String nome, int status, String nomeResponsavel, String cnpj, String inscEstadual, float credito, String banco, String agencia, String contaCorrente, int tipoPagamento, int desempenho ){

        this.id = id;
        this.contrato = contrato;
        this.nome = nome;
        this.status = status;
        this.nomeResponsavel = nomeResponsavel;
        this.cnpj = cnpj;
        this.inscEstadual = inscEstadual;
        this.credito = credito;
        this.banco = banco;
        this.agencia = agencia;
        this.contaCorrente = contaCorrente;
        this.tipoPagamento = tipoPagamento;
        this.desempenho = desempenho;

    }

    public Fornecedor (int contrato, String nome, int status, String nomeResponsavel, String cnpj, String inscEstadual, float credito, String banco, String agencia, String contaCorrente, int tipoPagamento, int desempenho ){

        this.contrato = contrato;
        this.nome = nome;
        this.status = status;
        this.nomeResponsavel = nomeResponsavel;
        this.cnpj = cnpj;
        this.inscEstadual = inscEstadual;
        this.credito = credito;
        this.banco = banco;
        this.agencia = agencia;
        this.contaCorrente = contaCorrente;
        this.tipoPagamento = tipoPagamento;
        this.desempenho = desempenho;

    }


    public Fornecedor(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getContrato() {
        return contrato;
    }

    public void setContrato(int contrato) {
        this.contrato = contrato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscEstadual() {
        return inscEstadual;
    }

    public void setInscEstadual(String inscEstadual) {
        this.inscEstadual = inscEstadual;
    }

    public float getCredito() {
        return credito;
    }

    public void setCredito(float credito) {
        this.credito = credito;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(String contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public int getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(int tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public int getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(int desempenho) {
        this.desempenho = desempenho;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return this.getId() + " - " + this.getNome() + " - " + this.getContrato();
    }

}
