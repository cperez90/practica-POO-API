package org.daw;
import org.daw.model.Manga;
import org.daw.model.MediaContent;
import org.daw.service.ApiService;
import org.daw.view.ApiView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        ApiView apiView = new ApiView();
        ApiService apiService = new ApiService();
        //System.out.println("Lista de nombres:");
        //Los parámetros el primero para elegir la lista de entre anime, manga y personajes, y segundo para el limite de paginas para sacar los datos
        //Los endpoint pueden ser todo anime, manga o characters
        apiView.showItemsNames("anime",1);

        //Los parámetros serian todo anime o manga y el segundo un id
        apiView.showItemsCharacters("manga",20);

        //Los parámetros el primero para elegir la lista de entre anime, manga y personajes, y segundo para el limite de paginas para sacar los datos
        //Los endpoint pueden ser todo anime, manga o characters
        //Ordena por nombre
        apiView.showItemsNameSortByName("characters",1);

        //Los parámetros el primero para elegir la lista de entre anime y manga, y segundo para el limite de paginas para sacar los datos
        //Los endpoint pueden ser todo anime, manga o characters
        //Ordena por score
        apiView.showItemsNameSortByScore("manga",1);

        //Los parámetros el primero para elegir la lista de entre anime, manga y personajes, y segundo para el limite de paginas para sacar los datos
        //el tercer paratroop es para filtrar por pablaba o letra
        //Los endpoint pueden ser todo anime, manga o characters
        apiView.showItemsFilterName("anime",1,"cow");

        //Los parámetros el primero para elegir la lista de entre anime o manga, y segundo para el limite de paginas para sacar los datos
        //el tercero para filtrar por palabra o letra y el cuarto es para poner el score mínimo para que filtre por el
        //Los endpoint pueden ser todo anime o manga
        //filtra por palabra o letra y por el score mínimo
        apiView.showItemsFilterNameAndScore("anime",1,"cow",1);

        //Los parámetros el primero para elegir la lista de entre anime o manga, y segundo para el limite de paginas para sacar los datos
        //el tercero para poner el score mínimo para que filtre por el
        //Los endpoint pueden ser todo anime o manga
        //filtra por el score mínimo que le das por parámetro
        apiView.showItemsHighScoreNames("anime",1,8.4);

        //El parámetro es para el límite de páginas para sacar los datos
        //te da el anime y el manga con mejor score de la lista data
        apiView.showItemHighestScoreName(2);

        //Los parámetros el primero para elegir la lista de entre anime, manga y personajes, y segundo para el limite de paginas para sacar los datos
        //el tercero es una condición de filtro que te puedes poner tu pero ojo que hay que caster para poder usar los métodos
        //Los endpoint pueden ser todo anime, manga o characters
        apiView.showItemsFilterPredicate("manga",1,manga -> ((Manga) manga).getPublishing() == false);


        MediaContent item1 = apiService.getByItemId("anime",20);
        Thread.sleep(410);
        MediaContent item2 = apiService.getByItemId("manga",11);
        apiView.ShowComparison(item1,item2);
    }
}