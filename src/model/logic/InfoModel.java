package Model.Logic;

import Model.Domain.Pelicula;

public class InfoModel {
    private Pelicula pelicula;

    public InfoModel(Pelicula p){
        this.pelicula = p;
    }

    public Pelicula getPelicula(){
        return pelicula;
    }

}
