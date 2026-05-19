package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import dominio.Hechizo;
import dominio.HechizoAgua;
import dominio.HechizoFuego;
import dominio.HechizoPlanta;
import dominio.HechizoTierra;
import dominio.Mago;

public class SistemaImp implements Sistema {
	private ArrayList<Mago> listaMagos;
	private ArrayList<Hechizo> listaHechizos;
	private static Scanner lector;

	public SistemaImp() {
		this.listaMagos = new ArrayList<Mago>();
		this.listaHechizos = new ArrayList<Hechizo>();
	}

	@Override
	public void guardarHechizos() {
		try {
			File arch = new File("datos/Hechizos.txt");
			lector = new Scanner(arch);
			while (lector.hasNextLine()) {
				String linea = lector.nextLine();
				String[] partes = linea.split(";");
				// Información de los hechizos
				String nombre = partes[0].strip();
				String tipo = partes[1].strip();
				int daño = Integer.parseInt(partes[2].strip());
				// Informacion segun que tipo de hechizo es, asi se evita errores de outofindex
				if (tipo.equals("Fuego")) {
					int duracionQuemadura = Integer.parseInt(partes[3].strip());
					HechizoFuego hechizo = new HechizoFuego(nombre, tipo, daño, duracionQuemadura);
					listaHechizos.add(hechizo);
				} else if (tipo.equals("Tierra")) {
					int mejoraDefensa = Integer.parseInt(partes[3].strip());
					HechizoTierra hechizo = new HechizoTierra(nombre, tipo, daño, mejoraDefensa);
					listaHechizos.add(hechizo);
				} else if (tipo.equals("Planta")) {
					int duracionStun = Integer.parseInt(partes[3].strip());
					int cantPlantas = Integer.parseInt(partes[4].strip());
					HechizoPlanta hechizo = new HechizoPlanta(nombre, tipo, daño, duracionStun, cantPlantas);
					listaHechizos.add(hechizo);
				} else if (tipo.equals("Agua")) {
					int cantidadHeal = Integer.parseInt(partes[3].strip());
					int presionAgua = Integer.parseInt(partes[4].strip());
					HechizoAgua hechizo = new HechizoAgua(nombre, tipo, daño, cantidadHeal, presionAgua);
					listaHechizos.add(hechizo);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se encontró el archivo Hechizos.txt");
		}
	}

	@Override
	public void guardarMagos() {
		try {
			File arch = new File("datos/Magos.txt");
			lector = new Scanner(arch);
			while(lector.hasNextLine()) {
				String linea = lector.nextLine();
				String[] partes = linea.split(";");
				String nombre = partes[0].strip();
				Mago mago = new Mago(nombre);
				String[] partesHechizos = partes[1].split("|");
				for (int i = 0; i<partesHechizos.length; i++) {
					String nombreHechizo = partesHechizos[i];//Para una cantidad i de hechizos y evitar error IndexOutOfBounds
					mago.getHechizos().add(buscarHechizo(nombreHechizo));//Buscamos el hechizo y se agrega el hechizo a la lista del mago
				}
				listaMagos.add(mago);//Agregamos el mago a la lista de magos 
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se encontró el archivo Magos.txt");
		}

	}

	@Override
	public Hechizo buscarHechizo(String nombreHechizo) {
		for(Hechizo h : listaHechizos) {//Recorrer la lista de hechizos
			if (h.getNombre().equalsIgnoreCase(null)) {//Si el nombre del hechizo en la lista es igual, se crea un objeto Hechizo igual que el que esta en la lista
				//Se transforma h que es un objeto Hechizo a un objeto de las subclases de Hechizo para poder obtener los atributos exclusivos
				if (h.getTipo().equals("Fuego")) {
					HechizoFuego hechizo = (HechizoFuego) h;//Casting
					return hechizo;
				} else if (h.getTipo().equals("Tierra")) {
					HechizoTierra hechizo = (HechizoTierra) h;
					return hechizo;
				} else if (h.getTipo().equals("Planta")) {
					HechizoPlanta hechizo = (HechizoPlanta) h;
					return hechizo;
				} else if (h.getTipo().equals("Agua")) {
					HechizoAgua hechizo = (HechizoAgua) h;
					return hechizo;
				}
			}
		}
		return null;//Si no existe el Hechizo en la lista
	}
	
}
