package figuras;

import fisicas.Vector;

public class Fuerza extends Figuras implements Vector{
	private double magnitud;
	private double puntoInicioX,puntoInicioY,puntoFinX,puntoFinY;
	private boolean ancladoOrigen;
	public Fuerza(int idGrupo,int idGeneral,double[] puntoInicio,double[] puntoFin) {
		super("Fuerza",idGeneral,idGrupo);
		this.puntoInicioX=puntoInicio[0];
		this.puntoInicioY=puntoInicio[1];
		this.puntoFinX=puntoFin[0];
		this.puntoFinY=puntoFin[1];
	}
	public void ancladoOrigen(boolean valor) {
		ancladoOrigen=valor;
	}
	public boolean ancladoOrigen() {
		return ancladoOrigen;
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
	@Override
	public double[] componentes() {
		return new double[] {puntoFinX-origenX(),puntoFinY-origenY()};
	}
}
