package util.comparator;

import java.util.Comparator;

import model.domain.Pelicula;

public class ComparatorDuracion implements Comparator<Pelicula> {
		
	public int compare(Pelicula p1, Pelicula p2) {
		 return Double.compare(p1.getDuracion(),(int)p2.getDuracion());
		  }
		 
}
