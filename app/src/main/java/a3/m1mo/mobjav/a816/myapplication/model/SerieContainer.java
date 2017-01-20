package a3.m1mo.mobjav.a816.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Diagnostifo on 28/10/2016.
 */

public class SerieContainer {
    @SerializedName("results")
    private List<Serie> listaDeSeries;

    public List<Serie> getListaDeSeries(){
        return listaDeSeries;
    }

    public void setListaDeSeries(List<Serie> listaDeSeries) {
        this.listaDeSeries = listaDeSeries;
    }
}
