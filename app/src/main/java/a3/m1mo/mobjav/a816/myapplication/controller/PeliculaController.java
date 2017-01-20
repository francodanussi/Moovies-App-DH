package a3.m1mo.mobjav.a816.myapplication.controller;

import android.content.Context;

import java.util.List;

import a3.m1mo.mobjav.a816.myapplication.DAO.PeliculaDAO;
import a3.m1mo.mobjav.a816.myapplication.model.Pelicula;
import a3.m1mo.mobjav.a816.myapplication.util.HTTPConnectionManager;
import a3.m1mo.mobjav.a816.myapplication.util.ResultListener;

/**
 * Created by Diagnostifo on 26/10/2016.
 */
public class PeliculaController {

    public void getPeliculas(Context context, final ResultListener<List<Pelicula>> listenerPeliculaDeLaView) {
        PeliculaDAO peliculaDAO = new PeliculaDAO(context);
        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            peliculaDAO.getPeliculasFromWEB(new ResultListener<List<Pelicula>>() {
                @Override
                public void finish(List<Pelicula> listaDePeliculas) {
                    listenerPeliculaDeLaView.finish(listaDePeliculas);
                }
            });
        } else {
            List<Pelicula> listaDePeliculas = peliculaDAO.getPeliculasFromDataBase();
            listenerPeliculaDeLaView.finish(listaDePeliculas);
        }
    }

    public void addPeliculaFavorite(Context context, Integer idPelicula) {
        PeliculaDAO peliculaDAO = new PeliculaDAO(context);
        peliculaDAO.addPeliculaFavoriteDAO(idPelicula);
    }

    public List<Pelicula> getPeliculasFavorite(Context context) {
        PeliculaDAO peliculaDAO = new PeliculaDAO(context);
        List<Pelicula> listaDePeliculas = peliculaDAO.getPeliculaFavoriteDAO();
        return listaDePeliculas;
    }

    public Boolean CheckisFavourite(Context context, Integer idPelicula) {
        PeliculaDAO peliculaDAO = new PeliculaDAO(context);
         return peliculaDAO.checkIsFavorite(idPelicula);

    }

    public void addPeliculaWatchLater(Context context, Integer idPelicula) {
        PeliculaDAO peliculaDAO = new PeliculaDAO(context);
        peliculaDAO.addPeliculaWatchLaterDAO(idPelicula);
    }

    public Boolean checkIsWatchLater(Context context, Integer idPelicula) {
        PeliculaDAO peliculaDAO = new PeliculaDAO(context);
        return peliculaDAO.checkIsWatchLater(idPelicula);
    }

    public List<Pelicula>getPeliculasWatchLater(Context context) {
        PeliculaDAO peliculaDAO = new PeliculaDAO(context);
        List<Pelicula> listaDePeliculas = peliculaDAO.getPeliculasWatchLater();
        return listaDePeliculas;
    }

    public void getPeliculasTrending(Context context, final ResultListener<List<Pelicula>> listenerPeliculaDeLaView) {
        PeliculaDAO peliculaDAO = new PeliculaDAO(context);
        if (HTTPConnectionManager.isNetworkingOnline(context)) {
           peliculaDAO.getPeliculasTrendingFromWEB(new ResultListener<List<Pelicula>>() {
               @Override
               public void finish(List<Pelicula> resultado) {

               }
           });
        } else {
            List<Pelicula> listaDePeliculas = peliculaDAO.getPeliculasFromDataBase();
            listenerPeliculaDeLaView.finish(listaDePeliculas);
        }
    }

    public void getPeliculasAventura(Context context, final ResultListener<List<Pelicula>> listenerPeliculaDeLaView) {
        PeliculaDAO peliculaDAO = new PeliculaDAO(context);
        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            peliculaDAO.getPeliculasAventuraFromWEB(new ResultListener<List<Pelicula>>() {
                @Override
                public void finish(List<Pelicula> resultado) {

                }
            });
        } else {
            List<Pelicula> listaDePeliculas = peliculaDAO.getPeliculasFromDataBase();
            listenerPeliculaDeLaView.finish(listaDePeliculas);
        }
    }

    public void getPeliculasDocumental(Context context, final ResultListener<List<Pelicula>> listenerPeliculaDeLaView) {
        PeliculaDAO peliculaDAO = new PeliculaDAO(context);
        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            peliculaDAO.getPeliculasDocumentalFromWEB(new ResultListener<List<Pelicula>>() {
                @Override
                public void finish(List<Pelicula> resultado) {

                }
            });
        } else {
            List<Pelicula> listaDePeliculas = peliculaDAO.getPeliculasFromDataBase();
            listenerPeliculaDeLaView.finish(listaDePeliculas);
        }
    }

    public void getPeliculasDrama(Context context, final ResultListener<List<Pelicula>> listenerPeliculaDeLaView) {
        PeliculaDAO peliculaDAO = new PeliculaDAO(context);
        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            peliculaDAO.getPeliculasDramaFromWEB(new ResultListener<List<Pelicula>>() {
                @Override
                public void finish(List<Pelicula> resultado) {

                }
            });
        } else {
            List<Pelicula> listaDePeliculas = peliculaDAO.getPeliculasFromDataBase();
            listenerPeliculaDeLaView.finish(listaDePeliculas);
        }
    }

}
