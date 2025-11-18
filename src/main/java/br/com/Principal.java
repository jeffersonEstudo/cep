package br.com;

import br.com.entities.PartesDoJson;
import com.google.gson.Gson;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Principal {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String busca = "";
        List<ConsultandoCEP> cepArrayList = new ArrayList<>();
        while (!busca.equalsIgnoreCase("sair")) {
            try {
                System.out.println("Digite seu CEP: ");
                busca = entrada.nextLine();

                if (busca.equalsIgnoreCase("sair")){
                    break;
                }
                String endereco = "https://viacep.com.br/ws/" + busca + "/json/";

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();

                //System.out.println(json);


                Gson gson = new Gson();

                PartesDoJson pegandoCampos = gson.fromJson(json, PartesDoJson.class);
                ConsultandoCEP cep = new ConsultandoCEP(pegandoCampos);
                cepArrayList.add(cep);
                //File file = new File("C:\\Users\\jefferson.amorim\\IdeaProjects\\cep\\listasCEP\\lista.txt");
                FileWriter escrita = new FileWriter( busca+".json");
                escrita.write(String.valueOf(cepArrayList));
                escrita.close();
                System.out.println(cep);

            } catch (IllegalArgumentException e) {
                System.out.println("Erro Argumento ilegal. " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }catch (IllegalStateException e){
                System.out.println(e.getMessage());
            }

        }
        System.out.println("O programa finalizou!");
    }


}