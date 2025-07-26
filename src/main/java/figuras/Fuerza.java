package figuras;

import fisicas.Vector;

public class Fuerza extends Figuras implements Vector{
	private double magnitud=1;
	private double puntoInicioX,puntoInicioY,puntoFinX,puntoFinY;
	private boolean ancladoOrigen;
	private double unitarioX,unitarioY;
	public Fuerza(int idGrupo,int idGeneral,double[] puntoInicio,double[] puntoFin) {
		super("Fuerza",idGeneral,idGrupo);
		this.puntoInicioX=puntoInicio[0];
		this.puntoInicioY=puntoInicio[1];
		this.puntoFinX=puntoFin[0];
		this.puntoFinY=puntoFin[1];
		double punto[] = componentes();
	    double longitud = Math.sqrt(Math.pow(punto[0], 2) + Math.pow(punto[1], 2));
	    magnitud=longitud;
	    unitarioX = punto[0] / longitud;
	    unitarioY = punto[1] / longitud;
	    
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
	@Override
	public double magnitud() {
		return magnitud;
	}
	@Override
	public double[] componentes() {
		return new double[] {puntoFinX-origenX(),origenY()-puntoFinY};
	}
	@Override
	public double[] origen() {
		return new double[] {origenX(),origenY()};
	}
	@Override
	public double[] unitarios() {
	    double punto[] = componentes();
	    double longitud = Math.sqrt(Math.pow(punto[0], 2) + Math.pow(punto[1], 2));
	    magnitud=longitud;
	    unitarioX = punto[0] / longitud;
	    unitarioY = punto[1] / longitud;
	    return new double[] {unitarioX, unitarioY};
	}
	@Override
	public double angulo() {
		if(unitarioX==0) {
			return Math.PI/2;
		}
		return Math.atan2(unitarioY,unitarioX);
	}
	public void angulo(double nuevoAngulo) {
	    double anguloEnRadianes = Math.toRadians(nuevoAngulo);
	    unitarioX = Math.cos(anguloEnRadianes);
	    unitarioY = Math.sin(anguloEnRadianes);
	    actualizarPuntoFin();
	}

	public void magnitud(double magnitud) {
	    this.magnitud = magnitud;
	    actualizarPuntoFin();
	}

	private void actualizarPuntoFin() {
	    puntoFinX = puntoInicioX + unitarioX * magnitud;
	    puntoFinY = puntoInicioY - unitarioY * magnitud;
	}
}
