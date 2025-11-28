package Comparator;

import java.util.Comparator;

import Model.Pelicula;

public class ComparatorTitulo implements Comparator<Pelicula> {
		
	public int compare(Pelicula p1, Pelicula p2) {
		 return p1.getTitulo().toUpperCase().compareTo(p2.getTitulo().toUpperCase());

		  }
		 
}
