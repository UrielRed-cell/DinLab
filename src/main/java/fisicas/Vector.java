package fisicas;

import figuras.Fuerza;

public interface Vector {
	double[] componentes();
	double[] origen();
	double[] unitarios();
	double angulo();
	double magnitud();
	default Fuerza sumar(Fuerza fuerza) {
	    double[] u1 = unitarios();
	    double[] u2 = fuerza.unitarios();
	    System.out.println("x1: "+u1[0]+" y1: "+u1[1]+" x2: "+u2[0]+" y2: "+u2[1]);
	    for (int i = 0; i < 2; i++) {
	        u1[i] = magnitud() * u1[i];
	        u2[i] = fuerza.magnitud() * u2[i];
	    }
	    double[] vR = new double[2];
	    for (int i = 0; i < 2; i++) {
	        vR[i] = u1[i] + u2[i];
	    }
	    //Agregar un limite de tamaño, si un vector supera el tamaño del 
	    //canvas entonces que se dibuje con la maxima distancia permisible
	    System.out.println("xR: "+vR[0]+" yR: "+vR[1]);
	    double origen[] = origen();
	    double[] puntoFin = new double[2];
	    puntoFin[0] = origen[0] + vR[0];
	    puntoFin[1] = origen[1] - vR[1];
	    System.out.println("Resultante obsoluto: "+puntoFin[0]+puntoFin[1]);
	    Fuerza resultante = new Fuerza(0, 0, origen, puntoFin);
	    //resultante.corregirLongitud(); // Llamar a corregirLongitud() después de calcular la suma
	    return resultante;
	}
}
