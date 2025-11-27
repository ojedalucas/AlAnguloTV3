package Model.Comparator;

import java.util.Comparator;

import Model.Modelo.*;

public class ComparatorMail implements Comparator<Usuario> {
		
	public int compare(Usuario u1, Usuario u2) {
		 return u1.getEmail().compareTo(u2.getEmail());

		  }
		 
}
