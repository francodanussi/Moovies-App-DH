package a3.m1mo.mobjav.a816.myapplication.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import a3.m1mo.mobjav.a816.myapplication.Fragment.FragmentPeliculaDetalle;
import a3.m1mo.mobjav.a816.myapplication.model.Pelicula;

/**
 * Created by dh-mob-tm on 08/11/16.
 */
public class AdapterFragmentPelicula extends FragmentStatePagerAdapter {
    private List<FragmentPeliculaDetalle> listaDeFragments;
    public AdapterFragmentPelicula(FragmentManager fm, List<Pelicula> listaDePeliculas) {
        super(fm);
        listaDeFragments = new ArrayList<>();
        for (Pelicula unaPelicula : listaDePeliculas) {
            listaDeFragments.add(FragmentPeliculaDetalle.dameUnFragment(unaPelicula));
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
