package a3.m1mo.mobjav.a816.myapplication.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import a3.m1mo.mobjav.a816.myapplication.Adapter.AdapterFragmentSerie;
import a3.m1mo.mobjav.a816.myapplication.R;
import a3.m1mo.mobjav.a816.myapplication.controller.SerieController;
import a3.m1mo.mobjav.a816.myapplication.model.Serie;
import a3.m1mo.mobjav.a816.myapplication.util.ResultListener;

/**
 * Created by dh-mob-tm on 10/11/16.
 */
public class FragmentViewPagerSerie extends Fragment {

    public static final String POSITION = "position";
    private Integer position;
    private ViewPager viewPager;

    public FragmentViewPagerSerie() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_pager_serie, container, false);
        Bundle unBundle = getArguments();
        position = unBundle.getInt(POSITION);

        viewPager = (ViewPager) view.findViewById(R.id.viewPagerDetalleSerie);
        SerieController serieController = new SerieController();
        serieController.getSeries(getContext(), new ResultListener<List<Serie>>() {

            @Override
            public void finish(List<Serie> resultado) {
                AdapterFragmentSerie adapterFragmentSerie = new AdapterFragmentSerie(getActivity().getSupportFragmentManager(), resultado);
                viewPager.setAdapter(adapterFragmentSerie);
                viewPager.setCurrentItem(position);

            }
        });


        return view;
    }
}
