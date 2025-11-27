package TP2.Comparator;

import java.util.Comparator;

import TP2.Modelo.*;

public class ComparatorMail implements Comparator<Usuario> {
		
	public int compare(Usuario u1, Usuario u2) {
		 return u1.getEmail().compareTo(u2.getEmail());

		  }
		 
}
