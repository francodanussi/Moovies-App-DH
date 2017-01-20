package a3.m1mo.mobjav.a816.myapplication.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import a3.m1mo.mobjav.a816.myapplication.model.Serie;
import a3.m1mo.mobjav.a816.myapplication.Fragment.FragmentSerieDetalle;

/**
 * Created by dh-mob-tm on 08/11/16.
 */
public class AdapterFragmentSerie extends FragmentStatePagerAdapter {
    private List<FragmentSerieDetalle> listaDeFragments;
    public AdapterFragmentSerie(FragmentManager fm, List<Serie> listaDeSeries) {
        super(fm);
        listaDeFragments = new ArrayList<>();
        for (Serie unaSerie : listaDeSeries) {
            listaDeFragments.add(FragmentSerieDetalle.dameUnFragment(unaSerie));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return listaDeFragments.get(position);
    }

    @Override
    public int getCount() {
        return listaDeFragments.size();
    }
}

