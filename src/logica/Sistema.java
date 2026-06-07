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
	boolean modificarMago(String nombreMagoMod, int opcionMagoMod, String datoNuevo);
	boolean eliminarHechizo(String hechizoElim);
	boolean modificarHechizo(String nombreHechizoMod, int opcionHechizoMod, String datoNuevo);
	boolean agregarHechizo(String nombreHechizo, String tipo, int daño, int stat1, int stat2);
}
