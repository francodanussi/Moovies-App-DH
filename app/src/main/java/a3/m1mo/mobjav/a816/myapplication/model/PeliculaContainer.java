package a3.m1mo.mobjav.a816.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Diagnostifo on 26/10/2016.
 */
public class PeliculaContainer {
    @SerializedName("results")
    private List<Pelicula> listaDePeliculas;

    public void setListaDePeliculas(List<Pelicula> listaDePeliculas) {
        this.listaDePeliculas = listaDePeliculas;
    }

    public List<Pelicula> getListaDePeliculas() {
        return listaDePeliculas;
    }
}
