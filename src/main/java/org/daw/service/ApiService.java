package org.daw.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.daw.model.Anime;
import org.daw.model.AnimeListResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
    private static final URI apiURI = URI.create("https://api.jikan.moe/v4/anime/");
    private final HttpClient httpClient;
    private final Gson gson;

    public ApiService() {
        this(HttpClient.newHttpClient(), new GsonBuilder().create());
    }

    public ApiService(HttpClient httpClient, Gson gson) {
        this.httpClient = httpClient;
        this.gson = gson;
    }

    public AnimeListResponse getAnimeList() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(apiURI).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ensureSuccess(response, apiURI.toString());
        return gson.fromJson(response.body(), AnimeListResponse.class);

    }

    public Anime getAnimeById(int id) throws InterruptedException, IOException, URISyntaxException {
        try {
            HttpRequest request = HttpRequest.newBuilder(apiURI).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ensureSuccess(response, apiURI.toString());
            return gson.fromJson(response.body(), Anime.class);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException(e.getMessage());
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private void ensureSuccess(HttpResponse<?> response, String url) {
        if (response.statusCode() >= 400) {
            throw new RuntimeException("La llamada " + url + " ha fallado con el código " + response.statusCode());
        }
    }
}
