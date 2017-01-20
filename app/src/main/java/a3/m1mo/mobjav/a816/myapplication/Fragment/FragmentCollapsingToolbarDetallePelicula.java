package a3.m1mo.mobjav.a816.myapplication.Fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;

import a3.m1mo.mobjav.a816.myapplication.R;
import a3.m1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a3.m1mo.mobjav.a816.myapplication.model.Pelicula;
import a3.m1mo.mobjav.a816.myapplication.util.TMDBHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCollapsingToolbarDetallePelicula extends Fragment {

    public static final String TITLE = "title";
    public static final String GENRE = "genre";
    public static final String POSTER_PATH = "poster path";
    public static final String OVERVIEW = "overview";
    public static final String URL_TRAILER = "url trailer";
    public static final String ID = "id";
    private Integer idPelicula;
    private Context context;
    private String unUrltrailer;
    private FloatingActionButton compartir;
    private FloatingActionButton floatingActionButtonFav;
    private FloatingActionButton floatingActionButtonWatchLater;
    private FloatingActionButton floatingActionButtonMisFav;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar mToolbar;


    public static FragmentCollapsingToolbarDetallePelicula dameUnFragment(Pelicula unaPelicula) {
        FragmentCollapsingToolbarDetallePelicula fragmentfirst = new FragmentCollapsingToolbarDetallePelicula();
        Bundle unBundle = new Bundle();
        unBundle.putString(TITLE, unaPelicula.getTitle());
        unBundle.putString(POSTER_PATH, unaPelicula.getPoster_path());
        unBundle.putString(OVERVIEW, unaPelicula.getOverview());
        unBundle.putString(URL_TRAILER, unaPelicula.getUrlTrailer());
        unBundle.putInt(ID, unaPelicula.getId());
        fragmentfirst.setArguments(unBundle);
        return fragmentfirst;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collapsing_toolbar_detalle_pelicula, container, false);

        mToolbar = (Toolbar)view.findViewById(R.id.toolbar_peliculas);
        mToolbar.setTitle("My title");
        // setSupportActionBar(mToolbar);

        collapsingToolbarLayout = (CollapsingToolbarLayout)view.findViewById(R.id.collapsing_toolbar_peliculas);
        collapsingToolbarLayout.setTitle("Moovies");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        final Bundle unBundle = getArguments();
        String unTitle = unBundle.getString(TITLE);
        String unImage = unBundle.getString(POSTER_PATH);
        String unOverview = unBundle.getString(OVERVIEW);
        idPelicula = unBundle.getInt(ID);
        unUrltrailer= unBundle.getString(URL_TRAILER);


        TextView titleMovie = (TextView) view.findViewById(R.id.textViewNombrePeliculaEnDetalle);
        TextView overViewMovie = (TextView) view.findViewById(R.id.overview_en_detalle_pelicula);
        ImageView imageMovie = (ImageView) view.findViewById(R.id.imageViewImagenPeliculaEnDetalle);

        context = getContext();
        titleMovie.setText(unTitle);
        overViewMovie.setText(unOverview);
        Glide.with(getContext()).
                load(TMDBHelper.getImagePoster(TMDBHelper.IMAGE_SIZE_W300, unImage))
                .into(imageMovie);

        floatingActionButtonFav = (FloatingActionButton) view.findViewById(R.id.menu_item_favorites);
        floatingActionButtonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckisFavorite(idPelicula)){
                    floatingActionButtonFav.setImageResource(R.drawable.estrellavacia);
                }
                else {
                    PeliculaController peliculaController = new PeliculaController();
                    peliculaController.addPeliculaFavorite(getContext(),unBundle.getInt(ID));
                    floatingActionButtonFav.setImageResource(R.drawable.estrellafavoritos);
                }
            }
        });

        if (CheckisFavorite(idPelicula)){
            floatingActionButtonFav.setImageResource(R.drawable.estrellafavoritos);
        } else
        if(!CheckisFavorite(idPelicula)){
            floatingActionButtonFav.setImageResource(R.drawable.estrellavacia);
        }

        floatingActionButtonWatchLater = (FloatingActionButton) view.findViewById(R.id.menu_item_watch_later);
        floatingActionButtonWatchLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkIsWatchlater(idPelicula)){
                    floatingActionButtonWatchLater.setImageResource(R.drawable.watchlater1);
                }
                else {
                    PeliculaController peliculaController = new PeliculaController();
                    peliculaController.addPeliculaWatchLater(getContext(),unBundle.getInt(ID));
                    floatingActionButtonWatchLater.setImageResource(R.drawable.watchlater2);
                }
            }
        });

        if (checkIsWatchlater(idPelicula)){
            floatingActionButtonWatchLater.setImageResource(R.drawable.watchlater1);
        }
        else {
            floatingActionButtonWatchLater.setImageResource(R.drawable.watchlater2);
        }

        imageMovie.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                if (unUrltrailer != null){
                    intent.setData(Uri.parse(unUrltrailer));
                    startActivity(intent);
                }
            }
        });

        compartir = (FloatingActionButton) view.findViewById(R.id.menu_item_compartir_pelicula);
        compartir.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {

                                             Intent sendIntent = new Intent();
                                             sendIntent.setAction(Intent.ACTION_SEND);
                                             sendIntent.putExtra(Intent.EXTRA_TEXT, ("Es *mooy* buena "+ unBundle.getString(TITLE)) + "\nMirá el trailer: " + unBundle.getString(URL_TRAILER) + "\n_Compartido desde MooviesApp_");
                                             sendIntent.setType("text/plain");
                                             startActivity(sendIntent);
                                         }
                                     }
        );


        return view;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public boolean checkIsWatchlater (Integer idPelicula) {
        PeliculaController peliculaController = new PeliculaController();
        return peliculaController.checkIsWatchLater(getContext(), idPelicula);
    }

    public boolean CheckisFavorite(Integer idPelicula) {
        PeliculaController peliculaController = new PeliculaController();
        return peliculaController.CheckisFavourite(getContext(), idPelicula);
    }


}


