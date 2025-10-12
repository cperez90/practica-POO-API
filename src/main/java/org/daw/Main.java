package org.daw;

import org.daw.model.Anime;
import org.daw.service.ApiService;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ApiService api = new ApiService();
        List<String> animes = api.getAllAnimeTitle();
        System.out.println("Total Animes: " + animes.size());
        for (String anime : animes) {
            System.out.println("Anime Title: " + anime);
        }
        System.out.println("Buscando anime por id.");
        Anime anime = api.getAnimeById(1);
        System.out.println("Titulo: " + anime.getTitle());
        System.out.println("Puntuacion: " + anime.getPopularity());
    }
}