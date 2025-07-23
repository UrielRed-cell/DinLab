package fisicas;

public abstract class Figuras {
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
}
