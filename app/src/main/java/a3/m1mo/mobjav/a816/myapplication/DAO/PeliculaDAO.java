package a3.m1mo.mobjav.a816.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import a3.m1mo.mobjav.a816.myapplication.model.Pelicula;
import a3.m1mo.mobjav.a816.myapplication.model.PeliculaContainer;
import a3.m1mo.mobjav.a816.myapplication.model.TrailerContainerPelicula;
import a3.m1mo.mobjav.a816.myapplication.util.DAOexception;
import a3.m1mo.mobjav.a816.myapplication.util.HTTPConnectionManager;
import a3.m1mo.mobjav.a816.myapplication.util.ResultListener;
import a3.m1mo.mobjav.a816.myapplication.util.TMDBHelper;

/**
 * Created by SilvestreLottero on 10/25/16.
 */

public class PeliculaDAO extends SQLiteOpenHelper {


    // Declaración DB
    private static final String MOVIES_DB = "MoviesDB";
    private static final Integer DATABASEVERSION = 1;

    // Declaración de las variables de las películas


    private static final String TITLE = "title";
    private static final String OVERVIEW = "overview";
    private static final String POSTER_PATH = "poster_path";
    private static final String RELEASE_DATE = "release_date";
    private static final String ORIGINAL_TITLE = "original_title";
    private static final String ORIGINAL_LANGUAGE = "original_language";
    private static final String BACKDROP_PATH = "backdrop_path";
    private static final String ID = "id";
    private static final String VOTE_COUNT = "vote_count";
    private static final String POPULARITY = "popularity";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String ADULT = "adult";
    private static final String VIDEO = "video";
    private static final String GENRE_IDS = "genre_ids";
    private static final String HISTORY_USER = "history_user";
    private static final String WATCH_LATER = "watch_later";
    private static final String FAVORITE_FLAG = "favorite_flag";




    // Creación de la tabla de películas en la DB

        public PeliculaDAO(Context context) {
        super(context, MOVIES_DB, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + MOVIES_DB + "("
                + TITLE + " TEXT, "
                + OVERVIEW + " TEXT, "
                + POSTER_PATH + " TEXT, "
                + RELEASE_DATE + " TEXT, "
                + ORIGINAL_TITLE + " TEXT, "
                + ORIGINAL_LANGUAGE + " TEXT, "
                + BACKDROP_PATH + " TEXT, "
                + ID + " INTEGER PRIMARY KEY, "
                + VOTE_COUNT + " TEXT, "
                + POPULARITY + " TEXT, "
                + VOTE_AVERAGE + " TEXT, "
                + ADULT + " TEXT, "
                + VIDEO + " TEXT, "
                + GENRE_IDS + " TEXT, "
                + HISTORY_USER + " TEXT, "
                + WATCH_LATER + " INTEGER DEFAULT 0, "
                + FAVORITE_FLAG + " INTEGER DEFAULT 0"
                + ")";


        sqLiteDatabase.execSQL(createTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // Agregar una película a la DB

    public void addMovietoDAO(Pelicula unaPelicula) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues row = new ContentValues();

        row.put(TITLE, unaPelicula.getTitle());
        row.put(OVERVIEW, unaPelicula.getOverview());
        row.put(POSTER_PATH, unaPelicula.getPoster_path());
        row.put(RELEASE_DATE, unaPelicula.getRelease_date());
        row.put(ORIGINAL_LANGUAGE, unaPelicula.getOriginal_language());
        row.put(ORIGINAL_TITLE, unaPelicula.getOriginal_title());
        row.put(BACKDROP_PATH, unaPelicula.getBackdrop_path());
        row.put(ID, unaPelicula.getId());
        row.put(VOTE_COUNT, unaPelicula.getVote_count());
        row.put(POPULARITY, unaPelicula.getPopularity());
        row.put(VOTE_AVERAGE, unaPelicula.getVote_average());
        row.put(ADULT, unaPelicula.getAdult());
        row.put(VIDEO, unaPelicula.getVideo());
        row.put(WATCH_LATER, 0);
        row.put(FAVORITE_FLAG, 0);


        database.insert(MOVIES_DB, null, row);
        database.close();
    }


    public Pelicula getPelicula(Pelicula unaPelicula) {


        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + MOVIES_DB
                + " WHERE " + ID + "== " + unaPelicula.getId();

        //Con este método obtengo una cursor/puntero/dedo que apunta al comienzo de la tabla de la query

        Cursor cursor = database.rawQuery(selectQuery, null);

        //Con este método lo posiciono sobre la primera fila

        cursor.moveToFirst();

        //LEI DE LA TABLA EL VALOR ID Y LO ALMACENE EN LA PELICULA

        Integer unId = cursor.getInt(cursor.getColumnIndex(ID));
        String unaOverview = (cursor.getString(cursor.getColumnIndex(OVERVIEW)));
        String unTitulo = (cursor.getString(cursor.getColumnIndex(TITLE)));
        String unRelease = (cursor.getString(cursor.getColumnIndex(RELEASE_DATE)));
        String unPosterPath = (cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
        String unOriginalLanguage = (cursor.getString(cursor.getColumnIndex(ORIGINAL_LANGUAGE)));
        String unOriginalTitle = (cursor.getString(cursor.getColumnIndex(ORIGINAL_TITLE)));
        String unBackdropPath = (cursor.getString(cursor.getColumnIndex(BACKDROP_PATH)));
        Integer unVoteCount = (cursor.getInt(cursor.getColumnIndex(VOTE_COUNT)));
        Double unVoteAverage = (cursor.getDouble(cursor.getColumnIndex(VOTE_AVERAGE)));
        String unAdult = (cursor.getString(cursor.getColumnIndex(ADULT)));
        String unVideo = (cursor.getString(cursor.getColumnIndex(VIDEO)));
        Double unaPopularity = (cursor.getDouble(cursor.getColumnIndex(POPULARITY)));
        String unGenreIds = (cursor.getString(cursor.getColumnIndex(GENRE_IDS)));

        unaPelicula.setId(unId);
        unaPelicula.setRelease_date(unRelease);
        unaPelicula.setOverview(unaOverview);
        unaPelicula.setTitle(unTitulo);
        unaPelicula.setPoster_path(unPosterPath);
        unaPelicula.setOriginal_language(unOriginalLanguage);
        unaPelicula.setOriginal_title(unOriginalTitle);
        unaPelicula.setBackdrop_path(unBackdropPath);
        unaPelicula.setVote_count(unVoteCount);
        unaPelicula.setVote_average(unVoteAverage);
        unaPelicula.setAdult(Boolean.parseBoolean(unAdult));
        unaPelicula.setRelease_date(unVideo);
        unaPelicula.setPopularity(unaPopularity);
       // unaPelicula.setGenre_ids(unGenreIds.toCharArray());





        //Antes de salir del método tengo que cerrar la conexión con la base de datos y liberar el cursor
        cursor.close();
        database.close();

        return unaPelicula;

    }

    public List<Pelicula> getPeliculasFromDataBase() {

        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + MOVIES_DB;
        Cursor cursor = database.rawQuery(selectQuery, null);

        List<Pelicula> listaDePeliculas = new ArrayList<>();
        while (cursor.moveToNext()) {


            Integer unId = cursor.getInt(cursor.getColumnIndex(ID));
            String unaOverview = (cursor.getString(cursor.getColumnIndex(OVERVIEW)));
            String unTitulo = (cursor.getString(cursor.getColumnIndex(TITLE)));
            String unRelease = (cursor.getString(cursor.getColumnIndex(RELEASE_DATE)));
            String unPosterPath = (cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
            String unOriginalLanguage = (cursor.getString(cursor.getColumnIndex(ORIGINAL_LANGUAGE)));
            String unOriginalTitle = (cursor.getString(cursor.getColumnIndex(ORIGINAL_TITLE)));
            String unBackdropPath = (cursor.getString(cursor.getColumnIndex(BACKDROP_PATH)));
            Integer unVoteCount = (cursor.getInt(cursor.getColumnIndex(VOTE_COUNT)));
            Double unVoteAverage = (cursor.getDouble(cursor.getColumnIndex(VOTE_AVERAGE)));
            String unAdult = (cursor.getString(cursor.getColumnIndex(ADULT)));
            String unVideo = (cursor.getString(cursor.getColumnIndex(VIDEO)));
            Double unaPopularity = (cursor.getDouble(cursor.getColumnIndex(POPULARITY)));
            Integer unWatchLater = (cursor.getInt(cursor.getColumnIndex(WATCH_LATER)));
            Integer unFavorite = (cursor.getInt(cursor.getColumnIndex(FAVORITE_FLAG)));



          Pelicula unaPelicula = new Pelicula();

              unaPelicula.setId(unId);
              unaPelicula.setRelease_date(unRelease);
              unaPelicula.setOverview(unaOverview);
              unaPelicula.setTitle(unTitulo);
              unaPelicula.setPoster_path(unPosterPath);
              unaPelicula.setOriginal_language(unOriginalLanguage);
              unaPelicula.setOriginal_title(unOriginalTitle);
              unaPelicula.setBackdrop_path(unBackdropPath);
              unaPelicula.setVote_count(unVoteCount);
              unaPelicula.setVote_average(unVoteAverage);
              unaPelicula.setAdult(Boolean.parseBoolean(unAdult));
              unaPelicula.setRelease_date(unVideo);
              unaPelicula.setPopularity(unaPopularity);
              unaPelicula.setFavorite(unFavorite);
              unaPelicula.setWatchLater(unWatchLater);


            listaDePeliculas.add(unaPelicula);
          }
            cursor.close();
            database.close();
        return listaDePeliculas;
    }

    public boolean checkIfExist (Pelicula unaPelicula){
        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + MOVIES_DB + " WHERE " + ID + "=" + unaPelicula.getId()  ;
        Cursor cursor = database.rawQuery(selectQuery, null);
        Integer count = cursor.getCount();
        cursor.close();
        database.close();
        return count > 0;

    }

    public void addMoviesDB (List<Pelicula> listMovie){
        for (Pelicula unaPelicula: listMovie){
            if (!checkIfExist(unaPelicula)){
                addMovietoDAO(unaPelicula);
            }
        }
    }

    public boolean checkIsFavorite (Integer idPelicula){

        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + MOVIES_DB + " WHERE " + ID + "=" + idPelicula + " AND " + FAVORITE_FLAG + "=" + 1  ;
        Cursor cursor = database.rawQuery(selectQuery, null);
        Integer count = cursor.getCount();
        cursor.close();
        database.close();
        if (count > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void addPeliculaFavoriteDAO (Integer idPelicula){
        if (!checkIsFavorite(idPelicula)){
            SQLiteDatabase database = getWritableDatabase();
            ContentValues data=new ContentValues();
            data.put(FAVORITE_FLAG,1);

            database.update(MOVIES_DB, data, " " + ID + "=" + idPelicula, null);
        } else {
            SQLiteDatabase database = getWritableDatabase();
            ContentValues data=new ContentValues();
            data.put(FAVORITE_FLAG,0);

            database.update(MOVIES_DB, data, " " + ID + "=" + idPelicula, null);
        }
    }

    public List<Pelicula> getPeliculaFavoriteDAO () {
        List<Pelicula> listaDePeliculasFavoritas = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + MOVIES_DB;
        Cursor cursor = database.rawQuery(selectQuery, null);



        while (cursor.moveToNext()) {
                Pelicula unaPelicula = new Pelicula();
                Integer unId = cursor.getInt(cursor.getColumnIndex(ID));
                unaPelicula.setId(unId);
            if (checkIsFavorite(unaPelicula.getId())) {
                listaDePeliculasFavoritas.add(unaPelicula);

            String unaOverview = (cursor.getString(cursor.getColumnIndex(OVERVIEW)));
            String unTitulo = (cursor.getString(cursor.getColumnIndex(TITLE)));
            String unRelease = (cursor.getString(cursor.getColumnIndex(RELEASE_DATE)));
            String unPosterPath = (cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
            String unOriginalLanguage = (cursor.getString(cursor.getColumnIndex(ORIGINAL_LANGUAGE)));
            String unOriginalTitle = (cursor.getString(cursor.getColumnIndex(ORIGINAL_TITLE)));
            String unBackdropPath = (cursor.getString(cursor.getColumnIndex(BACKDROP_PATH)));
            Integer unVoteCount = (cursor.getInt(cursor.getColumnIndex(VOTE_COUNT)));
            Double unVoteAverage = (cursor.getDouble(cursor.getColumnIndex(VOTE_AVERAGE)));
            String unAdult = (cursor.getString(cursor.getColumnIndex(ADULT)));
            String unVideo = (cursor.getString(cursor.getColumnIndex(VIDEO)));
            Double unaPopularity = (cursor.getDouble(cursor.getColumnIndex(POPULARITY)));
            Integer unWatchLater = (cursor.getInt(cursor.getColumnIndex(WATCH_LATER)));
            Integer unFavorite = (cursor.getInt(cursor.getColumnIndex(FAVORITE_FLAG)));

            unaPelicula.setRelease_date(unRelease);
            unaPelicula.setOverview(unaOverview);
            unaPelicula.setTitle(unTitulo);
            unaPelicula.setPoster_path(unPosterPath);
            unaPelicula.setOriginal_language(unOriginalLanguage);
            unaPelicula.setOriginal_title(unOriginalTitle);
            unaPelicula.setBackdrop_path(unBackdropPath);
            unaPelicula.setVote_count(unVoteCount);
            unaPelicula.setVote_average(unVoteAverage);
            unaPelicula.setAdult(Boolean.parseBoolean(unAdult));
            unaPelicula.setRelease_date(unVideo);
            unaPelicula.setPopularity(unaPopularity);
            unaPelicula.setFavorite(unFavorite);
            unaPelicula.setWatchLater(unWatchLater);

            }
        }
        cursor.close();
        database.close();
        return listaDePeliculasFavoritas;
        }

    public boolean checkIsWatchLater (Integer idPelicula) {
            SQLiteDatabase database = getReadableDatabase();

            String selectQuery = "SELECT * FROM " + MOVIES_DB + " WHERE " + ID + "=" + idPelicula + " AND " + WATCH_LATER + "=" + 1  ;
            Cursor cursor = database.rawQuery(selectQuery, null);
            Integer count = cursor.getCount();
            return count > 0;
        }

    public void addPeliculaWatchLaterDAO (Integer idPelicula){
        if (!checkIsWatchLater(idPelicula)){
            SQLiteDatabase database = getWritableDatabase();
            ContentValues data=new ContentValues();
            data.put(WATCH_LATER, 1);

            database.update(MOVIES_DB, data, " " + ID + "=" + idPelicula, null);
        } else {
            SQLiteDatabase database = getWritableDatabase();
            ContentValues data=new ContentValues();
            data.put(WATCH_LATER, 0);

            database.update(MOVIES_DB, data, " WHERE " + ID + "=" + idPelicula, null);
        }
    }

    public List<Pelicula> getPeliculasWatchLater() {
        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + MOVIES_DB;
        Cursor cursor = database.rawQuery(selectQuery, null);

        List<Pelicula> listaDePeliculasWatchLater = new ArrayList<>();
        while (cursor.moveToNext()) {

            Integer unId = cursor.getInt(cursor.getColumnIndex(ID));
            String unaOverview = (cursor.getString(cursor.getColumnIndex(OVERVIEW)));
            String unTitulo = (cursor.getString(cursor.getColumnIndex(TITLE)));
            String unRelease = (cursor.getString(cursor.getColumnIndex(RELEASE_DATE)));
            String unPosterPath = (cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
            String unOriginalLanguage = (cursor.getString(cursor.getColumnIndex(ORIGINAL_LANGUAGE)));
            String unOriginalTitle = (cursor.getString(cursor.getColumnIndex(ORIGINAL_TITLE)));
            String unBackdropPath = (cursor.getString(cursor.getColumnIndex(BACKDROP_PATH)));
            Integer unVoteCount = (cursor.getInt(cursor.getColumnIndex(VOTE_COUNT)));
            Double unVoteAverage = (cursor.getDouble(cursor.getColumnIndex(VOTE_AVERAGE)));
            String unAdult = (cursor.getString(cursor.getColumnIndex(ADULT)));
            String unVideo = (cursor.getString(cursor.getColumnIndex(VIDEO)));
            Double unaPopularity = (cursor.getDouble(cursor.getColumnIndex(POPULARITY)));
            Integer unWatchLater = (cursor.getInt(cursor.getColumnIndex(WATCH_LATER)));
            Integer unFavorite = (cursor.getInt(cursor.getColumnIndex(FAVORITE_FLAG)));


            Pelicula unaPelicula = new Pelicula();

            unaPelicula.setId(unId);
            unaPelicula.setRelease_date(unRelease);
            unaPelicula.setOverview(unaOverview);
            unaPelicula.setTitle(unTitulo);
            unaPelicula.setPoster_path(unPosterPath);
            unaPelicula.setOriginal_language(unOriginalLanguage);
            unaPelicula.setOriginal_title(unOriginalTitle);
            unaPelicula.setBackdrop_path(unBackdropPath);
            unaPelicula.setVote_count(unVoteCount);
            unaPelicula.setVote_average(unVoteAverage);
            unaPelicula.setAdult(Boolean.parseBoolean(unAdult));
            unaPelicula.setRelease_date(unVideo);
            unaPelicula.setPopularity(unaPopularity);
            unaPelicula.setFavorite(unFavorite);
            unaPelicula.setWatchLater(unWatchLater);

            if (checkIsWatchLater(unaPelicula.getId())) {
                listaDePeliculasWatchLater.add(unaPelicula);
            }
        }
        cursor.close();
        database.close();
        return listaDePeliculasWatchLater;
    }








    class RetrieveFeedTaskPelicula extends AsyncTask<String, Void, List<Pelicula>> {
        private ResultListener<List<Pelicula>> listener;

        public void setListener(ResultListener<List<Pelicula>> listener) {
            this.listener = listener;
        }


        public RetrieveFeedTaskPelicula(ResultListener<List<Pelicula>> listener) {
            this.listener = listener;
        }

        @Override
        protected List<Pelicula> doInBackground(String... params) {

            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;
            String url = TMDBHelper.getPopularMovies(TMDBHelper.LANGUAGE_ENGLISH, 1);
            try {
                input = connectionManager.getRequestString(url);
            } catch (DAOexception daOexception) {
                daOexception.printStackTrace();
            }
            Gson gson = new Gson();
            PeliculaContainer peliculaContainer = gson.fromJson(input, PeliculaContainer.class);

            List<Pelicula> peliculasList = peliculaContainer.getListaDePeliculas();
            int i = 0;


            for (i = 0; i < peliculasList.size(); i++) {
                Pelicula unaPelicula = peliculasList.get(i);


                String urlTrailer = TMDBHelper.getTrailerURL(unaPelicula.getId().toString(), unaPelicula.getOriginal_language());

                try {
                    input = connectionManager.getRequestString(urlTrailer);
                    Gson gsonTrailer = new Gson();
                    TrailerContainerPelicula trailerContainerPelicula = gsonTrailer.fromJson(input, TrailerContainerPelicula.class);

                    if (trailerContainerPelicula.getListaDeTrailer() != null) {
                        Pelicula.TrailerPelicula trailerPelicula = trailerContainerPelicula.getListaDeTrailer().get(0);
                        unaPelicula.setUrlTrailer("http://youtube.com/watch?v=" + trailerPelicula.getKey());
                    }

                } catch (DAOexception daOexception) {
                    daOexception.printStackTrace();
                }

            }
            return peliculasList;
        }

        @Override
        protected void onPostExecute(List<Pelicula> peliculasList) {
            addMoviesDB(peliculasList);
            listener.finish(peliculasList);
        }


    }

    public void getPeliculasFromWEB(ResultListener<List<Pelicula>> listener) {
        RetrieveFeedTaskPelicula retrieveFeedTask = new RetrieveFeedTaskPelicula(listener);
        retrieveFeedTask.execute();
    }





    public class RetrieveFeedTaskTrendingPelicula extends AsyncTask<String, Void, List<Pelicula>> {
        private ResultListener<List<Pelicula>> otroListener;

        public void setListener(ResultListener<List<Pelicula>> otrolistener) {
            this.otroListener = otrolistener;
        }


        public RetrieveFeedTaskTrendingPelicula(ResultListener<List<Pelicula>> otrolistener) {
            this.otroListener = otrolistener;
        }

        @Override
        protected List<Pelicula> doInBackground(String... params) {

            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;
            String url = TMDBHelper.getMoviesByGenre(TMDBHelper.MOVIE_GENRE_ADVENTURE, 1, TMDBHelper.LANGUAGE_ENGLISH);
            try {
                input = connectionManager.getRequestString(url);
            } catch (DAOexception daOexception) {
                daOexception.printStackTrace();
            }
            Gson gson = new Gson();
            PeliculaContainer peliculaContainer = gson.fromJson(input, PeliculaContainer.class);

            List<Pelicula> peliculasTrendingList = peliculaContainer.getListaDePeliculas();
            int i = 0;


            for (i = 0; i < peliculasTrendingList.size(); i++) {
                Pelicula unaPelicula = peliculasTrendingList.get(i);


                String urlTrailer = TMDBHelper.getTrailerURL(unaPelicula.getId().toString(), unaPelicula.getOriginal_language());

                try {
                    input = connectionManager.getRequestString(urlTrailer);
                    Gson gsonTrailer = new Gson();
                    TrailerContainerPelicula trailerContainerPelicula = gsonTrailer.fromJson(input, TrailerContainerPelicula.class);

                    if (trailerContainerPelicula.getListaDeTrailer() != null) {
                        Pelicula.TrailerPelicula trailerPelicula = trailerContainerPelicula.getListaDeTrailer().get(0);
                        unaPelicula.setUrlTrailer("http://youtube.com/watch?v=" + trailerPelicula.getKey());
                    }

                } catch (DAOexception daOexception) {
                    daOexception.printStackTrace();
                }

            }
            return peliculasTrendingList;
        }

        @Override
        protected void onPostExecute(List<Pelicula> peliculasTrendingList) {
            addMoviesDB(peliculasTrendingList);
            otroListener.finish(peliculasTrendingList);

        }


    }
    public void getPeliculasTrendingFromWEB(ResultListener<List<Pelicula>> otroListener) {
        RetrieveFeedTaskTrendingPelicula retrieveFeedTask2 = new RetrieveFeedTaskTrendingPelicula(otroListener);
        retrieveFeedTask2.execute();
    }





    public class RetrieveFeedTaskAventuraPelicula extends AsyncTask<String, Void, List<Pelicula>> {
        private ResultListener<List<Pelicula>> esteListener;

        public void setListener(ResultListener<List<Pelicula>> esteListener) {
            this.esteListener = esteListener;
        }


        public RetrieveFeedTaskAventuraPelicula(ResultListener<List<Pelicula>> esteListener) {
            this.esteListener = esteListener;
        }

        @Override
        protected List<Pelicula> doInBackground(String... params) {

            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;
            String url = TMDBHelper.getMoviesByGenre(TMDBHelper.MOVIE_GENRE_ADVENTURE, 1, TMDBHelper.LANGUAGE_ENGLISH);
            try {
                input = connectionManager.getRequestString(url);
            } catch (DAOexception daOexception) {
                daOexception.printStackTrace();
            }
            Gson gson = new Gson();
            PeliculaContainer peliculaContainer = gson.fromJson(input, PeliculaContainer.class);

            List<Pelicula> peliculasAventuraList = peliculaContainer.getListaDePeliculas();
            int i = 0;


            for (i = 0; i < peliculasAventuraList.size(); i++) {
                Pelicula unaPelicula = peliculasAventuraList.get(i);


                String urlTrailer = TMDBHelper.getTrailerURL(unaPelicula.getId().toString(), unaPelicula.getOriginal_language());

                try {
                    input = connectionManager.getRequestString(urlTrailer);
                    Gson gsonTrailer = new Gson();
                    TrailerContainerPelicula trailerContainerPelicula = gsonTrailer.fromJson(input, TrailerContainerPelicula.class);

                    if (trailerContainerPelicula.getListaDeTrailer() != null) {
                        Pelicula.TrailerPelicula trailerPelicula = trailerContainerPelicula.getListaDeTrailer().get(0);
                        unaPelicula.setUrlTrailer("http://youtube.com/watch?v=" + trailerPelicula.getKey());
                    }

                } catch (DAOexception daOexception) {
                    daOexception.printStackTrace();
                }

            }
            return peliculasAventuraList;
        }

        @Override
        protected void onPostExecute(List<Pelicula> peliculasAventuraList) {
            addMoviesDB(peliculasAventuraList);
            esteListener.finish(peliculasAventuraList);

        }


    }

    public void getPeliculasAventuraFromWEB(ResultListener<List<Pelicula>>esteListener) {
        RetrieveFeedTaskDocumentalPelicula retrieveFeedTaskDocumentalPelicula = new RetrieveFeedTaskDocumentalPelicula(esteListener);
        retrieveFeedTaskDocumentalPelicula.execute();
    }


    public class RetrieveFeedTaskDocumentalPelicula extends AsyncTask<String, Void, List<Pelicula>> {
        private ResultListener<List<Pelicula>> documentalListener;

        public void setListener(ResultListener<List<Pelicula>> documentalListener) {
            this.documentalListener = documentalListener;
        }


        public RetrieveFeedTaskDocumentalPelicula(ResultListener<List<Pelicula>> documentalListener) {
            this.documentalListener = documentalListener;
        }

        @Override
        protected List<Pelicula> doInBackground(String... params) {

            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;
            String url = TMDBHelper.getMoviesByGenre(TMDBHelper.MOVIE_GENRE_DOCUMENTARY, 1, TMDBHelper.LANGUAGE_ENGLISH);
            try {
                input = connectionManager.getRequestString(url);
            } catch (DAOexception daOexception) {
                daOexception.printStackTrace();
            }
            Gson gson = new Gson();
            PeliculaContainer peliculaDocumentalContainer = gson.fromJson(input, PeliculaContainer.class);

            List<Pelicula> peliculasDocumentalList = peliculaDocumentalContainer.getListaDePeliculas();
            int i = 0;


            for (i = 0; i < peliculasDocumentalList.size(); i++) {
                Pelicula unaPelicula = peliculasDocumentalList.get(i);


                String urlTrailer = TMDBHelper.getTrailerURL(unaPelicula.getId().toString(), unaPelicula.getOriginal_language());

                try {
                    input = connectionManager.getRequestString(urlTrailer);
                    Gson gsonTrailer = new Gson();
                    TrailerContainerPelicula trailerContainerPelicula = gsonTrailer.fromJson(input, TrailerContainerPelicula.class);

                    if (trailerContainerPelicula.getListaDeTrailer() != null) {
                        Pelicula.TrailerPelicula trailerPelicula = trailerContainerPelicula.getListaDeTrailer().get(0);
                        unaPelicula.setUrlTrailer("http://youtube.com/watch?v=" + trailerPelicula.getKey());
                    }

                } catch (DAOexception daOexception) {
                    daOexception.printStackTrace();
                }

            }
            return peliculasDocumentalList;
        }

        @Override
        protected void onPostExecute(List<Pelicula> peliculasDocumentalList) {
            addMoviesDB(peliculasDocumentalList);
            documentalListener.finish(peliculasDocumentalList);
        }


    }

    public void getPeliculasDocumentalFromWEB(ResultListener<List<Pelicula>>esteListener) {
        RetrieveFeedTaskAventuraPelicula retrieveFeedTaskAventuraPelicula = new RetrieveFeedTaskAventuraPelicula(esteListener);
        retrieveFeedTaskAventuraPelicula.execute();
    }





    public class RetrieveFeedTaskDramaPelicula extends AsyncTask<String, Void, List<Pelicula>> {
        private ResultListener<List<Pelicula>> dramaListener;

        public void setListener(ResultListener<List<Pelicula>> dramaListener) {
            this.dramaListener = dramaListener;
        }


        public RetrieveFeedTaskDramaPelicula(ResultListener<List<Pelicula>> dramaListener) {
            this.dramaListener = dramaListener;
        }

        @Override
        protected List<Pelicula> doInBackground(String... params) {

            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;
            String url = TMDBHelper.getMoviesByGenre(TMDBHelper.MOVIE_GENRE_DRAMA, 1, TMDBHelper.LANGUAGE_ENGLISH);
            try {
                input = connectionManager.getRequestString(url);
            } catch (DAOexception daOexception) {
                daOexception.printStackTrace();
            }
            Gson gson = new Gson();
            PeliculaContainer peliculaContainer = gson.fromJson(input, PeliculaContainer.class);

            List<Pelicula> peliculasDramaList = peliculaContainer.getListaDePeliculas();
            int i = 0;


            for (i = 0; i < peliculasDramaList.size(); i++) {
                Pelicula unaPelicula = peliculasDramaList.get(i);


                String urlTrailer = TMDBHelper.getTrailerURL(unaPelicula.getId().toString(), unaPelicula.getOriginal_language());

                try {
                    input = connectionManager.getRequestString(urlTrailer);
                    Gson gsonTrailer = new Gson();
                    TrailerContainerPelicula trailerContainerPelicula = gsonTrailer.fromJson(input, TrailerContainerPelicula.class);

                    if (trailerContainerPelicula.getListaDeTrailer() != null) {
                        Pelicula.TrailerPelicula trailerPelicula = trailerContainerPelicula.getListaDeTrailer().get(0);
                        unaPelicula.setUrlTrailer("http://youtube.com/watch?v=" + trailerPelicula.getKey());
                    }

                } catch (DAOexception daOexception) {
                    daOexception.printStackTrace();
                }

            }
            return peliculasDramaList;
        }

        @Override
        protected void onPostExecute(List<Pelicula> peliculasDramaList) {
            addMoviesDB(peliculasDramaList);
            dramaListener.finish(peliculasDramaList);
        }


    }

    public void getPeliculasDramaFromWEB(ResultListener<List<Pelicula>>dramaListener) {
        RetrieveFeedTaskDramaPelicula retrieveFeedTaskDramaPelicula = new RetrieveFeedTaskDramaPelicula(dramaListener);
        retrieveFeedTaskDramaPelicula.execute();
    }
}
