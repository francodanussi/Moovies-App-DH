package a3.m1mo.mobjav.a816.myapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import a3.m1mo.mobjav.a816.myapplication.R;
import a3.m1mo.mobjav.a816.myapplication.model.Serie;
import a3.m1mo.mobjav.a816.myapplication.util.TMDBHelper;

/**
 * Created by Lemon on 11/24/2016.
 */
public class AdapterFavoriteSerieRecycler extends RecyclerView.Adapter {

    private View.OnClickListener listener;
    private Context context;
    private List<Serie> listaDeSeriesFavoritos;

    public void setListaDeSerieFavoritos(List<Serie> listaDeSeriesFavoritos) {
        this.listaDeSeriesFavoritos = listaDeSeriesFavoritos;
    }

    public AdapterFavoriteSerieRecycler(Context context) {
        this.context = context;
    }





    private class FavoritesViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTituloFavorites;
        private ImageView imageViewImagenFavorites;

        public FavoritesViewHolder(View view) {
            super(view);
            textViewTituloFavorites = (TextView) view.findViewById(R.id.textView_titulo_celda_favorites);
            imageViewImagenFavorites = (ImageView) view.findViewById(R.id.imageView_celda_favorites);
        }

        public void loadFavorite(Serie serie) {
            textViewTituloFavorites.setText(serie.getName());
            Glide.with(context).
                    load(TMDBHelper.getImagePoster(TMDBHelper.IMAGE_SIZE_W300, serie.getPoster_path())).
                    listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            return false;
                        }
                    }).centerCrop().
                    into(imageViewImagenFavorites);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(parent.getContext());
        View viewDeLaCelda = inflater.inflate(R.layout.celda_favorites, parent, false);
        FavoritesViewHolder favoritesViewHolder = new FavoritesViewHolder(viewDeLaCelda);

        return favoritesViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Serie serie = listaDeSeriesFavoritos.get(position);
        FavoritesViewHolder favoritesViewHolder = (FavoritesViewHolder) holder;
        favoritesViewHolder.loadFavorite(serie);
    }

    @Override
    public int getItemCount() {
        return listaDeSeriesFavoritos.size();
    }

}