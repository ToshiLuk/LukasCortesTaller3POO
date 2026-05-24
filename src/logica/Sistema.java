package logica;

import dominio.Hechizo;

public interface Sistema {
	void guardarHechizos();
	void guardarMagos();
	Hechizo buscarHechizo(String nombreHechizo);
	boolean agregarMago(String nombreMago);
}
