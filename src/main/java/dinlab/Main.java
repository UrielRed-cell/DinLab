package dinlab;

import interfaz.VentanaPrincipal;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		VentanaPrincipal ventanaPrincipal=new VentanaPrincipal();
		ventanaPrincipal.mostrar();
	}

    public static void main(String[] args) {
        launch(args);
    }
}