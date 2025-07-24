package fisicas;

import figuras.Fuerza;

public interface Vector {
	double[] componentes();
	
	default Fuerza sumar(Fuerza fuerza) {
		double[] ceros= {0,0};
		double[] v1=componentes();
		double[] v2=fuerza.componentes();
		double[] vR=new double[2];
		for(int i=0;i<2;i++) {
			vR[i]=v1[i]+v2[i];
		}
		return new Fuerza(0,0,ceros,vR);
	}
}
