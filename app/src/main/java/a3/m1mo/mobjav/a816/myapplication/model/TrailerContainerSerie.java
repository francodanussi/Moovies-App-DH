package a3.m1mo.mobjav.a816.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Diagnostifo on 24/11/2016.
 */

public class TrailerContainerSerie {
    @SerializedName("results")
    private List<Serie.TrailerSerie> listaDeTrailer;

    public List<Serie.TrailerSerie> getListaDeTrailer() {
        return listaDeTrailer;
    }
}
