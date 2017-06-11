package com.alberto.carlos.sabortropical.Entidades;

/**
 * Created by carlos.alberto on 11/06/2017.
 */

public class Assinatura {


    private long id;
    private  String dataInicio;
    private String dataTermino;
    private String status;

    public Assinatura (long id, String dataInicio, String dataTermino, String status){

        this.id = id;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.status =status;

    }

    public Assinatura (String dataInicio, String dataTermino, String status){

        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.status =status;

    }

    public Assinatura(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
