package org.daw.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.daw.model.*;
import org.daw.model.Character;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiService {
    private static final URI BASE_URL = URI.create("https://api.jikan.moe/v4/");
    private final HttpClient httpClient;
    private final Gson gson;

    public ApiService() {
        this(HttpClient.newHttpClient(), new GsonBuilder().create());
    }

    public ApiService(HttpClient httpClient, Gson gson) {
        this.httpClient = httpClient;
        this.gson = gson;
    }

    public <T extends MediaItem> ListResponse<T> getItemsListPage(String endpoint, int page, Class<T> typeClass) throws IOException, InterruptedException {
        URI apiURI = URI.create(BASE_URL + endpoint + "?page=" + page);
        HttpRequest request = HttpRequest.newBuilder(apiURI).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ensureSuccess(response, apiURI.toString());
        Type responseType = TypeToken.getParameterized(ListResponse.class, typeClass).getType();
        return gson.fromJson(response.body(), responseType);
    }

    public <T extends MediaItem> List<T> getListAllItems(String endpoint, int maxPage , Class<T> typeClass) throws IOException, InterruptedException {
        List<T> items = new ArrayList<>();
        int page = 1;
        boolean hasNextPage = true;

        while (hasNextPage) {
            ListResponse<T> response = getItemsListPage(endpoint,page, typeClass);
            items.addAll(response.getData());
            if (page == maxPage) {
                break;
            }
            hasNextPage = response.getPagination().isHas_next_page();
            page++;
            Thread.sleep(1090);
        }
        return items;
    }

    @SuppressWarnings("unchecked")
    public <T extends MediaItem> T getByItemId(String endpoint, int id) throws InterruptedException, IOException {
        URI apiURI = URI.create(BASE_URL + endpoint.toLowerCase() + "/" + id + "/full");
        HttpRequest request = HttpRequest.newBuilder(apiURI).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ensureSuccess(response, apiURI.toString());
        Class<T> typeClass;
        if ("anime".equalsIgnoreCase(endpoint)) {
            typeClass = (Class<T>) Anime.class;
        } else if ("manga".equalsIgnoreCase(endpoint)) {
            typeClass = (Class<T>) Manga.class;
        } else if ("characters".equalsIgnoreCase(endpoint)) {
            typeClass = (Class<T>) Character.class;
        } else {
            throw new IllegalArgumentException("Unsupported endpoint: " + endpoint);
        }
        String body = response.body();
        Type responseType = TypeToken.getParameterized(SingleResponse.class, typeClass).getType();
        SingleResponse<T> singleResponse = gson.fromJson(body, responseType);
        return singleResponse.getData();
    }

    public List<CharacterItems> getCharactersByItemId (String endpoint, int id) throws InterruptedException, IOException {
        URI apiURI = URI.create(BASE_URL + endpoint + "/" + id + "/characters");
        HttpRequest request = HttpRequest.newBuilder(apiURI).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ensureSuccess(response, apiURI.toString());

        String body = response.body();
        Type responseType = new  TypeToken<ListCharactersItemsResponse>() {}.getType();
        ListCharactersItemsResponse listCharactersItemsResponse = gson.fromJson(body, responseType);
        return listCharactersItemsResponse.getData();
    }

    private void ensureSuccess(HttpResponse<?> response, String url) {
        if (response.statusCode() >= 400) {
            throw new RuntimeException("La llamada " + url + " ha fallado con el código " + response.statusCode());
        }
    }
}
