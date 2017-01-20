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
 * Created by dh-mob-tm on 30/11/16.
 */

    public class AdapterWatchLaterSerieRecycler extends RecyclerView.Adapter{
        private View.OnClickListener listener;
        private Context context;
        private List<Serie> listaDeWatchLater;

        public void setListaDeWatchLater(List<Serie> listaDeWatchLater) {
            this.listaDeWatchLater = listaDeWatchLater;
        }

        public AdapterWatchLaterSerieRecycler(Context context) {
            this.context = context;
        }

        private class WatchLaterViewHolder extends RecyclerView.ViewHolder {
            private TextView textViewTituloWatchLater;
            private ImageView imageViewImagenWatchLater;



            public WatchLaterViewHolder(View view){
                super(view);
                textViewTituloWatchLater = (TextView)view.findViewById(R.id.textView_titulo_celda_watchlater);
                imageViewImagenWatchLater = (ImageView) view.findViewById(R.id.imageView_celda_watchlater);
            }

            public void loadWatchlater(Serie serie){
                textViewTituloWatchLater.setText(serie.getName());
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
                        into(imageViewImagenWatchLater);
            }

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)LayoutInflater.from(parent.getContext());
            View viewDeLaCelda = inflater.inflate(R.layout.celda_watchlater,parent,false);
            AdapterWatchLaterSerieRecycler.WatchLaterViewHolder watchLaterViewHolder = new AdapterWatchLaterSerieRecycler.WatchLaterViewHolder(viewDeLaCelda);
            return watchLaterViewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Serie serie = listaDeWatchLater.get(position);
            AdapterWatchLaterSerieRecycler.WatchLaterViewHolder watchLaterViewHolder = (AdapterWatchLaterSerieRecycler.WatchLaterViewHolder) holder;
            watchLaterViewHolder.loadWatchlater(serie);
        }

        @Override
        public int getItemCount() {
            return listaDeWatchLater.size();
        }


    }

