package org.daw;
import org.daw.model.Anime;
import org.daw.model.Character;
import org.daw.model.Manga;
import org.daw.view.ApiView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        ApiView apiView = new ApiView();
        //System.out.println("Lista de nombres:");
        //Los parametros el primero para elegir la lista de entre anime, manga y personajes, y segundo para el limite de paginas para sacar los datos
        //Los endpoint pueden ser todo anime, manga o characters
        apiView.showItemsNames("anime",1);

        //Los parametros serian todo anime o manga y el segundo un id
        apiView.showItemsCharacters("manga",20);

        //Los parametros el primero para elegir la lista de entre anime, manga y personajes, y segundo para el limite de paginas para sacar los datos
        //Los endpoint pueden ser todo anime, manga o characters
        //Ordena por nombre
        apiView.showItemsNameSortByName("characters",1);

        //Los parametros el primero para elegir la lista de entre anime y manga, y segundo para el limite de paginas para sacar los datos
        //Los endpoint pueden ser todo anime, manga o characters
        //Ordena por score
        apiView.showItemsNameSortByScore("manga",1);

        //Los parametros el primero para elegir la lista de entre anime, manga y personajes, y segundo para el limite de paginas para sacar los datos
        //el tercer paramatro es para filtrar por pablabra o letra
        //Los endpoint pueden ser todo anime, manga o characters
        apiView.showItemsFilterName("anime",1,"cow");

        //Los parametros el primero para elegir la lista de entre anime o manga, y segundo para el limite de paginas para sacar los datos
        //el tercero para filtrar por palabra o letra y el cuarto es para poner el score minimo para que filtre por el
        //Los endpoint pueden ser todo anime o manga
        //filtra por palabra o letra y por el score minimo
        apiView.showItemsFilterNameAndScore("anime",1,"cow",1);

        //Los parametros el primero para elegir la lista de entre anime o manga, y segundo para el limite de paginas para sacar los datos
        //el tercero para poner el score minimo para que filtre por el
        //Los endpoint pueden ser todo anime o manga
        //filtra por el score minimo que le das por parametro
        apiView.showItemsHighScoreNames("anime",1,8.4);

        //El parametro es para el limite de paginas para sacar los datos
        //te da el anime y el manga con mejor score de la lista data
        apiView.showItemHighestScoreName(2);


        //Los parametros el primero para elegir la lista de entre anime, manga y personajes, y segundo para el limite de paginas para sacar los datos
        //el tercero es una condicion de filtro que te puedes poner tu pero ojo que hay que castear para poder usar los metodos
        //Los endpoint pueden ser todo anime, manga o characters
        apiView.showItemsFilterPredicate("manga",1,manga -> ((Manga) manga).getPublishing() == false);


    }
}