package org.daw.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.daw.model.ListResponse;
import org.daw.model.SingleResponse;
import org.daw.model.MediaItem;

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
            Thread.sleep(610);
        }
        return items;
    }

    public <T extends MediaItem> T getByItemId(String endpoint, int id, Class<T> typeClass) throws InterruptedException, IOException {
        URI apiURI = URI.create(BASE_URL + endpoint + "/" + id);
        HttpRequest request = HttpRequest.newBuilder(apiURI).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ensureSuccess(response, apiURI.toString());

        String body = response.body();
        Type responseType = TypeToken.getParameterized(SingleResponse.class, typeClass).getType();
        SingleResponse<T> singleResponse = gson.fromJson(body, responseType);
        return singleResponse.getData();
    }

    public <T extends MediaItem> List<T> getCharactersByItemId (String endpoint, int id, Class<T> typeClass) throws InterruptedException, IOException {
        URI apiURI = URI.create(BASE_URL + endpoint + "/" + id + "/characters");
        HttpRequest request = HttpRequest.newBuilder(apiURI).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ensureSuccess(response, apiURI.toString());

        String body = response.body();
        Type responseType = TypeToken.getParameterized(ListResponse.class, typeClass).getType();
        return gson.fromJson(body, responseType);
    }

    private void ensureSuccess(HttpResponse<?> response, String url) {
        if (response.statusCode() >= 400) {
            throw new RuntimeException("La llamada " + url + " ha fallado con el código " + response.statusCode());
        }
    }
}
