package interfaz;

import figuras.Fuerza;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PanelInfoObjeto {
	VBox vBox;
	public PanelInfoObjeto(VBox vBox) {
		this.vBox=vBox;
	}
	public void agregarFuerza(Fuerza fuerza) {
		Button btnFuerza=new Button("Fuerza: "+fuerza.idGrupo());
		btnFuerza.setOnAction(e->{
			VentanaFuerza ventanaFuerza=new VentanaFuerza(fuerza);
			ventanaFuerza.mostrar();
			
		});
		vBox.getChildren().add(btnFuerza);
	}
	public void limpiar() {
		Label titulo=new Label("Panel de información");
		vBox.getChildren().clear();
		vBox.getChildren().add(titulo);
	}
	
}
