package com.alberto.carlos.sabortropical.Entidades;

/**
 * Created by carlos.alberto on 11/06/2017.
 */

public class Fornecedor {

    private long id;
    private String contrato;
    private long status;
    private String nomeResponsavel;
    private String cnpj;
    private String inscEstadual;
    private long credito;
    private String banco;
    private String agencia;
    private String contaCorrente;
    private String tipoPagamento;
    private long desempenho;

    public Fornecedor (long id, String contrato, long status, String nomeResponsavel, String cnpj, String inscEstadual, long credito, String banco, String agencia, String contaCorrente, String tipoPagamento, long desempenho ){

        this.id = id;
        this.contrato = contrato;
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

    public Fornecedor (String contrato, long status, String nomeResponsavel, String cnpj, String inscEstadual, long credito, String banco, String agencia, String contaCorrente, String tipoPagamento, long desempenho ){

        this.contrato = contrato;
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

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
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

    public long getCredito() {
        return credito;
    }

    public void setCredito(long credito) {
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

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public long getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(long desempenho) {
        this.desempenho = desempenho;
    }
}
