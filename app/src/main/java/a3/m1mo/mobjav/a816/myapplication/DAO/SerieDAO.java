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

import a3.m1mo.mobjav.a816.myapplication.model.Serie;
import a3.m1mo.mobjav.a816.myapplication.model.SerieContainer;
import a3.m1mo.mobjav.a816.myapplication.model.TrailerContainerSerie;
import a3.m1mo.mobjav.a816.myapplication.util.DAOexception;
import a3.m1mo.mobjav.a816.myapplication.util.HTTPConnectionManager;
import a3.m1mo.mobjav.a816.myapplication.util.ResultListener;
import a3.m1mo.mobjav.a816.myapplication.util.TMDBHelper;

/**
 * Created by Diagnostifo on 28/10/2016.
 */

public class SerieDAO extends SQLiteOpenHelper {

    private static final String SERIES_DB = "SeriesDB";
    private static final Integer DATABASEVERSION = 1;

    private static final String NAME ="name";
    private static final String ORIGINAL_NAME = "original_name";
    private static final String POSTER_PATH = "poster_path";
    private static final String OVERVIEW = "overview";
    private static final String BACKDROP_PATH = "backdrop_path";
    private static final String ORIGINAL_LANGUAGE = "original_language";
    private static final String POPULARITY = "popularity";
    private static final String ID = "id";
    private static final String VOTE_COUNT = "vote_count";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String WATCH_LATER = "watch_later";
    private static final String FAVORITE_FLAG = "favorite_flag";

    public SerieDAO(Context context) {
        super(context, SERIES_DB, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + SERIES_DB + "("
                + NAME + " TEXT, "
                + OVERVIEW + " TEXT, "
                + POSTER_PATH + " TEXT, "
                + ORIGINAL_NAME + " TEXT, "
                + ORIGINAL_LANGUAGE + " TEXT, "
                + BACKDROP_PATH + " TEXT, "
                + ID + " INTEGER PRIMARY KEY,  "
                + VOTE_COUNT + " TEXT, "
                + POPULARITY + " TEXT, "
                + WATCH_LATER + " INTEGER DEFAULT 0, "
                + FAVORITE_FLAG + " INTEGER DEFAULT 0, "
                + VOTE_AVERAGE + " TEXT " + ")";


        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void getSeriesFromWEB(ResultListener<List<Serie>> listener) {
        RetrieveFeedTaskSerie retrieveFeedTaskSerie = new RetrieveFeedTaskSerie(listener);
        retrieveFeedTaskSerie.execute();
    }

    public void addSeriestoDAO(Serie unaSerie) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues row = new ContentValues();

        row.put(NAME, unaSerie.getName());
        row.put(OVERVIEW, unaSerie.getOverview());
        row.put(POSTER_PATH, unaSerie.getPoster_path());
        row.put(ORIGINAL_NAME, unaSerie.getOriginal_name());
        row.put(ORIGINAL_LANGUAGE, unaSerie.getOriginal_language());
        row.put(BACKDROP_PATH, unaSerie.getBackdrop_path());
        row.put(ID, unaSerie.getId());
        row.put(VOTE_COUNT, unaSerie.getVote_count());
        row.put(POPULARITY, unaSerie.getPopularity());
        row.put(VOTE_AVERAGE, unaSerie.getVote_average());
        row.put(WATCH_LATER, 0);
        row.put(FAVORITE_FLAG, 0);



        database.insert(SERIES_DB, null, row);
        database.close();
    }

    public Serie getSerie (Serie unaSerie) {


        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + SERIES_DB
                + " WHERE " + ID + "== " + unaSerie.getId();



        Cursor cursor = database.rawQuery(selectQuery, null);



        cursor.moveToFirst();



        Integer unId = cursor.getInt(cursor.getColumnIndex(ID));
        String unaOverview = (cursor.getString(cursor.getColumnIndex(OVERVIEW)));
        String unName = (cursor.getString(cursor.getColumnIndex(NAME)));
        String unPosterPath = (cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
        String unOriginalLanguage = (cursor.getString(cursor.getColumnIndex(ORIGINAL_LANGUAGE)));
        String unOriginalName = (cursor.getString(cursor.getColumnIndex(ORIGINAL_NAME)));
        String unBackdropPath = (cursor.getString(cursor.getColumnIndex(BACKDROP_PATH)));
        Integer unVoteCount = (cursor.getInt(cursor.getColumnIndex(VOTE_COUNT)));
        Double unVoteAverage = (cursor.getDouble(cursor.getColumnIndex(VOTE_AVERAGE)));
        Double unaPopularity = (cursor.getDouble(cursor.getColumnIndex(POPULARITY)));


        unaSerie.setId(unId);
        unaSerie.setOverview(unaOverview);
        unaSerie.setName(unName);
        unaSerie.setPoster_path(unPosterPath);
        unaSerie.setOriginal_language(unOriginalLanguage);
        unaSerie.setOriginal_name(unOriginalName);
        unaSerie.setBackdrop_path(unBackdropPath);
        unaSerie.setVote_count(unVoteCount);
        unaSerie.setVote_average(unVoteAverage);
        unaSerie.setPopularity(unaPopularity);


        cursor.close();
        database.close();

        return unaSerie;

    }

    public List<Serie> getSeriesFromDataBase() {

        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + SERIES_DB;
        Cursor cursor = database.rawQuery(selectQuery, null);

        List<Serie> listaDeSeries = new ArrayList<>();
        while (cursor.moveToNext()) {


            Integer unId = cursor.getInt(cursor.getColumnIndex(ID));
            String unaOverview = (cursor.getString(cursor.getColumnIndex(OVERVIEW)));
            String unName = (cursor.getString(cursor.getColumnIndex(NAME)));
            String unPosterPath = (cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
            String unOriginalLanguage = (cursor.getString(cursor.getColumnIndex(ORIGINAL_LANGUAGE)));
            String unOriginalName = (cursor.getString(cursor.getColumnIndex(ORIGINAL_NAME)));
            String unBackdropPath = (cursor.getString(cursor.getColumnIndex(BACKDROP_PATH)));
            Integer unVoteCount = (cursor.getInt(cursor.getColumnIndex(VOTE_COUNT)));
            Double unVoteAverage = (cursor.getDouble(cursor.getColumnIndex(VOTE_AVERAGE)));
            Double unaPopularity = (cursor.getDouble(cursor.getColumnIndex(POPULARITY)));
            Integer unWatchLater = (cursor.getInt(cursor.getColumnIndex(WATCH_LATER)));
            Integer unFavorite = (cursor.getInt(cursor.getColumnIndex(FAVORITE_FLAG)));
            Serie unaSerie = new Serie();

                unaSerie.setId(unId);
                unaSerie.setOverview(unaOverview);
                unaSerie.setName(unName);
                unaSerie.setPoster_path(unPosterPath);
                unaSerie.setOriginal_language(unOriginalLanguage);
                unaSerie.setOriginal_name(unOriginalName);
                unaSerie.setBackdrop_path(unBackdropPath);
                unaSerie.setVote_count(unVoteCount);
                unaSerie.setVote_average(unVoteAverage);
                unaSerie.setPopularity(unaPopularity);
                unaSerie.setFavorite(unFavorite);
                unaSerie.setWatch_later(unWatchLater);

            listaDeSeries.add(unaSerie);
        }
        cursor.close();
        database.close();
        return listaDeSeries;
    }

    public boolean checkIfExist (Serie unaSerie){
        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + SERIES_DB + " WHERE " + ID + "=" + unaSerie.getId()  ;
        Cursor cursor = database.rawQuery(selectQuery, null);
        Integer count = cursor.getCount();
        cursor.close();
        database.close();
        return count > 0;

    }

    public boolean checkIsFavorite (Integer idSerie){

        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + SERIES_DB + " WHERE " + ID + "=" + idSerie + " AND " + FAVORITE_FLAG + "=" + 1  ;
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

    public void addSeriesDB (List<Serie> listSerie){
        for (Serie unaSerie: listSerie){
            if (!checkIfExist(unaSerie)){
                addSeriestoDAO(unaSerie);
            }
        }
    }

    public boolean checkIsWatchLater (Integer idSerie){

        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + SERIES_DB + " WHERE " + ID + "=" + idSerie + " AND " + WATCH_LATER + "=" + 1  ;
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

    public void addSerieWatchLaterDAO (Integer idSerie){
        if (!checkIsWatchLater(idSerie)){
            SQLiteDatabase database = getWritableDatabase();
            ContentValues data=new ContentValues();
            data.put(WATCH_LATER, 1);

            database.update(SERIES_DB, data, " " + ID + "=" + idSerie, null);
        } else {
            SQLiteDatabase database = getWritableDatabase();
            ContentValues data=new ContentValues();
            data.put(WATCH_LATER, 0);

            database.update(SERIES_DB, data, " " + ID + "=" + idSerie, null);
        }
    }

    public void addSerieFavoriteDAO (Integer idSerie){
        if (!checkIsFavorite(idSerie)){
            SQLiteDatabase database = getWritableDatabase();
            ContentValues data=new ContentValues();
            data.put(FAVORITE_FLAG,1);

            database.update(SERIES_DB, data, " " + ID + "=" + idSerie, null);
        } else {
            SQLiteDatabase database = getWritableDatabase();
            ContentValues data=new ContentValues();
            data.put(FAVORITE_FLAG,0);

            database.update(SERIES_DB, data, " " + ID + "=" + idSerie, null);
        }
    }

    public List<Serie> getSerieFavoriteDAO() {

        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + SERIES_DB;
        Cursor cursor = database.rawQuery(selectQuery, null);

        List<Serie> listaDeSeriesFavorites = new ArrayList<>();
        while (cursor.moveToNext()) {


            Integer unId = cursor.getInt(cursor.getColumnIndex(ID));
            String unaOverview = (cursor.getString(cursor.getColumnIndex(OVERVIEW)));
            String unName = (cursor.getString(cursor.getColumnIndex(NAME)));
            String unPosterPath = (cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
            String unOriginalLanguage = (cursor.getString(cursor.getColumnIndex(ORIGINAL_LANGUAGE)));
            String unOriginalName = (cursor.getString(cursor.getColumnIndex(ORIGINAL_NAME)));
            String unBackdropPath = (cursor.getString(cursor.getColumnIndex(BACKDROP_PATH)));
            Integer unVoteCount = (cursor.getInt(cursor.getColumnIndex(VOTE_COUNT)));
            Double unVoteAverage = (cursor.getDouble(cursor.getColumnIndex(VOTE_AVERAGE)));
            Double unaPopularity = (cursor.getDouble(cursor.getColumnIndex(POPULARITY)));
            Integer unFavorite = (cursor.getInt(cursor.getColumnIndex(FAVORITE_FLAG)));



            Serie unaSerie = new Serie();

            unaSerie.setId(unId);
            unaSerie.setOverview(unaOverview);
            unaSerie.setName(unName);
            unaSerie.setPoster_path(unPosterPath);
            unaSerie.setOriginal_language(unOriginalLanguage);
            unaSerie.setOriginal_name(unOriginalName);
            unaSerie.setBackdrop_path(unBackdropPath);
            unaSerie.setVote_count(unVoteCount);
            unaSerie.setVote_average(unVoteAverage);
            unaSerie.setPopularity(unaPopularity);
            unaSerie.setFavorite(unFavorite);
            if (checkIsFavorite(unaSerie.getId())) {
                listaDeSeriesFavorites.add(unaSerie);
            }
        }
            cursor.close();
            database.close();
        return listaDeSeriesFavorites;
        }

    public List<Serie> getSerieWatchLaterDAO() {

        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + SERIES_DB;
        Cursor cursor = database.rawQuery(selectQuery, null);

        List<Serie> listaDeSeriesFavorites = new ArrayList<>();
        while (cursor.moveToNext()) {


            Integer unId = cursor.getInt(cursor.getColumnIndex(ID));
            String unaOverview = (cursor.getString(cursor.getColumnIndex(OVERVIEW)));
            String unName = (cursor.getString(cursor.getColumnIndex(NAME)));
            String unPosterPath = (cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
            String unOriginalLanguage = (cursor.getString(cursor.getColumnIndex(ORIGINAL_LANGUAGE)));
            String unOriginalName = (cursor.getString(cursor.getColumnIndex(ORIGINAL_NAME)));
            String unBackdropPath = (cursor.getString(cursor.getColumnIndex(BACKDROP_PATH)));
            Integer unVoteCount = (cursor.getInt(cursor.getColumnIndex(VOTE_COUNT)));
            Double unVoteAverage = (cursor.getDouble(cursor.getColumnIndex(VOTE_AVERAGE)));
            Double unaPopularity = (cursor.getDouble(cursor.getColumnIndex(POPULARITY)));
            Integer unWatchLater = (cursor.getInt(cursor.getColumnIndex(WATCH_LATER)));
            Integer unFavorite = (cursor.getInt(cursor.getColumnIndex(FAVORITE_FLAG)));


            Serie unaSerie = new Serie();

            unaSerie.setId(unId);
            unaSerie.setOverview(unaOverview);
            unaSerie.setName(unName);
            unaSerie.setPoster_path(unPosterPath);
            unaSerie.setOriginal_language(unOriginalLanguage);
            unaSerie.setOriginal_name(unOriginalName);
            unaSerie.setBackdrop_path(unBackdropPath);
            unaSerie.setVote_count(unVoteCount);
            unaSerie.setVote_average(unVoteAverage);
            unaSerie.setPopularity(unaPopularity);
            unaSerie.setWatch_later(unWatchLater);
            unaSerie.setFavorite(unFavorite);
            if (checkIsWatchLater(unaSerie.getId())) {
                listaDeSeriesFavorites.add(unaSerie);
            }
        }
        cursor.close();
        database.close();
        return listaDeSeriesFavorites;
    }


   class RetrieveFeedTaskSerie extends AsyncTask<String, Void, List<Serie>> {

            private ResultListener<List<Serie>> listener;

            public RetrieveFeedTaskSerie(ResultListener<List<Serie>> listener) {
                this.listener = listener;
            }

            @Override
            protected List<Serie> doInBackground(String... params) {
                HTTPConnectionManager connectionManager = new HTTPConnectionManager();
                String input = null;
                String url =  TMDBHelper.getTVPopular(TMDBHelper.LANGUAGE_ENGLISH, 1);
                try {
                    input = connectionManager.getRequestString(url);
                } catch (DAOexception daOexception) {
                    daOexception.printStackTrace();
                }
                Gson gson = new Gson();
                SerieContainer serieContainer  = gson.fromJson(input, SerieContainer.class);
                List<Serie> seriesList = serieContainer.getListaDeSeries();

                int i = 0;


                for (i = 0; i < seriesList.size(); i++) {
                    Serie unaSerie  = seriesList.get(i);


                      String urlTrailer = TMDBHelper.getTVShowVideo(unaSerie.getOriginal_language(), unaSerie.getId().toString());

                    try {
                        input = connectionManager.getRequestString(urlTrailer);
                        Gson gsonTrailer = new Gson();
                        TrailerContainerSerie trailerContainerSerie = gsonTrailer.fromJson(input, TrailerContainerSerie.class);

                        if (trailerContainerSerie.getListaDeTrailer() != null && trailerContainerSerie.getListaDeTrailer().size() > 0) {
                            Serie.TrailerSerie trailerSerie = trailerContainerSerie.getListaDeTrailer().get(0);
                            unaSerie.setUrl_trailer("http://youtube.com/watch?v=" + trailerSerie.getKey());
                        }


                    } catch (DAOexception daOexception) {
                        daOexception.printStackTrace();
                    }

                }
                return seriesList;
            }

            @Override
            protected void onPostExecute(List<Serie> listaDeSeries) {
                addSeriesDB(listaDeSeries);
                listener.finish(listaDeSeries);
            }
        }
}

