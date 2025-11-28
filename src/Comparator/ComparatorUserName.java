package Comparator;

import java.util.Comparator;

import Model.Usuario;

public class ComparatorUserName implements Comparator<Usuario> {
		
	public int compare(Usuario u1, Usuario u2) {
		 return u1.getNombreUsuario().toUpperCase().compareTo(u2.getNombreUsuario().toUpperCase());

		  }
		 
}
