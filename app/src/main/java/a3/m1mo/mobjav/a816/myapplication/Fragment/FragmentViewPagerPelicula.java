package a3.m1mo.mobjav.a816.myapplication.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import a3.m1mo.mobjav.a816.myapplication.Adapter.AdapterFragmentPelicula;
import a3.m1mo.mobjav.a816.myapplication.R;
import a3.m1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a3.m1mo.mobjav.a816.myapplication.model.Pelicula;
import a3.m1mo.mobjav.a816.myapplication.util.ResultListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentViewPagerPelicula extends Fragment {

    public static final String POSITION = "position";
    private Integer position;
    private ViewPager viewPager;

    public FragmentViewPagerPelicula() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_pager_pelicula, container, false);
        Bundle unBundle = getArguments();
        position = unBundle.getInt(POSITION);

        viewPager = (ViewPager) view.findViewById(R.id.viewPagerDetallePelicula);
        PeliculaController peliculaController = new PeliculaController();
        peliculaController.getPeliculas(getContext(), new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                AdapterFragmentPelicula adapterFragmentPelicula = new AdapterFragmentPelicula(getChildFragmentManager(), resultado);
                viewPager.setAdapter(adapterFragmentPelicula);
                viewPager.setCurrentItem(position);
            }
        });


        return view;
    }
}