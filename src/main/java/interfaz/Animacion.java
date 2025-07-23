
package interfaz;

import fisicas.ArrayFig;
import fisicas.Figuras;
import fisicas.Fuerza;
import util.CSV;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Animacion {
	Canvas canvas;
	GraphicsContext gc;
	ArrayFig arrayFg;
	public Animacion(Canvas canvas) {
		this.canvas=canvas;
		gc=this.canvas.getGraphicsContext2D();
		CSV.nuevo();
		this.arrayFg=new ArrayFig();;
	}
	public void limpiarCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	public void limpiarSector(double[] posInicial,double[] posFinal,boolean sd) {
		double posIniX=posInicial[0];
		double posIniY=posInicial[1];
		double posFinX=posFinal[0];
		double posFinY=posFinal[0];

		//caso 1
		if(posIniX<=posFinX && posIniY>=posFinY) {
			gc.clearRect(posIniX, posFinY, Math.abs(posFinY-posIniX), Math.abs(posFinY-posIniY));
		}
		else if(posIniX<=posFinX && posIniY<=posFinY){
			gc.clearRect(posIniX, posIniY, Math.abs(posFinY-posIniX),Math.abs(posFinY-posIniY));
		}else if(posIniX>=posFinX && posIniY>=posFinY) {
			gc.clearRect(posFinX, posFinY, Math.abs(posFinY-posIniX), Math.abs(posFinY-posIniY));
		}
		else if(posIniX>=posFinX && posIniY<=posFinY) {
			gc.clearRect(posFinX, posIniY,Math.abs(posFinY-posIniX), Math.abs(posFinY-posIniY));
		}
		else {
			System.out.println("Algo salio muy mal ...");
		}
		
	}
	public void limpiarSector(double[] posInicial, double[] posFinal) {
		double posIniX = Math.min(posInicial[0], posFinal[0]) - 2;
	    double posIniY = Math.min(posInicial[1], posFinal[1]) - 2;
	    double ancho = Math.abs(posFinal[0] - posInicial[0]) + 4;
	    double alto = Math.abs(posFinal[1] - posInicial[1]) + 4;

	    gc.setFill(Color.WHITE);
	    gc.fillRect(posIniX, posIniY, ancho, alto);
	}
	public void  dibujarFuerza() {
		Fuerza fuerza;
		int ids[]=new int[2];
		boolean dibujado[]=new boolean[1];
		dibujado[0]=true;
	    double[] puntoInicio = new double[2];
	    double[] puntoFin=new double[2];

	    // Agregar el canvas temporal encima del canvas principal
	    canvas.setOnMousePressed(e -> {
	        if (dibujado[0]) {
	            puntoInicio[0] = e.getX();
	            puntoInicio[1] = e.getY();
	            dibujado[0]=false;
	        }
	    });
	    canvas.setOnMouseDragged(e->{
	    	if(!dibujado[0]) {
	    		limpiarCanvas();
	    		redibujar();
	    		double puntoIntX=e.getX();
	    		double puntoIntY=e.getY();
	    		puntoFin[0]=puntoIntX;
	    		puntoFin[1]=puntoIntY;
	    		//limpiarSector(puntoInicio,puntoFin);
	    		gc.setStroke(Color.BLACK);
	            gc.setLineWidth(2);
	            gc.strokeLine(puntoInicio[0], puntoInicio[1], puntoIntX, puntoIntY);
	            //limpiarSector(puntoInicio,puntoFin);
	            
	    	}
	    });
	    canvas.setOnMouseReleased(e -> {
	        if (!dibujado[0]) {
	            double puntoFinX = e.getX();
	            double puntoFinY = e.getY();
	            puntoFin[0]=puntoFinX;
	            puntoFin[1]=puntoFinY;
	            dibujado[0] = true;
	            // Desactivar eventos de mouse
	            canvas.setOnMousePressed(null);
	            canvas.setOnMouseDragged(null);
	            canvas.setOnMouseReleased(null);
	        }
	    });
	    
	    ids = CSV.ultimoId("Fuerza");
	    if (ids[0] == -1 && ids[1] == -1) {
	        ids[0] = 1;
	        ids[1] = 1;
	    } else {
	        ids[0]++;
	        ids[1]++;
	    }
	    CSV.escribir("Fuerza", ids[1], ids[1]);
	    fuerza = new Fuerza(ids[0], ids[1], puntoInicio, puntoFin);
	    arrayFg.añadir(fuerza);
	    redibujar();
        //gc.setStroke(Color.BLACK);
        //gc.setLineWidth(2);
        //gc.strokeLine(puntoInicio[0], puntoInicio[1], puntoFin[0], puntoFin[1]);
	}
	public void dibujarFuerza(Fuerza fuerza) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(fuerza.inicioX(),fuerza.inicioY(),fuerza.finX(),fuerza.finY());
	}
	public void redibujar() {
	    for (Figuras fg : arrayFg.array()) {
	        if (fg instanceof Fuerza) {
	            Fuerza fuerza = (Fuerza) fg;
	            gc.setStroke(Color.BLACK);
	            gc.setLineWidth(2);
	            gc.strokeLine(fuerza.inicioX(), fuerza.inicioY(), fuerza.finX(), fuerza.finY());
	        }
	    }
	}
}
