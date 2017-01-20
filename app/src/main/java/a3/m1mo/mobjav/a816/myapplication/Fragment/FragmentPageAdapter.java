package a3.m1mo.mobjav.a816.myapplication.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Diagnostifo on 05/12/2016.
 */

public class FragmentPageAdapter extends FragmentStatePagerAdapter {
    private List<FragmentRecyclerPelicula> listPeliculas;


    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
        this.listPeliculas = listPeliculas;

    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.listPeliculas.get(position).getTag();
    }
}
