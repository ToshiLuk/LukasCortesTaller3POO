package dominio;

import java.util.ArrayList;

public class Mago {
	private String nombre;
	private ArrayList<Hechizo> hechizos;
	public Mago(String nombre) {
		this.nombre = nombre;
		this.hechizos = new ArrayList<>();
	}
	public String getNombre() {
		return nombre;
	}
	public ArrayList<Hechizo> getHechizos() {
		return hechizos;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double calcularPuntuacionTotal() {
		double total = 0;
		for (Hechizo h : this.hechizos) {
			total += h.calcularPuntuacion();
		}
		return total;
	}
}
