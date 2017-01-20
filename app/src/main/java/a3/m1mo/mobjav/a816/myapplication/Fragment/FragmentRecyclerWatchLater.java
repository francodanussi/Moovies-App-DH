package a3.m1mo.mobjav.a816.myapplication.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import a3.m1mo.mobjav.a816.myapplication.Adapter.AdapterWatchLaterPeliculaRecycler;
import a3.m1mo.mobjav.a816.myapplication.Adapter.AdapterWatchLaterSerieRecycler;
import a3.m1mo.mobjav.a816.myapplication.R;
import a3.m1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a3.m1mo.mobjav.a816.myapplication.controller.SerieController;
import a3.m1mo.mobjav.a816.myapplication.model.Pelicula;
import a3.m1mo.mobjav.a816.myapplication.model.Serie;

/**
 * Created by Diagnostifo on 29/11/2016.
 */

public class FragmentRecyclerWatchLater extends Fragment{

        private RecyclerView recyclerViewWatchLaterPelicula;
        private RecyclerView recyclerViewWatchLaterSerie;
        private List<Pelicula> listaDePeliculas;
        private List<Serie> listaDeSeries;
        private AdapterWatchLaterPeliculaRecycler adapterWatchLaterPeliculaRecycler;
        private AdapterWatchLaterSerieRecycler adapterWatchLaterSerieRecycler;
        private Context context;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            context = getContext();
            View view = inflater.inflate(R.layout.fragment_watchlater, container, false);

            recyclerViewWatchLaterPelicula = (RecyclerView) view.findViewById(R.id.recycler_view_watch_later_peliculas);

            recyclerViewWatchLaterPelicula.setHasFixedSize(true);
            recyclerViewWatchLaterPelicula.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

            listaDePeliculas = new ArrayList<>();
            adapterWatchLaterPeliculaRecycler = new AdapterWatchLaterPeliculaRecycler(getContext());
            adapterWatchLaterPeliculaRecycler.setListaDeWatchLater(listaDePeliculas);
            recyclerViewWatchLaterPelicula.setAdapter(adapterWatchLaterPeliculaRecycler);
            cargarPeliculasWatchLater();

            recyclerViewWatchLaterSerie = (RecyclerView) view.findViewById(R.id.recycler_view_watch_later_series);

            recyclerViewWatchLaterSerie.setHasFixedSize(true);
            recyclerViewWatchLaterSerie.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

            listaDeSeries = new ArrayList<>();
            adapterWatchLaterSerieRecycler = new AdapterWatchLaterSerieRecycler(getContext());
            adapterWatchLaterSerieRecycler.setListaDeWatchLater(listaDeSeries);
            recyclerViewWatchLaterSerie.setAdapter(adapterWatchLaterSerieRecycler);
            cargarSeriesWatchLater();

            return view;
        }


        public void cargarPeliculasWatchLater() {
            PeliculaController peliculaController = new PeliculaController();
            List<Pelicula> listaDePeliculas = peliculaController.getPeliculasWatchLater(context);
            adapterWatchLaterPeliculaRecycler.setListaDeWatchLater(listaDePeliculas);
            adapterWatchLaterPeliculaRecycler.notifyDataSetChanged();
        }

    public void cargarSeriesWatchLater() {
        SerieController serieController = new SerieController();
        List<Serie> listaDeSeriesWatchLater = serieController.getSeriesWatchLater(context);
        adapterWatchLaterSerieRecycler.setListaDeWatchLater(listaDeSeriesWatchLater);
        adapterWatchLaterSerieRecycler.notifyDataSetChanged();
    }

    }


