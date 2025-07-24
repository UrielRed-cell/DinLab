package interfaz;


import figuras.ArrayFig;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class VentanaPrincipal {
	private Stage stage;
	private Scene scene;
	private Canvas canvas;
	private Animacion animacion;
	public VentanaPrincipal() {
		stage = new Stage();
		configurarVentana();
	}

	public void configurarVentana() {
		BorderPane borderPanePrincipal = crearBorderPanePrincipal();
	    scene = new Scene(borderPanePrincipal, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
	    stage.setScene(scene);
	    stage.setTitle("DinLab");
	}

	private BorderPane crearBorderPanePrincipal() {
		VBox panelInfo;
		BorderPane borderPane = new BorderPane();
		Canvas cn=crearCanvas();
		panelInfo=crearPanelInfo();
		animacion=new Animacion(cn,panelInfo);
		borderPane.setCenter(cn);
		borderPane.setTop(crearMenuBar());
		borderPane.setLeft(crearBotones());
		borderPane.setRight(panelInfo);
		return borderPane;
	}

	private VBox crearMenuBar() {
		VBox vBox = new VBox();
		vBox.getChildren().addAll(crearMenuSuperior(), crearMenuInferior());
		return vBox;
	}

	private MenuBar crearMenuSuperior() {
		MenuBar menuBar = new MenuBar();
		Menu vista = new Menu("Vista");
		MenuItem limpiarVista=new MenuItem("Limpiar vista");
		MenuItem verEjes = new MenuItem("Ejes");
		verEjes.setOnAction(e->animacion.dibujarEjes());
		vista.getItems().addAll(verEjes,limpiarVista);
		menuBar.getMenus().addAll(vista);
		return menuBar;
	}

	private MenuBar crearMenuInferior() {
		MenuBar menuBar = new MenuBar();
		Menu ejecutar = new Menu("Ejecutar");
		ejecutar.setOnAction(e->{
			animacion.ejecutar();
		});
		menuBar.getMenus().addAll(ejecutar);
		return menuBar;
	}

	private VBox crearBotones() {
		VBox vBox = new VBox();
		HBox hBox = new HBox();
		Button btnFuerza = new Button("Fuerza");
		btnFuerza.setOnAction(e->animacion.dibujarFuerza());
		hBox.getChildren().addAll(btnFuerza);
		vBox.getChildren().addAll(hBox);
		return vBox;
	}
	
	private Canvas crearCanvas() {
		canvas=new Canvas(400,400);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		return canvas;
	}
	
	private VBox crearPanelInfo() {
		VBox vBox=new VBox();
		Label titulo=new Label("Panel de información");
		vBox.getChildren().add(titulo);
		return vBox;
	}
	public GraphicsContext interfazCanvas() {
		return canvas.getGraphicsContext2D();
	}
	
	public void mostrar() {
		stage.show();
	}
}
