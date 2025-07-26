
package interfaz;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import figuras.ArrayFig;
import figuras.Ejes;
import figuras.Figuras;
import figuras.Fuerza;
import util.CSV;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Animacion {
	Canvas canvas;
	GraphicsContext gc;
	ArrayFig arrayFg;
	PanelInfoObjeto panelInfo;
	boolean ejesActivos;
	public Animacion(Canvas canvas,VBox vBox) {
		this.canvas=canvas;
		Figuras.coordenadasOrigen(canvas.getWidth(), canvas.getHeight());
		gc=this.canvas.getGraphicsContext2D();
		CSV.nuevo();
		arrayFg=new ArrayFig();
		panelInfo=new PanelInfoObjeto(vBox);
	}
	public void destructor(boolean señal) {
		if(señal) {
			arrayFg.eliminarTodo();
			limpiarCanvas();
			redibujar();
			panelInfo.limpiar();
		}
		else {
			System.out.println("Error en destructor");
		}
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
	public void dibujarEjes() {
		int[] ids = CSV.ultimoId("Fuerza");
        Ejes ejes=new Ejes(ids[0],ids[1]);
        arrayFg.añadir(ejes);
        ejesActivos=true;
        dibujarEjes(ejes);
	}
	public void dibujarEjes(Ejes ejes) {
		gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(0, canvas.getHeight()/2, canvas.getWidth(), canvas.getHeight()/2);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(canvas.getWidth()/2, 0, canvas.getWidth()/2, canvas.getHeight());
	}
	public boolean puntoCercaOrigen(double x,double y,int sensibilidad) {
		double distancia=Math.sqrt(Math.pow(x-canvas.getWidth()/2,2)+Math.pow(y-canvas.getHeight()/2,2));
		if(distancia<sensibilidad) {
			return  true;
		}
		return false;
	}
	public void dibujarFuerza() {
	    final double[] puntoInicio = new double[2];
	    final double[] puntoFin = new double[2];
	    AtomicBoolean dibujado = new AtomicBoolean(true);

	    canvas.setOnMousePressed(e -> {
	        if (dibujado.get()) {
	            puntoInicio[0] = e.getX();
	            puntoInicio[1] = e.getY();
	            dibujado.set(false);
	        }
	    });
	    canvas.setOnMouseDragged(e -> {
	        if (!dibujado.get()) {
	            limpiarCanvas();
	            redibujar();
	            double puntoIntX = e.getX();
	            double puntoIntY = e.getY();
	            puntoFin[0] = puntoIntX;
	            puntoFin[1] = puntoIntY;
	            gc.setStroke(Color.BLACK);
	            gc.setLineWidth(2);
	            gc.strokeLine(puntoInicio[0], puntoInicio[1], puntoIntX, puntoIntY);
	        }
	    });
	    canvas.setOnMouseReleased(e -> {
	        if (!dibujado.get()) {
	            puntoFin[0] = e.getX();
	            puntoFin[1] = e.getY();
	            dibujado.set(true);

	            int[] ids = CSV.ultimoId("Fuerza");
	            if (ids[0] == -1 && ids[1] == -1) {
	                ids[0] = 1;
	                ids[1] = 1;
	            } else {
	                ids[0]++;
	                ids[1]++;
	            }
	            CSV.escribir("Fuerza", ids[0], ids[1]);
	            Fuerza fuerza = new Fuerza(ids[0], ids[1], puntoInicio, puntoFin);
	            if(ejesActivos && puntoCercaOrigen(puntoInicio[0],puntoInicio[1],5)) {
	            	fuerza.ancladoOrigen(true);
	            }
	            else {
	            	fuerza.ancladoOrigen(false);
	            }
	            arrayFg.añadir(fuerza);
	            limpiarCanvas();
	            redibujar();
	            panelInfo.agregarFuerza(fuerza);
	            // Desactivar eventos de mouse
	            canvas.setOnMousePressed(null);
	            canvas.setOnMouseDragged(null);
	            canvas.setOnMouseReleased(null);
	        }
	    });
	}
	public void dibujarFuerza(Fuerza fuerza) {
	    gc.setStroke(Color.BLACK);
	    gc.setLineWidth(2);
	    
	    // Dibujar la línea del vector
	    gc.strokeLine(fuerza.inicioX(), fuerza.inicioY(), fuerza.finX(), fuerza.finY());
	    
	    // Calcular la dirección del vector
	    double dx = fuerza.finX() - fuerza.inicioX();
	    double dy = fuerza.finY() - fuerza.inicioY();
	    double longitud = Math.sqrt(dx * dx + dy * dy);
	    double angulo = Math.atan2(dy, dx);
	    
	    // Dibujar la punta de la flecha
	    double tamañoPunta = 10; // Ajusta este valor según sea necesario
	    double xPunta1 = fuerza.finX() - tamañoPunta * Math.cos(angulo + Math.PI / 6);
	    double yPunta1 = fuerza.finY() - tamañoPunta * Math.sin(angulo + Math.PI / 6);
	    double xPunta2 = fuerza.finX() - tamañoPunta * Math.cos(angulo - Math.PI / 6);
	    double yPunta2 = fuerza.finY() - tamañoPunta * Math.sin(angulo - Math.PI / 6);
	    
	    gc.strokeLine(fuerza.finX(), fuerza.finY(), xPunta1, yPunta1);
	    gc.strokeLine(fuerza.finX(), fuerza.finY(), xPunta2, yPunta2);
	 // Dibujar la leyenda
	    gc.setFill(Color.RED);
	    gc.setFont(new Font(12));
	    double xTexto = (fuerza.inicioX() + fuerza.finX()) / 2;
	    double yTexto = (fuerza.inicioY() + fuerza.finY()) / 2 - 10;
	    String texto = String.format("%.2f N, %.2f°", fuerza.magnitud(), Math.toDegrees(fuerza.angulo()));
	    gc.fillText(texto, xTexto, yTexto);
	}
	public void redibujar() {
	    for (Figuras fg : arrayFg.array()) {
	        if (fg instanceof Fuerza) {
	            dibujarFuerza((Fuerza) fg);
	        }
	        else if(fg instanceof Ejes) {
	        	dibujarEjes((Ejes) fg);
	        }
	    }
	}
	
	public void ejecutar() {
        System.out.println("SE LLAMO EJES");
		System.out.println("Bien");
	    if (ejesActivos) {
			System.out.println("Siguiente");
	        ArrayList<Fuerza> arrayFuerzaOrigen = obtenerFuerzasAncladasAlOrigen();
	        Fuerza fuerzaResultante = sumaDeFuerzas(arrayFuerzaOrigen);	
	        System.out.println("X: "+fuerzaResultante.finX()+"Y: "+fuerzaResultante.finY());
	        arrayFg.añadir(fuerzaResultante);
	        limpiarCanvas();
	        redibujar();
	    }
	}

	private ArrayList<Fuerza> obtenerFuerzasAncladasAlOrigen() {
	    ArrayList<Fuerza> arrayFuerzaOrigen = new ArrayList<>();
	    for (Figuras fg : arrayFg.array()) {
	        if (fg instanceof Fuerza && ((Fuerza) fg).ancladoOrigen()) {
	            arrayFuerzaOrigen.add((Fuerza) fg);
	        }
	    }
	    return arrayFuerzaOrigen;
	}

	private Fuerza sumaDeFuerzas(ArrayList<Fuerza> arrayFuerzaOrigen) {
	    if (arrayFuerzaOrigen.isEmpty()) {
	        throw new RuntimeException("No hay fuerzas para sumar");
	    }
	    Fuerza suma = arrayFuerzaOrigen.get(0);
	    for (int i = 1; i < arrayFuerzaOrigen.size(); i++) {
	        suma = suma.sumar(arrayFuerzaOrigen.get(i));
	    }
	    return suma;
	}
}
