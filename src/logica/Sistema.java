package logica;

import java.util.ArrayList;

import dominio.Hechizo;
import dominio.Mago;

public interface Sistema {
	void guardarHechizos();
	void guardarMagos();
	Hechizo buscarHechizo(String nombreHechizo);
	boolean agregarMago(String nombreMago);
	ArrayList<Mago> getMagos();
	ArrayList<Hechizo> getHechizos();
	boolean modificarMago(String nombreMagoMod, int opcionMagoMod);
}
