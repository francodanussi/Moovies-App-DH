package a3.m1mo.mobjav.a816.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dh-mob-tm on 17/11/16.
 */
public class TrailerContainerPelicula {
    @SerializedName ("results")
    private List<Pelicula.TrailerPelicula> listaDeTrailer;

    public List<Pelicula.TrailerPelicula> getListaDeTrailer() {
        return listaDeTrailer;
    }
}
