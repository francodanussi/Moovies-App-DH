package a3.m1mo.mobjav.a816.myapplication.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;

import a3.m1mo.mobjav.a816.myapplication.R;
import a3.m1mo.mobjav.a816.myapplication.controller.SerieController;
import a3.m1mo.mobjav.a816.myapplication.model.Serie;
import a3.m1mo.mobjav.a816.myapplication.util.TMDBHelper;

/**
 * Created by dh-mob-tm on 10/11/16.
 */
public class FragmentSerieDetalle extends Fragment {
    public static final String TITLE = "title";
    public static final String GENRE = "genre";
    public static final String POSTER_PATH = "poster path";
    public static final String OVERVIEW = "overview";
    public static final String TRAILER_URL = "trailer url";
    public static final String ID = "id";
    private Context context;
    private String unUrltrailer;
    private Integer idSerie;
    private FloatingActionButton floatingActionButtonFav;
    private FloatingActionButton floatingActionButtonWatchLater;
    private FloatingActionButton floatingActionButtonMisFav;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Bundle unBundle;
    private FloatingActionButton compartir;


    public static FragmentSerieDetalle dameUnFragment (Serie unaSerie){
        FragmentSerieDetalle fragmentfirst = new FragmentSerieDetalle();
        Bundle unBundle2 = new Bundle();
        unBundle2.putString(TITLE, unaSerie.getName());
        unBundle2.putString(POSTER_PATH, unaSerie.getPoster_path());
        unBundle2.putString(OVERVIEW, unaSerie.getOverview());
        unBundle2.putInt(ID, unaSerie.getId());
        unBundle2.putString(TRAILER_URL, unaSerie.getUrl_trailer());
        fragmentfirst.setArguments(unBundle2);
        return fragmentfirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        CoordinatorLayout view = (CoordinatorLayout) inflater.inflate(R.layout.serie_seleccionada_detalle, container, false);
        context=getContext();
        unBundle = getArguments();
        String unTitle = unBundle.getString(TITLE);
        String unImage = unBundle.getString(POSTER_PATH);
        String unOverview = unBundle.getString(OVERVIEW);
        idSerie = unBundle.getInt(ID);
        unUrltrailer = unBundle.getString(TRAILER_URL);


        TextView titleSerie = (TextView)  view.findViewById(R.id.textViewNombreSerieEnDetalle);
        TextView overViewSerie = (TextView) view.findViewById(R.id.overview_en_detalle_serie);
        ImageView imageSerie = (ImageView) view.findViewById(R.id.imageViewImagenSerieEnDetalle);

        titleSerie.setText(unTitle);
        overViewSerie.setText(unOverview);
        Glide.with(getContext()).
                load(TMDBHelper.getImagePoster(TMDBHelper.IMAGE_SIZE_W300,unImage))
                .into(imageSerie);

        floatingActionButtonFav = (FloatingActionButton) view.findViewById(R.id.menu_item_favorites);
        floatingActionButtonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckisFavorite(idSerie)){
                    floatingActionButtonFav.setImageResource(R.drawable.estrellavacia);
                }
                else {
                    SerieController serieController = new SerieController();
                    serieController.addSerieFavorite(getContext(),unBundle.getInt(ID));
                    floatingActionButtonFav.setImageResource(R.drawable.estrellafavoritos);
                }
            }
        });

        if (CheckisFavorite(idSerie)){
            floatingActionButtonFav.setImageResource(R.drawable.estrellafavoritos);
        } else
        if(!CheckisFavorite(idSerie)){
            floatingActionButtonFav.setImageResource(R.drawable.estrellavacia);
        }


        floatingActionButtonWatchLater = (FloatingActionButton) view.findViewById(R.id.menu_item_watch_later);
        floatingActionButtonWatchLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkIsWatchlater(idSerie)){
                    floatingActionButtonWatchLater.setImageResource(R.drawable.watchlater1);
                }
                else {
                    SerieController serieController = new SerieController();
                    serieController.addSerieWatchLater(getContext(),unBundle.getInt(ID));
                    floatingActionButtonWatchLater.setImageResource(R.drawable.watchlater2);
                }
            }
        });

        if (checkIsWatchlater(idSerie)){
            floatingActionButtonWatchLater.setImageResource(R.drawable.watchlater1);
        }
        else {
            floatingActionButtonWatchLater.setImageResource(R.drawable.watchlater2);
        }

        imageSerie.setOnClickListener(new View.OnClickListener(){
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


        compartir = (FloatingActionButton) view.findViewById(R.id.menu_item_compartir_serie);
        compartir.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Intent sendIntent = new Intent();
                                             sendIntent.setAction(Intent.ACTION_SEND);
                                             sendIntent.putExtra(Intent.EXTRA_TEXT, ("Es *mooy* buena "+unBundle.getString(TITLE)) + "\nMira el trailer: " + unBundle.getString(TRAILER_URL) + "\n_Compartido desde Moovies_");
                                             sendIntent.setType("text/plain");
                                             startActivity(sendIntent);
                                         }
                                     }
        );


        return view;
    }

    public boolean CheckisFavorite(Integer idSerie) {
        SerieController serieController = new SerieController();
        return serieController.CheckisFavourite(getContext(), idSerie);
    }

    public boolean checkIsWatchlater (Integer idSerie) {
        SerieController serieController = new SerieController();
        return serieController.checkIsWatchLater(getContext(), idSerie);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    }



