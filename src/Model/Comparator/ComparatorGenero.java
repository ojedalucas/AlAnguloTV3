package Model.Comparator;

import java.util.Comparator;

import Model.Modelo.*;

public class ComparatorGenero implements Comparator<Pelicula> {
		
	public int compare(Pelicula p1, Pelicula p2) {
		 return p1.getGenero().name().compareTo(p2.getGenero().name());

		  }
		 
}
