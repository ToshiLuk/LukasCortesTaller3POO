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
				if (linea.trim().isEmpty())
					continue; // Salta las líneas en blanco
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
				if (linea.trim().isEmpty())
					continue; // Salta las líneas en blanco
				String[] partes = linea.split(";");
				if (partes.length == 1) {
					Mago mago = new Mago(partes[0].strip());
					listaMagos.add(mago);
					continue;
				}
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
		// Buscamos el hechizo por nombre en nuestra lista de hechizos que ya hicimos
		// con el txt
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
				return false;// Si hay un mago con el mismo nombre se cierra el metodo
			}
		}
		// No hay nombres repetidos cuando se pasa aqui
		Mago magoNuevo = new Mago(nombreMago);
		listaMagos.add(magoNuevo);
		// Guardar en Magos.txt
		try {
			File arch = new File("datos/Magos.txt");
			// True para que agregue texto en vez de sobreescribir
			FileWriter fw = new FileWriter(arch, true);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.newLine();
			writer.write(nombreMago + ";");
			writer.close();
		} catch (Exception e) {
			System.out.println("Error: No se pudo guardar en Magos.txt");
			return false;
		}
		return true;// Todo bem
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
	public boolean modificarMago(String nombreMagoMod, int opcionMagoMod, String datoNuevo) {
		for (Mago m : listaMagos) {
			if (m.getNombre().equalsIgnoreCase(nombreMagoMod)) {
				switch (opcionMagoMod) {
				// Nombre
				case 1:
					// Cambiamos el nombre
					m.setNombre(datoNuevo);
					// Actualizamos el txt
					actualizarArchivoMagos();
					return true;
				// Agregar Hechizo
				case 2:
					// Buscamos el hechizo y creamos una copia
					Hechizo hechizoNuevo = buscarHechizo(datoNuevo);
					// Si el hechizo existe
					if (hechizoNuevo != null) {
						m.getHechizos().add(hechizoNuevo);
						actualizarArchivoMagos();
						return true;
					}
					return false;
				// Quitar Hechizo
				case 3:
					// Buscamos el hechizo a borrar entre los hechizos del mago
					for (Hechizo h : m.getHechizos()) {
						if (h.getNombre().equalsIgnoreCase(datoNuevo)) {
							m.getHechizos().remove(h);
							actualizarArchivoMagos();
							return true;
						}
					}
					return false;
				// Eliminar Mago
				case 4:
					// Quitamos de la lista de magos el mago a eliminar
					listaMagos.remove(m);
					actualizarArchivoMagos();
					return true;
				}
			}
		}
		return false;
	}

	// Método privado para sobreescribir el archivo Magos.txt
	private void actualizarArchivoMagos() {
		try {
			File arch = new File("datos/Magos.txt");
			// El 'false' = Borrar y crear el archivo de nuevo, Sobreescribir
			FileWriter fw = new FileWriter(arch, false);
			BufferedWriter writer = new BufferedWriter(fw);

			for (Mago m : listaMagos) {
				// Escribimos el nombre y el punto y coma
				writer.write(m.getNombre() + ";");

				// Escribimos sus hechizos separados por "|"
				ArrayList<Hechizo> hechizosMago = m.getHechizos();
				for (int i = 0; i < hechizosMago.size(); i++) {
					writer.write(hechizosMago.get(i).getNombre());

					// Si NO es el último hechizo, se pone "|"
					if (i < hechizosMago.size() - 1) {
						writer.write("|");
					}
				}

				// Saltamos de línea para el siguiente mago
				writer.newLine();
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("Error grave: No se pudo actualizar Magos.txt");
		}
	}
	// Método privado para sobreescribir el archivo Hechizos.txt
		private void actualizarArchivoHechizos() {
			try {
				File arch = new File("datos/Hechizos.txt");
				// El 'false' borra todo el archivo y lo crea de nuevo
				FileWriter fw = new FileWriter(arch, false); 
				BufferedWriter writer = new BufferedWriter(fw);
				
				for (Hechizo h : listaHechizos) {
					// 1. Escribimos la parte común que tienen TODOS los hechizos
					writer.write(h.getNombre() + ";" + h.getTipo() + ";" + h.getDaño() + ";");
					
					// 2. Escribimos la parte específica dependiendo del elemento
					if (h instanceof HechizoFuego) {
						HechizoFuego hFuego = (HechizoFuego) h; // Transformamos
						writer.write(String.valueOf(hFuego.getDuracionQuemadura()));
						
					} else if (h instanceof HechizoTierra) {
						HechizoTierra hTierra = (HechizoTierra) h;
						writer.write(String.valueOf(hTierra.getMejoraDefensa()));
						
					} else if (h instanceof HechizoPlanta) {
						HechizoPlanta hPlanta = (HechizoPlanta) h;
						// Ojo aquí: Planta y Agua separan sus últimos dos datos con una coma
						writer.write(hPlanta.getDuracionStun() + "," + hPlanta.getCantPlanta());
						
					} else if (h instanceof HechizoAgua) {
						HechizoAgua hAgua = (HechizoAgua) h;
						writer.write(hAgua.getCantHeal() + "," + hAgua.getPresionAgua());
					}
					
					// 3. Salto de línea para el próximo hechizo
					writer.newLine();
				}
				writer.close();
			} catch (Exception e) {
				System.out.println("Error grave: No se pudo actualizar Hechizos.txt");
			}
		}
	@Override
	public boolean eliminarHechizo(String hechizoElim) {
		for(Hechizo h : listaHechizos) {
			if(h.getNombre().equalsIgnoreCase(hechizoElim)) {
				listaHechizos.remove(h);
				actualizarArchivoHechizos();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean modificarHechizo(String nombreHechizoMod, int opcionHechizoMod, String datoNuevo) {
		for(Hechizo h : listaHechizos) {
			if(h.getNombre().equalsIgnoreCase(nombreHechizoMod)) {
				switch(opcionHechizoMod) {
				//Cambiar Nombre
				case 1:
					h.setNombre(datoNuevo);
					actualizarArchivoHechizos();
					return true;
				//Cambiar Daño	
				case 2:
					h.setDaño(Integer.parseInt(datoNuevo));
					actualizarArchivoHechizos();
					return true;
				//Cambiar Stat Especial 1
				case 3:
					int valorOp3 = Integer.parseInt(datoNuevo);
					if(h.getTipo().equalsIgnoreCase("Fuego")) ((HechizoFuego) h).setDuracionQuemadura(valorOp3);
					else if(h.getTipo().equalsIgnoreCase("Tierra")) ((HechizoTierra) h).setMejoraDefensa(valorOp3);
					else if(h.getTipo().equalsIgnoreCase("Planta")) ((HechizoPlanta) h).setDuracionStun(valorOp3);
					else if(h.getTipo().equalsIgnoreCase("Agua")) ((HechizoAgua) h).setCantHeal(valorOp3);
					actualizarArchivoHechizos();
					return true;
				//Cambiar Stat Especial 2
				case 4:
					int valorOp4 = Integer.parseInt(datoNuevo);
					if(h.getTipo().equalsIgnoreCase("Planta")) ((HechizoPlanta) h).setCantPlanta(valorOp4);
					else if(h.getTipo().equalsIgnoreCase("Agua")) ((HechizoAgua) h).setPresionAgua(valorOp4);
					actualizarArchivoHechizos();
					return true;
				}
			}
		}
		return false;
	}
}
