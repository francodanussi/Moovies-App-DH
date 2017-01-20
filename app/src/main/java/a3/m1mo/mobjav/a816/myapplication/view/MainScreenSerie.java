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

import a3.m1mo.mobjav.a816.myapplication.Fragment.FragmentRecyclerSerie;
import a3.m1mo.mobjav.a816.myapplication.Fragment.FragmentViewPagerSerie;
import a3.m1mo.mobjav.a816.myapplication.R;

/**
 * Created by dh-mob-tm on 10/11/16.
 */
public class MainScreenSerie extends AppCompatActivity implements FragmentRecyclerSerie.seleccionableDeSerie {

    public static final String KEY_USER = "nombre";
    public static final String KEY_PASSWORD = "password";
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.moovies_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.mainscreenLayout);

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

        FragmentRecyclerSerie fragmentRecyclerSerie = new FragmentRecyclerSerie();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.acaVaElFragmentPOSTA, fragmentRecyclerSerie);
        fragmentTransaction.commit();

        //       Intent unIntent = getIntent();
        //       Bundle unBundle = unIntent.getExtras();

//        String usuario = unBundle.getString(MainScreenPelicula.KEY_USER);
        //      String password = unBundle.getString(MainScreenPelicula.KEY_PASSWORD);


        navigationView = (NavigationView) findViewById(R.id.IDNavigationView);
        navigationView.setNavigationItemSelectedListener(new ListenerNavigationView());
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


    @Override
    public void serieSeleccionada(int position) {
        FragmentViewPagerSerie fragment_viewPagerSerie = new FragmentViewPagerSerie();

        Bundle unBundle = new Bundle();
        unBundle.putInt(FragmentViewPagerSerie.POSITION, position);

        fragment_viewPagerSerie.setArguments(unBundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainscreenLayout, fragment_viewPagerSerie);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    private class ListenerNavigationView implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {

            seleccionItem(item);

            return true;
        }
    }

    public void seleccionItem(MenuItem unItem) {


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


}

