package Model.Comparator;

import java.util.Comparator;

import Model.Modelo.*;

public class ComparatorDuracion implements Comparator<Pelicula> {
		
	public int compare(Pelicula p1, Pelicula p2) {
		 return Double.compare(p1.getDuracion(),(int)p2.getDuracion());
		  }
		 
}
