package dominio;

public class HechizoFuego extends Hechizo{
	private int duracionQuemadura;
	public HechizoFuego(String nombre, String tipo, int daño, int duracionQuemadura) {
		super(nombre, tipo, daño);
		this.duracionQuemadura = duracionQuemadura;
	}

	@Override
	public double calcularPuntuacion() {
		return this.daño * this.duracionQuemadura;
	}
}
