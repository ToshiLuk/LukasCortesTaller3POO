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

	public int getDuracionQuemadura() {
		return duracionQuemadura;
	}

	@Override
	public String obtenerDetalles() {
		// TODO Auto-generated method stub
		return super.obtenerDetalles() + " | Duración Quemadura: " + this.duracionQuemadura;
	}
	
}
