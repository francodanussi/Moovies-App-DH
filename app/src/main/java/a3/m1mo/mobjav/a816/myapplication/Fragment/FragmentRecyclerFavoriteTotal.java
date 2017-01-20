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

import a3.m1mo.mobjav.a816.myapplication.Adapter.AdapterFavoritePeliculaRecycler;
import a3.m1mo.mobjav.a816.myapplication.Adapter.AdapterFavoriteSerieRecycler;
import a3.m1mo.mobjav.a816.myapplication.R;
import a3.m1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a3.m1mo.mobjav.a816.myapplication.controller.SerieController;
import a3.m1mo.mobjav.a816.myapplication.model.Pelicula;
import a3.m1mo.mobjav.a816.myapplication.model.Serie;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecyclerFavoriteTotal extends Fragment {
    public static final String TITLE = "title";
    public static final String GENRE = "genre";
    public static final String POSTER_PATH = "poster path";
    public static final String OVERVIEW = "overview";
    public static final String URL_TRAILER = "url trailer";
    public static final String ID = "id";
    private Integer idPelicula;
    private RecyclerView recyclerViewMovie;
    private RecyclerView recyclerViewSerie;
    private List<Pelicula> listaDePeliculas;
    private List<Serie> listaDeSerie;
    private AdapterFavoritePeliculaRecycler adapterFavoritePeliculaRecycler;
    private AdapterFavoriteSerieRecycler adapterFavoriteSerieRecycler;
    private Context context;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        context = getContext();

        recyclerViewMovie = (RecyclerView) view.findViewById(R.id.recyclerView_pelicula_Favorite);
        recyclerViewMovie.setHasFixedSize(true);
        recyclerViewMovie.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        listaDePeliculas = new ArrayList<>();
        adapterFavoritePeliculaRecycler = new AdapterFavoritePeliculaRecycler(getContext());
        adapterFavoritePeliculaRecycler.setListaDePeliculasFavoritos(listaDePeliculas);
        recyclerViewMovie.setAdapter(adapterFavoritePeliculaRecycler);
        cargarFavoritosPelicula();

        recyclerViewSerie = (RecyclerView) view.findViewById(R.id.recyclerView_serie_Favorite);
        recyclerViewSerie.setHasFixedSize(true);
        recyclerViewSerie.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        listaDeSerie = new ArrayList<>();
        adapterFavoriteSerieRecycler = new AdapterFavoriteSerieRecycler(getContext());
        adapterFavoriteSerieRecycler.setListaDeSerieFavoritos(listaDeSerie);
        recyclerViewSerie.setAdapter(adapterFavoriteSerieRecycler);
        cargarFavoritosSerie();

        return view;
    }


    public void cargarFavoritosPelicula() {
        PeliculaController peliculaController = new PeliculaController();
        List<Pelicula> listaDePeliculasFavoritos = peliculaController.getPeliculasFavorite(context);
        adapterFavoritePeliculaRecycler.setListaDePeliculasFavoritos(listaDePeliculasFavoritos);
        adapterFavoritePeliculaRecycler.notifyDataSetChanged();
    }


    public void cargarFavoritosSerie() {
        SerieController serieController = new SerieController();
        List<Serie> listaDeSeriesFavoritos = serieController.getSeriesFavorite(context);
        adapterFavoriteSerieRecycler.setListaDeSerieFavoritos(listaDeSeriesFavoritos);
        adapterFavoriteSerieRecycler.notifyDataSetChanged();
    }
}

