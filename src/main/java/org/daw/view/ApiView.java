package org.daw.view;

import org.daw.model.*;
import org.daw.model.Character;
import org.daw.service.ApiService;
import org.daw.service.MediaService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ApiView {
    private final ApiService apiService;
    private final MediaService mediaService;

    public ApiView() {
        apiService = new ApiService();
        mediaService = new MediaService();
    }

    public void showItemsNames(String endpoint,int page) throws IOException, InterruptedException {
        switch (endpoint.toLowerCase()) {
            case "anime" -> {
                System.out.println("Animes Names:");
                List<Anime> animes = apiService.getListAllItems(endpoint, page, Anime.class);
                List<String> names = mediaService.getNames(animes);
                names.forEach(System.out::println);
            }
            case "manga" -> {
                System.out.println("Mangas Names:");
                List<Manga> mangas = apiService.getListAllItems(endpoint, page, Manga.class);
                List<String> names = mediaService.getNames(mangas);
                names.forEach(System.out::println);
            }
            case "characters" -> {
                System.out.println("Characters Names:");
                List<Character> characters = apiService.getListAllItems(endpoint, page, Character.class);
                List<String> names = mediaService.getNames(characters);
                names.forEach(System.out::println);
            }
            default -> System.out.println("Unknown endpoint: " + endpoint);
        }
        System.out.println("----------------------------------------------------");
        System.out.println("----------------------------------------------------");
        Thread.sleep(710);
    }

    public void showItemsNameSortByName(String endpoint, int page) throws IOException, InterruptedException {
        System.out.println("Sorting by name:");
        switch (endpoint.toLowerCase()) {
            case "anime" -> {
                System.out.println("Animes Names:");
                List<Anime> animes = apiService.getListAllItems(endpoint, page, Anime.class);
                List<Anime> listSort = mediaService.sortByName(animes);
                listSort.forEach(a -> System.out.println(a.getDisplayName()));
            }
            case "manga" -> {
                System.out.println("Mangas Names:");
                List<Manga> mangas = apiService.getListAllItems(endpoint, page, Manga.class);
                List<Manga> listSort = mediaService.sortByName(mangas);
                listSort.forEach(a -> System.out.println(a.getDisplayName()));
            }
            case "characters" -> {
                System.out.println("Characters Names:");
                List<Character> characters = apiService.getListAllItems(endpoint, page, Character.class);
                List<Character> listSort = mediaService.sortByName(characters);
                listSort.forEach(a -> System.out.println(a.getDisplayName()));
            }
            default -> System.out.println("Unknown endpoint: " + endpoint);
        }
        System.out.println("----------------------------------------------------");
        System.out.println("----------------------------------------------------");
        Thread.sleep(610);
    }

    public void showItemsNameSortByScore(String endpoint, int page) throws IOException, InterruptedException {
        System.out.println("Sorting by score:");
        switch (endpoint.toLowerCase()) {
            case "anime" -> {
                System.out.println("Animes Names sorting by score: ");
                List<Anime> animes = apiService.getListAllItems(endpoint, page, Anime.class);
                List<Anime> listSort = mediaService.sortByScoreThenName(animes);
                listSort.forEach(a -> System.out.println(a.getDisplayName() + " -> " + a.getScore()));
            }
            case "manga" -> {
                System.out.println("Mangas Names sorting by score: ");
                List<Manga> mangas = apiService.getListAllItems(endpoint, page, Manga.class);
                List<Manga> listSort = mediaService.sortByScoreThenName(mangas);
                listSort.forEach(m -> System.out.println(m.getDisplayName() + " -> " + m.getScore()));
            }
            default -> System.out.println("Unknown endpoint: " + endpoint);
        }
        System.out.println("----------------------------------------------------");
        System.out.println("----------------------------------------------------");
        Thread.sleep(710);
    }

    public void showItemsFilterName(String endpoint,int page,String name) throws IOException, InterruptedException {
        System.out.println("Filtering by name:");
        switch (endpoint.toLowerCase()) {
            case "anime" -> {
                System.out.println("Animes Names contain " + name + ":");
                List<Anime> animes = apiService.getListAllItems(endpoint, page, Anime.class);
                List<Anime> animesFilter = mediaService.filterByNameContains(animes, name);
                List<String> names = mediaService.getNames(animesFilter);
                names.forEach(System.out::println);
            }
            case "manga" -> {
                System.out.println("Mangas Names contain " + name + ":");
                List<Manga> mangas = apiService.getListAllItems(endpoint, page, Manga.class);
                List<Manga> mangasFilter = mediaService.filterByNameContains(mangas, name);
                List<String> names = mediaService.getNames(mangasFilter);
                names.forEach(System.out::println);
            }
            case "characters" -> {
                System.out.println("Characters Names contain " + name + ":");
                List<Character> characters = apiService.getListAllItems(endpoint, page, Character.class);
                List<Character> charactersFilter = mediaService.filterByNameContains(characters, name);
                List<String> names = mediaService.getNames(charactersFilter);
                names.forEach(System.out::println);
            }
            default -> System.out.println("Unknown endpoint: " + endpoint);
        }
        System.out.println("----------------------------------------------------");
        System.out.println("----------------------------------------------------");
        Thread.sleep(610);
    }

    public void showItemsFilterNameAndScore(String endpoint,int page,String name,double minScore) throws IOException, InterruptedException {
        System.out.println("Filtering by name and score:");
        switch (endpoint.toLowerCase()) {
            case "anime" -> {
                System.out.println("Animes Names contain " + name + " and score:");
                List<Anime> animes = apiService.getListAllItems(endpoint, page, Anime.class);
                List<Anime> animesFilter = mediaService.filterByNameAndScoreContains(animes, name, minScore);
                animesFilter.forEach(a -> System.out.println(a.getDisplayName()+" -> "+a.getScore()));
            }
            case "manga" -> {
                System.out.println("Mangas Names contain " + name + "  and score:");
                List<Manga> mangas = apiService.getListAllItems(endpoint, page, Manga.class);
                List<Manga> mangasFilter = mediaService.filterByNameAndScoreContains(mangas, name, minScore);
                mangasFilter.forEach(m -> System.out.println(m.getDisplayName()+" -> "+m.getScore()));

            }
            default -> System.out.println("Unknown endpoint: " + endpoint);
        }
        System.out.println("----------------------------------------------------");
        System.out.println("----------------------------------------------------");
        Thread.sleep(710);
    }

    public <T extends MediaItem> void showItemsFilterPredicate(String endpoint, int page, Predicate<T> predicate) throws IOException, InterruptedException {
        System.out.println("Filtering by predicate:");
        switch (endpoint.toLowerCase()) {
            case "anime" -> {
                System.out.println("Animes Names:");
                List<Anime> animes = apiService.getListAllItems(endpoint, page, Anime.class);
                List<Anime> animesFilter = mediaService.filterByPredicate(animes,(Predicate<Anime>) predicate);
                animesFilter.forEach(a -> System.out.println(a.getDisplayName()));
            }
            case "manga" -> {
                System.out.println("Mangas Names:");
                List<Manga> mangas = apiService.getListAllItems(endpoint, page, Manga.class);
                List<Manga> mangasFilter = mediaService.filterByPredicate(mangas,(Predicate<Manga>) predicate);
                mangasFilter.forEach(m -> System.out.println(m.getDisplayName()));
            }
            case "characters" -> {
                System.out.println("Characters Names:");
                List<Character> characters = apiService.getListAllItems(endpoint, page, Character.class);
                List<Character> charactersFilter = mediaService.filterByPredicate(characters, (Predicate<Character>) predicate);
                charactersFilter.forEach(c -> System.out.println(c.getDisplayName()));
            }
        }
        System.out.println("----------------------------------------------------");
        System.out.println("----------------------------------------------------");
        Thread.sleep(610);
    }

    public void showItemsCharacters(String endpoint,int id) throws IOException, InterruptedException {
        switch (endpoint.toLowerCase()) {
            case "anime" -> {
                List<CharacterItems> characters = apiService.getCharactersByItemId(endpoint,id);
                Anime name = apiService.getByItemId(endpoint,id,Anime.class);
                System.out.println("Anime -> " + name.getDisplayName());
                System.out.println("Characters: ");
                characters.forEach(c -> System.out.println(c.getCharacter().getDisplayName()));
            }
            case "manga" -> {

                List<CharacterItems> characters = apiService.getCharactersByItemId(endpoint,id);
                Manga name = apiService.getByItemId(endpoint,id,Manga.class);
                System.out.println("Manga -> " + name.getDisplayName());
                System.out.println("Characters: ");
                characters.forEach(c -> System.out.println(c.getCharacter().getDisplayName()));
            }
            default -> System.out.println("Unknown endpoint: " + endpoint);
        }
        System.out.println("----------------------------------------------------");
        System.out.println("----------------------------------------------------");
        Thread.sleep(710);
    }

    public void showItemsHighScoreNames(String endpoint,int page,double minScore) throws IOException, InterruptedException {
        System.out.println("Filtering by high score names:");
        switch (endpoint.toLowerCase()) {
            case "anime" -> {
                System.out.println("Animes Names:");
                List<Anime> animes = apiService.getListAllItems(endpoint, page, Anime.class);
                List<String> names = mediaService.getHighScoreNames(animes,minScore);
                names.forEach(System.out::println);
            }
            case "manga" -> {
                System.out.println("Mangas Names:");
                List<Manga> mangas = apiService.getListAllItems(endpoint, page, Manga.class);
                List<String> names = mediaService.getHighScoreNames(mangas,minScore);
                names.forEach(System.out::println);
            }
            default -> System.out.println("Unknown endpoint: " + endpoint);
        }
        System.out.println("----------------------------------------------------");
        System.out.println("----------------------------------------------------");
        Thread.sleep(610);
    }

    public void showItemHighestScoreName(int page) throws IOException, InterruptedException {
        System.out.println("highest score Anime name:");
        List<Anime> animes = apiService.getListAllItems("anime", page, Anime.class);
        Optional<Anime> anime = mediaService.getHighestScoreName(animes);
        anime.ifPresent(value -> System.out.println(value.getDisplayName()));
        Thread.sleep(310);
        System.out.println("highest score Manga name:");
        List<Manga> mangas = apiService.getListAllItems("manga", page, Manga.class);
        Optional<Manga> manga = mediaService.getHighestScoreName(mangas);
        manga.ifPresent(value -> System.out.println(value.getDisplayName()));
        System.out.println("----------------------------------------------------");
        System.out.println("----------------------------------------------------");
        Thread.sleep(710);
    }
}
