package dominio;

public class HechizoPlanta extends Hechizo{
	private int duracionStun;
	private int cantPlanta;
	public HechizoPlanta(String nombre, String tipo, int daño, int duracionStun, int cantPlanta) {
		super(nombre, tipo, daño);
		this.duracionStun = duracionStun;
		this.cantPlanta = cantPlanta;
	}

	@Override
	public double calcularPuntuacion() {
		return this.daño + (this.duracionStun * this.cantPlanta);
	}

	public int getDuracionStun() {
		return duracionStun;
	}

	public int getCantPlanta() {
		return cantPlanta;
	}
	
}
