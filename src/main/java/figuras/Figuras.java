package figuras;


public abstract class Figuras {
	private static double coordenadaOrigenX,coordenadaOrigenY;
	private String nombre;
	private int idGeneral;
	private int idGrupo;
	
	public Figuras(String nombre,int idGeneral,int idGrupo) {
		this.nombre=nombre;
		this.idGeneral=idGeneral;
		this.idGrupo=idGrupo;
	}
	public int idGeneral() {
		return idGeneral;
	}
	public int idGrupo() {
		return idGrupo;
	}
	public String nombre() {
		return nombre;
	}
	public static void coordenadasOrigen(double x,double y) {
		coordenadaOrigenX=x/2;
		coordenadaOrigenY=y/2;
	}
	public double origenX() {
		return coordenadaOrigenX;
	}
	public double origenY() {
		return coordenadaOrigenY;
	}
}
