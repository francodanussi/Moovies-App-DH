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
import a3.m1mo.mobjav.a816.myapplication.model.Pelicula;
import a3.m1mo.mobjav.a816.myapplication.util.TMDBHelper;

/**
 * Created by Diagnostifo on 07/12/2016.
 */

public class AdapterPeliculasDocumentalRecycler extends RecyclerView.Adapter implements View.OnClickListener {

    private View.OnClickListener listener;
    private List<Pelicula> listaDePeliculas;
    private Context context;

    public void setListaDePeliculas(List<Pelicula> listaDePeliculas) {
        this.listaDePeliculas = listaDePeliculas;
    }

    public List<Pelicula> getListaDePeliculas() {
        return listaDePeliculas;
    }

    public AdapterPeliculasDocumentalRecycler(Context context) {
        this.context = context;
    }

    private class PeliculasDocumentalViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTituloPelicula;
        private TextView textViewRatingPelicula;
        private ImageView imageViewImagenPelicula;

        public PeliculasDocumentalViewHolder(View view){
            super(view);
            textViewTituloPelicula = (TextView)view.findViewById(R.id.textViewTituloPelicula);
            textViewRatingPelicula = (TextView)view.findViewById(R.id.textViewRatingPelicula);
            imageViewImagenPelicula = (ImageView) view.findViewById(R.id.imageViewImagenPelicula);
        }

        public void loadPelicula(Pelicula unaPelicula){
            textViewTituloPelicula.setText(unaPelicula.getTitle());
            textViewRatingPelicula.setText("Rating: "+ unaPelicula.getVote_average());
            Glide.with(context).
                    load(TMDBHelper.getImagePoster(TMDBHelper.IMAGE_SIZE_W300,unaPelicula.getPoster_path())).
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
                    into(imageViewImagenPelicula);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)LayoutInflater.from(parent.getContext());
        View viewDeLaCelda = inflater.inflate(R.layout.celda_pelicula,parent,false);
        AdapterPeliculasDocumentalRecycler.PeliculasDocumentalViewHolder peliculasDocumentalViewHolder = new AdapterPeliculasDocumentalRecycler.PeliculasDocumentalViewHolder(viewDeLaCelda);
        viewDeLaCelda.setOnClickListener(this);
        return peliculasDocumentalViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Pelicula unaPelicula = listaDePeliculas.get(position);
        AdapterPeliculasDocumentalRecycler.PeliculasDocumentalViewHolder peliculasViewHolder = (AdapterPeliculasDocumentalRecycler.PeliculasDocumentalViewHolder)holder;
        peliculasViewHolder.loadPelicula(unaPelicula);
    }

    @Override
    public int getItemCount() {
        return listaDePeliculas.size();
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view);
    }


    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

}

