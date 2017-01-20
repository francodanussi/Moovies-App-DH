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
 * Created by dh-mob-tm on 10/11/16.
 */
public class AdapterSeriesRecycler extends RecyclerView.Adapter implements View.OnClickListener  {
    private View.OnClickListener listener;
    private List<Serie> listaDeSeries;
    private Context context;

    public void setListaDeSeries(List<Serie> listaDeSeries) {
        this.listaDeSeries = listaDeSeries;
    }

    public List<Serie> getListaDeSeries() {
        return listaDeSeries;
    }

    public AdapterSeriesRecycler(Context context) {
        this.context = context;
    }

    private class SeriesViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTituloSerie;
        private TextView textViewRatingSerie;
        private ImageView imageViewImagenSerie;

        public SeriesViewHolder(View view){
            super(view);
            textViewTituloSerie= (TextView)view.findViewById(R.id.textViewTituloSerie);
            textViewRatingSerie = (TextView)view.findViewById(R.id.textViewRatingSerie);
            imageViewImagenSerie = (ImageView) view.findViewById(R.id.imageViewImagenSerie);
        }

        public void loadSerie(Serie unaSerie){
            textViewTituloSerie.setText(unaSerie.getName());
            textViewRatingSerie.setText("Rating: "+ unaSerie.getVote_average());
            Glide.with(context).
                    load(TMDBHelper.getImagePoster(TMDBHelper.IMAGE_SIZE_W300,unaSerie.getPoster_path())).
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
                    into(imageViewImagenSerie);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)LayoutInflater.from(parent.getContext());
        View viewDeLaCelda = inflater.inflate(R.layout.celda_serie,parent,false);
        SeriesViewHolder seriesViewHolder = new SeriesViewHolder(viewDeLaCelda);
        viewDeLaCelda.setOnClickListener(this);
        return seriesViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Serie unaSerie = listaDeSeries.get(position);
        SeriesViewHolder seriesViewHolder = (SeriesViewHolder)holder;
        seriesViewHolder.loadSerie(unaSerie);
    }

    @Override
    public int getItemCount() {
        return listaDeSeries.size();
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view);
    }


    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

}


