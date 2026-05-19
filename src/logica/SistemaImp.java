package logica;

import java.util.ArrayList;

import dominio.Hechizo;
import dominio.Mago;

public class SistemaImp implements Sistema{
	private ArrayList<Mago> listaMagos;
	private ArrayList<Hechizo> listaHechizos;
	
	public SistemaImp() {
		this.listaMagos = new ArrayList<Mago>();
		this.listaHechizos = new ArrayList<Hechizo>();
	}

	@Override
	public void guardarHechizos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardarMagos() {
		// TODO Auto-generated method stub
		
	}
}
