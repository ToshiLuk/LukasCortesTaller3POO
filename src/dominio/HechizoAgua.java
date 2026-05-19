package dominio;

public class HechizoAgua extends Hechizo{
	private int cantHeal;
	private int presionAgua;
	public HechizoAgua(String nombre, String tipo, int daño, int cantHeal, int presionAgua) {
		super(nombre, tipo, daño);
		this.cantHeal = cantHeal;
		this.presionAgua = presionAgua;
	}
	@Override
	public double calcularPuntuacion() {
		return (this.daño + this.cantHeal + this.presionAgua)*2;
	}
	public int getCantHeal() {
		return cantHeal;
	}
	public int getPresionAgua() {
		return presionAgua;
	}
	
}
