package interfaz;


import figuras.Fuerza;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VentanaFuerza {
	private Stage stage;
	private Fuerza fuerza;
	public VentanaFuerza(Fuerza fuerza) {
		this.fuerza=fuerza;
		stage=new Stage();
		stage.setTitle("Fuerza "+this.fuerza.idGrupo());
        stage.initModality(Modality.NONE);         
        Scene escena = new Scene(crearInterfaz(), 300, 250);
        stage.setScene(escena);
	}
	public VBox crearInterfaz() {
		VBox vBox=new VBox();
		Label lbEstado;
		if(fuerza.ancladoOrigen()) {
			lbEstado=new Label("La fuerza esta anclada al origen");
		}
		else {
			lbEstado=new Label("La fuerza es un vector libre");
		}
		TextArea txtMagnitud=new TextArea(String.valueOf(fuerza.magnitud()));
		Button btnMagnitud=new Button("Aplicar");
		btnMagnitud.setOnAction(e->{
			fuerza.magnitud(Double.parseDouble(txtMagnitud.getText()));
		});
		vBox.getChildren().addAll(lbEstado,txtMagnitud,btnMagnitud);
		return vBox;
	}
	public void mostrar() {
		stage.show();
	}
}
