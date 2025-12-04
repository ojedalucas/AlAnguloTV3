package util.comparator;

import java.util.Comparator;
import model.domain.Usuario;

public class ComparatorUserName implements Comparator<Usuario> {
		
	public int compare(Usuario u1, Usuario u2) {
		 return u1.getNombreUsuario().toUpperCase().compareTo(u2.getNombreUsuario().toUpperCase());
		  }
		 
}
