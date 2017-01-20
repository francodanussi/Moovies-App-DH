package a3.m1mo.mobjav.a816.myapplication.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import a3.m1mo.mobjav.a816.myapplication.Adapter.AdapterPeliculasRecycler;
import a3.m1mo.mobjav.a816.myapplication.R;
import a3.m1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a3.m1mo.mobjav.a816.myapplication.model.Pelicula;
import a3.m1mo.mobjav.a816.myapplication.util.ResultListener;

/**
 * Created by dh-mob-tm on 06/12/16.
 */
public class FragmentRecyclerTrendingPelicula extends Fragment {


    private RecyclerView unRecyclerView;
    private List<Pelicula> listaDePeliculas;
    private AdapterPeliculasRecycler unAdapter;
    private seleccionableDePelicula seleccionableDePelicula;
    private Context context;
    private SwipeRefreshLayout pullToRefresh;
    private PeliculaController peliculaController;

    public FragmentRecyclerTrendingPelicula() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_peliculas, container, false);
        ListenerDePelicula listenerDePelicula = new ListenerDePelicula();
        unRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewPeliculas);
        unRecyclerView.setHasFixedSize(true);
        unRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        listaDePeliculas = new ArrayList<>();
        unAdapter = new AdapterPeliculasRecycler(getContext());
        CargarPeliculas();
        unAdapter.setListaDePeliculas(listaDePeliculas);
        unRecyclerView.setAdapter(unAdapter);
        unAdapter.setListener(listenerDePelicula);
        // pullToRefresh = (SwipeRefreshLayout)view.findViewById(R.id.swipeContainer);
        //peliculaController = new PeliculaController();
        // update();
        // pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        //   @Override
        //  public void onRefresh() {
        //       update();
        //    }
        //});




        return view;
    }



    public void CargarPeliculas() {

        // cargo asi nomás lo que venga, para chusmear nomás que funque.
        // SL // arregle el constucor, estoy hardcodiando esto para ver y mejorar el layout

        PeliculaController peliculaController = new PeliculaController();
        peliculaController.getPeliculasTrending(getContext(), new ResultListener<List<Pelicula>>() {

            @Override
            public void finish(List<Pelicula> resultado) {


                unAdapter.setListaDePeliculas(resultado);
                unAdapter.notifyDataSetChanged();


            }

        });
    }

    private void update() {
        pullToRefresh.setRefreshing(true);

        peliculaController.getPeliculas(context, new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {

                unAdapter.setListaDePeliculas(resultado);
                unAdapter.notifyDataSetChanged();
                pullToRefresh.setRefreshing(false);
            }
        });
    }




    @Override
    public void onAttach (Activity activity){
        super.onAttach(activity);
        seleccionableDePelicula = (seleccionableDePelicula) activity;
    }

    public interface seleccionableDePelicula {

        void peliculaSeleccionada(int position);


    }

    private class ListenerDePelicula implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            int posClickeada = unRecyclerView.getChildAdapterPosition(view);

            seleccionableDePelicula.peliculaSeleccionada(posClickeada);

        }
    }

}