package a3.m1mo.mobjav.a816.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import a3.m1mo.mobjav.a816.myapplication.Fragment.FragmentRecyclerPelicula;
import a3.m1mo.mobjav.a816.myapplication.Fragment.FragmentViewPagerPelicula;
import a3.m1mo.mobjav.a816.myapplication.R;
import a3.m1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a3.m1mo.mobjav.a816.myapplication.controller.SerieController;
import a3.m1mo.mobjav.a816.myapplication.model.Pelicula;
import a3.m1mo.mobjav.a816.myapplication.model.Serie;
import a3.m1mo.mobjav.a816.myapplication.util.ResultListener;

public class MainScreenPelicula extends AppCompatActivity implements FragmentRecyclerPelicula.seleccionableDePelicula{
    public static final String KEY_USER="nombre";
    public static final String KEY_PASSWORD="password";
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);


        // ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.moovies_toolbar);
        setSupportActionBar(toolbar);

/*        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        FragmentPageAdapter adapterViewPager = new FragmentPageAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapterViewPager);

        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(12);*/


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.mainscreenLayout);
        navigationView = (NavigationView) findViewById(R.id.IDNavigationView);
        navigationView.setNavigationItemSelectedListener(new ListenerNavigationView());
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.holadrawer1, R.string.holadrawer2) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(mDrawerToggle);


        FragmentRecyclerPelicula fragmentRecyclerPelicula = new FragmentRecyclerPelicula();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.acaVaElFragmentPOSTA, fragmentRecyclerPelicula);
        fragmentTransaction.commit();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuhome, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled
        // the nav drawer indicator touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    public void seleccionItem (MenuItem unItem){


        switch (unItem.getItemId()) {
            case R.id.IDNavigationViewPeliculas:
                Intent unIntent = new Intent(this, MainScreenPelicula.class);
                startActivity(unIntent);
                break;
            case R.id.IDNavigationViewSeries:
                Intent otroIntent = new Intent(this, MainScreenSerie.class);
                startActivity(otroIntent);
                break;
            case R.id.IDNavigationViewFavoritos:
                Intent favoriteIntent = new Intent(this, FavoriteActivityTotal.class);
                startActivity(favoriteIntent);
                break;
            case R.id.IdNavigationViewWatchLater:
                Intent watchLaterIntent = new Intent(this, WatchLaterActivity.class);
                startActivity(watchLaterIntent);
                break;
            case R.id.IDNavigationViewDocumental:
                Intent documentalIntent = new Intent (this, MainScreenDocumental.class);
                startActivity(documentalIntent);
                break;
            case R.id.IDNavigationViewTrending:
                Intent trendingIntent = new Intent (this, MainScreenTrending.class);
                startActivity(trendingIntent);
                break;
        }

    }


    @Override
    public void peliculaSeleccionada(int position) {
        FragmentViewPagerPelicula fragment_viewPagerPelicula = new FragmentViewPagerPelicula();

        Bundle unBundle = new Bundle();
        unBundle.putInt(FragmentViewPagerPelicula.POSITION, position);

        fragment_viewPagerPelicula.setArguments(unBundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainscreenLayout, fragment_viewPagerPelicula);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private class ListenerNavigationView implements NavigationView.OnNavigationItemSelectedListener{

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                seleccionItem(item);

                return true;
            }
        }

    public void loadPeliculas(View view) {
        PeliculaController peliculaController = new PeliculaController();
        peliculaController.getPeliculas(this, new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> listaDePeliculas) {

            }
        });
    }

    public void loadSerie(View view) {
        SerieController serieController = new SerieController();
        serieController.getSeries(this, new ResultListener<List<Serie>>() {
            @Override
            public void finish(List<Serie> listaDeSeries) {
            }
        });
    }

}

