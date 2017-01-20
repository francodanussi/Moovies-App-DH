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

import a3.m1mo.mobjav.a816.myapplication.Adapter.AdapterSeriesRecycler;
import a3.m1mo.mobjav.a816.myapplication.R;
import a3.m1mo.mobjav.a816.myapplication.controller.SerieController;
import a3.m1mo.mobjav.a816.myapplication.model.Serie;
import a3.m1mo.mobjav.a816.myapplication.util.ResultListener;

/**
 * Created by dh-mob-tm on 10/11/16.
 */
public class FragmentRecyclerSerie extends Fragment {
    private RecyclerView unRecyclerView;
    private List<Serie> listaDeSeries;
    private AdapterSeriesRecycler unAdapter;
    private seleccionableDeSerie seleccionableDeSerie;
    private Context context;
    private SwipeRefreshLayout pullToRefresh;
    private SerieController serieController;

    public FragmentRecyclerSerie() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_series, container, false);
        ListenerDeSerie listenerDeSerie= new ListenerDeSerie();
        unRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewSeries);
        unRecyclerView.setHasFixedSize(true);
        unRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        listaDeSeries = new ArrayList<>();
        unAdapter = new AdapterSeriesRecycler(getContext());
        CargarSeries();
        unAdapter.setListaDeSeries(listaDeSeries);
        unRecyclerView.setAdapter(unAdapter);
        unAdapter.setListener(listenerDeSerie);
        // pullToRefresh = (SwipeRefreshLayout)view.findViewById(R.id.swipeContainer);
        SerieController serieController = new SerieController();
        // update();
        // pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        //   @Override
        //  public void onRefresh() {
        //       update();
        //    }
        //});

        return view;
    }

    public void CargarSeries() {


        SerieController serieController = new SerieController();
        serieController.getSeries(getContext(), new ResultListener<List<Serie>>() {

            @Override
            public void finish(List<Serie> resultado) {

                unAdapter.setListaDeSeries(resultado);
                unAdapter.notifyDataSetChanged();
            }

        });
    }

    private void update() {
        pullToRefresh.setRefreshing(true);
        serieController.getSeries(context, new ResultListener<List<Serie>>() {
            @Override
            public void finish(List<Serie> resultado) {

                unAdapter.setListaDeSeries(resultado);
                unAdapter.notifyDataSetChanged();
                pullToRefresh.setRefreshing(false);
            }
        });
    }




    @Override
    public void onAttach (Activity activity){
        super.onAttach(activity);
        seleccionableDeSerie = (seleccionableDeSerie) activity;
    }

    public interface seleccionableDeSerie {

        void serieSeleccionada(int position);


    }

    private class ListenerDeSerie implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            int posClickeada = unRecyclerView.getChildAdapterPosition(view);

            seleccionableDeSerie.serieSeleccionada(posClickeada);

        }
    }

}

