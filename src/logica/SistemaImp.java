package logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
					String partesPlanta[] = partes[3].split(",");
					int duracionStun = Integer.parseInt(partesPlanta[0].strip());
					int cantPlantas = Integer.parseInt(partesPlanta[1].strip());
					HechizoPlanta hechizo = new HechizoPlanta(nombre, tipo, daño, duracionStun, cantPlantas);
					listaHechizos.add(hechizo);
				} else if (tipo.equals("Agua")) {
					String[] partesAgua = partes[3].split(",");
					int cantidadHeal = Integer.parseInt(partesAgua[0].strip());
					int presionAgua = Integer.parseInt(partesAgua[1].strip());
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
			while (lector.hasNextLine()) {
				String linea = lector.nextLine();
				String[] partes = linea.split(";");
				String nombre = partes[0].strip();
				Mago mago = new Mago(nombre);
				String[] partesHechizos = partes[1].split("\\|");
				for (int i = 0; i < partesHechizos.length; i++) {
					// Para una cantidad i de hechizos y evitar error IndexOutOfBounds 
					String nombreHechizo = partesHechizos[i];
					// Buscamos el hechizo y se agrega el hechizo a la lista del mago
					mago.getHechizos().add(buscarHechizo(nombreHechizo));
				}
				// Agregamos el mago a la lista de magos
				listaMagos.add(mago);
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se encontró el archivo Magos.txt");
		}

	}

	@Override
	public Hechizo buscarHechizo(String nombreHechizo) {
		// Buscamos el hechizo por nombre en nuestra lista de hechizos que ya hicimos con el txt											
		for (Hechizo h : listaHechizos) {
			if (h.getNombre().equalsIgnoreCase(nombreHechizo)) {
				return h;
			}
		}
		return null;
	}

	@Override
	public boolean agregarMago(String nombreMago) {
		for (Mago m : listaMagos) {// Comprobamos si ya existe un mago con este nombre
			if (m.getNombre().equalsIgnoreCase(nombreMago)) {
				return false;//Si hay un mago con el mismo nombre se cierra el metodo
			}
		}
		//No hay nombres repetidos cuando se pasa aqui
		Mago magoNuevo = new Mago(nombreMago);
		listaMagos.add(magoNuevo);
		//Guardar en Magos.txt
		try {
			File arch = new File("datos/Magos.txt");
			//True para que agregue texto en vez de sobreescribir
			FileWriter fw = new FileWriter(arch, true);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.newLine();
			writer.write(nombreMago + ";");
			writer.close();
		} catch (Exception e) {
			System.out.println("Error: No se pudo guardar en Magos.txt");
			return false;
		}
		return true;//Todo bem
	}

	@Override
	public ArrayList<Mago> getMagos() {
		return listaMagos;
	}

	@Override
	public ArrayList<Hechizo> getHechizos() {
		return listaHechizos;
	}

	@Override
	public boolean modificarMago(String nombreMagoMod, int opcionMagoMod) {
		for(Mago m : listaMagos) {
			if(m.getNombre().equalsIgnoreCase(nombreMagoMod)) {
				switch(opcionMagoMod) {
					//Nombre
				case 1:
					
					break;
					//Agregar Hechizo	
				case 2:
					break;
					//Quitar Hechizo
				case 3:
					break;
					//Eliminar Mago
				case 4:
					break;
				}
			}
		}
		return false;
	}
	
}
