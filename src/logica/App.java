package logica;

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
				break;
			case 3:// Salir
				System.out.println("Saliendo...");
				break;
			}
		} while (opcion != 3);

	}

	private static void mostrarAdministrador(int opcionMenu, Sistema sistema) {
		System.out.println("=== Menu Administrador ===\n");
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
			int cont = 0;
			for (Mago m : sistema.getMagos()) {

				System.out.print(cont + ") " + m.getNombre() + " - Hechizos: ");
				for (Hechizo h : sistema.getMagos().get(cont).getHechizos()) {
					System.out.print(h.getNombre() + "|");
				}
				System.out.println();
				cont += 1;
			}
			int opcionMagoMod = 0;
			do {
				System.out.println("Ingrese el nombre del mago que quiere modificar?");
				System.out.println("O ingrese Salir para volver");
				System.out.print("> ");
				String nombreMagoMod = sc.nextLine();
				System.out.println("Que quieres modificar del mago");
				System.out.println("1) Nombre");
				System.out.println("2) Agregar Hechizo");
				System.out.println("3) Quitar Hechizo");
				System.out.println("4) Eliminar Mago");
				System.out.println("5) Salir");
				System.out.print("Ingrese una opcion: ");
				opcionMagoMod = Integer.parseInt(sc.nextLine());
				if (opcionMagoMod == 5) {
					System.out.println("Saliendo...");
				} else if (opcionMagoMod != 5 && opcionMagoMod != 1 && opcionMagoMod != 2 && opcionMagoMod != 3 && opcionMagoMod != 4) {
					System.out.println("Saliendo porque ingresó una opcion incorrecta");
				} else if(sistema.modificarMago(nombreMagoMod, opcionMagoMod)){
					System.out.println("Se modificó correctamente el mago " + nombreMagoMod);
				}
			} while (opcionMagoMod != 5);

			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		default:
			break;
		}

	}
}
