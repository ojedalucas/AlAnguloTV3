package util.comparator;

import java.util.Comparator;

import model.domain.Pelicula;

public class ComparatorTitulo implements Comparator<Pelicula> {
		
	public int compare(Pelicula p1, Pelicula p2) {
		 return p1.getTitulo().toUpperCase().compareTo(p2.getTitulo().toUpperCase());

		  }
		 
}
