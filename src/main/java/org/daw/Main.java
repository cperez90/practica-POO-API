package org.daw;

import org.daw.model.Anime;
import org.daw.service.ApiService;
import org.daw.service.MediaService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ApiService api = new ApiService();
        MediaService mediaService = new MediaService();
        List<Anime> animes = api.getListAllItems("anime",3,Anime.class);
        for(Anime a : animes){
            System.out.println("ID: " + a.getMalId() + ", Name: " + a.getDisplayName() + ",Score " + a.getScore());
        }
        List<Anime> filteredAnimes = mediaService.filterByNameContains(animes, "naruto");
        filteredAnimes.forEach(a -> System.out.println("ID: " + a.getMalId() + " -> " + a.getDisplayName() + " -> " + a.getScore()));
        List<Anime> filteredPredicate = mediaService.filterByPredicate(animes,a -> a.getYear() > 1990 && a.getYear() < 2000);
        System.out.println("Filtrado con el predicate en parametro: ");
        for(Anime a : filteredPredicate){
            System.out.println("ID: " + a.getMalId() + " -> " + a.getDisplayName() + " -> " + a.getScore());
        }
        System.out.println("Total de animes: " + filteredPredicate.size());
        Optional<Anime> topAnime = mediaService.getHighestScore(animes);
        System.out.println("Top Anime:");
        topAnime.ifPresent(anime -> System.out.println("ID: " + anime.getMalId() + " -> " + anime.getDisplayName() + " -> " + anime.getScore()));
        System.out.println("Buscando anime por id.");
        Anime anime = api.getByItemId("anime",20, Anime.class);
        System.out.println("Titulo: " + anime.getDisplayName());
        System.out.println("Puntuacion: " + anime.getScore());
    }
}