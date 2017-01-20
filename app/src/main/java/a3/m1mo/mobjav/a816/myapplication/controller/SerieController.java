package a3.m1mo.mobjav.a816.myapplication.controller;

import android.content.Context;

import java.util.List;

import a3.m1mo.mobjav.a816.myapplication.DAO.SerieDAO;
import a3.m1mo.mobjav.a816.myapplication.model.Serie;
import a3.m1mo.mobjav.a816.myapplication.util.HTTPConnectionManager;
import a3.m1mo.mobjav.a816.myapplication.util.ResultListener;

/**
 * Created by Diagnostifo on 28/10/2016.
 */

public class SerieController {

    public void getSeries(Context context, final ResultListener<List<Serie>> listenerSerieDeLaView) {
        SerieDAO serieDAO = new SerieDAO(context);
        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            serieDAO.getSeriesFromWEB(new ResultListener<List<Serie>>() {
                @Override
                public void finish(List<Serie> resultado) {
                    listenerSerieDeLaView.finish(resultado);
                }
            });
        } else {
            List<Serie> listaDeSeries = serieDAO.getSeriesFromDataBase();
            listenerSerieDeLaView.finish(listaDeSeries);
        }
    }

    public void addSerieFavorite(Context context, Integer idSerie) {

        SerieDAO serieDAO = new SerieDAO(context);
        serieDAO.addSerieFavoriteDAO(idSerie);
    }

    public List<Serie> getSeriesFavorite(Context context) {
        SerieDAO serieDAO = new SerieDAO(context);
        List<Serie> listaDeSeries = serieDAO.getSerieFavoriteDAO();
        return listaDeSeries;
    }

    public Boolean CheckisFavourite(Context context, Integer idSerie) {
        SerieDAO serieDAO = new SerieDAO(context);
        return serieDAO.checkIsFavorite(idSerie);
    }

    public Boolean checkIsWatchLater(Context context, Integer idSerie) {

        SerieDAO serieDAO = new SerieDAO(context);
        return serieDAO.checkIsWatchLater(idSerie);

    }


    public List<Serie>getSeriesWatchLater(Context context) {
        SerieDAO serieDAO = new SerieDAO(context);
        List<Serie> listaDeSeries = serieDAO.getSerieWatchLaterDAO();
        return listaDeSeries;
    }

    public void addSerieWatchLater (Context context, Integer idSerie){
        SerieDAO serieDAO = new SerieDAO(context);
        serieDAO.addSerieWatchLaterDAO(idSerie);
    }
}


