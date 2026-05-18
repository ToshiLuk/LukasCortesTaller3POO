package dominio;

import java.util.ArrayList;

public class Mago {
	private String nombre;
	private ArrayList<Hechizo> hechizos;
	public Mago(String nombre, ArrayList<Hechizo> hechizos) {
		this.nombre = nombre;
		this.hechizos = new ArrayList<>();
	}
	public String getNombre() {
		return nombre;
	}
	public ArrayList<Hechizo> getHechizos() {
		return hechizos;
	}
	
}
