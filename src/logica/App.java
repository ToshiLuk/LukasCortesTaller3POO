package logica;

import java.util.ArrayList;
import java.util.Scanner;

import dominio.Hechizo;
import dominio.Mago;

public class App {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int opcion = 0;
		int opcionMenu = 0;
		boolean opcionValida = false;
		Sistema sistema = new SistemaImp();
		sistema.guardarHechizos();
		sistema.guardarMagos();
		do {
			do {
				System.out.println("Menus:");
				System.out.println("1) Administrador");
				System.out.println("2) Analista");
				System.out.println("3) Salir");
				System.out.print("Seleccione una opción: ");
				try {
					opcion = Integer.parseInt(sc.nextLine()); // Lee y convierte a int

					if (opcion >= 1 && opcion <= 3) {
						opcionValida = true; // El número es válido, rompemos este mini-bucle
					} else {
						System.out.println("Error: El número debe ser 1, 2 o 3.\n");
					}
				} catch (NumberFormatException e) {
					// Atrapa si se pone letras como "a", "hola", etc.
					System.out.println("Error: Ingresó una letra o carácter inválido. Debe ser un número.\n");
				}
			} while (!opcionValida);

			switch (opcion) {
			case 1:// Administrador
				System.out.println("=== Menu Administrador ===");
				System.out.println("\n1. Agregar Mago");
				System.out.println("2. Modificar Mago");
				System.out.println("3. Eliminar Mago");
				System.out.println("4. Agregar Hechizo");
				System.out.println("5. Modificar Hechizo");
				System.out.println("6. Eliminar Hechizo");
				System.out.print("Seleccione una opción: ");
				opcionMenu = Integer.parseInt(sc.nextLine());
				mostrarAdministrador(opcionMenu, sistema);
				break;
			case 2:// Analista
				System.out.println("=== Menu Analista ===");
				System.out.println("\n1. Top 10 Mejores Hechizos");
				System.out.println("2. Top 3 Mejores Magos");
				System.out.println("3. Mostrar todos los hechizos");
				System.out.println("4. Mostrar todos los magos");
				System.out.println("5 Mostrar todos los hechizos junto a su puntuación");
				System.out.println("6.Mostrar todos los magos junto a su puntuación");
				opcionMenu = Integer.parseInt(sc.nextLine());
				mostrarAnalista(opcionMenu, sistema);
				break;
			case 3:// Salir
				System.out.println("Saliendo...");
				break;
			}
		} while (opcion != 3);

	}

	private static void mostrarAnalista(int opcionMenu, Sistema sistema) {
		switch(opcionMenu) {
		//Top 10 hechizos
		case 1:
			System.out.println("\n--- Top 10 Mejores Hechizos ---");
			//Clonamos la lista de hechizo
			ArrayList<Hechizo> topHechizos = new ArrayList<Hechizo>(sistema.getHechizos());
			//Se ordena de mayor a menor
			topHechizos.sort((h1,h2) -> Double.compare(h2.calcularPuntuacion(), h1.calcularPuntuacion()));
			//Para que sean solo 10
			int limiteHechizo = Math.min(10, topHechizos.size());
			for(int i= 0; i < limiteHechizo; i++) {
				Hechizo h = topHechizos.get(i);
				//Print puntuación
				System.out.println((i+1) + ") " + h.getNombre() + " | Tipo: " + h.getTipo() + "| Puntos: " + h.calcularPuntuacion());
			}
			System.out.println();
			break;
		//Top 3 magos
		case 2:
			System.out.println("\n--- Top 3 Mejores Magos ---");
			//Clonamos la lista de magos
			ArrayList<Mago> topMagos = new ArrayList<>(sistema.getMagos());
			topMagos.sort((m1, m2) -> Double.compare(m2.calcularPuntuacionTotal(), m1.calcularPuntuacionTotal()));
			int limiteMago = Math.min(3, topMagos.size());
			for(int i = 0; i < limiteMago; i++) {
				Mago m = topMagos.get(i);
				System.out.println((i + 1) + ") " + m.getNombre() + " | Puntos Totales: " + m.calcularPuntuacionTotal());
			}
			System.out.println();
			break;
		//Mostrar los hechizos
		case 3:
			System.out.println("\n--- Todos los hechizos ---");
			for(Hechizo h : sistema.getHechizos()) {
				System.out.println("- " + h.getNombre() + " (" + h.getTipo() + ")");
			}
			System.out.println();
			break;
		//Mostrar magos
		case 4:
			System.out.println("\n--- Todos los magos ---");
			for (Mago m : sistema.getMagos()) {
				System.out.print("- " + m.getNombre() + " | Hechizos: ");
				for (Hechizo h : m.getHechizos()) {
					System.out.print(h.getNombre() + ", ");
				}
				System.out.println();
			}
			System.out.println();
			break;
		//Hechizos con su puntuacion
		case 5:
			System.out.println("\n--- Hechizo y su puntuación ---");
			for (Hechizo h : sistema.getHechizos()) {
				System.out.println("- " + h.getNombre() + " | Puntuación: " + h.calcularPuntuacion());
			}
			System.out.println();
			break;
		//Magos con su puntcuacion
		case 6:
			System.out.println("\n--- Magos y su puntuación ---");
			for (Mago m : sistema.getMagos()) {
				System.out.println("- " + m.getNombre() + " | Puntuación Total: " + m.calcularPuntuacionTotal());
			}
			System.out.println();
			break;
		}
	}

	private static void mostrarAdministrador(int opcionMenu, Sistema sistema) {
		switch (opcionMenu) {
		case 1:
			System.out.println("Ingrese el nombre del mago que quiere agregar");
			System.out.print("> ");
			String nombreMago = sc.nextLine().strip();
			if (sistema.agregarMago(nombreMago)) {
				System.out.println("Se agregó el mago " + nombreMago + " sin problemas.\n");
			} else {
				System.out.println("No se pudo agregar el mago " + nombreMago + "\n");
			}
			break;
		case 2:
			int opcionMagoMod = 0;
			do {
				int cont = 0;
				for (Mago m : sistema.getMagos()) {

					System.out.print((cont + 1) + ") " + m.getNombre() + " - Hechizos: ");
					for (Hechizo h : sistema.getMagos().get(cont).getHechizos()) {
						System.out.print(h.getNombre() + "|");
					}
					System.out.println();
					cont += 1;
				}
				System.out.println("==================================================");
				System.out.println("Ingrese el nombre del mago que quiere modificar?");
				System.out.println("O ingrese Salir para volver");
				System.out.print("> ");
				String nombreMagoMod = sc.nextLine();
				if (nombreMagoMod.equalsIgnoreCase("Salir"))
					break;
				System.out.println("Que quieres modificar del mago " + nombreMagoMod);
				System.out.println("1) Nombre");
				System.out.println("2) Agregar Hechizo");
				System.out.println("3) Quitar Hechizo");
				System.out.println("4) Salir");
				System.out.print("Ingrese una opcion: ");
				opcionMagoMod = Integer.parseInt(sc.nextLine());
				String datoNuevo = "";
				switch (opcionMagoMod) {
				case 1:
					System.out.println("Ingrese el nuevo nombre para " + nombreMagoMod);
					System.out.print(">");
					datoNuevo = sc.nextLine();
					break;
				case 2:
					cont = 0;
					for (Hechizo h : sistema.getHechizos()) {
						System.out.println((cont + 1) + ") " + h.getNombre());
						cont += 1;
					}
					System.out.println("Ingrese que hechizo quiere agregarle a " + nombreMagoMod);
					System.out.print("> ");
					datoNuevo = sc.nextLine();
					break;
				case 3:
					cont = 0;
					for (Mago m : sistema.getMagos()) {
						if (m.getNombre().equalsIgnoreCase(nombreMagoMod)) {
							if (m.getHechizos().isEmpty()) {
								System.out.println("Este mago no tiene hechizos...");
							} else {
								for (Hechizo h : m.getHechizos()) {
									System.out.println((cont + 1) + h.getNombre());
									cont += 1;
								}
							}
						}
					}
					System.out.println("Ingrese que hechizo le quiere quitar a " + nombreMagoMod);
					datoNuevo = sc.nextLine();
					break;
				}
				if (opcionMagoMod == 4) {
					System.out.println("Saliendo...");
				} else if (opcionMagoMod != 4 && opcionMagoMod != 1 && opcionMagoMod != 2 && opcionMagoMod != 3) {
					System.out.println("Saliendo porque ingresó una opcion incorrecta");
				} else if (sistema.modificarMago(nombreMagoMod, opcionMagoMod, datoNuevo)) {
					System.out.println("Se modificó correctamente el mago " + nombreMagoMod + "\n");
				} else {
					System.out.println("No se pudo hacer correctamente las modificaciones a " + nombreMagoMod);
				}
			} while (opcionMagoMod != 4);

			break;
		case 3:
			int cont = 0;
			for (Mago m : sistema.getMagos()) {
				System.out.println((cont + 1) + ") " + m.getNombre());
			}
			System.out.println("Ingrese el nombre del mago que quiere eliminar?");
			System.out.print(">");
			String nombreMagoMod = sc.nextLine();
			if (sistema.modificarMago(nombreMagoMod, 4, "")) {
				System.out.println("Se eliminó el mago " + nombreMagoMod);
			} else {
				System.out.println("No se pudo eliminar el mago");
			}
			break;
		case 4:
			System.out.println("=== Agregar Nuevo Hechizo ===");
			System.out.println("Ingrese el nombre del hechizo: ");
			String nombreHechizo = sc.nextLine().strip();
			
			System.out.println("Seleccione el tipo de elemento:");
			System.out.println("1) Fuego");
			System.out.println("2) Tierra");
			System.out.println("3) Planta");
			System.out.println("4) Agua");
			System.out.print("> ");
			int opcionTipo = Integer.parseInt(sc.nextLine());
			
			String tipoNuevo = "";
			int stat1 = 0;
			int stat2 = 0;
			
			System.out.print("Ingrese el Daño Base: ");
			int daño = Integer.parseInt(sc.nextLine());
			
			//Dependiendo del tipo, pedimos los diferentes datos
			switch(opcionTipo) {
			case 1:
				tipoNuevo = "Fuego";
				System.out.print("Ingrese la duración de quemadura: ");
				stat1 = Integer.parseInt(sc.nextLine());
				break;
			case 2:
				tipoNuevo = "Tierra";
				System.out.print("Ingrese la mejora de defensa: ");
				stat1 = Integer.parseInt(sc.nextLine());
				break;
			case 3:
				tipoNuevo = "Planta";
				System.out.print("Ingrese la duración de stun: ");
				stat1 = Integer.parseInt(sc.nextLine());
				System.out.print("Ingrese la cantidad de plantas: ");
				stat2 = Integer.parseInt(sc.nextLine());
				break;
			case 4:
				tipoNuevo = "Agua";
				System.out.print("Ingrese la cantidad de curación: ");
				stat1 = Integer.parseInt(sc.nextLine());
				System.out.print("Ingrese la presión de agua: ");
				stat2 = Integer.parseInt(sc.nextLine());
				break;
			default:
				System.out.println("Tipo invalido. Saliendo...");
				break;
			}
			//Si el tipo no esta vacio
			if(!tipoNuevo.isEmpty()) {
				if(sistema.agregarHechizo(nombreHechizo, tipoNuevo, daño, stat1, stat2)) {
					System.out.println("¡Se agregó el hechizo " + nombreHechizo + " al catálogo global exitosament!\n");
				}else {
					System.out.println("Error: Ya hay un hechizo con ese nombre en el catálogo.\n");
				}
			}
			break;
		case 5:
			int opcionHechizoMod = 0;
			String datoNuevo = "";
			do {
				cont = 0;
				for (Hechizo h : sistema.getHechizos()) {
					cont++;
					//
					System.out.println(
							cont + ") " + h.getNombre() + " | Tipo: " + h.getTipo() + " | " + h.obtenerDetalles());
				}
				System.out.println("==================================================");
				System.out.println("Ingrese el nombre del hechizo que quiere modificar");
				System.out.print(">");
				String nombreHechizoMod = sc.nextLine();
				System.out.println("Que quieres modificar del hechizo " + nombreHechizoMod);
				System.out.println("1) Nombre");
				System.out.println("2) Cambiar Stats");
				System.out.println("3) Salir");
				System.out.print("Ingrese una opcion: ");
				opcionHechizoMod = Integer.parseInt(sc.nextLine());
				switch (opcionHechizoMod) {
				case 1:
					System.out.println("Ingrese el nombre");
					System.out.print("> ");
					datoNuevo = sc.nextLine();
					if (sistema.modificarHechizo(nombreHechizoMod, opcionHechizoMod, datoNuevo)) {
						System.out.println("Se cambió el nombre correctamente");
					} else {
						System.out.println("No se pudo cambiar el nombre...");
					}
					break;
				case 2:
					for (Hechizo h : sistema.getHechizos()) {
						if (h.getNombre().equalsIgnoreCase(nombreHechizoMod)) {
							int statElegida = 0; 
							int opcionParaEnviar = 0;
							
							switch (h.getTipo()) {
							case "Fuego":
								System.out.println(h.getNombre() + " | " + h.obtenerDetalles());
								System.out.println("Que stat quiere cambiar?");
								System.out.println("1) Daño");
								System.out.println("2) Duración de quemadura");
								
								statElegida = Integer.parseInt(sc.nextLine());
								if (statElegida == 1) opcionParaEnviar = 2;
								if (statElegida == 2) opcionParaEnviar = 3;
								break;
							case "Tierra":
								System.out.println(h.getNombre() + " | " + h.obtenerDetalles());
								System.out.println("Que stat quiere cambiar?");
								System.out.println("1) Daño");
								System.out.println("2) Mejora de Defensa");
								
								statElegida = Integer.parseInt(sc.nextLine());
								if (statElegida == 1) opcionParaEnviar = 2;
								if (statElegida == 2) opcionParaEnviar = 3;
								break;
							case "Planta":
								System.out.println(h.getNombre() + " | " + h.obtenerDetalles());
								System.out.println("Que stat quiere cambiar?");
								System.out.println("1) Daño");
								System.out.println("2) Duración de Stun");
								System.out.println("3) Cantidad de Plantas");
								
								statElegida = Integer.parseInt(sc.nextLine());
								if (statElegida == 1) opcionParaEnviar = 2;
								if (statElegida == 2) opcionParaEnviar = 3;
								if (statElegida == 3) opcionParaEnviar = 4;
								break;
							case "Agua":
								System.out.println(h.getNombre() + " | " + h.obtenerDetalles());
								System.out.println("Que stat quiere cambiar?");
								System.out.println("1) Daño");
								System.out.println("2) Cantidad de Curación");
								System.out.println("3) Presión de Agua");
								statElegida = Integer.parseInt(sc.nextLine());
								
								if (statElegida == 1) opcionParaEnviar = 2;
								if (statElegida == 2) opcionParaEnviar = 3;
								if (statElegida == 3) opcionParaEnviar = 4;
								break;
							}
							
							// Control de errores y envío
							if (opcionParaEnviar >= 2 && opcionParaEnviar <= 4) {
								System.out.print("Ingrese a que numero lo va a cambiar: ");
								datoNuevo = sc.nextLine();
								// Llamado al metodo para cambiar el hechizo
								if (sistema.modificarHechizo(nombreHechizoMod, opcionParaEnviar, datoNuevo)) {
									System.out.println("Se cambio la stat correctamente\n");
								} else {
									System.out.println("Error: No se pudo cambiar la stat...\n");
								}
							}
						}
					}
					break;
				case 3:
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Hubo un error...");
					break;
				}
			} while (opcionHechizoMod != 3);

			break;
		case 6:
			cont = 0;
			for (Hechizo h : sistema.getHechizos()) {
				System.out.print((cont + 1) + ") " + h.getNombre());
				System.out.print("|Daño: " + h.getDaño() + "|Tipo: " + h.getTipo());
				System.out.println();
			}
			System.out.println("==================================================");
			System.out.println("Ingrese el nombre del hechizo que quiere eliminar");
			System.out.print(">");
			String hechizoElim = sc.nextLine();
			if (sistema.eliminarHechizo(hechizoElim)) {
				System.out.println("Se eliminó el hechizo");
			} else {
				System.out.println("No se pudo eliminar el hechizo");
			}
			break;
		default:
			System.out.println("Error");
			break;
		}

	}
}
