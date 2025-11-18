package br.com;

import br.com.entities.PreenchendoCampos;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String busca = "";

        try{
            System.out.println("Digite seu CEP: ");
            busca = entrada.nextLine();
            String endereco = "https://viacep.com.br/ws/" + busca + "/json/";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            //System.out.println(json);


            Gson gson = new Gson();

            PreenchendoCampos pegandoCampos = gson.fromJson(json, PreenchendoCampos.class);
            Cep cep = new Cep(pegandoCampos);


            System.out.println(cep);

        }catch (IllegalArgumentException e){
            System.out.println("Erro Argumento ilegal. " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    }
