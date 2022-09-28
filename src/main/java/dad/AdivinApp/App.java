package dad.AdivinApp;

import java.util.Random;

import javax.print.DocFlavor.STRING;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
	
	
	private TextField textField;
	private Button combrobar;
	private Label label;
	private VBox root;
	
	
	private Random ran = new Random();
	private int randomNumber;
	private int numOfTries;
	

	@Override
	public void start(Stage stage) throws Exception {
		randomNumber = setRandomNumber();
		
		label = new Label("Introduce un numero del 1 y 100");
		
		textField = new TextField();
		textField.setPromptText("Introduce un numero");
		textField.setFocusTraversable(false);
		
		combrobar = new Button("Comprobar");
		combrobar.setDefaultButton(true);
		
		
		combrobar.setOnAction(this::onComprobarAction);
		combrobar.setTooltip(new Tooltip("Verifica si el numero es el correcto"));		
		
		
		root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(label,textField,combrobar);
		root.setFillWidth(false);
		
		root.setSpacing(5);
		
		Scene scene = new Scene(root,450,450);
		
		
		stage.setResizable(false);
		stage.setTitle("JAVAFX");
		stage.setScene(scene);
		
		stage.show();
	}
	
	public int setRandomNumber() {
		int randomNumber = ran.nextInt(0,100);
		System.out.println(randomNumber);
		return randomNumber;
	}
	
	public boolean onComprobarAction(ActionEvent e) {
		
		int num;
		//Errors
		try {
			num = Integer.parseInt(textField.getText());
			if(num > 100 | num < 1) {
				createAlert("AdivinApp","Error","El numero introducido no es valido",AlertType.ERROR);
				return false;
			}

		} catch (NumberFormatException  e2) {
			createAlert("AdivinApp","Error","El numero introducido no es valido",AlertType.ERROR);
			return false;
		}
		
		numOfTries++;
		
		//Win
		if(num == randomNumber) {
			createAlert("AdivinApp","¡Has ganado!", "Solo has necesitado " + numOfTries + " intentos \n\n Vuelve a jugar y hazlo mejor.", AlertType.INFORMATION);
			randomNumber = setRandomNumber();
			return true;
		}else { //Fail
			String str = "";
			if(randomNumber > num) {
				str = "El numero a adivinar es mayor que " +  num;
			}else {
				str = "El numero a adivinar es menor que " + num;
			}
			
			str.concat("\n\n Vuelve a intentarlo");
			
			createAlert("AdivinApp","¡Has fallado!",str, AlertType.WARNING);
		}
		
		return false;
		
	}
	
	public void createAlert(String title,String header,String content,AlertType type) {
		Alert alert = new Alert(type);
		
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		
		alert.showAndWait();
		
		textField.setText("");
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

