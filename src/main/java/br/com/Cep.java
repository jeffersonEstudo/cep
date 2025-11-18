package br.com;

import br.com.entities.PreenchendoCampos;

public class Cep {
    private String cep;
    private String logradouro;
    private String estado;


    public Cep(String cep, String logradouro, String estado) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.estado = estado;
    }

    public Cep(PreenchendoCampos pegandoCampos) {
        this.cep = pegandoCampos.cep();
        this.estado = pegandoCampos.estado();
        this.logradouro = pegandoCampos.logradouro();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


@Override
    public String toString(){
     return "CEP: " + cep;
}


}
