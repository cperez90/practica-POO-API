package org.daw.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.daw.model.Anime;
import org.daw.model.AnimeListResponse;
import org.daw.model.AnimeResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiService {
    private static final URI BaseURL = URI.create("https://api.jikan.moe/v4/anime");
    private final HttpClient httpClient;
    private final Gson gson;

    public ApiService() {
        this(HttpClient.newHttpClient(), new GsonBuilder().create());
    }

    public ApiService(HttpClient httpClient, Gson gson) {
        this.httpClient = httpClient;
        this.gson = gson;
    }

    public AnimeListResponse getAnimeList(int page) throws IOException, InterruptedException {
        URI apiURI = URI.create(BaseURL + "?page=" + page);
        HttpRequest request = HttpRequest.newBuilder(apiURI).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ensureSuccess(response, apiURI.toString());
        return gson.fromJson(response.body(), AnimeListResponse.class);
    }

    public List<String> getAllAnimeTitle() throws IOException, InterruptedException {
        List<String> titles = new ArrayList<>();
        int page = 1;
        boolean hasNextPage = true;

        while (hasNextPage) {
            System.out.println("Page " + page);
            AnimeListResponse response = getAnimeList(page);
            for (Anime anime : response.getData()){
                titles.add(anime.getTitle());
            }
            if (page >= 2) {
                System.out.println("limite de 2 paginas");
                break;
            }
            hasNextPage = response.getPagination().isHas_next_page();
            page++;
            Thread.sleep(410);
        }
        return titles;
    }

    public Anime getAnimeById(int id) throws InterruptedException, IOException {
        URI apiURI = URI.create(BaseURL + "/" + id);
        HttpRequest request = HttpRequest.newBuilder(apiURI).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ensureSuccess(response, apiURI.toString());

        AnimeResponse animeResponse = gson.fromJson(response.body(), AnimeResponse.class);
        return animeResponse.getData();
    }

    private void ensureSuccess(HttpResponse<?> response, String url) {
        if (response.statusCode() >= 400) {
            throw new RuntimeException("La llamada " + url + " ha fallado con el código " + response.statusCode());
        }
    }
}
