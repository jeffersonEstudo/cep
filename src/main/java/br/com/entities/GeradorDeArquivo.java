package br.com.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GeradorDeArquivo {

    public void salvaJson(PartesDoJson endereco) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
    }
}
