package fisicas;

public class Fuerza extends Figuras{
	private double magnitud;
	private double puntoInicioX,puntoInicioY,puntoFinX,puntoFinY;
	
	public Fuerza(int idGeneral,int idGrupo,double[] puntoInicio,double[] puntoFin) {
		super("Fuerza",idGeneral,idGrupo);
		this.puntoInicioX=puntoInicio[0];
		this.puntoInicioY=puntoInicio[1];
		this.puntoFinX=puntoFin[0];
		this.puntoFinY=puntoFin[1];
	}
	public double[] obtenerPuntos() {
		return new double[] {puntoInicioX,puntoInicioY,puntoFinX,puntoFinY}; 
	}
	public double inicioX() {
		return puntoInicioX;
	}
	public double inicioY() {
		return puntoInicioY;
	}
	public double finX() {
		return puntoFinX;
	}
	public double finY() {
		return puntoFinY;
	}
	public double magnitud() {
		return magnitud;
	}
	public void magnitud(double magnitud) {
		this.magnitud=magnitud;
	}
}
