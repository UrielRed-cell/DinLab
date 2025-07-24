package figuras;

import java.util.ArrayList;

public class ArrayFig {
	private ArrayList<Figuras> arrayFg=new ArrayList<>();
	public ArrayFig() {
		System.out.println("Listo array");
	}
	public void añadir(Figuras fg) {
		arrayFg.add(fg);
	}
	public boolean eliminar(int[] indice) {
	    for (int i = 0; i < arrayFg.size(); i++) {
	        Figuras fg = arrayFg.get(i);
	        if (fg.idGrupo() == indice[0] && fg.idGeneral() == indice[1]) {
	            arrayFg.remove(i);
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean eliminar(int[] indice,boolean lambda) {
	    return arrayFg.removeIf(fg -> fg.idGrupo() == indice[0] && fg.idGeneral() == indice[1]);
	}
	public ArrayList<Figuras> array(){
		return arrayFg;
	}
}
